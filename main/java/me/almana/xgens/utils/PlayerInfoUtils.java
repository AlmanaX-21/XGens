package me.almana.xgens.utils;

import com.google.gson.Gson;
import me.almana.xgens.XGens;
import me.almana.xgens.models.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.*;

public class PlayerInfoUtils {

    private static List<PlayerInfo> playerInfos = new ArrayList<>();

    public static PlayerInfo createPlayerInfo(UUID playerUUID, List<Location> genLocations) {

        PlayerInfo playerInfo = new PlayerInfo(playerUUID, genLocations);
        playerInfos.add(playerInfo);

        return playerInfo;
    }

    public static void deletePlayerInfo(UUID playerUUID) {

        for (PlayerInfo pi: playerInfos) {

            if (pi.getPlayerUUID().equals(playerUUID)) {

                playerInfos.remove(pi);
                break;
            }
        }
    }

    public  static PlayerInfo findPlayerInfo(UUID playerUUID) {

        for (PlayerInfo pi: playerInfos) {

            if (pi.getPlayerUUID().equals(playerUUID)) {

                return pi;
            }
        }
        return null;
    }
    public static PlayerInfo updatePlayerInfo(UUID playerUUID, PlayerInfo newPlayerInfo) {

        for (PlayerInfo pi: playerInfos) {

            if (pi.getPlayerUUID().equals(playerUUID)) {

                newPlayerInfo.setPlayerUUID(newPlayerInfo.getPlayerUUID());
                newPlayerInfo.setGenLocs(newPlayerInfo.getGenLocs());
                return pi;
            }
        }
        return  null;
    }

    public static void savePlayerInfo() throws IOException {

        ByteArrayOutputStream io = new ByteArrayOutputStream();
        BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
        os.writeObject(playerInfos);
        os.flush();
        byte[] serialisedObjects = io.toByteArray();

        String encodedObject;
        encodedObject = Base64.getEncoder().encodeToString(serialisedObjects);

        Gson gson = new Gson();
        File file = new File(XGens.getPlugin().getDataFolder().getAbsolutePath() + "/playerInfo.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(encodedObject, writer);
        writer.flush();
        writer.close();
        XGens.getPlugin().getServer().getLogger().info("[XGens]:- Saved Player info.");
    }

    public static void loadPlayerInfo() throws FileNotFoundException {

        Gson gson = new Gson();
        File file = new File(XGens.getPlugin().getDataFolder().getAbsolutePath() + "/playerInfo.json");
        if (file.exists()) {

            Reader reader = new FileReader(file);
            PlayerInfo[] p = gson.fromJson(reader, PlayerInfo[].class);
            playerInfos = new ArrayList<>(Arrays.asList(p));
            Bukkit.getLogger().info("[XGens]:- Loaded Player Info.");
        }
    }
}
