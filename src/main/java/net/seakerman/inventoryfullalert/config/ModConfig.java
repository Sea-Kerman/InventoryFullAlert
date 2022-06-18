package net.seakerman.inventoryfullalert.config;

import me.shedaniel.autoconfig.ConfigData;             // autoconfig is better for settings menu
import me.shedaniel.autoconfig.annotation.Config;      // easy-to-read code and less files
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "inventoryfullalert")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip                    //setting hint that can be easily localized through lang files
    public float barOffsetX = 0.5f;
    @ConfigEntry.Gui.Tooltip
    public float barOffsetY = 0.9f;

    @ConfigEntry.Gui.Tooltip
    public int barWidth = 108;

    @ConfigEntry.Gui.Tooltip
    public boolean crosshairWarning = true;

    @ConfigEntry.Gui.Tooltip
    public int lowThreshold = 27;
    @ConfigEntry.Gui.Tooltip
    public int midThreshold = 33;
    @ConfigEntry.Gui.Tooltip
    public int highThreshold = 36;

    @ConfigEntry.ColorPicker(allowAlpha = true)   //color picker entry type
    public int noThresholdColor = 0xFF00C832;     //#ARGB format
    @ConfigEntry.ColorPicker(allowAlpha = true)
    public int lowThresholdColor = 0xFFDCC800;
    @ConfigEntry.ColorPicker(allowAlpha = true)
    public int midThresholdColor = 0xFFFF8200;
    @ConfigEntry.ColorPicker(allowAlpha = true)
    public int highThresholdColor = 0xFFDC0000;
}