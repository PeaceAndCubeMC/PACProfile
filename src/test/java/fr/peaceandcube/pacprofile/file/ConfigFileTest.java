package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.statistic.Statistics;
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
            homes:
              default_color: red
              enable_teleportation: true
              enable_deletion: true
            online_players:
              enable_teleportation: true
            # Toggles specific statistics
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
    void testCreateConfigFile() {
        PluginMock mockPlugin = MockBukkit.createMockPlugin();
        ConfigFile configFile = new ConfigFile("config.yml", mockPlugin);

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
}
