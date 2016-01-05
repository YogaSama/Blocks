package fr.creatruth.development.floatingtext;

import static fr.creatruth.development.floatingtext.MetadataType.*;

/**
 * Created by zyuiop on 01/05/15.
 * Part of Epicube project
 */
public enum MetadataIndex {

    STATUS(0, BYTE),

    NAME_TAG(2, STRING),
    SHOW_NAME_TAG(3, BYTE),
    HEALTH(6, FLOAT),

    ARMORSTAND_FLAGS(10, BYTE),
    ;

    private final int index;
    private final MetadataType type;

    MetadataIndex(int index, MetadataType type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public MetadataType getType() {
        return type;
    }
}
