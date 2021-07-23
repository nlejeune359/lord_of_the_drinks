package interfaces;

/**
 * Interface liee a la position des elements du reseau pair a pair
 */
public interface PositionI {

	/**
	 * Return the distance with the other position
	 * @param other
	 * @return double
	 */
	public double distance(PositionI other);
}
