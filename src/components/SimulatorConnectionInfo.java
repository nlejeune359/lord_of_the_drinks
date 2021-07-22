package components;

import interfaces.P2PAddressI;
import interfaces.PositionI;

public class SimulatorConnectionInfo {

    private P2PAddressI address;
    private String communicationInboundPortURI;
    private String routingInboundPortURI;
    private PositionI initialPosition;
    private double initialRange;

    public SimulatorConnectionInfo(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI, PositionI initialPosition, double initialRange) {
        this.address = address;
        this.communicationInboundPortURI = communicationInboundPortURI;
        this.routingInboundPortURI = routingInboundPortURI;
        this.initialPosition = initialPosition;
        this.initialRange = initialRange;
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
