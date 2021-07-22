package components;

import interfaces.IPAddressI;
import interfaces.P2PAddressI;

public class ExternalConnectionInfo {

    /**
     * @return l’adresse du voisin
     */

    private IPAddressI address;
    private String communicationInboundPortURI;

    public ExternalConnectionInfo(IPAddressI address, String communicationInboundPortURI) {
        this.address = address;
        this.communicationInboundPortURI = communicationInboundPortURI;
    }

    public IPAddressI getAddress() {
        return this.address;
    }

    /**
     * @return URI du port de communication entrant du voisin
     */
    public String getCommunicationInboundPortURI() {
        return this.communicationInboundPortURI;
    }

    @Override
    public String toString() {
        return "ExternalConnectionInfo{" +
                "address=" + address.toString() +
                ", communicationInboundPortURI='" + communicationInboundPortURI + '\'' +
                '}';
    }

}
