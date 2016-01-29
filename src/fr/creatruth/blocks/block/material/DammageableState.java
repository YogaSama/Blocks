package fr.creatruth.blocks.block.material;

public class DammageableState extends State {

    private short maxDurability;

    public DammageableState(short maxDurability) {
        this.maxDurability = maxDurability;
    }

    @Override
    public boolean isValid(byte data) {
        return 0 <= data && data <= maxDurability;
    }

    @Override
    public byte ajustToReal(byte data) {
        if (data < 0) return 0;
        if (data > maxDurability) return (byte) maxDurability;
        return data;
    }
}
