package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import interfaces.AddressI;
import interfaces.ExternalCommunicationCI;
import interfaces.IPAddressI;
import interfaces.MessageI;

/**
 * Connecteur d un element externe du reseau pair a pair
 */
public class ExternalElementConnector extends AbstractConnector implements ExternalCommunicationCI {
    @Override
    public void routeExternalMessage(MessageI m) throws Exception {

    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {
        ((ExternalCommunicationCI)this.offering).connectExternal(address, communicationInboundPortURI);
    }
}
