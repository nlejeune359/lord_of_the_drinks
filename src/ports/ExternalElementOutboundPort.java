package ports;

import components.AccessPointComponent;
import components.ExternalElementComponent;
import components.NodeComponent;
import connectors.ExternalElementConnector;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.*;

/**
 * Port sortant d un element externe au reseau pair a pair
 */
public class ExternalElementOutboundPort extends AbstractOutboundPort implements ExternalCommunicationCI {
    public ExternalElementOutboundPort(ComponentI owner) throws Exception {
        super(ExternalElementConnector.class, owner);
    }

    @Override
    public void routeExternalMessage(MessageI m) throws Exception {
        ((ExternalCommunicationCI)this.getConnector()).routeExternalMessage(m);
    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {
        ((ExternalCommunicationCI)this.getConnector()).connectExternal(address, communicationInboundPortURI);
    }
}
