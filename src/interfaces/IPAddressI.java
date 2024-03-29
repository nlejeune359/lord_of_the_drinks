package interfaces;

/**
 * Adresse d un element externe au reseau pair a pair
 */
public interface IPAddressI extends AddressI {

	/**
	 * Check if AdressI is a P2PAdress
	 */
	public default boolean isP2PAddress() {
		return false;
	}
	
	/**
	 * Check if AdressI is an IPAddress
	 */
	public default boolean isIPAddress() {
		return true;
	}
}
