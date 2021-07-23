package connectors;

import components.ConnectionInfo;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.*;

import java.util.Set;

/**
 * Connecteur d un element interne au reseau pair a pair
 */
public class NetworkNodeConnector extends AbstractConnector implements NetworkNodeServicesCI, RegistrationCI , ExternalCommunicationCI{
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

    @Override
    public void routeExternalMessage(MessageI m) throws Exception {

    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {

    }
}
