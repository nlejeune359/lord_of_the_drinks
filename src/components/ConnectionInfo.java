package components;

import interfaces.P2PAddressI;
import interfaces.PositionI;

public class ConnectionInfo {
	/**
	 * @return l’adresse du voisin
	 */
	
	private P2PAddressI address;
	private String communicationInboundPortURI;
	private String routingInboundPortURI;

	public ConnectionInfo(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) {
		this.address = address;
		this.communicationInboundPortURI = communicationInboundPortURI;
		this.routingInboundPortURI = routingInboundPortURI;
	}
	
	public P2PAddressI getAddress() {
		return this.address;
	}
	
	/**
	 * @return URI du port de communication entrant du voisin
	 */
	public String getCommunicationInboundPortURI() {
		return this.communicationInboundPortURI;
	}
	
	/**
	 * @return URI du port de gestion du routage entrant du voisin
	 */
	public String getRoutingInboundPortURI() {
		return this.routingInboundPortURI;
	}

	@Override
	public String toString() {
		return "ConnectionInfo{" +
				"address=" + address +
				", communicationInboundPortURI='" + communicationInboundPortURI + '\'' +
				", routingInboundPortURI='" + routingInboundPortURI + '\'' +
				'}';
	}
}
