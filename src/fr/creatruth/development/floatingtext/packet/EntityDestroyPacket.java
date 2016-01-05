package fr.creatruth.development.floatingtext.packet;

import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import fr.creatruth.development.reflection.ConstructorBuilder;

public class EntityDestroyPacket extends EntityPacket {

    public EntityDestroyPacket(int id) {
        ClassAccess<?> access = ClassAccess.forName(PackAPI.NMS.get("PacketPlayOutEntityDestroy"));
        ConstructorBuilder cb = access.getConstructor(int[].class);
        this.packet = cb.newInstance(new int[]{id});
    }
}
