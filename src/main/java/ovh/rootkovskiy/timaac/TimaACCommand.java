package ovh.rootkovskiy.timaac;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimaACCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("timaac.selector"))) {
            sender.sendMessage("you can not do this");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("usage: /timaac on/off");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on":
                //todo on all events
                return true;
            case "off":
                //todo: off all events
                return true;
            default:
                sender.sendMessage("usage: /timaac on/off");
                return true;
        }
    }
}
