package components;

import connectors.NetworkNodeConnector;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import interfaces.MessageI;
import interfaces.NetworkNodeServicesCI;
import interfaces.P2PAddressI;
import interfaces.PositionI;
import ports.NetworkNodeInboundPort;
import ports.NetworkNodeOutboundPort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Composant representant un element du reseau pair a pair
 * Les points d acces et les elements internes etendent ce composant
 */
public class NodeComponent extends AbstractComponent implements NetworkNodeServicesCI {

    protected P2PAddressI addr;
    protected NetworkNodeOutboundPort simulatorPort;
    protected NetworkNodeInboundPort inboundPort;
    protected Map<String, NetworkNodeOutboundPort> outboundPorts = new HashMap<String, NetworkNodeOutboundPort>();
    protected String SIMULATOR_URI;
    protected PositionI initialPosition;
    protected double initialRange;
    protected DeviceType deviceType;

    protected Set<ConnectionInfo> neighbours = new HashSet<>();

    /**
     * Differents types de peripheriques connectes au reseau
     */
    public enum DeviceType {
        SMARTPHONE, TABLET, LAPTOP, DESKTOP
    }

    /**
     * cree un point acces classique
     * @param position
     * @param range
     * @param type
     * @throws Exception
     */
    protected NodeComponent(PositionI position, double range, DeviceType type) throws Exception {
        super(1, 0);
        this.initialPosition = position;
        this.initialRange = range;
        this.deviceType = type;
        this.addr = new NetworkAddress(generateAddr());
        this.inboundPort = new NetworkNodeInboundPort(this);
        this.initialize();
    }

    /**
     * cree un point d'acces avec addresse et uri du port entrant
     * @param address
     * @param inboundPortURI
     * @param position
     * @param range
     * @param type
     * @throws Exception
     */
    protected NodeComponent(String address, String inboundPortURI, PositionI position, double range, DeviceType type) throws Exception {
        super(1, 0);
        this.initialPosition = position;
        this.initialRange = range;
        this.deviceType = type;
        this.addr = new NetworkAddress(address);
        this.inboundPort = new NetworkNodeInboundPort(inboundPortURI, this);
        this.initialize();
    }

    protected void initialize() throws Exception
    {
        this.inboundPort.publishPort();
        this.simulatorPort = new NetworkNodeOutboundPort(this);
        this.simulatorPort.publishPort();
        this.toggleLogging();
        this.toggleTracing();
    }

    /**
     * Generate a random address for the component
     *
     * @return String
     */
    public static String generateAddr()
    {
        String s = "";
        double d;
        for (int i = 1; i <= 16; i++) {
            d = Math.random() * 10;
            s = s + ((int) d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }

        return s;
    }

    @Override
    public synchronized void shutdown() throws ComponentShutdownException
    {
        try {
            this.inboundPort.unpublishPort();
            this.simulatorPort.unpublishPort();
        } catch(Exception e)
        {
            throw new ComponentShutdownException(e);
        }
    }

    @Override
    public synchronized void execute() throws Exception
    {
    	// envois un message au premier voisin
        Message m = new Message(((ConnectionInfo)this.neighbours.toArray()[0]).getAddress(), "Message", 5);
        this.sendMessageToNeighbours(m);
    }

    @Override
    public void connect(P2PAddressI address, String communicationInboundPortURI, String routingInboundPortURI) throws Exception {
    	// si ce n'est pas un voisin
        if(!containsPortURI(communicationInboundPortURI))
        {
        	// on l'ajoute dans la liste des voisins
            ConnectionInfo newNeighbour = new ConnectionInfo(
                    address,
                    communicationInboundPortURI,
                    routingInboundPortURI
            );
            this.neighbours.add(newNeighbour);
            this.logMessage("Neighbours : " + this.neighbours.toString() +"\n");

            // on lui cree un port
            NetworkNodeOutboundPort outboundPort = new NetworkNodeOutboundPort(this);
            this.outboundPorts.put(communicationInboundPortURI, outboundPort);

            // on connecte le port au voisin
            this.doPortConnection(
                    this.outboundPorts.get(communicationInboundPortURI).getPortURI(),
                    communicationInboundPortURI,
                    NetworkNodeConnector.class.getCanonicalName()
            );
        }
    }

    @Override
    public void routeMessage(MessageI m) throws Exception {
    	// Si le message nous est destine alors on l'affiche sinon on le renvois aux voisins
        if(m.getAddress().equals(this.addr)) {
            this.logMessage("Message received : " + m.getContent() + "\n");
        }
        else {
            m.decrementHops();
            if(m.stillAlive()) {
                this.sendMessageToNeighbours(m);
            }
            else {
                this.logMessage("Dead message received : " + m.getContent() +"\n");
            }
        }
    }

    @Override
    public void ping() {

    }

    /**
     * verifie si communicationInboundPortURI existe dans la liste des neighbours
     *
     * @param communicationInboundPortURI
     * @return boolean
     */
    private boolean containsPortURI(String communicationInboundPortURI)
    {
        for(ConnectionInfo c: this.neighbours)
        {
            if(c.getCommunicationInboundPortURI().equals(communicationInboundPortURI))
                return true;
        }
        return false;
    }

    /**
     * envois le message à tous les voisins
     *
     * @param m
     * @throws Exception
     */
    private void sendMessageToNeighbours(MessageI m) throws Exception {
        for(ConnectionInfo neighbour: neighbours)
        {
            NetworkNodeOutboundPort port = this.outboundPorts.get(neighbour.getCommunicationInboundPortURI());
            port.routeMessage(m);
        }
    }
}
