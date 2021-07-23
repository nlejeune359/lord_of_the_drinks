package interfaces;

import java.util.Set;

import components.RouteInfo;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

/**
 * Pour le routage au sein du reseau pair a pair
 */
public interface RoutingManagementCI extends RequiredCI, OfferedCI {

	/**
	 * Give routing informations to update routing tables of the component
	 *
	 * @param neighbour
	 * @param routes
	 */
	public void updateRouting(
		P2PAddressI neighbour,
		Set<RouteInfo> routes
	);

	/**
	 * Update path to closest access point
	 *
	 * @param neighbour
	 * @param numberOfHops
	 */
	public void updateAccessPoint(
		P2PAddressI neighbour, 
		int numberOfHops
	);
}
