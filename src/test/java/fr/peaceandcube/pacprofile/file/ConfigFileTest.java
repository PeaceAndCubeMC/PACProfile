package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.claims.ClaimsModule;
import fr.peaceandcube.pacprofile.module.coins.CoinsModule;
import fr.peaceandcube.pacprofile.module.dynmap.DynmapModule;
import fr.peaceandcube.pacprofile.module.headtickets.HeadTicketsModule;
import fr.peaceandcube.pacprofile.module.homes.HomesModule;
import fr.peaceandcube.pacprofile.module.identity.IdentityModule;
import fr.peaceandcube.pacprofile.module.links.LinksModule;
import fr.peaceandcube.pacprofile.module.mails.MailsModule;
import fr.peaceandcube.pacprofile.module.onlineplayers.OnlinePlayersModule;
import fr.peaceandcube.pacprofile.module.rules.RulesModule;
import fr.peaceandcube.pacprofile.module.settings.SettingsModule;
import fr.peaceandcube.pacprofile.module.statistics.StatisticsModule;
import fr.peaceandcube.pacprofile.module.statistics.data.Statistics;
import fr.peaceandcube.pacprofile.module.warps.WarpsModule;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.plugin.PluginMock;

import java.util.List;

public class ConfigFileTest {

    private static final String CONFIG_CONTENT = """
            debug_logging: false
            date_format: yyyy-MM-dd HH:mm:ss
            first_time_advancement_name: ''
            head_tickets_scoreboard: ''
            commands_on_click:
              coins: ''
              head_tickets: ''
              mails: mail read
              rules: ''
              links: ''
              dynmap: ''
            warps:
              spawn:
                name: Spawn
                icon: grass_block
                category: Spawn
            statistics:
              health:
                enabled: true
              max_health:
                enabled: true
              oxygen_bonus:
                enabled: true
              armor:
                enabled: true
              armor_toughness:
                enabled: true
              knockback_resistance:
                enabled: true
              speed:
                enabled: true
              sneaking_speed:
                enabled: true
              attack_damage:
                enabled: true
              attack_speed:
                enabled: true
              mining_efficiency:
                enabled: true
              luck:
                enabled: true
            head_tickets:
              quests_scoreboard: ''
            homes:
              default_color: red
              enable_teleportation: true
              enable_deletion: true
            online_players:
              enable_teleportation: true
            """;

    private List<Module> modules;

    @BeforeEach
    void runBefore() {
        MockBukkit.mock();
        modules = List.of(
                new IdentityModule(), new StatisticsModule(), new SettingsModule(), new CoinsModule(),
                new HeadTicketsModule(), new MailsModule(), new HomesModule(), new ClaimsModule(),
                new OnlinePlayersModule(), new WarpsModule(), new RulesModule(), new LinksModule(),
                new DynmapModule()
        );
    }

    @AfterEach
    void runAfter() {
        MockBukkit.unmock();
    }

    @Test
    void testCreateConfigFile() {
        PluginMock mockPlugin = MockBukkit.createMockPlugin();

        ConfigFile configFile = new ConfigFile("config.yml", mockPlugin, modules);

        Assertions.assertTrue(configFile.file.exists());
        Assertions.assertEquals(CONFIG_CONTENT, configFile.config.saveToString());
        Assertions.assertEquals("yyyy-MM-dd HH:mm:ss", configFile.getDateFormat());
        Assertions.assertEquals("", configFile.getFirstTimeAdvancementName());
        Assertions.assertEquals("", configFile.getHeadTicketsScoreboard());
        Assertions.assertEquals("", configFile.getCommandOnClickCoins());
        Assertions.assertEquals("", configFile.getCommandOnClickHeadTickets());
        Assertions.assertEquals("mail read", configFile.getCommandOnClickMails());
        Assertions.assertEquals("", configFile.getCommandOnClickRules());
        Assertions.assertEquals("", configFile.getCommandOnClickLinks());
        Assertions.assertEquals("", configFile.getCommandOnClickDynmap());
        Assertions.assertEquals(List.of(new WarpEntry("spawn", "Spawn", Material.GRASS_BLOCK, "Spawn")), configFile.getWarps());
        Assertions.assertEquals("red", configFile.getDefaultHomeColor());
        Assertions.assertTrue(configFile.isHomeTeleportationEnabled());
        Assertions.assertTrue(configFile.isHomeDeletionEnabled());
        Assertions.assertTrue(configFile.isOnlinePlayerTeleportationEnabled());
        Statistics.ALL.forEach(statistic -> Assertions.assertTrue(configFile.isStatisticEnabled(statistic.getName())));
    }

    @Test
    void testUpdateConfigFileWithInvalidValues() {
        PluginMock mockPlugin = MockBukkit.createMockPlugin();
        mockPlugin.getConfig().set("debug_logging", "not a boolean");
        mockPlugin.getConfig().createSection("homes").set("default_color", false);
        mockPlugin.saveConfig();

        ConfigFile configFile = new ConfigFile("config.yml", mockPlugin, modules);

        Assertions.assertTrue(configFile.file.exists());
        Assertions.assertFalse(configFile.hasDebugLogging());
        Assertions.assertEquals("red", configFile.getDefaultHomeColor());
    }

    @Test
    void testUpdateConfigFileWithValidValues() {
        PluginMock mockPlugin = MockBukkit.createMockPlugin();
        mockPlugin.getConfig().createSection("homes").set("default_color", "yellow");
        mockPlugin.getConfig().createSection("commands_on_click").set("rules", "rules");
        mockPlugin.saveConfig();

        ConfigFile configFile = new ConfigFile("config.yml", mockPlugin, modules);

        Assertions.assertTrue(configFile.file.exists());
        Assertions.assertEquals("yellow", configFile.getDefaultHomeColor());
        Assertions.assertTrue(configFile.isHomeTeleportationEnabled());
        Assertions.assertEquals("rules", configFile.getCommandOnClickRules());
        Assertions.assertEquals("", configFile.getCommandOnClickLinks());
    }
}
