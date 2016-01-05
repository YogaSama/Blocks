package fr.creatruth.development.floatingtext;

public enum MetadataType {

    BYTE,
    SHORT,
    INT,
    FLOAT,
    STRING,
    ;

    public int getId() {
        return ordinal();
    }
}