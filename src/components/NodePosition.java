package components;

import interfaces.PositionI;

/**
 * Position d un element au sein du reseau pair a pair
 */
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
    	x = (((NodePosition)other).x - this.x) * (((NodePosition)other).x - this.x);
    	y = (((NodePosition)other).y - this.y) * (((NodePosition)other).y - this.y);
        return Math.sqrt(x + y);
    }
}
