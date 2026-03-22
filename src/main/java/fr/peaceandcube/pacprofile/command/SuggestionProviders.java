package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.module.Module;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SuggestionProviders {

    public static List<String> getOnlinePlayers(String prefix) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(startsWith(prefix))
                .toList();
    }

    public static List<String> getModules(String prefix) {
        List<Module> modules = PACProfile.getInstance().getModules();
        List<String> names = new ArrayList<>();
        names.add("main");
        names.addAll(modules.stream()
                .filter(module -> module.isOpenable() && module.isEnabled())
                .map(Module::name)
                .toList()
        );
        return names.stream()
                .filter(startsWith(prefix))
                .toList();
    }

    private static Predicate<String> startsWith(String prefix) {
        return string -> string.toLowerCase().startsWith(prefix.toLowerCase());
    }
}
