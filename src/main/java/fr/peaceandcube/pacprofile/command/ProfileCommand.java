package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.lang.TranslationManager;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ProfileCommand implements TabExecutor {

    private static final String PERM_PROFILE = "pacprofile.profile";
    private static final String PERM_PROFILE_OTHERS = "pacprofile.profile.others";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player viewer) {
            if (!sender.hasPermission(PERM_PROFILE)) {
                return false;
            }

            Player player = viewer;
            Optional<Module> optionalModule = Optional.empty();

            // Get player arg
            if (args.length == 2) {
                if (!sender.hasPermission(PERM_PROFILE_OTHERS)) {
                    return false;
                }

                player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage(Component.text(
                            TranslationManager.translate("command_profile_player_not_found"),
                            NamedTextColor.RED
                    ));
                    return true;
                }
            }

            // Get module arg
            if (args.length >= 1 && !args[0].equals("main")) {
                optionalModule = PACProfile.getInstance().getModules().stream()
                        .filter(module -> module.isOpenable() && module.isEnabled() && module.name().equals(args[0]))
                        .findFirst();
                if (optionalModule.isEmpty()) {
                    sender.sendMessage(Component.text(
                            TranslationManager.translate("command_profile_module_not_found"),
                            NamedTextColor.RED
                    ));
                    return true;
                }
            }

            // Perform action
            if (optionalModule.isEmpty()) {
                ProfileGui gui = new ProfileGui(viewer, player);
                gui.open();

                if (viewer.getName().equals(player.getName())) {
                    Logger.debug("%s opened its profile".formatted(viewer.getName()));
                } else {
                    Logger.debug("%s opened %s's profile".formatted(viewer.getName(), player.getName()));
                }

                // Give first time advancement
                if (!PACProfile.getInstance().config.getFirstTimeAdvancementName().isEmpty()) {
                    NamespacedKey key = NamespacedKey.fromString(PACProfile.getInstance().config.getFirstTimeAdvancementName());
                    Advancement advancement = Bukkit.getAdvancement(key);
                    if (advancement != null && !viewer.getAdvancementProgress(advancement).isDone()) {
                        viewer.getAdvancementProgress(advancement).getRemainingCriteria().forEach(criterion ->
                                viewer.getAdvancementProgress(advancement).awardCriteria(criterion));
                    }
                }
            } else {
                Module module = optionalModule.get();
                module.guiItem(player).getLeftClickAction().accept(new CommandGuiContext(viewer, player));

                if (viewer.getName().equals(player.getName())) {
                    Logger.debug("%s opened module %s of its profile".formatted(viewer.getName(), module.name()));
                } else {
                    Logger.debug("%s opened module %s of %s's profile".formatted(viewer.getName(), module.name(), player.getName()));
                }
            }
        } else {
            sender.sendMessage(Component.text(
                    TranslationManager.translate("command_profile_sender_not_player"),
                    NamedTextColor.RED
            ));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return SuggestionProviders.getModules(args[0]);
        }

        if (args.length == 2 && sender.hasPermission(PERM_PROFILE_OTHERS)) {
            return SuggestionProviders.getOnlinePlayers(args[1]);
        }

        return List.of();
    }
}
