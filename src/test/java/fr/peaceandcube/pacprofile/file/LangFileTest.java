package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.identity.IdentityModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.plugin.PluginMock;

import java.util.List;

public class LangFileTest {

    private static final String FILE_CONTENT = """
            command_profile_sender_not_player: Command sender must be a player!
            command_profile_player_not_found: Target player was not found.
            command_reload_success: PACProfile was successfully reloaded
            not_defined: Not defined
            invalid: Invalid
            confirmation_title: Confirmation
            confirmation_yes: Confirm
            confirmation_no: Cancel
            profile: Profile of %s
            exit: Exit
            page_previous: Previous Page
            page_next: Next Page
            color_white: White
            color_light_gray: Light Gray
            color_gray: Gray
            color_black: Black
            color_brown: Brown
            color_red: Red
            color_orange: Orange
            color_yellow: Yellow
            color_lime: Lime
            color_green: Green
            color_cyan: Cyan
            color_light_blue: Light Blue
            color_blue: Blue
            color_purple: Purple
            color_magenta: Magenta
            color_pink: Pink
            order_by: 'Order by: '
            order_default: Default
            order_name_az: Name (A-Z)
            order_name_za: Name (Z-A)
            order_area_asc: Area (Ascending)
            order_area_desc: Area (Descending)
            order_category_az: Category (A-Z)
            order_category_za: Category (Z-A)
            order_color: Color
            order_click: ⇒ Click to toggle order
            profile_rank: 'Rank: '
            profile_rank_expiration: 'Expiration: '
            profile_nickname: 'Nickname: '
            profile_birthday: 'Birthday: '
            profile_join_date: 'Joined on: '
            """;

    @BeforeEach
    void runBefore() {
        MockBukkit.mock();
    }

    @AfterEach
    void runAfter() {
        MockBukkit.unmock();
    }

    @Test
    void testCreateLangFile() {
        List<Module> modules = List.of(new IdentityModule());
        PluginMock mockPlugin = MockBukkit.createMockPlugin();

        LangFile langFile = new LangFile("config.yml", mockPlugin, modules);

        Assertions.assertTrue(langFile.file.exists());
        Assertions.assertEquals(FILE_CONTENT, langFile.config.saveToString());
        Assertions.assertEquals("Nickname: ", langFile.translate("profile_nickname"));
        Assertions.assertEquals("Birthday: ", langFile.translate("profile_birthday"));
    }

    @Test
    void testUpdateLangFile() {
        List<Module> modules = List.of(new IdentityModule());
        PluginMock mockPlugin = MockBukkit.createMockPlugin();
        mockPlugin.getConfig().set("profile_rank", "");
        mockPlugin.getConfig().set("profile_nickname", "Username: ");
        mockPlugin.saveConfig();

        LangFile langFile = new LangFile("config.yml", mockPlugin, modules);

        Assertions.assertTrue(langFile.file.exists());
        Assertions.assertEquals("Rank: ", langFile.translate("profile_rank"));
        Assertions.assertEquals("Username: ", langFile.translate("profile_nickname"));
        Assertions.assertEquals("Birthday: ", langFile.translate("profile_birthday"));
    }
}
