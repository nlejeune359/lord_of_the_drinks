package components;

import interfaces.PositionI;

public class NodePosition implements PositionI {
    public double x;
    public double y;

    public NodePosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public double distance(PositionI other) {
        return 0;
    }
}
