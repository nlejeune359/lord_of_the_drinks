package components;

import connectors.NetworkNodeConnector;
import connectors.SimulatorConnector;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.NetworkNodeServicesCI;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import ports.NetworkNodeInboundPort;
import ports.NetworkNodeOutboundPort;

import java.util.Set;

@RequiredInterfaces(required = {NetworkNodeServicesCI.class})
public class NetworkNodeComponent extends AbstractComponent {

    protected String addr;
    protected NetworkNodeOutboundPort outboundPort;
    protected NetworkNodeInboundPort inboundPort;
    protected String SIMULATOR_URI;
    public static final String NETWORK_NODE_INBOUND_PORT_URI = "nn-in-uri";
    public static final String NETWORK_NODE_OUTBOUND_PORT_URI = "nn-out-uri";
    protected PositionI initialPosition;
    protected double initialRange;
    protected DeviceType deviceType;

    protected Set<ConnectionInfo> neighbours;

    public enum DeviceType {
        SMARTPHONE, TABLET, LAPTOP, DESKTOP
    }

    //String simulatorURI, PositionI position, double range, DeviceType type
    protected NetworkNodeComponent(String simulatorURI, PositionI position, double range, DeviceType type) throws Exception {
        super(1, 0);
        this.SIMULATOR_URI = simulatorURI;
        this.initialPosition = position;
        this.initialRange = range;
        this.deviceType = type;
        this.initialize();
    }

    protected void initialize() throws Exception
    {
        this.addr = generateAddr();
        this.inboundPort = new NetworkNodeInboundPort(NETWORK_NODE_INBOUND_PORT_URI, this);
        this.inboundPort.publishPort();
        this.outboundPort = new NetworkNodeOutboundPort(NETWORK_NODE_OUTBOUND_PORT_URI, this);
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

    /*@Override
    public synchronized void start() throws ComponentStartException
    {
        try {
            System.out.println("oui");
            this.doPortConnection(this.outboundPort.getPortURI(), this.SIMULATOR_URI, NetworkNodeConnector.class.getCanonicalName());
            this.neighbours = this.outboundPort.registrationInternal(this.addr, this.inboundPort.getPortURI(), this.initialPosition, this.initialRange, this.inboundPort.getPortURI());
        } catch(Exception e)
        {
            throw new ComponentStartException(e);
        }
        super.start();
    }*/

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
}
