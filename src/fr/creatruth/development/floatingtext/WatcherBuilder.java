package fr.creatruth.development.floatingtext;

import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;


import java.util.*;

public class WatcherBuilder implements Cloneable {

    private Map<MetadataIndex, Object> map;

    public WatcherBuilder(EnumMap<MetadataIndex, Object> map) {
        this.map = map;
    }

    public WatcherBuilder() {
        this.map = new EnumMap<>(MetadataIndex.class);
        this.map.put(MetadataIndex.STATUS, 0);
    }

    public void setBit(MetadataIndex index, int bit, boolean status) {
        if (status) {
            set(index, getNumber(index).intValue() | bit);
        } else {
            set(index, getNumber(index).intValue() & ~bit);
        }
    }

    private Number getNumber(MetadataIndex index) {
        if (!map.containsKey(index))
            return 0;

        Object o = map.get(index);
        if (!(o instanceof Number)) {
            throw new IllegalArgumentException("Index " + index + " is of non-number type " + index.getType());
        }
        return (Number) o;
    }

    public void set(MetadataIndex index, Object value) {
        if (value instanceof Number) {
            Number n = (Number) value;
            switch (index.getType()) {
                case BYTE:
                    value = n.byteValue();
                    break;
                case SHORT:
                    value = n.shortValue();
                    break;
                case INT:
                    value = n.intValue();
                    break;
                case FLOAT:
                    value = n.floatValue();
                    break;
            }
        }
        map.put(index, value);
    }

    public Object build() {
        ClassAccess<?> access = ClassAccess.forName(PackAPI.NMS.get("DataWatcher"));
        Class          entity = ClassAccess.forName(PackAPI.NMS.get("Entity")).getClazz();
        Object         wc = access.getConstructor(entity).newInstance(new Object[]{null});

        for (Entry entry : getEntryList()) {
            int id = entry.index.getIndex();
            Object value = entry.value;
            try {
                access.getDeclaredMethod("a", int.class, Object.class).invoke(wc, id, value);
            } catch (Exception e) {
                access.getDeclaredMethod("watch", int.class, Object.class).invoke(wc, id, value);
            }
        }
        return wc;
    }

    private List<Entry> getEntryList() {
        List<Entry> result = new ArrayList<>(map.size());
        for (Map.Entry<MetadataIndex, Object> entry : map.entrySet()) {
            result.add(new Entry(entry.getKey(), entry.getValue()));
        }
        Collections.sort(result);
        return result;
    }

    @Override
    public WatcherBuilder clone() {
        try {
            return new WatcherBuilder(new EnumMap<>(this.map));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (Entry entry : getEntryList())
            s += entry.toString() + "\n";
        return s;
    }

    public static final class Entry implements Comparable<Entry> {

        public final MetadataIndex index;
        public final Object value;

        public Entry(MetadataIndex index, Object value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Entry o) {
            return o.index.getIndex() - index.getIndex();
        }

        @Override
        public String toString() {
            return index + "=" + value;
        }
    }
}
