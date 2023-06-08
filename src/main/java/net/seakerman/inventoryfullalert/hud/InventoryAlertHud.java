package net.seakerman.inventoryfullalert.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;

import net.seakerman.inventoryfullalert.InventoryFullAlert;

@Environment(EnvType.CLIENT)
public class InventoryAlertHud
{   
    public static void draw(DrawContext context)
    {
        RenderSystem.enableBlend();
        drawInfo(context);

        MinecraftClient.getInstance().getProfiler().pop();
    }

    private static void drawInfo(DrawContext context)
    {

        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();    // getScaledWidth() and getScaledHeight() return width and height
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();  // with applied scale factor

        float barOffsetX = InventoryFullAlert.config.barOffsetX;
        float barOffsetY = InventoryFullAlert.config.barOffsetY;

        int barWidth = InventoryFullAlert.config.barWidth;

        int barPosX = Math.round(screenWidth * barOffsetX) - barWidth;
        int barPosY = Math.round((screenHeight * barOffsetY) - 3.5f);

        int crosshairX = Math.round(screenWidth * 0.5f);
        int crosshairY = Math.round(screenHeight * 0.5f);

        int lowThreshold = InventoryFullAlert.config.lowThreshold;
        int midThreshold = InventoryFullAlert.config.midThreshold;
        int highThreshold = InventoryFullAlert.config.highThreshold;

        int fillAmount = getInventoryFillStatus();

        String outputString = generateStatusBarString(fillAmount, barWidth);

        //get correct bar color
        int color = InventoryFullAlert.config.noThresholdColor;

        if (fillAmount >= highThreshold)
        {
            color = InventoryFullAlert.config.highThresholdColor;
        }
        else if (fillAmount >= midThreshold)
        {
            color = InventoryFullAlert.config.midThresholdColor;
        }
        else if (fillAmount >= lowThreshold)
        {
            color = InventoryFullAlert.config.lowThresholdColor;
        }

        //render the bar
        TextRenderer fontRenderer = MinecraftClient.getInstance().textRenderer;
        if (fontRenderer != null)
        {
            context.drawText(fontRenderer, outputString, barPosX, barPosY, color, true);

            if (InventoryFullAlert.config.crosshairWarning && fillAmount >= highThreshold) {
                context.drawText(fontRenderer,"!", crosshairX - 8, crosshairY - 4, color, false);
                context.drawText(fontRenderer,"!", crosshairX + 6, crosshairY - 4, color, false);
            }
        }
    }

    private static int getInventoryFillStatus()
    {
        int fullslots = 0;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            PlayerInventory inventory = player.getInventory();
            if (inventory != null) {
                for (int i = 0; i < 36; i++) {
                    if (!inventory.getStack(i).isEmpty()) {
                        fullslots++;
                    }
                }
            }
        }
        return fullslots;
    }

    private static String generateStatusBarString(int fillAmount, int width)
    {
        float barLength = fillAmount / 36f * width;
        float barEmptyLength = (36 - fillAmount) / 36f * width;
        return new String(new char[(int) barLength]).replace("\0", "|") + new String(new char[(int) barEmptyLength]).replace("\0", "."); //simplified output generator
    }
}