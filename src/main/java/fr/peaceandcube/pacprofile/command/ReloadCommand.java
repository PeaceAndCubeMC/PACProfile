package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReloadCommand implements CommandExecutor, TabExecutor {
    private static final String PERM_RELOAD = "pacprofile.reload";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(PERM_RELOAD)) {
            PACProfile.reload();
            sender.sendMessage(Messages.RELOAD_SUCCESS);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return List.of();
    }
}
