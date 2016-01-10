package fr.creatruth.development.material;

import fr.creatruth.blocks.block.BaseBlock;

import java.util.Collection;

public class State {

    private byte[]     data;
    private BaseBlock  base;

    /**
     * Constructeur.
     * @param collection Collection de byte.
     */
    public State(Collection<Byte> collection) {
        int i = 0;
        this.data = new byte[collection.size()];
        for (byte d : collection) this.data[i++] = d;
    }

    /**
     * Constructeur.
     * @param data Tableau de byte.
     */
    public State(byte... data) {
        this.data = data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setBase(BaseBlock base) {
        this.base = base;
    }

    public BaseBlock getBase() {
        return base;
    }

    public boolean isValid(byte data) {
        for (byte d : this.data) if (d == data) return true;
        return false;
    }

    public byte ajustToReal(byte data) {
        if      (this.data[0] > data)                    return this.data[0];
        else if (this.data[this.data.length - 1] < data) return this.data[this.data.length - 1];

        byte near = this.data[0];
        for (byte d : this.data) if (Math.abs(data - d) < near) near = d;
        return near;
    }
}
