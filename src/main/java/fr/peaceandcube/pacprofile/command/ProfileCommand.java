package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.lang.TranslationManager;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.util.SuggestionProviders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProfileCommand implements CommandExecutor, TabExecutor {
    private static final String PERM_PROFILE = "pacprofile.profile";
    private static final String PERM_PROFILE_OTHERS = "pacprofile.profile.others";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player viewer) {
            if (args.length == 0) {
                if (sender.hasPermission(PERM_PROFILE)) {
                    ProfileGui gui = new ProfileGui(viewer, viewer);
                    gui.open();
                    Logger.debug("%s opened its profile".formatted(viewer.getName()));

                    // Give first time advancement
                    if (!PACProfile.getInstance().config.getFirstTimeAdvancementName().equals("")) {
                        NamespacedKey key = NamespacedKey.fromString(PACProfile.getInstance().config.getFirstTimeAdvancementName());
                        Advancement advancement = Bukkit.getAdvancement(key);
                        if (advancement != null && !viewer.getAdvancementProgress(advancement).isDone()) {
                            viewer.getAdvancementProgress(advancement).getRemainingCriteria().forEach(criterion -> viewer.getAdvancementProgress(advancement).awardCriteria(criterion));
                        }
                    }

                    return true;
                }
            } else if (args.length == 1) {
                if (sender.hasPermission(PERM_PROFILE_OTHERS)) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null) {
                        ProfileGui gui = new ProfileGui(viewer, player);
                        gui.open();
                        Logger.debug("%s opened %s's profile".formatted(viewer.getName(), player.getName()));
                    } else {
                        sender.sendMessage(Component.text(
                                TranslationManager.translate("command_profile_player_not_found"),
                                NamedTextColor.RED)
                        );
                    }
                    return true;
                }
            }
        } else {
            sender.sendMessage(Component.text(
                    TranslationManager.translate("command_profile_sender_not_player"),
                    NamedTextColor.RED)
            );
            return true;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1 && sender.hasPermission(PERM_PROFILE_OTHERS)) {
            return SuggestionProviders.getOnlinePlayers(args[0]);
        }

        return List.of();
    }
}
