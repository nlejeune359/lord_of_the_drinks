package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.RequiredCI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;

public class NetworkNodeOutboundPort extends AbstractOutboundPort {
    public NetworkNodeOutboundPort(String uri, Class<? extends RequiredCI> implementedInterface, ComponentI owner) throws Exception {
        super(uri, implementedInterface, owner);
    }
}
