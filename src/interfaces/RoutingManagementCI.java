package interfaces;

import java.util.Set;

import components.RouteInfo;
import fr.sorbonne_u.components.interfaces.OfferedCI;
import fr.sorbonne_u.components.interfaces.RequiredCI;

public interface RoutingManagementCI extends RequiredCI, OfferedCI {
	public void updateRouting(
		P2PAddressI neighbour,
		Set<RouteInfo> routes
	);
	public void updateAccessPoint(
		P2PAddressI neighbour, 
		int numberOfHops
	);
}
