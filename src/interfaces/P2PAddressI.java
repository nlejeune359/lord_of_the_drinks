package interfaces;

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
