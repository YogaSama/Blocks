package fr.creatruth.development.reflection;

import fr.creatruth.blocks.BMain;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MethodAccess {

    private static final Map<String, Method> methods = new HashMap<>();

    private Method method;

    public MethodAccess(Class<?> clazz, boolean declared, String methodName, Class... args) {
        String name = clazz.getName() + "." + methodName;
        if (!methods.containsKey(name))
            try {
                methods.put(name, declared ?
                                  clazz.getDeclaredMethod(methodName, args) :
                                  clazz.getMethod(methodName, args));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        this.method = methods.get(name);
    }

    /**
     *
     * @param instance
     * @param args
     * @return
     */
    public Object invoke(Object instance, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (InvocationTargetException e) {
        } catch (Exception e) {
            BMain.log("§cinvoke(%s - %s - %s) : %s", instance, method.getName(), Arrays.toString(args), this);
        }
        return null;
    }

    public Method getMethod() {
        return method;
    }
}
