package components;

import fr.sorbonne_u.components.AbstractComponent;
import interfaces.P2PAddressI;

public class NetworkNodeComponent extends AbstractComponent {

    protected P2PAddressI addr;

    protected NetworkNodeComponent(P2PAddressI addr) {
        super(1, 0);

        this.addr = addr;

        this.toggleLogging();
    }
}
