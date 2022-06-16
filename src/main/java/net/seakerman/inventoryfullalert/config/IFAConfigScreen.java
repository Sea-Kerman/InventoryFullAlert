package net.seakerman.inventoryfullalert.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.seakerman.inventoryfullalert.InventoryFullAlert.inventoryAlertConfigData;
import static net.seakerman.inventoryfullalert.InventoryFullAlert.saveConfig;

public class IFAConfigScreen
{
    public Screen getConfigScreen(Screen parent, boolean isTransparent)
    {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("Inventory Full Alert"))
                .setSavingRunnable(() -> {
                    saveConfig();
                });

        ConfigCategory category = builder.getOrCreateCategory(Text.of(""));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //Bar X
        category.addEntry(entryBuilder.startIntField(Text.of("Bar X"),inventoryAlertConfigData.x)
                .setDefaultValue(50) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 368 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.x = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Bar Y
        category.addEntry(entryBuilder.startIntField(Text.of("Bar Y"),inventoryAlertConfigData.y)
                .setDefaultValue(50) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 482 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.y = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Crosshair X
        category.addEntry(entryBuilder.startIntField(Text.of("Crosshair X"),inventoryAlertConfigData.crosshairx)
                .setDefaultValue(100) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 472 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.crosshairx = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Crosshair Y
        category.addEntry(entryBuilder.startIntField(Text.of("Crosshair Y"),inventoryAlertConfigData.crosshairy)
                .setDefaultValue(100) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 268 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.crosshairy = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Bar Width
        category.addEntry(entryBuilder.startIntField(Text.of("Bar Width"),inventoryAlertConfigData.barWidth)
                .setDefaultValue(108) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Suggest 108 for 1920x1080 GUI scale 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.barWidth = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Warning On Crosshair
        category.addEntry(entryBuilder.startBooleanToggle(Text.of("Warning On Crosshair"),inventoryAlertConfigData.crosshairWarning)
                .setDefaultValue(true) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Show a bracket around the crosshair when inventory is full")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.crosshairWarning = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Low Threshold
        category.addEntry(entryBuilder.startIntField(Text.of("Low Threshold"),inventoryAlertConfigData.lowThreshold)
                .setDefaultValue(27) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Value at which to switch colors")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.lowThreshold = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Mid Threshold
        category.addEntry(entryBuilder.startIntField(Text.of("Mid Threshold"),inventoryAlertConfigData.midThreshold)
                .setDefaultValue(33) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Value at which to switch colors")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.midThreshold = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //High Threshold
        category.addEntry(entryBuilder.startIntField(Text.of("High Threshold"),inventoryAlertConfigData.highThreshold)
                .setDefaultValue(36) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Value at which to switch colors (and consider a full inventory)")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.highThreshold = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)

        //Color 1
        SubCategoryBuilder color1Settings = entryBuilder.startSubCategory(Text.of("Color 1")).setTooltip(Text.of("The color displayed when the inventory is below the low threshold"));
        color1Settings.add(entryBuilder.startIntField(Text.of("R"),inventoryAlertConfigData.color1_red)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Red Value of Color 1")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color1_red = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color1Settings.add(entryBuilder.startIntField(Text.of("G"),inventoryAlertConfigData.color1_green)
                .setDefaultValue(200) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Green Value of Color 1")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color1_green = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color1Settings.add(entryBuilder.startIntField(Text.of("B"),inventoryAlertConfigData.color1_blue)
                .setDefaultValue(50) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Blue Value of Color 1")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color1_blue = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color1Settings.add(entryBuilder.startIntField(Text.of("A"),inventoryAlertConfigData.color1_alpha)
                .setDefaultValue(255) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Alpha Value of Color 1")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color1_alpha = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        category.addEntry(color1Settings.build());

        //Color 2
        SubCategoryBuilder color2Settings = entryBuilder.startSubCategory(Text.of("Color 2")).setTooltip(Text.of("The color displayed when inventory is above the low threshold but below the mid"));
        color2Settings.add(entryBuilder.startIntField(Text.of("R"),inventoryAlertConfigData.color2_red)
                .setDefaultValue(220) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Red Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color2_red = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color2Settings.add(entryBuilder.startIntField(Text.of("G"),inventoryAlertConfigData.color2_green)
                .setDefaultValue(200) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Green Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color2_green = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color2Settings.add(entryBuilder.startIntField(Text.of("B"),inventoryAlertConfigData.color2_blue)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Blue Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color2_blue = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color2Settings.add(entryBuilder.startIntField(Text.of("A"),inventoryAlertConfigData.color2_alpha)
                .setDefaultValue(255) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Alpha Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color2_alpha = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        category.addEntry(color2Settings.build());

        //Color 3
        SubCategoryBuilder color3Settings = entryBuilder.startSubCategory(Text.of("Color 3")).setTooltip(Text.of("The color displayed when inventory is above the mid threshold but below the high"));
        color3Settings.add(entryBuilder.startIntField(Text.of("R"),inventoryAlertConfigData.color3_red)
                .setDefaultValue(250) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Red Value of Color 3")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color3_red = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color3Settings.add(entryBuilder.startIntField(Text.of("G"),inventoryAlertConfigData.color3_green)
                .setDefaultValue(170) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Green Value of Color 3")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color3_green = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color3Settings.add(entryBuilder.startIntField(Text.of("B"),inventoryAlertConfigData.color3_blue)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Blue Value of Color 3")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color3_blue = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color3Settings.add(entryBuilder.startIntField(Text.of("A"),inventoryAlertConfigData.color3_alpha)
                .setDefaultValue(255) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Alpha Value of Color 3")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color3_alpha = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        category.addEntry(color3Settings.build());

        //Color 4
        SubCategoryBuilder color4Settings = entryBuilder.startSubCategory(Text.of("Color 4")).setTooltip(Text.of("The color displayed when inventory is above the high threshold"));
        color4Settings.add(entryBuilder.startIntField(Text.of("R"),inventoryAlertConfigData.color4_red)
                .setDefaultValue(220) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Red Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color4_red = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color4Settings.add(entryBuilder.startIntField(Text.of("G"),inventoryAlertConfigData.color4_green)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Green Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color4_green = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color4Settings.add(entryBuilder.startIntField(Text.of("B"),inventoryAlertConfigData.color4_blue)
                .setDefaultValue(0) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Blue Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color4_blue = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        color4Settings.add(entryBuilder.startIntField(Text.of("A"),inventoryAlertConfigData.color4_alpha)
                .setDefaultValue(255) // Recommended: Used when user click "Reset"
                .setTooltip(Text.of("Alpha Value of Color 2")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> inventoryAlertConfigData.color4_alpha = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config)
        category.addEntry(color4Settings.build());

        return builder.setTransparentBackground(isTransparent).build();
    }
}
