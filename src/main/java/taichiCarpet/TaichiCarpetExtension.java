package taichiCarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import taichiCarpet.commands.*;
import taichiCarpet.logging.*;
import taichiCarpet.network.RegistarPackets;
import taichiCarpet.utils.*;

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

    public static final String MOD_ID = "taichi-carpet";
    public static final String MOD_NAME = "taichiCarpet";
    public static final String MOD_VERSION = "1.2.0-1.20.1";

    public static final Logger LOGGER = LoggerFactory.getLogger("taichi-carpet");
    public static final EnvType ENV = FabricLoader.getInstance().getEnvironmentType();
    public static void noop() { }

    public static void loadExtension()
    {
        CarpetServer.manageExtension(new TaichiCarpetExtension());
    }

    @Override
    public void onGameStarted()
    {
        CarpetServer.settingsManager.parseSettingsClass(TaichiCarpetSettings.class);
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
        viewCommand.register(dispatcher);
        simulationCommand.register(dispatcher);
        hatCommand.register(dispatcher);
        sitCommand.register(dispatcher);
        noticeCommand.register(dispatcher);
    }

    @Override
    public SettingsManager extensionSettingsManager()
    {
        return null;
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player)
    {
        sendMassage.loginMessageNotifier(player);
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayerEntity player)
    {
    }

    @Override
    public void onInitialize() {
        System.out.println("Initializing taichi Carpet Extension");
        TaichiCarpetExtension.loadExtension();
        RegistarPackets.onServer.register();
    }

    @Override
    public void registerLoggers() {
        taichiLoggerRegistry.registerLoggers();
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
