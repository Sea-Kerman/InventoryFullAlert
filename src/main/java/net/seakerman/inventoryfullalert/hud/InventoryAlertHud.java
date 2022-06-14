package net.seakerman.inventoryfullalert.hud;

import net.seakerman.inventoryfullalert.config.Config;
import net.seakerman.inventoryfullalert.InventoryFullAlert;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

import java.awt.*;

import static net.seakerman.inventoryfullalert.InventoryFullAlert.inventoryAlertConfigData;

@Environment(EnvType.CLIENT)
public class InventoryAlertHud
{
    //private final MinecraftClient client;
    //private final TextRenderer fontRenderer;

    //private MatrixStack matrixStack;
    //private final ItemRenderer itemRenderer;

    //public InventoryAlertHud()
    //{
    //this.client = MinecraftClient.getInstance();
    //this.fontRenderer = MinecraftClient.getInstance().textRenderer;
    //this.itemRenderer = MinecraftClient.getInstance().getItemRenderer();
    //}

    public static void draw(MatrixStack matrixStack)
    {


        RenderSystem.enableBlend();

        drawInfo(matrixStack);

        MinecraftClient.getInstance().getProfiler().pop();
    }

    private static void drawInfo(MatrixStack matrixStack)
    {
        int x = inventoryAlertConfigData.x;
        int y = inventoryAlertConfigData.y;
        int crosshairx = inventoryAlertConfigData.crosshairx;
        int crosshairy = inventoryAlertConfigData.crosshairy;

        int lowThreshold = inventoryAlertConfigData.lowThreshold;
        int midThreshold = inventoryAlertConfigData.midThreshold;
        int highThreshold = inventoryAlertConfigData.highThreshold;

        int barWidth = inventoryAlertConfigData.barWidth;

        int fillAmount = getInventoryFillStatus();

        String outputString = generateStatusBarString(fillAmount,barWidth);

        //get correct bar color
        int color = getColorFromRGBA(new Color(inventoryAlertConfigData.color1_red,
                                                inventoryAlertConfigData.color1_green,
                                                inventoryAlertConfigData.color1_blue,
                                                inventoryAlertConfigData.color1_alpha));

        if (fillAmount >= highThreshold)
        {
            color = getColorFromRGBA(new Color(inventoryAlertConfigData.color4_red,
                    inventoryAlertConfigData.color4_green,
                    inventoryAlertConfigData.color4_blue,
                    inventoryAlertConfigData.color4_alpha));

        }
        else if (fillAmount >= midThreshold)
        {
            color = getColorFromRGBA(new Color(inventoryAlertConfigData.color3_red,
                    inventoryAlertConfigData.color3_green,
                    inventoryAlertConfigData.color3_blue,
                    inventoryAlertConfigData.color3_alpha));
        }
        else if (fillAmount >= lowThreshold)
        {
            color = getColorFromRGBA(new Color(inventoryAlertConfigData.color2_red,
                    inventoryAlertConfigData.color2_green,
                    inventoryAlertConfigData.color2_blue,
                    inventoryAlertConfigData.color2_alpha));
        }

        //render the bar
        TextRenderer fontRenderer = MinecraftClient.getInstance().textRenderer;
        if (fontRenderer != null)
        {
            fontRenderer.drawWithShadow(matrixStack, outputString, x, y, color);

            if (inventoryAlertConfigData.crosshairWarning && fillAmount >= highThreshold) {
                fontRenderer.drawWithShadow(matrixStack, "|__|", crosshairx, crosshairy, color);
            }
        }
    }

    private static int getColorFromRGBA(Color tempcolor)
    {
        return (tempcolor.getRGB() & 0x00ffffff) | (tempcolor.getAlpha() << 24);
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
        float barLength = (fillAmount/36f) * width;
        float barEmptyLength = (36-fillAmount)/36f * width;
        String output = "";
        for (int i = 0; i < barLength; i++)
        {
            output = output.concat("|");
        }
        for (int i = 0; i < barEmptyLength; i++)
        {
            output = output.concat(".");
        }
        return output;
    }
}