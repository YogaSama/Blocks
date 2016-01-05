package fr.creatruth.development.reflection;

import org.bukkit.Bukkit;

public enum PackAPI {

    OBC("org.bukkit.craftbukkit."),
    NMS("net.minecraft.server."),
    ;

    private String reference;

    PackAPI(String reference) {
        this.reference = reference;
    }

    public String get(String className) {
        return reference + getServerVersion() + "." + className;
    }

    private static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }
}
