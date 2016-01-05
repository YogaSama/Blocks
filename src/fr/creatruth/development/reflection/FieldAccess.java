package fr.creatruth.development.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldAccess {

    private static final Map<String, Field> fields = new HashMap<>();

    private Class    clazz;
    private Object   instance;

    public FieldAccess(Object instance) {
        this(instance.getClass(), instance);
    }

    public FieldAccess(Class<?> clazz, Object instance) {
        this.clazz    = clazz;
        this.instance = instance;
    }

    public void setDeclared(String fieldName, Object value) {
        try {
            getField0(true, fieldName).set(instance, value);
        } catch (Exception e) {e.printStackTrace();}
    }

    public Object getDeclared(String fieldName) {
        try {
            return getField0(true, fieldName).get(instance);
        } catch (Exception e) {e.printStackTrace();return null;}
    }

    public void set(String fieldName, Object value) {
        try {
            getField0(false, fieldName).set(instance, value);
        } catch (Exception e) {e.printStackTrace();}
    }

    public Object get(String fieldName) {
        try {
            return getField0(false, fieldName).get(instance);
        } catch (Exception e) {e.printStackTrace();return null;}
    }

    public Field getField(String fieldName) {
        return getField0(false, fieldName);
    }

    public Field getDeclaredField(String fieldName) {
        return getField0(true, fieldName);
    }

    private Field getField0(boolean declared, String fieldName) {
        Field field = null;

        String name = clazz.getName() + "." + fieldName;
        if (!fields.containsKey(name)) {
            try {
                field = declared ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
                field.setAccessible(true);
                fields.put(name, field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        else
            field = fields.get(name);

        return field;
    }
}
