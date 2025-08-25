package com.tonpseudo.attackindicator;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class AttackIndicatorMixin {

    private static final Identifier BAR_TEXTURE =
            new Identifier("attackindicatorinverser", "textures/gui/attack_bar.png");

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void invertAttackIndicator(MatrixStack matrices, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        float attackStrength = client.player.getAttackCooldownProgress(0f);

        int width = 16;
        int filled = Math.round(width * attackStrength);

        int startX = client.getWindow().getScaledWidth() / 2 + 10;
        int startY = client.getWindow().getScaledHeight() - 20;

        client.getTextureManager().bindTexture(BAR_TEXTURE);

        for (int i = 0; i < filled; i++) {
            int x = startX - i;
            client.inGameHud.drawTexture(matrices, x, startY, 0, 0, 1, 2);
        }

        ci.cancel();
    }
}
