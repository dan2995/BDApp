package mt.edu.um.b_d_app;

import com.sun.prism.shader.Solid_TextureFirstPassLCD_Loader;

/**
 * Created by Daniela on 08/05/2015.
 */
public abstract class Iterator {

    public abstract AtomicTransaction first();

    public abstract AtomicTransaction next();

    public abstract boolean isDone();

    public abstract AtomicTransaction currentItem();

}
