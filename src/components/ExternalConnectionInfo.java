package components;

import interfaces.IPAddressI;
import interfaces.P2PAddressI;

/**
 * Informations de connexion d un element externe au reseau pair a pair
 */
public class ExternalConnectionInfo {

    private IPAddressI address;
    private String communicationInboundPortURI;

    public ExternalConnectionInfo(IPAddressI address, String communicationInboundPortURI) {
        this.address = address;
        this.communicationInboundPortURI = communicationInboundPortURI;
    }

    /**
     * @return l’adresse du composant
     */
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
