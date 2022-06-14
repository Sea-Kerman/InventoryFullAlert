package net.seakerman.inventoryfullalert.hud;

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
import net.seakerman.inventoryfullalert.config.Config;



@Environment(EnvType.CLIENT)
public class InventoryAlertHud
{
    private final MinecraftClient client;
    private final TextRenderer fontRenderer;
    private ClientPlayerEntity player;
    private MatrixStack matrixStack;
    private final ItemRenderer itemRenderer;

    public InventoryAlertHud(MinecraftClient client)
    {
        this.client = client;
        this.fontRenderer = client.textRenderer;
        this.itemRenderer = client.getItemRenderer();
    }

    public void draw(MatrixStack matrixStack)
    {
        this.player = this.client.player;

        this.matrixStack = matrixStack;

        RenderSystem.enableBlend();

        this.drawInfo();

        this.client.getProfiler().pop();
    }

    private void drawInfo()
    {
        int x = 50;
        int y = 50;
        int lowThreshold = 33;
        int highThreshold = 36;

        int fillAmount = getInventoryFillStatus();

        String outputString = generateStatusBarString(fillAmount,80);

        //get correct bar color
        int color = Config.color1;
        if (fillAmount >= highThreshold)
        {
            color = Colors.lightRed;
        }
        else if (fillAmount >= lowThreshold)
        {
            color = Colors.lightYellow;
        }

        this.fontRenderer.draw(this.matrixStack,outputString,x,y,color);
    }
    private int getInventoryFillStatus()
    {
        int fullslots = 0;
        PlayerInventory inventory = this.player.getInventory();
        for (int i = 0; i < 36; i++) {
            if (!inventory.getStack(i).equals(ItemStack.EMPTY))
            {
                fullslots ++;
            }
        }
        return fullslots;
    }
    private String generateStatusBarString(int fillAmount, int width)
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
