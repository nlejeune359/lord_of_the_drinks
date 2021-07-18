package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.NetworkNodeServicesCI;

public class NetworkNodeInboundPort extends AbstractInboundPort implements NetworkNodeServicesCI {

    public  NetworkNodeInboundPort(String uri, ComponentI owner) throws Exception
    {
        super(uri, NetworkNodeServicesCI.class, owner);
    }
}
