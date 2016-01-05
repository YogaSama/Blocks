package fr.creatruth.development.reflection;

import fr.creatruth.blocks.BMain;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClassAccess<T> {

    private static final Map<String, ConstructorBuilder<?>> constructors = new HashMap<>();
    private static final Map<String, ClassAccess<?>>        classes      = new HashMap<>();

    private Class<T> clazz;

    /**
     *
     * @param clazz
     */
    public ClassAccess(Class<T> clazz) {
        this.clazz  = clazz;
    }

    /**
     *
     * @return
     */
    public Class<T> getClazz() {
        return clazz;
    }

    /**
     *
     * @return
     */
    public T newInstance() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            BMain.log("§cnewInstance : " + this);
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param parametersType
     * @return
     */
    @SuppressWarnings("unchecked")
    public ConstructorBuilder<T> getConstructor(Class<?>... parametersType) {
        try {
            String key;
            if (parametersType == null) {
                key = clazz.getName();
                if (!constructors.containsKey(key))
                    constructors.put(key, new ConstructorBuilder<>(clazz));
            }
            else {
                key = "";
                for (Class<?> aParametersType : parametersType)
                    key += aParametersType.getName();

                if (!constructors.containsKey(key))
                    constructors.put(key, new ConstructorBuilder<>(clazz, parametersType));
            }
            return (ConstructorBuilder<T>) constructors.get(key);
        } catch (Exception e) {
            BMain.log("§cgetConstructor(%s) : %s", Arrays.toString(parametersType), this);
            throw new NullPointerException();
        }
    }

    /**
     *
     * @param methodName
     * @param args
     * @return
     */
    public MethodAccess getDeclaredMethod(String methodName, Class... args) {
        return new MethodAccess(clazz, true, methodName, args);
    }

    /**
     *
     * @param methodName
     * @param args
     * @return
     */
    public MethodAccess getMethod(String methodName, Class... args) {
        return new MethodAccess(clazz, false, methodName, args);
    }

    /**
     *
     * @param instance
     * @param method
     * @param args
     * @return
     */
    public Object invoke(Object instance, Method method, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (Exception e) {
            BMain.log("§cinvoke(%s - %s - %s) : %s", instance, method.getName(), Arrays.toString(args), this);
            //e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param className
     * @return
     */
    public static ClassAccess<?> forName(String className) {
        try {
            if (!classes.containsKey(className)) {
                classes.put(className, new ClassAccess<>(Class.forName(className)));
            }
            return classes.get(className);
        } catch (Exception e) {
            throw new NullPointerException(className);
        }
    }

    @Override
    public String toString() {
        return clazz.getName();
    }
}
