package ports;

import components.ConnectionInfo;
import components.SimulatorComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.interfaces.RequiredCI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import interfaces.RegistrationCI;

import java.util.Set;

public class SimulatorOutboundPort extends AbstractOutboundPort implements RegistrationCI {

    public SimulatorOutboundPort(ComponentI owner) throws Exception
    {
        super(RegistrationCI.class, owner);
        assert(owner instanceof SimulatorComponent);
    }

    public SimulatorOutboundPort(String uri, ComponentI owner) throws Exception
    {
        super(uri, RegistrationCI.class, owner);
        assert(owner instanceof SimulatorComponent);
    }

    @Override
    public Set<ConnectionInfo> registrationInternal(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
        return null;
    }

    @Override
    public Set<ConnectionInfo> registrationAccessPoint(P2PAddressI address, String communicationInboundPort, PositionI initialPosition, double initialRange, String routingInboundPortURI) throws Exception {
        return null;
    }

    @Override
    public void unregister(P2PAddressI address) throws Exception {

    }
}
