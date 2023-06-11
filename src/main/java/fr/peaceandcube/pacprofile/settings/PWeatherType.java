package fr.peaceandcube.pacprofile.settings;

import org.bukkit.WeatherType;

/**
 * Represents the different weather types that can be set through the /pweather command.
 */
public enum PWeatherType {
    SUN(WeatherType.CLEAR),
    STORM(WeatherType.DOWNFALL);

    private final WeatherType weatherType;

    PWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public WeatherType getWeatherType() {
        return this.weatherType;
    }

    public PWeatherType next() {
        return this.ordinal() < PWeatherType.values().length - 1 ? PWeatherType.values()[this.ordinal() + 1] : PWeatherType.values()[0];
    }

    public static PWeatherType fromWeatherType(WeatherType weatherType) {
        for (PWeatherType pWeatherType : PWeatherType.values()) {
            if (pWeatherType.getWeatherType().equals(weatherType)) {
                return pWeatherType;
            }
        }
        return SUN;
    }
}
