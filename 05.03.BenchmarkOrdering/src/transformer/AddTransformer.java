package transformer;

/**
* Created by Angelika Langer on 28.02.14.
*/
public final class AddTransformer extends Transformer {
    private double summand;
    public AddTransformer(double v, double s)  {
        super(v);
        summand = s;
    }

    @Override
    public void transform() {
        value += summand;
        transformed = true;
    }
}
