package fr.peaceandcube.pacprofile.logging;

import fr.peaceandcube.pacprofile.PACProfile;

public class Logger {

    public static void info(String message) {
        PACProfile.getInstance().getLogger().info(message);
    }

    public static void debug(String message) {
        if (PACProfile.getInstance().config.hasDebugLogging()) {
            PACProfile.getInstance().getLogger().info(message);
        }
    }
}
