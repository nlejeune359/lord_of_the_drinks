package ports;

import components.NetworkNodeComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.MessageI;
import interfaces.NetworkNodeServicesCI;
import interfaces.P2PAddressI;

public class NetworkNodeInboundPort extends AbstractInboundPort implements NetworkNodeServicesCI {

    public NetworkNodeInboundPort(ComponentI owner) throws Exception
    {
        super(NetworkNodeServicesCI.class, owner);
    }

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {
        ((NetworkNodeComponent)this.getOwner()).connect(address, communicationInboundPortURI, routingInboundPortURI);
    }

    @Override
    public void routeMessage(MessageI m) throws Exception {
        ((NetworkNodeComponent)this.getOwner()).routeMessage(m);
    }

    @Override
    public void ping() throws Exception {
        ((NetworkNodeComponent)this.getOwner()).ping();
    }
}
