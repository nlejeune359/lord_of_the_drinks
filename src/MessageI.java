import java.io.Serializable;

public interface MessageI {
	/**
	 * @return retourne l’adresse du destinataire du message
	 */
	public AddressI getAddress();
	
	/**
	 * @return retourne le contenu utile du message à récupérer par le destinataire
	 */
	public Serializable getContent();
	
	/**
	 * @return retourne vrai si le message doit encore être routé et faux sinon
	 */
	public boolean stillAlive();
	
	/**
	 * décremente le nombre de saut encore autorisé dans le routage du message
	 */
	public void decrementHops();
}
