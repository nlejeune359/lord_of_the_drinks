package ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;

public class NetworkNodeInboundPort extends AbstractInboundPort {
    public NetworkNodeInboundPort(String uri, Class<? extends OfferedCI> implementedInterface, ComponentI owner, String pluginURI, String executorServiceURI) throws Exception {
        super(uri, implementedInterface, owner, pluginURI, executorServiceURI);
    }
}
