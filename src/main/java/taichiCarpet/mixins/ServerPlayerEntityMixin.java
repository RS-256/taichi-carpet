package taichiCarpet.mixins;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.utils.DiscordWebhook;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource damageSource, CallbackInfo ci) throws IOException {
        if (Objects.equals(TaichiCarpetSettings.deathNoticeDiscord, "#None")) return;
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;
        String deathMessage = damageSource.getDeathMessage(playerEntity).getString();
        String AvaterUri = "https://cravatar.eu/avatar/"+playerEntity.getUuidAsString();

        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject()
            .setAuthor(deathMessage, AvaterUri)
            .setColor(Color.RED);

        DiscordWebhook discordWebhook = new DiscordWebhook(TaichiCarpetSettings.deathNoticeDiscord);

        if (!Objects.equals(TaichiCarpetSettings.serverName, "#None")) {
            discordWebhook.setUsername(TaichiCarpetSettings.serverName);
        }

        if (Objects.equals(TaichiCarpetSettings.deathNoticeDiscordType, "text")) {
            discordWebhook.setContent(deathMessage);
        } else if (Objects.equals(TaichiCarpetSettings.deathNoticeDiscordType, "embed")) {
            discordWebhook.addEmbed(embed);
        }

        discordWebhook.execute();
    }
}
