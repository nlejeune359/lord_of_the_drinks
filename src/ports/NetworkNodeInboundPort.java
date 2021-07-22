package ports;

import components.AccessPointComponent;
import components.InternalNodeComponent;
import components.NodeComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.*;

public class NetworkNodeInboundPort extends AbstractInboundPort implements NetworkNodeServicesCI, ExternalCommunicationCI {

    public NetworkNodeInboundPort(ComponentI owner) throws Exception
    {
        super(NetworkNodeServicesCI.class, owner);
    }

    public NetworkNodeInboundPort(String uri, ComponentI owner) throws Exception {
        super(uri, NetworkNodeServicesCI.class, owner);
    }

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {
        ((NodeComponent)this.getOwner()).connect(address, communicationInboundPortURI, routingInboundPortURI);
    }

    @Override
    public void routeMessage(MessageI m) throws Exception {
        ((NodeComponent)this.getOwner()).routeMessage(m);
    }

    @Override
    public void ping() throws Exception {
        ((NodeComponent)this.getOwner()).ping();
    }

    @Override
    public void routeExternalMessage(MessageI m) throws Exception {
        ((AccessPointComponent)this.getOwner()).routeExternalMessage(m);
    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {
        ((AccessPointComponent)this.getOwner()).connectExternal(address, communicationInboundPortURI);
    }
}
