package fr.peaceandcube.pacprofile.settings;

/**
 * Represents the different time types that can be set through the /ptime command.
 */
public enum PTimeType {
    SUNRISE(23000, 23999),
    DAY(0, 999),
    MORNING(1000, 5999),
    NOON(6000, 8999),
    AFTERNOON(9000, 11999),
    SUNSET(12000, 13999),
    NIGHT(14000, 17999),
    MIDNIGHT(18000, 22999);

    private final long minTicks;
    private final long maxTicks;

    PTimeType(long minTicks, long maxTicks) {
        this.minTicks = minTicks;
        this.maxTicks = maxTicks;
    }

    public long getMinTicks() {
        return this.minTicks;
    }

    public long getMaxTicks() {
        return this.maxTicks;
    }

    public PTimeType next() {
        return this.ordinal() < PTimeType.values().length - 1 ? PTimeType.values()[this.ordinal() + 1] : PTimeType.values()[0];
    }

    public static PTimeType fromTicks(long ticks) {
        for (PTimeType pTimeType : PTimeType.values()) {
            if (ticks >= pTimeType.getMinTicks() && ticks < pTimeType.getMaxTicks()) {
                return pTimeType;
            }
        }
        return null;
    }
}
