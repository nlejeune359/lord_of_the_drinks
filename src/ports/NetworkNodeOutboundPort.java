package ports;

import components.ConnectionInfo;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.RequiredCI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.NetworkNodeServicesCI;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import interfaces.RegistrationCI;

import java.util.Set;

public class NetworkNodeOutboundPort extends AbstractOutboundPort implements NetworkNodeServicesCI, RegistrationCI {

    public NetworkNodeOutboundPort(ComponentI owner) throws Exception
    {
        super(NetworkNodeServicesCI.class, owner);
    }

    @Override
    public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
        return ((RegistrationCI)this.getConnector()).registrationInternal(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
    }

    @Override
    public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
        return ((RegistrationCI)this.getConnector()).registrationAccessPoint(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
    }

    @Override
    public void unregister(P2PAddressI address) throws Exception {
        ((RegistrationCI)this.getConnector()).unregister(address);
    }
}
