package components;

import interfaces.AddressI;
import interfaces.P2PAddressI;

public class NetworkAddress implements P2PAddressI {
	
	private static final long serialVersionUID = 1L;
	private String addr;
	
	public NetworkAddress(String addr) 
	{
		this.addr = addr;
	}
	
	@Override
	public boolean equals(AddressI a) {
		if(a == null) return false;
		if(!a.isP2PAddress()) return false;
		NetworkAddress address = (NetworkAddress) a;
		if(!this.addr.equals(address.addr)) return false;
		return true;
	}

	/**
	 * Return the address of NetworkAddress
	 *
	 * @return NetworkAddress.addr
	 */
	public String getAddr() {
		return addr;
	}
}
