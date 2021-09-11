package fr.peaceandcube.pacprofile.util;

import fr.peaceandcube.pacprofile.PACProfile;
import org.bukkit.Material;

public enum Color {
    WHITE("white", Material.WHITE_DYE, Material.WHITE_BED),
    ORANGE("orange", Material.ORANGE_DYE, Material.ORANGE_BED),
    MAGENTA("magenta", Material.MAGENTA_DYE, Material.MAGENTA_BED),
    LIGHT_BLUE("light_blue", Material.LIGHT_BLUE_DYE, Material.LIGHT_BLUE_BED),
    YELLOW("yellow", Material.YELLOW_DYE, Material.YELLOW_BED),
    LIME("lime", Material.LIME_BED, Material.LIME_BED),
    PINK("pink", Material.PINK_DYE, Material.PINK_BED),
    GRAY("gray", Material.GRAY_DYE, Material.GRAY_BED),
    LIGHT_GRAY("light_gray", Material.LIGHT_GRAY_DYE, Material.LIGHT_GRAY_BED),
    CYAN("cyan", Material.CYAN_DYE, Material.CYAN_BED),
    PURPLE("purple", Material.PURPLE_DYE, Material.PURPLE_BED),
    BLUE("blue", Material.BLUE_DYE, Material.BLUE_BED),
    BROWN("brown", Material.BROWN_DYE, Material.BROWN_BED),
    GREEN("green", Material.GREEN_DYE, Material.GREEN_BED),
    RED("red", Material.RED_DYE, Material.RED_BED),
    BLACK("black", Material.BLACK_DYE, Material.BLACK_BED);

    private final String name;
    private final Material dye;
    private final Material bed;

    Color(String name, Material dye, Material bed) {
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
        return PACProfile.getInstance().lang.translate("color_" + this.name);
    }

    public static Color byName(String name) {
        for (Color color : Color.values()) {
            if (color.getName().equals(name)) {
                return color;
            }
        }
        return null;
    }
}
