package components;

import connectors.ExternalElementConnector;
import connectors.NetworkNodeConnector;
import fr.sorbonne_u.components.AbstractComponent;
import interfaces.*;
import ports.ExternalElementInboundPort;
import ports.ExternalElementOutboundPort;
import ports.NetworkNodeInboundPort;
import ports.NetworkNodeOutboundPort;

/**
 * Composant representant un element externe au reseau pair a pair
 */
public class ExternalElementComponent extends AbstractComponent implements ExternalCommunicationCI {

    protected IPAddressI addr;
    protected ExternalElementInboundPort inboundPort;
    protected ExternalElementOutboundPort outboundPort;
    protected String accesPointInboundPortURI;
    protected P2PAddressI accessPointAddress;

    protected ExternalElementComponent(String address, String inboundPortURI, String accessPointAddress, String accessPointInboundPortURI) throws Exception{
        super(1, 0);
        this.addr = new ExternalNetworkAddress(address);
        this.inboundPort = new ExternalElementInboundPort(inboundPortURI, this);
        this.inboundPort.publishPort();
        this.outboundPort = new ExternalElementOutboundPort(this);
        this.outboundPort.publishPort();
        this.accesPointInboundPortURI = accessPointInboundPortURI;
        this.accessPointAddress = new NetworkAddress(accessPointAddress);
    }

    @Override
    public synchronized void start() {
        try {
        	// connexion au point d'acces
            this.doPortConnection(
                    this.outboundPort.getPortURI(),
                    this.accesPointInboundPortURI,
                    ExternalElementConnector.class.getCanonicalName()
            );
            
            this.outboundPort.connectExternal(this.addr, this.inboundPort.getPortURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void routeExternalMessage(MessageI m) throws Exception {

    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {

    }
}
