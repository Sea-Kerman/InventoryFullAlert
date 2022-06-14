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
        int lowThreshold = inventoryAlertConfigData.lowThreshold;
        int midThreshold = inventoryAlertConfigData.midThreshold;
        int highThreshold = inventoryAlertConfigData.highThreshold;

        int fillAmount = getInventoryFillStatus();

        String outputString = generateStatusBarString(fillAmount,80);

        //get correct bar color
        int color = inventoryAlertConfigData.color1;
        if (fillAmount >= highThreshold)
        {
            color = inventoryAlertConfigData.color4;
        }
        else if (fillAmount >= midThreshold)
        {
            color = inventoryAlertConfigData.color3;
        }
        else if (fillAmount >= lowThreshold)
        {
            color = inventoryAlertConfigData.color2;
        }
        TextRenderer fontRenderer = MinecraftClient.getInstance().textRenderer;
        if (fontRenderer != null)
            fontRenderer.draw(matrixStack,outputString,x,y,color);
    }
    private static int getInventoryFillStatus()
    {
        int fullslots = 0;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            PlayerInventory inventory = player.getInventory();
            if (inventory != null) {
                for (int i = 0; i < 36; i++) {
                    if (inventory.getStack(i).isEmpty()) {
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