package ports;

import components.ConnectionInfo;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.RequiredCI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.*;

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

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {
        ((CommunicationCI)this.getConnector()).connect(address, communicationInboundPortURI, routingInboundPortURI);
    }

    @Override
    public void routeMessage(MessageI m) throws Exception {
        ((CommunicationCI)this.getConnector()).routeMessage(m);
    }

    @Override
    public void ping() throws Exception {
        ((CommunicationCI)this.getConnector()).ping();
    }
}
