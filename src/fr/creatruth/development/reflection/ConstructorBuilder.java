package fr.creatruth.development.reflection;

import fr.creatruth.blocks.BMain;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class ConstructorBuilder<T> {

    private Constructor<T> constructor;

    public ConstructorBuilder(Class<T> clazz, Class<?>... parametersType) throws NoSuchMethodException {
        this.constructor = clazz.getConstructor(parametersType);
    }

    public T newInstance(Object... parameters) {
        try {
            return this.constructor.newInstance(parameters);
        } catch (Exception e) {
            BMain.log("§cnewInstance(%s) : %s", Arrays.toString(parameters), this);
            e.printStackTrace();
            return null;
        }
    }

    public Constructor<T> getConstructor() {
        return this.constructor;
    }

    @Override
    public String toString() {
        return constructor.getName() + "(" + Arrays.toString(constructor.getParameterTypes()) + ")";
    }
}
