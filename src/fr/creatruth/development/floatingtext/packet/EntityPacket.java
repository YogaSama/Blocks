package fr.creatruth.development.floatingtext.packet;

import fr.creatruth.blocks.utils.PlayerUtils;
import fr.creatruth.development.reflection.FieldAccess;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.bukkit.entity.Player;

abstract public class EntityPacket {

    protected int       id;
    protected Object    packet;

    private LazyInitializer<FieldAccess> access = new LazyInitializer<FieldAccess>() {
        @Override
        protected FieldAccess initialize() throws ConcurrentException {
            return new FieldAccess(packet);
        }
    };

    /**
     *
     * @param player
     */
    public void send(Player player) {
        PlayerUtils.send(player, packet);
    }

    /**
     *
     * @param field
     * @param value
     */
    protected void set(String field, Object value) {
        try {
            access.get().setDeclared(field, value);
        } catch (ConcurrentException e) {
        }
    }
}
