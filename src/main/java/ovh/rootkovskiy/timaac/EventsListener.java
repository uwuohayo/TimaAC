package ovh.rootkovskiy.timaac;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class EventsListener implements Listener {

    private final List<Player> notVerifedPlayer = new ArrayList<>();

    public EventsListener() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : notVerifedPlayer) {
                    player.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("chatmessage")));
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, Main.getInstance().getConfig().getInt("chatmessage-time-interval") * 20L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            notVerifedPlayer.add(player);

            World world = Bukkit.getWorld(Main.getInstance().getConfig().getString("world"));
            int x = Main.getInstance().getConfig().getInt("x");
            int y = Main.getInstance().getConfig().getInt("y");
            int z = Main.getInstance().getConfig().getInt("z");
            Location checkLocation = new Location(world, x, y, z);

            player.teleport(checkLocation);
            player.setGameMode(GameMode.ADVENTURE);
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

        if (notVerifedPlayer.contains(player)) {
            if (event.getMessage().contains(Main.getInstance().getConfig().getString("confirm-message"))) {
                notVerifedPlayer.remove(player);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(),() -> player.setGameMode(GameMode.SURVIVAL), 0L);
                player.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("verification-passed")));
            } else {
                event.setCancelled(true);
            }
        }
    }
}
