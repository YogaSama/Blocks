package fr.creatruth.development.floatingtext.packet;

import com.google.common.collect.Lists;
import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import fr.creatruth.development.reflection.ConstructorBuilder;

import java.util.List;

import static fr.creatruth.development.floatingtext.MetadataIndex.NAME_TAG;

public class EntityRenamePacket extends EntityPacket {

    public EntityRenamePacket(int id, String name) {
        ClassAccess<?> access = ClassAccess.forName(PackAPI.NMS.get("PacketPlayOutEntityDestroy"));
        this.packet = access.newInstance();
        set("a", id);
        List<Object> watchers = Lists.newArrayList();

        ClassAccess<?> wClass = ClassAccess.forName(PackAPI.NMS.get("DataWatcher.WatchableObject"));
        ConstructorBuilder cb = wClass.getConstructor(int.class, int.class, Object.class);
        Object watcher        = cb.newInstance(NAME_TAG.getType().getId(), NAME_TAG.getIndex(), name);
        watchers.add(watcher);
        set("b", watchers);
    }
}
