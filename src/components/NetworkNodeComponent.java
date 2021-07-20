package components;

import connectors.NetworkNodeConnector;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredInterfaces(required = {NetworkNodeServicesCI.class})
public class NetworkNodeComponent extends AbstractComponent implements NetworkNodeServicesCI {

    protected String addr;
    protected NetworkNodeOutboundPort SimulatorPort;
    protected NetworkNodeInboundPort inboundPort;
    protected Map<String, NetworkNodeOutboundPort> outboundPorts = new HashMap<String, NetworkNodeOutboundPort>();
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
        this.SimulatorPort = new NetworkNodeOutboundPort(this);
        this.SimulatorPort.publishPort();

        //System.out.println("address : " + this.addr + " inboundPort: " +this.inboundPort.getPortURI() + " outboundPort: " +this.SimulatorPort.getPortURI());
        this.toggleLogging();
        this.toggleTracing();
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
        	//System.out.println("-- do port connection --");
            this.doPortConnection(
            	this.SimulatorPort.getPortURI(),
            	SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI, 
           		NetworkNodeConnector.class.getCanonicalName()
            );
            
            //System.out.println("-- registrationInternal --");
            this.neighbours = this.SimulatorPort.registrationInternal(
            	new NetworkAddress(this.addr),
            	this.inboundPort.getPortURI(),
            	this.initialPosition,
            	this.initialRange,
            	SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI
            );

            this.logMessage("Neighbours : " + this.neighbours.toString());

            for(ConnectionInfo c: this.neighbours)
            {
                NetworkNodeOutboundPort outboundPort = new NetworkNodeOutboundPort(this);
                this.outboundPorts.put(c.getCommunicationInboundPortURI(), outboundPort);
                this.doPortConnection(
                        this.outboundPorts.get(c.getCommunicationInboundPortURI()).getPortURI(),
                        c.getCommunicationInboundPortURI(),
                        NetworkNodeConnector.class.getCanonicalName()
                );
                // System.out.println("Port Connected");
                this.outboundPorts.get(c.getCommunicationInboundPortURI()).connect(new NetworkAddress(this.addr), this.inboundPort.getPortURI(), this.outboundPorts.get(c.getCommunicationInboundPortURI()).getPortURI());
            }
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
            this.SimulatorPort.unpublishPort();
        } catch(Exception e)
        {
            throw new ComponentShutdownException(e);
        }
    }

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {

        // System.out.println("address: " + address + " communicationInboudPortURI: " + communicationInboundPortURI + " routingInboundPortURI: " + routingInboundPortURI);

        if(!containsPortURI(communicationInboundPortURI))
        {
            ConnectionInfo newNeighbour = new ConnectionInfo(
                    address,
                    communicationInboundPortURI,
                    routingInboundPortURI
            );
            this.neighbours.add(newNeighbour);

            this.logMessage("Neighbours : " + this.neighbours.toString());

            NetworkNodeOutboundPort outboundPort = new NetworkNodeOutboundPort(this);
            this.outboundPorts.put(communicationInboundPortURI, outboundPort);

            /*System.out.println(this.outboundPorts.get(communicationInboundPortURI).getPortURI());
            System.out.println(this.isPortConnected(this.outboundPorts.get(communicationInboundPortURI).getPortURI()));
            System.out.println(communicationInboundPortURI);*/

            this.doPortConnection(
                    this.outboundPorts.get(communicationInboundPortURI).getPortURI(),
                    communicationInboundPortURI,
                    NetworkNodeConnector.class.getCanonicalName()
            );
            this.outboundPorts.get(communicationInboundPortURI).connect(new NetworkAddress(this.addr), this.inboundPort.getPortURI(), communicationInboundPortURI);
        }
    }

    @Override
    public void routeMessage(MessageI m) {

    }

    @Override
    public void ping() {

    }

    /**
     * Check if communicationInboundPortURI already exists in list of neighbours
     *
     * @param communicationInboundPortURI
     * @return
     */
    private boolean containsPortURI(String communicationInboundPortURI)
    {
        for(ConnectionInfo c: this.neighbours)
        {
            if(c.getCommunicationInboundPortURI().equals(communicationInboundPortURI))
                return true;
        }
        return false;
    }
}
