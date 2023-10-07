package net.seakerman.inventoryfullalert.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.seakerman.inventoryfullalert.InventoryFullAlert;
import net.seakerman.inventoryfullalert.hud.InventoryAlertHud;

@Environment(EnvType.CLIENT)
@Mixin(value = InGameHud.class)
public abstract class GameInfoMixin
{
	@Shadow
	@Final
	private MinecraftClient client;

	@Shadow public abstract DebugHud getDebugHud();

	@Inject(method = "render", at = @At("HEAD"))
	private void onDraw(DrawContext context, float tickDelta, CallbackInfo ci) {
		if (!this.getDebugHud().shouldShowDebugHud() && InventoryFullAlert.config != null) {
			if(InventoryFullAlert.config.onOff){
				// Draw Game info on every GameHud render
				InventoryAlertHud.draw(context);
			}
		}
	}
}