package components;

import interfaces.P2PAddressI;
import interfaces.PositionI;

/**
 * Informations de connexion, position et portee d un noeud du reseau pair a pair
 * Utilise par le simulateur pour stocker les noeuds du reseau pair a pair
 */
public class SimulatorConnectionInfo {

    private P2PAddressI address;
    private String communicationInboundPortURI;
    private String routingInboundPortURI;
    private PositionI initialPosition;
    private double initialRange;
    private NodeType nodeType;

    /**
     * Types des noeuds du reseau
     */
    public enum NodeType {
        INTERNAL, ACCESS_POINT
    }

    public SimulatorConnectionInfo(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI, PositionI initialPosition, double initialRange, NodeType nodeType) {
        this.address = address;
        this.communicationInboundPortURI = communicationInboundPortURI;
        this.routingInboundPortURI = routingInboundPortURI;
        this.initialPosition = initialPosition;
        this.initialRange = initialRange;
        this.nodeType = nodeType;
    }

    public PositionI getInitialPosition() {
        return initialPosition;
    }

    public double getInitialRange() {
        return initialRange;
    }

    public P2PAddressI getAddress() {
        return address;
    }

    public String getCommunicationInboundPortURI() {
        return communicationInboundPortURI;
    }

    public String getRoutingInboundPortURI() {
        return routingInboundPortURI;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    @Override
    public String toString() {
        return "SimulatorConnectionInfo{" +
                "address=" + address +
                ", communicationInboundPortURI='" + communicationInboundPortURI + '\'' +
                ", routingInboundPortURI='" + routingInboundPortURI + '\'' +
                ", initialPosition=" + initialPosition +
                ", initialRange=" + initialRange +
                '}';
    }
}
