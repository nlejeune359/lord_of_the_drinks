package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.NetworkNodeServicesCI;

public class NetworkNodeInboundPort extends AbstractInboundPort implements NetworkNodeServicesCI {

    public NetworkNodeInboundPort(ComponentI owner) throws Exception
    {
        super(NetworkNodeServicesCI.class, owner);
    }
}
