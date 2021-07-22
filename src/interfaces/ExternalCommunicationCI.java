package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

public interface ExternalCommunicationCI extends RequiredCI, OfferedCI {

    public void routeExternalMessage(MessageI m) throws Exception;

    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception;
}
