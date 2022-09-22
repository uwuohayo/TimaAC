package ovh.rootkovskiy.timaac;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventsListener implements Listener {

    public EventsListener() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Main.notVerifedPlayers) {
                    player.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("chatmessage")));
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, Main.getInstance().getConfig().getInt("chatmessage-time-interval") * 20L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            Main.notVerifedPlayers.add(player);

            World world = Bukkit.getWorld(Main.getInstance().getConfig().getString("world"));
            int x = Main.getInstance().getConfig().getInt("x");
            int y = Main.getInstance().getConfig().getInt("y");
            int z = Main.getInstance().getConfig().getInt("z");
            Location checkLocation = new Location(world, x, y, z);

            player.teleport(checkLocation);
            GameMode gamemode = GameMode.valueOf(Main.getInstance().getConfig().getString("uncheked-gm"));
            player.setGameMode(gamemode);
            player.sendTitle(ColorUtils.format(Main.getInstance().getConfig().getString("title")),
                    ColorUtils.format(Main.getInstance().getConfig().getString("subtitle")),
                    Main.getInstance().getConfig().getInt("fadein"),
                    Main.getInstance().getConfig().getInt("stay"),
                    Main.getInstance().getConfig().getInt("fadeout"));

        }
    }

    @EventHandler
    public void onChatSend(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (Main.notVerifedPlayers.contains(player)) {
            if (event.getMessage().contains(Main.getInstance().getConfig().getString("confirm-message"))) {
                Main.notVerifedPlayers.remove(player);
                GameMode gamemode = GameMode.valueOf(Main.getInstance().getConfig().getString("checked-gm"));
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),() -> player.setGameMode(gamemode), 0L);
                player.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("verification-passed")));
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChatCommandSend(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (Main.notVerifedPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }
}
