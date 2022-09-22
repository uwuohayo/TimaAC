package ovh.rootkovskiy.timaac;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class TimaACCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("timaac.selector"))) {
            sender.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("command-no-perms")));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("command-usage")));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on":
                Bukkit.getPluginManager().registerEvents(Main.getInstance().listener, Main.getInstance());
                sender.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("command-on")));
                return true;
            case "off":
                HandlerList.unregisterAll(Main.getInstance().listener);
                Main.getInstance().notVerifiedPlayers.clear();
                sender.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("command-off")));
                return true;
            default:
                sender.sendMessage(ColorUtils.format(Main.getInstance().getConfig().getString("command-usage")));
                return true;
        }
    }
}
