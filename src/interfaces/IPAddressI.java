package interfaces;

public interface IPAddressI extends AddressI {
	public default boolean isP2PAddress() {
		return false;
	}	
	public default boolean isIPAddress() {
		return true;
	}
}
