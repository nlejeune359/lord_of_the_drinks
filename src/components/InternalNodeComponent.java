package components;

import connectors.NetworkNodeConnector;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.*;
import ports.NetworkNodeInboundPort;
import ports.NetworkNodeOutboundPort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredInterfaces(required = {NetworkNodeServicesCI.class})
public class InternalNodeComponent extends NodeComponent {

    protected InternalNodeComponent(PositionI position, double range, NodeComponent.DeviceType type) throws Exception {
        super(position, range, type);
    }



    @Override
    public synchronized void start() throws ComponentStartException
    {
        try {
        	//System.out.println("-- do port connection --");
            this.doPortConnection(
            	this.simulatorPort.getPortURI(),
            	SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI, 
           		NetworkNodeConnector.class.getCanonicalName()
            );
            
            //System.out.println("-- registrationInternal --");
            this.neighbours = this.simulatorPort.registrationInternal(
                    this.addr,
                    this.inboundPort.getPortURI(),
                    this.initialPosition,
                    this.initialRange,
                    SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI
            );

            this.logMessage("Neighbours : " + this.neighbours.toString() +"\n");

            for(ConnectionInfo c: this.neighbours)
            {
                NetworkNodeOutboundPort outboundPort = new NetworkNodeOutboundPort(this);
                this.outboundPorts.put(c.getCommunicationInboundPortURI(), outboundPort);
                this.doPortConnection(
                        this.outboundPorts.get(c.getCommunicationInboundPortURI()).getPortURI(),
                        c.getCommunicationInboundPortURI(),
                        NetworkNodeConnector.class.getCanonicalName()
                );
                // System.out.println("Port Connected");
                this.outboundPorts.get(c.getCommunicationInboundPortURI()).connect(this.addr, this.inboundPort.getPortURI(), this.outboundPorts.get(c.getCommunicationInboundPortURI()).getPortURI());
            }
        } catch(Exception e) {
            throw new ComponentStartException(e);
        }
        super.start();
    }
}
