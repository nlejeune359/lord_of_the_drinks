import java.util.Set;

import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

/**
 * Pour l’enregistrement et la découverte des voisins, l’interface de composant
 * RegistrationCI est offerte par le composant simulateur et requise par les composants éléments du réseau
 * pair-à-pair.
 */
public interface RegistrationCI extends RequiredCI, OfferedCI	{
	/**
	 * Enregistre un élément internes 
	 * 
	 * @param address : adresse unique attribuée à l’élément lors de sa création qui va servir à l’identifier de manière unique et lui adresser des messages
	 * @param communicationInboundPort : URI du port entrant de l’élément via lequel il va offrir tous les services d’échange des messages sur le réseau
	 * @param initialPosition : position initiale 1 de l’élément de type PositionI qui abstrait la représentation exacte de la position en permettant simplement de calculer la distance entre deux positions 2
	 * @param initialRange : portée d’émission du participant, c’est à dire la distance maximale à laquelle il peut émettre de manière compréhensible pour le récepteur 3
	 * @param routingInboundPortURI : URI du port entrant de l’élément via lequel il va offrir tous les services de gestion du routage dans le réseau
	 * @return voisins : Ensemble de chaque voisin identifié par le simulateur
	 */
	public Set<ConnectionInfo> registrationInternal(
		P2PAddressI address,
		String communicationInboundPort,
		PositionI initialPosition,
		double initialRange,
		String routingInboundPortURI
	);
	
	/**
	 * Enregistre des éléments qui sont des points d’accès aux réseaux classiques
	 * 
	 * @param address : adresse unique attribuée à l’élément lors de sa création qui va servir à l’identifier de manière unique et lui adresser des messages
	 * @param communicationInboundPort : URI du port entrant de l’élément via lequel il va offrir tous les services d’échange des messages sur le réseau
	 * @param initialPosition : position initiale 1 de l’élément de type PositionI qui abstrait la représentation exacte de la position en permettant simplement de calculer la distance entre deux positions 2
	 * @param initialRange : portée d’émission du participant, c’est à dire la distance maximale à laquelle il peut émettre de manière compréhensible pour le récepteur 3
	 * @param routingInboundPortURI : URI du port entrant de l’élément via lequel il va offrir tous les services de gestion du routage dans le réseau
	 * @return voisins : Ensemble de chaque voisin identifié par le simulateur
	 */
	public Set<ConnectionInfo> registrationAccessPoint(
		P2PAddressI address,
		String communicationInboundPort,
		PositionI initialPosition,
		double initialRange,
		String routingInboundPortURI
	);
	
	/**
	 * @param Enlèver l'émément enregistré
	 */
	public void unregister(P2PAddressI address);
}
