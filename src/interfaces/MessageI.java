package interfaces;

import interfaces.AddressI;

import java.io.Serializable;

public interface MessageI {
	/**
	 * get the address of the recipient in the message
	 *
	 * @return MessageI.address
	 */
	public AddressI getAddress();
	
	/**
	 * get the content of the message
	 *
	 * @return MessageI.message
	 */
	public Serializable getContent();
	
	/**
	 * Check if the message is still alive -> number of hops > 0
	 *
	 * @return boolean
	 */
	public boolean stillAlive();
	
	/**
	 * decrement the number of hops of the message
	 */
	public void decrementHops();
}
