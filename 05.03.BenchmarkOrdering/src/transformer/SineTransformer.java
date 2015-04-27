package transformer;

import math.Sine;

/**
* Created by Angelika Langer on 28.02.14.
*/
public final class SineTransformer extends Transformer {
    public SineTransformer(double v)  {
        super(v);
    }

    @Override
    public void transform() {
        value = Sine.slowSin(value);
        transformed = true;
    }
}
