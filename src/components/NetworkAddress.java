package components;

import interfaces.AddressI;
import interfaces.P2PAddressI;

public class NetworkAddress implements P2PAddressI {
    protected String addr;

    public NetworkAddress(String addr) {
        this.addr = addr;
    }

    @Override
    public boolean equals(AddressI a) {
        return false;
    }

    @Override
    public boolean isP2PAddress() {
        return P2PAddressI.super.isP2PAddress();
    }

    @Override
    public boolean isIPAddress() {
        return P2PAddressI.super.isIPAddress();
    }
}
