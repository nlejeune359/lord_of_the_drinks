package components;

import interfaces.AddressI;
import interfaces.IPAddressI;

/**
 * Objet representant l adresse d un element externe au reseau pair a pair
 */
public class ExternalNetworkAddress implements IPAddressI {

    private String addr;

    public ExternalNetworkAddress(String addr)
    {
        this.addr = addr;
    }

    public String getAddr(){
        return this.addr;
    }

    @Override
    public boolean equals(AddressI a) {
        return false;
    }

    @Override
    public boolean isP2PAddress() {
        return false;
    }

    @Override
    public boolean isIPAddress() {
        return true;
    }

    @Override
    public String toString() {
        return "ExternalNetworkAddress{" +
                "addr='" + addr + '\'' +
                '}';
    }
}
