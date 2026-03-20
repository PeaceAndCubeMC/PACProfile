package fr.peaceandcube.pacprofile.util;

import fr.peaceandcube.pacprofile.PACProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class Messages {
    public static TextComponent SENDER_NOT_PLAYER;
    public static TextComponent PLAYER_NOT_FOUND;
    public static TextComponent RELOAD_SUCCESS;

    public static String NOT_DEFINED;
    public static String INVALID;

    public static String CONFIRMATION_TITLE;
    public static String CONFIRMATION_YES;
    public static String CONFIRMATION_NO;

    public static String PROFILE;
    public static String PROFILE_BIRTHDAY;
    public static String EXIT;
    public static String PAGE_PREVIOUS;
    public static String PAGE_NEXT;
    public static String ORDER_BY;
    public static String ORDER_CLICK;

    static {
        init();
    }

    public static void init() {
        SENDER_NOT_PLAYER = error(PACProfile.getInstance().lang.translate("command_profile_sender_not_player"));
        PLAYER_NOT_FOUND = error(PACProfile.getInstance().lang.translate("command_profile_player_not_found"));
        RELOAD_SUCCESS = success(PACProfile.getInstance().lang.translate("command_reload_success"));

        NOT_DEFINED = PACProfile.getInstance().lang.translate("not_defined");
        INVALID = PACProfile.getInstance().lang.translate("invalid");

        CONFIRMATION_TITLE = PACProfile.getInstance().lang.translate("confirmation_title");
        CONFIRMATION_YES = PACProfile.getInstance().lang.translate("confirmation_yes");
        CONFIRMATION_NO = PACProfile.getInstance().lang.translate("confirmation_no");

        PROFILE = PACProfile.getInstance().lang.translate("profile");
        PROFILE_BIRTHDAY = PACProfile.getInstance().lang.translate("profile_birthday");
        EXIT = PACProfile.getInstance().lang.translate("exit");
        PAGE_PREVIOUS = PACProfile.getInstance().lang.translate("page_previous");
        PAGE_NEXT = PACProfile.getInstance().lang.translate("page_next");
        ORDER_BY = PACProfile.getInstance().lang.translate("order_by");
        ORDER_CLICK = PACProfile.getInstance().lang.translate("order_click");
    }

    public static TextComponent error(String msg) {
        return Component.text(msg, NamedTextColor.RED);
    }

    public static TextComponent success(String msg) {
        return Component.text(msg, NamedTextColor.GREEN);
    }
}
