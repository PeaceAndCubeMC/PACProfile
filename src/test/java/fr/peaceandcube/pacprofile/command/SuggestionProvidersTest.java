package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.claims.ClaimsModule;
import fr.peaceandcube.pacprofile.module.coins.CoinsModule;
import fr.peaceandcube.pacprofile.module.homes.HomesModule;
import fr.peaceandcube.pacprofile.module.settings.SettingsModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

import java.util.List;

public class SuggestionProvidersTest {

    private ServerMock serverMock;

    @BeforeEach
    void runBefore() {
        serverMock = MockBukkit.mock();
    }

    @AfterEach
    void runAfter() {
        MockBukkit.unmock();
    }

    @ParameterizedTest
    @CsvSource({
        "'',3",
        "M,1",
        "t,2",
        "the,1",
        "Radiohead,0"
    })
    void testGetOnlinePlayers(String prefix, int expectedCount) {
        serverMock.addPlayer("Muse");
        serverMock.addPlayer("Tame_Impala");
        serverMock.addPlayer("The_Strokes");

        List<String> result = SuggestionProviders.getOnlinePlayers(prefix);

        Assertions.assertEquals(expectedCount, result.size());
    }

    @ParameterizedTest
    @CsvSource({
        "'',4,'main,settings,homes,claims'",
        "M,1,'main'",
        "c,1,'claims'"
    })
    void testGetModules(String prefix, int expectedCount, String expectedNames) {
        List<Module> modules = List.of(new SettingsModule(), new CoinsModule(), new HomesModule(), new ClaimsModule());

        List<String> result = SuggestionProviders.getModules(modules, prefix);

        Assertions.assertEquals(expectedCount, result.size());
        Assertions.assertEquals(expectedNames, String.join(",", result));
    }
}
