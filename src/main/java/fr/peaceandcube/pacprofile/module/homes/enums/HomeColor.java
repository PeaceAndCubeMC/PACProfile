package fr.peaceandcube.pacprofile.module.homes.enums;

import fr.peaceandcube.pacprofile.lang.TranslationManager;
import org.bukkit.Material;

public enum HomeColor {
    WHITE("white", Material.WHITE_DYE, Material.WHITE_BED),
    LIGHT_GRAY("light_gray", Material.LIGHT_GRAY_DYE, Material.LIGHT_GRAY_BED),
    GRAY("gray", Material.GRAY_DYE, Material.GRAY_BED),
    BLACK("black", Material.BLACK_DYE, Material.BLACK_BED),
    BROWN("brown", Material.BROWN_DYE, Material.BROWN_BED),
    RED("red", Material.RED_DYE, Material.RED_BED),
    ORANGE("orange", Material.ORANGE_DYE, Material.ORANGE_BED),
    YELLOW("yellow", Material.YELLOW_DYE, Material.YELLOW_BED),
    LIME("lime", Material.LIME_DYE, Material.LIME_BED),
    GREEN("green", Material.GREEN_DYE, Material.GREEN_BED),
    CYAN("cyan", Material.CYAN_DYE, Material.CYAN_BED),
    LIGHT_BLUE("light_blue", Material.LIGHT_BLUE_DYE, Material.LIGHT_BLUE_BED),
    BLUE("blue", Material.BLUE_DYE, Material.BLUE_BED),
    PURPLE("purple", Material.PURPLE_DYE, Material.PURPLE_BED),
    MAGENTA("magenta", Material.MAGENTA_DYE, Material.MAGENTA_BED),
    PINK("pink", Material.PINK_DYE, Material.PINK_BED);

    private final String name;
    private final Material dye;
    private final Material bed;

    HomeColor(String name, Material dye, Material bed) {
        this.name = name;
        this.dye = dye;
        this.bed = bed;
    }

    public String getName() {
        return this.name;
    }

    public Material getDye() {
        return this.dye;
    }

    public Material getBed() {
        return this.bed;
    }

    public String translate() {
        return TranslationManager.translate("color_" + this.name);
    }

    public static HomeColor byName(String name) {
        for (HomeColor color : HomeColor.values()) {
            if (color.getName().equals(name)) {
                return color;
            }
        }
        return null;
    }
}
