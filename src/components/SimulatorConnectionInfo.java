package components;

import interfaces.P2PAddressI;
import interfaces.PositionI;

public class SimulatorConnectionInfo extends ConnectionInfo {

    private PositionI initialPosition;
    private double initialRange;
    private NodeType nodeType;

    public SimulatorConnectionInfo(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI, PositionI initialPosition, double initialRange, NodeType nodeType) {
        super(address, communicationInboundPortURI, routingInboundPortURI);
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

    public NodeType getNodeType() {
        return nodeType;
    }
}
