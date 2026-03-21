package fr.peaceandcube.pacprofile.lang;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.module.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationManager {

    private static final Map<String, String> CACHED_TRANSLATIONS = new HashMap<>();

    public static void init(List<Module> modules) {
        CACHED_TRANSLATIONS.clear();

        GlobalTranslations.defaultTranslations().keySet().forEach(TranslationManager::add);
        for (Module module : modules) {
            module.defaultTranslations().keySet().forEach(TranslationManager::add);
        }
    }

    private static void add(String key) {
        CACHED_TRANSLATIONS.put(key, PACProfile.getInstance().lang.translate(key));
    }

    public static String translate(String key) {
        return CACHED_TRANSLATIONS.getOrDefault(key, key);
    }
}
