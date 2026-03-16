package fr.peaceandcube.pacprofile.config;

public class ConfigOption {

    private final Type type;
    private final Object value;

    private ConfigOption(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type type() {
        return type;
    }

    public Object value() {
        return value;
    }

    public static ConfigOption bool(boolean value) {
        return new ConfigOption(Type.BOOLEAN, value);
    }

    public static ConfigOption string(String value) {
        return new ConfigOption(Type.STRING, value);
    }

    public enum Type {
        BOOLEAN,
        STRING
    }
}
