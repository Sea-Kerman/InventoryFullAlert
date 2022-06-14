package net.seakerman.inventoryfullalert.config;

import net.seakerman.inventoryfullalert.hud.InventoryAlertHud;
import net.seakerman.inventoryfullalert.InventoryFullAlert;

import java.awt.*;

public class Config
{
    public int x = 50;
    public int y = 50;

    public int crosshairx = 100;
    public int crosshairy = 100;

    public int barWidth = 108;

    public boolean crosshairWarning = true;

    public int lowThreshold = 27;
    public int midThreshold = 33;
    public int highThreshold = 36;

    public int color1_red = 0;
    public int color1_green = 200;
    public int color1_blue = 50;
    public int color1_alpha = 255;

    public int color2_red = 220;
    public int color2_green = 200;
    public int color2_blue = 0;
    public int color2_alpha = 255;

    public int color3_red = 220;
    public int color3_green = 150;
    public int color3_blue = 0;
    public int color3_alpha = 255;

    public int color4_red = 220;
    public int color4_green = 0;
    public int color4_blue = 0;
    public int color4_alpha = 255;
}