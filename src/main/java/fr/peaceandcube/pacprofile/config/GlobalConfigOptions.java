package fr.peaceandcube.pacprofile.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalConfigOptions {

    public static Map<String, ConfigOption> configOptions() {
        Map<String, ConfigOption> configOptions = new LinkedHashMap<>();
        configOptions.put("debug_logging", ConfigOption.bool(false));
        configOptions.put("date_format", ConfigOption.string("yyyy-MM-dd HH:mm:ss"));
        configOptions.put("first_time_advancement_name", ConfigOption.string(""));
        return configOptions;
    }
}
