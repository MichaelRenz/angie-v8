package transformer;

/**
* Created by Angelika Langer on 28.02.14.
*/
public abstract class Transformer {
    protected double value;
    protected boolean transformed = false;

    public Transformer(double v)  {
        value = v;
    }

    public abstract void transform();

    public boolean isTransformed() {
        return transformed;
    }

    public double getValue() {
        return value;
    }
}
