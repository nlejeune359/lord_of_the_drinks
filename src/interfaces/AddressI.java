package interfaces;

public interface AddressI {

	/**
	 * Check if this is a P2PAdress
	 *
	 * @return boolean
	 */
	public boolean isP2PAddress();

	/**
	 * Check if this is an IPAddress
	 *
	 * @return boolean
	 */
	public boolean isIPAddress();

	/**
	 * Check if this is equal to a
	 * 
	 * @param a
	 * @return boolean
	 */
	public boolean equals(AddressI a);
}
