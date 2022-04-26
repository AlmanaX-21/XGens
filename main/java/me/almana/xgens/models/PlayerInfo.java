package me.almana.xgens.models;

import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PlayerInfo {

    private UUID playerUUID;
    private List<Location> genLocs;


    public PlayerInfo(UUID playerUUID, List<Location> genLocs) {
        this.playerUUID = playerUUID;
        this.genLocs = genLocs;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public List<Location> getGenLocs() {
        return genLocs;
    }

    public void setGenLocs(List<Location> genLocs) {
        this.genLocs = genLocs;
    }
}
