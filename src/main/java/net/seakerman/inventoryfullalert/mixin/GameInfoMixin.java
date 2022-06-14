package net.seakerman.inventoryfullalert.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.seakerman.inventoryfullalert.hud.InventoryAlertHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.seakerman.inventoryfullalert.InventoryFullAlert.hudInfo;
import static net.seakerman.inventoryfullalert.InventoryFullAlert.inventoryAlertConfigData;

@Environment(EnvType.CLIENT)
@Mixin(value = InGameHud.class)
public abstract class GameInfoMixin
{

	@Shadow
	@Final
	private MinecraftClient client;

	//unneeded
//	@Inject(method = "<init>(Lnet/minecraft/client/MinecraftClient;)V", at = @At(value = "RETURN"))
//	private void onInit(MinecraftClient client, CallbackInfo ci)
//	{
//		// Start Mixin
//		System.out.println("Init Inventory Monitoring Mixin");
//	}
	@Inject(method = "render", at = @At("HEAD"))
	private void onDraw(MatrixStack matrixStack, float esp, CallbackInfo ci) {
		if (!this.client.options.debugEnabled && !(inventoryAlertConfigData == null)) {
			// Draw Game info on every GameHud render
			hudInfo.draw(matrixStack);
		}
	}
}
