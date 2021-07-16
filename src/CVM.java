import components.SimulatorComponent;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;

public class CVM extends AbstractCVM {

    public CVM() throws Exception {
    }

    /**
     * @see fr.sorbonne_u.components.cvm.AbstractCVM#deploy()
     */
    @Override
    public void deploy() throws Exception {
        AbstractComponent.createComponent(
                SimulatorComponent.class.getCanonicalName(), new Object[]{0,1}
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
