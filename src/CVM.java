import components.*;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import interfaces.IPAddressI;

import java.util.HashSet;
import java.util.Set;

public class CVM extends AbstractCVM {

    public CVM() throws Exception {
    }

    /**
     * @see fr.sorbonne_u.components.cvm.AbstractCVM#deploy()
     */
    @Override
    public void deploy() throws Exception {
        ////////////////////////////////////////
        AbstractComponent.createComponent(
                SimulatorComponent.class.getCanonicalName(), new Object[]{}
        );

        AbstractComponent.createComponent(
                InternalNodeComponent.class.getCanonicalName(), new Object[]{new NodePosition(0, 0), 5.0, InternalNodeComponent.DeviceType.SMARTPHONE}
        );
        
        AbstractComponent.createComponent(
                InternalNodeComponent.class.getCanonicalName(), new Object[]{new NodePosition(0, 1), 5.0, InternalNodeComponent.DeviceType.SMARTPHONE}
        );

        AbstractComponent.createComponent(
                InternalNodeComponent.class.getCanonicalName(), new Object[]{new NodePosition(0, 1), 5.0, InternalNodeComponent.DeviceType.TABLET}
        );

        AbstractComponent.createComponent(
                AccessPointComponent.class.getCanonicalName(), new Object[]{new NodePosition(0, 1), 5.0, InternalNodeComponent.DeviceType.TABLET}
        );
        ////////////////////////////////////////

        AbstractComponent.createComponent(
                AccessPointComponent.class.getCanonicalName(), new Object[]{
                        "access-point",
                        "ap-ip",
                        new NodePosition(0, 0),
                        5.0,
                        InternalNodeComponent.DeviceType.LAPTOP
                }
        );

        AbstractComponent.createComponent(
                ExternalElementComponent.class.getCanonicalName(), new Object[]{"external-element", "e-ip", "access-point", "ap-ip"}
        );

        super.deploy();
    }

    public static void main(String args[]) {
        try {
            CVM cvm = new CVM();
            cvm.startStandardLifeCycle(1000L);
            Thread.sleep(10000L);
            System.exit(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
