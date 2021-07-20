package connectors;

import components.ConnectionInfo;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.*;

import java.util.Set;

public class NetworkNodeConnector extends AbstractConnector implements NetworkNodeServicesCI, RegistrationCI {
    @Override
    public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
        return ((RegistrationCI)this.offering).registrationInternal(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
    }

    @Override
    public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
        return ((RegistrationCI)this.offering).registrationAccessPoint(address, communicationInboundPort, initialPosition, initialRange, routingInboundPortURI);
    }

    @Override
    public void unregister(P2PAddressI address) throws Exception {
        ((RegistrationCI)this.offering).unregister(address);
    }

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {
        ((CommunicationCI)this.offering).connect(address, communicationInboundPortURI, routingInboundPortURI);
    }

    @Override
    public void routeMessage(MessageI m) throws Exception {
        ((CommunicationCI)this.offering).routeMessage(m);
    }

    @Override
    public void ping() {

    }
}
