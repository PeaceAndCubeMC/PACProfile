package fr.peaceandcube.pacprofile;

import com.earth2me.essentials.Essentials;
import fr.peaceandcube.pacprofile.command.ProfileCommand;
import fr.peaceandcube.pacprofile.command.ReloadCommand;
import fr.peaceandcube.pacprofile.file.ConfigFile;
import fr.peaceandcube.pacprofile.file.LangFile;
import fr.peaceandcube.pacprofile.file.PlayerDataFile;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class PACProfile extends JavaPlugin {
    private static PACProfile instance;
    private static LuckPerms luckPerms;
    private static Essentials essentials;
    private static GriefPrevention griefPrevention;

    public ConfigFile config;
    public LangFile lang;
    public PlayerDataFile playerData;

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
    }

    public static void reload() {
        PACProfile.getInstance().config.reload();
        PACProfile.getInstance().lang.reload();
        PACProfile.getInstance().playerData.reload();
        Messages.init();
        NameComponents.init();
        LoreComponents.init();
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
}
