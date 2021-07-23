package components;

import connectors.ExternalElementConnector;
import connectors.NetworkNodeConnector;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import interfaces.*;
import ports.ExternalElementOutboundPort;
import ports.NetworkNodeInboundPort;
import ports.NetworkNodeOutboundPort;

import java.util.HashMap;
import java.util.Map;

/**
 * Composant representant un point d access du reseau pair a pair
 */
public class AccessPointComponent extends NodeComponent implements ExternalCommunicationCI {

    protected Map<ExternalConnectionInfo, NetworkNodeOutboundPort> externalPorts = new HashMap<ExternalConnectionInfo, NetworkNodeOutboundPort>();

    protected AccessPointComponent(PositionI position, double range, NodeComponent.DeviceType type) throws Exception {
        super(position, range, type);
    }

    protected AccessPointComponent(String address, String inboundPortURI, PositionI position, double range, NodeComponent.DeviceType type) throws Exception {
        super(address, inboundPortURI, position, range, type);
    }

    @Override
    public synchronized void start() throws ComponentStartException
    {
        try {
            // connexion au simulateur
            this.doPortConnection(
                    this.simulatorPort.getPortURI(),
                    SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI,
                    NetworkNodeConnector.class.getCanonicalName()
            );

            // recupere les voisins du composant
            this.neighbours = this.simulatorPort.registrationAccessPoint(
                    this.addr,
                    this.inboundPort.getPortURI(),
                    this.initialPosition,
                    this.initialRange,
                    SimulatorComponent.REGISTRATION_NODE_INBOUND_PORT_URI
            );

            this.logMessage("Neighbours : " + this.neighbours.toString() +"\n");

            // on cree une connexion entre tous les voisins
            for(ConnectionInfo c: this.neighbours)
            {
                NetworkNodeOutboundPort outboundPort = new NetworkNodeOutboundPort(this);
                this.outboundPorts.put(c.getCommunicationInboundPortURI(), outboundPort);
                this.doPortConnection(
                        this.outboundPorts.get(c.getCommunicationInboundPortURI()).getPortURI(),
                        c.getCommunicationInboundPortURI(),
                        NetworkNodeConnector.class.getCanonicalName()
                );
                this.outboundPorts.get(c.getCommunicationInboundPortURI()).connect(this.addr, this.inboundPort.getPortURI(), this.outboundPorts.get(c.getCommunicationInboundPortURI()).getPortURI());
            }
        } catch(Exception e) {
            throw new ComponentStartException(e);
        }
        super.start();
    }

    @Override
    public void routeExternalMessage(MessageI m) throws Exception {

    }

    @Override
    public void connectExternal(AddressI address, String communicationInboundPortURI) throws Exception {
    	// verification de l'adresse
        if(!address.isIPAddress()){
            throw new Exception();
        }

        // connexion a l'element externe 
        ExternalConnectionInfo externalElement = new ExternalConnectionInfo((IPAddressI) address, communicationInboundPortURI);
        NetworkNodeOutboundPort outboundPort = new NetworkNodeOutboundPort(this);
        this.doPortConnection(
                outboundPort.getPortURI(),
                externalElement.getCommunicationInboundPortURI(),
                NetworkNodeConnector.class.getCanonicalName()
        );
        
        // Ajout du nouveau port dans la liste des ports destines aux elements externes au reseau 
        this.externalPorts.put(externalElement, outboundPort);

        logMessage("New external element added");
        logMessage(this.externalPorts.toString() + "\n");
    }
}
