package components;

import java.io.Serializable;

import interfaces.AddressI;
import interfaces.MessageI;

/**
 * Objet representant un message destinant a etre envoye au sein du reseau pair a pair
 */
public class Message implements MessageI{
	
	private AddressI address;
	private Serializable message;
	private int hops;
	
	public Message(AddressI address, Serializable message, int hops) {
		this.address = address;
		this.message = message;
		this.hops = hops;
	}
	
	@Override
	public AddressI getAddress() {
		return this.address;
	}

	@Override
	public Serializable getContent() {
		return this.message;
	}

	@Override
	public boolean stillAlive() {
		return hops > 0;
	}

	@Override
	public void decrementHops() {
		this.hops--;
		
	}

}
