
public interface P2PAddressI extends AddressI {
	public default boolean isP2PAddress() {
		return true;
	}
	
	public default boolean isIPAddress() {
		return false;
	}
}
