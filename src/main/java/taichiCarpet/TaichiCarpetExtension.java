package taichiCarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TaichiCarpetExtension implements CarpetExtension, ModInitializer {
    public static void noop() { }
    static
    {
        CarpetServer.manageExtension(new TaichiCarpetExtension());
    }

    public static void loadExtension()
    {
        CarpetServer.manageExtension(new TaichiCarpetExtension());
    }

    @Override
    public void onGameStarted()
    {
        System.out.println("initializing taichi-carpet");
        CarpetServer.settingsManager.parseSettingsClass(TaichiCarpetSettings.class);;
    }

    @Override
    public void onServerLoaded(MinecraftServer server)
    {
    }

    @Override
    public void onTick(MinecraftServer server)
    {
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, final CommandRegistryAccess commandBuildContext)
    {
    }

    @Override
    public SettingsManager extensionSettingsManager()
    {
        return null;
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player)
    {
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayerEntity player)
    {
    }

    @Override
    public void onInitialize() {
        System.out.println("Initializing taichi Carpet Extension");
        TaichiCarpetExtension.loadExtension();
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        InputStream langFile = TaichiCarpetExtension.class.getClassLoader().getResourceAsStream("assets/taichiCarpet/lang/%s.json".formatted(lang));
        if (langFile == null) {
            return Map.of();
        }
        String jsonData;
        try {
            jsonData = IOUtils.toString(langFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Map.of();
        }
        return new Gson().fromJson(jsonData, new TypeToken<Map<String, String>>() {}.getType());
    }
}
