package interfaces;

/**
 * Adresse d un element du reseau pair a pair (point d acces ou noeud interne)
 */
public interface P2PAddressI extends AddressI {
	
	@Override
	public default boolean isP2PAddress() {
		return true;
	}
	
	@Override
	public default boolean isIPAddress() {
		return false;
	}
}
