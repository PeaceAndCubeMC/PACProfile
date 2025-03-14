package fr.peaceandcube.pacprofile.order;

import fr.peaceandcube.pacprofile.PACProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum Order {
    DEFAULT("default"),
    NAME_AZ("name_az"),
    NAME_ZA("name_za"),
    AREA_ASC("area_asc"),
    AREA_DESC("area_desc"),
    CATEGORY_AZ("category_az"),
    CATEGORY_ZA("category_za"),
    COLOR("color");

    private final String name;

    Order(String name) {
        this.name = name;
    }

    public Component getText() {
        String text = PACProfile.getInstance().lang.translate("order_" + name);
        return Component.text(text, TextColor.color(0xFFFF55), TextDecoration.BOLD)
                .decoration(TextDecoration.ITALIC, false);
    }
}
