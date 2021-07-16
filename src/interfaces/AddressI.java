package interfaces;

public interface AddressI {
	public boolean isP2PAddress();
	public boolean isIPAddress();
	public boolean equals(AddressI a);
}
