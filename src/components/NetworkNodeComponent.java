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

    protected P2PAddressI addr;
    protected NetworkNodeOutboundPort outboundPort;
    protected NetworkNodeInboundPort inboundPort;
    public static final String NETWORK_NODE_INBOUND_PORT_URI = "nn-in-uri";
    public static final String NETWORK_NODE_OUTBOUND_PORT_URI = "nn-out-uri";
    protected PositionI initialPosition;
    protected double range;

    protected Set<ConnectionInfo> neighbours;

    protected NetworkNodeComponent(P2PAddressI addr) throws Exception {
        super(1, 0);
        this.addr = addr;

        this.inboundPort = new NetworkNodeInboundPort(NETWORK_NODE_INBOUND_PORT_URI, this);
        this.inboundPort.publishPort();
        this.outboundPort = new NetworkNodeOutboundPort(NETWORK_NODE_OUTBOUND_PORT_URI, this);
        this.outboundPort.publishPort();

        this.toggleLogging();
    }

    @Override
    public synchronized void start() throws ComponentStartException
    {
        try {
            this.doPortConnection(this.outboundPort.getPortURI(), this.inboundPort.getPortURI(), SimulatorConnector.class.getCanonicalName());
            this.neighbours = this.outboundPort.registrationInternal(this.addr, this.inboundPort.getPortURI(), this.initialPosition, this.range, this.inboundPort.getPortURI());
        } catch(Exception e)
        {
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
}
