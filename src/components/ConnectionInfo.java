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
	private PositionI initialPosition;
	private double initialRange;
	private NodeType nodeType;

	public ConnectionInfo(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI, PositionI initialPosition, double initialRange, NodeType nodeType) {
		this.address = address;
		this.communicationInboundPortURI = communicationInboundPortURI;
		this.routingInboundPortURI = routingInboundPortURI;
		this.initialPosition = initialPosition;
		this.initialRange = initialRange;
		this.nodeType = nodeType;
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
	
	public PositionI getPos() {
		return this.initialPosition;
	}
	
	public double getRange() {
		return this.initialRange;
	}
}
