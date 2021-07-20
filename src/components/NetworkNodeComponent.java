package components;

import connectors.NetworkNodeConnector;
import connectors.SimulatorConnector;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.MessageI;
import interfaces.NetworkNodeServicesCI;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import ports.NetworkNodeInboundPort;
import ports.NetworkNodeOutboundPort;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RequiredInterfaces(required = {NetworkNodeServicesCI.class})
public class NetworkNodeComponent extends AbstractComponent implements NetworkNodeServicesCI {

    protected String addr;
    protected NetworkNodeOutboundPort outboundPort;
    protected NetworkNodeInboundPort inboundPort;
    protected String SIMULATOR_URI;
    protected PositionI initialPosition;
    protected double initialRange;
    protected DeviceType deviceType;

    protected Set<ConnectionInfo> neighbours = new HashSet<>();

    public enum DeviceType {
        SMARTPHONE, TABLET, LAPTOP, DESKTOP
    }

    //String simulatorURI, PositionI position, double range, DeviceType type
    protected NetworkNodeComponent(PositionI position, double range, DeviceType type) throws Exception {
        super(1, 0);
        this.initialPosition = position;
        this.initialRange = range;
        this.deviceType = type;
        this.initialize();
    }

    protected void initialize() throws Exception
    {
        this.addr = generateAddr();
        this.inboundPort = new NetworkNodeInboundPort(this);
        this.inboundPort.publishPort();
        this.outboundPort = new NetworkNodeOutboundPort(this);
        this.outboundPort.publishPort();

        this.toggleLogging();
    }

    private String generateAddr()
    {
        String s = "";
        double d;
        for (int i = 1; i <= 16; i++) {
            d = Math.random() * 10;
            s = s + ((int) d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }

        return s;
    }

    @Override
    public synchronized void start() throws ComponentStartException
    {
        try {
        	System.out.println("-- do port connection --");
            this.doPortConnection(
            	this.outboundPort.getPortURI(), 
            	SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI, 
           		NetworkNodeConnector.class.getCanonicalName()
            );
            
            System.out.println("-- registrationInternal --");
            this.neighbours = this.outboundPort.registrationInternal(
            	new NetworkAddress(this.addr),
            	this.inboundPort.getPortURI(),
            	this.initialPosition,
            	this.initialRange,
            	SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI
            );
            System.out.println(this.neighbours);

            /*if(this.neighbours.size() > 0)
            {
                Iterator<ConnectionInfo> iterator = this.neighbours.iterator();

                ConnectionInfo next;
                while(iterator.hasNext())
                {
                    next = iterator.next();
                    this.doPortConnection(
                            this.outboundPort.getPortURI(),
                            next.getCommunicationInboundPortURI(),
                            NetworkNodeConnector.class.getCanonicalName()
                    );
                    this.outboundPort.connect(new NetworkAddress(this.addr), this.inboundPort.getPortURI(), next.getCommunicationInboundPortURI());
                }
            }*/

        } catch(Exception e) {
            throw new ComponentStartException(e);
        }
        super.start();
    }

    @Override
    public synchronized void shutdown() throws ComponentShutdownException
    {
        try {
            this.inboundPort.unpublishPort();
            this.outboundPort.unpublishPort();
        } catch(Exception e)
        {
            throw new ComponentShutdownException(e);
        }
    }

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {

        if(!containsPortURI(communicationInboundPortURI))
        {
            ConnectionInfo newNeighbour = new ConnectionInfo(
                    address,
                    communicationInboundPortURI,
                    routingInboundPortURI
            );
            this.neighbours.add(newNeighbour);
            this.doPortConnection(this.outboundPort.getPortURI(), communicationInboundPortURI, NetworkNodeComponent.class.getCanonicalName());
            this.outboundPort.connect(new NetworkAddress(this.addr), this.inboundPort.getPortURI(), communicationInboundPortURI);
        }
    }

    @Override
    public void routeMessage(MessageI m) {

    }

    @Override
    public void ping() {

    }

    private boolean containsPortURI(String communicationInboundPortURI)
    {
        Iterator<ConnectionInfo> iterator = this.neighbours.iterator();
        ConnectionInfo next;
        while(iterator.hasNext())
        {
            next = iterator.next();
            if(next.getCommunicationInboundPortURI() == communicationInboundPortURI)
                return true;
        }
        return false;
    }
}
