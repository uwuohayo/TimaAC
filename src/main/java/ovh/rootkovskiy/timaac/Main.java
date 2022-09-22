package ovh.rootkovskiy.timaac;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public final List<Player> notVerifiedPlayers = new ArrayList<>();

    public final EventsListener listener = new EventsListener();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        Bukkit.getPluginManager().registerEvents(listener, this);
        Bukkit.getPluginCommand("timaac").setExecutor(new TimaACCommand());
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "TimaAC "+getDescription().getVersion()+" Loaded and Enabled!" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "MC Version: "+ getServer().getBukkitVersion() + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Author: Timur Rootkovskiy (Adminov)" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "VK: @timurroot" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
    }

    @Override
    public void onDisable() {
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "TimaAC "+getDescription().getVersion()+" Disabled!" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "MC Version: "+ getServer().getBukkitVersion() + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "Author: Timur Rootkovskiy (Adminov)" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_CYAN + "VK: @timurroot" + ConsoleUtils.ANSI_RESET);
        System.out.println(ConsoleUtils.ANSI_GREEN + "#-#-#-#-#-#-#-#-#" + ConsoleUtils.ANSI_RESET);
    }

}
