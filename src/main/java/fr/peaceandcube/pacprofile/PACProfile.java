package fr.peaceandcube.pacprofile;

import com.earth2me.essentials.Essentials;
import fr.peaceandcube.pacprofile.command.ProfileCommand;
import fr.peaceandcube.pacprofile.command.ReloadCommand;
import fr.peaceandcube.pacprofile.file.ConfigFile;
import fr.peaceandcube.pacprofile.file.LangFile;
import fr.peaceandcube.pacprofile.file.PlayerDataFile;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.claims.ClaimsModule;
import fr.peaceandcube.pacprofile.module.coins.CoinsModule;
import fr.peaceandcube.pacprofile.module.dynmap.DynmapModule;
import fr.peaceandcube.pacprofile.module.headtickets.HeadTicketsModule;
import fr.peaceandcube.pacprofile.module.homes.HomesModule;
import fr.peaceandcube.pacprofile.module.links.LinksModule;
import fr.peaceandcube.pacprofile.module.mails.MailsModule;
import fr.peaceandcube.pacprofile.module.onlineplayers.OnlinePlayersModule;
import fr.peaceandcube.pacprofile.module.rules.RulesModule;
import fr.peaceandcube.pacprofile.module.settings.SettingsModule;
import fr.peaceandcube.pacprofile.module.statistics.StatisticsModule;
import fr.peaceandcube.pacprofile.module.warps.WarpsModule;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PACProfile extends JavaPlugin {
    private static PACProfile instance;
    private static LuckPerms luckPerms;
    private static Essentials essentials;
    private static GriefPrevention griefPrevention;

    public ConfigFile config;
    public LangFile lang;
    public PlayerDataFile playerData;

    private final List<Module> modules = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        luckPerms = LuckPermsProvider.get();
        essentials = Essentials.getPlugin(Essentials.class);
        griefPrevention = GriefPrevention.getPlugin(GriefPrevention.class);

        this.getCommand("reload").setExecutor(new ReloadCommand());
        this.getCommand("profile").setExecutor(new ProfileCommand());

        config = new ConfigFile("config.yml", this);
        lang = new LangFile("lang.yml", this);
        playerData = new PlayerDataFile("playerdata.yml", this);

        registerModules();
    }

    private void registerModules() {
        modules.add(new StatisticsModule());
        modules.add(new SettingsModule());
        modules.add(new CoinsModule());
        modules.add(new HeadTicketsModule());
        modules.add(new MailsModule());
        modules.add(new HomesModule());
        modules.add(new ClaimsModule());
        modules.add(new OnlinePlayersModule());
        modules.add(new WarpsModule());
        modules.add(new RulesModule());
        modules.add(new LinksModule());
        modules.add(new DynmapModule());
        Logger.info("Registered modules: %s".formatted(modules.stream().map(Module::name).toList()));
    }

    public static void reload() {
        PACProfile.getInstance().config.reload();
        PACProfile.getInstance().lang.reload();
        PACProfile.getInstance().playerData.reload();
        Messages.init();
        LoreComponents.init();
        Logger.info("All data has been reloaded");
    }

    public static PACProfile getInstance() {
        return instance;
    }

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public static Essentials getEssentials() {
        return essentials;
    }

    public static GriefPrevention getGriefPrevention() {
        return griefPrevention;
    }

    public List<Module> getModules() {
        return modules;
    }
}
