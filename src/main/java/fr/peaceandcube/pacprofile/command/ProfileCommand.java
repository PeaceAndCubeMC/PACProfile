package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacpi.player.PlayerSuggestionProviders;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ProfileCommand implements CommandExecutor, TabExecutor {
    private static final String PERM_PROFILE = "pacprofile.profile";
    private static final String PERM_PROFILE_OTHERS = "pacprofile.profile.others";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player viewer) {
            if (args.length == 0) {
                if (sender.hasPermission(PERM_PROFILE)) {
                    ProfileGui gui = new ProfileGui(viewer, viewer);
                    gui.open();
                    return true;
                }
            } else if (args.length == 1) {
                if (sender.hasPermission(PERM_PROFILE_OTHERS)) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null) {
                        ProfileGui gui = new ProfileGui(viewer, player);
                        gui.open();
                    } else {
                        sender.sendMessage(Messages.PLAYER_NOT_FOUND);
                    }
                    return true;
                }
            }
        } else {
            sender.sendMessage(Messages.SENDER_NOT_PLAYER);
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && sender.hasPermission(PERM_PROFILE_OTHERS)) {
            return PlayerSuggestionProviders.getOnlinePlayers(args[0]);
        }

        return new ArrayList<>();
    }
}
