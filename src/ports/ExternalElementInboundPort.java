package ports;

import components.ExternalElementComponent;
import connectors.ExternalElementConnector;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.*;

/**
 * Port entrant d un element externe au reseau pair a pair
 */
public class ExternalElementInboundPort extends AbstractInboundPort implements ExternalCommunicationCI {
    public ExternalElementInboundPort(String uri, ComponentI owner) throws Exception {
        super(uri, ExternalElementConnector.class, owner);
    }


    @Override
    public void routeExternalMessage(MessageI m) throws Exception {
        ((ExternalElementComponent)this.getOwner()).routeExternalMessage(m);
    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {
        ((ExternalElementComponent)this.getOwner()).connectExternal(address, communicationInboundPortURI);
    }
}
