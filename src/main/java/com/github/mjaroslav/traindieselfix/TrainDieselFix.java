package com.github.mjaroslav.traindieselfix;

import blusunrize.immersiveengineering.api.crafting.BottlingMachineRecipe;
import blusunrize.immersiveengineering.api.energy.DieselHandler;
import com.github.mjaroslav.traindieselfix.lib.ModInfo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidStack;
import train.common.api.LiquidManager;
import train.common.library.ItemIDs;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class TrainDieselFix {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        ModInfo.configEnable = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_ENABLE, true).getBoolean();
        ModInfo.configUseDieselInGenerator = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_USE_DIESEL_IN_GENERATOR, true).getBoolean();
        ModInfo.configUseRefinedFuelInGenerator = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_USE_REFINED_FUEL_IN_GENERATOR, true).getBoolean();
        ModInfo.configUseDieselInDrill = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_USE_DIESEL_IN_DRILL, true).getBoolean();
        ModInfo.configUseRefinedFuelInDrill = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_USE_REFINED_FUEL_IN_DRILL, true).getBoolean();
        ModInfo.configAddBottlerRecipes = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_ADD_BOTTLER_RECIPES, true).getBoolean();
        ModInfo.configDieselValue = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_DIESEL_VALUE, 175).getInt();
        ModInfo.configRefinedFuelValue = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_REFINED_FUEL_VALUE, 375).getInt();
        ModInfo.configAddFuelTypeToTooltips = config.get(CATEGORY_GENERAL, ModInfo.CONFIG_ADD_FUEL_TYPE_TO_TOOLTIPS, true).getBoolean();
        if (config.hasChanged())
            config.save();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (ModInfo.configEnable) {
            if (ModInfo.configUseDieselInGenerator)
                DieselHandler.registerFuel(LiquidManager.DIESEL, ModInfo.configDieselValue);
            if (ModInfo.configUseRefinedFuelInGenerator)
                DieselHandler.registerFuel(LiquidManager.REFINED_FUEL, ModInfo.configRefinedFuelValue);
            if (ModInfo.configUseDieselInDrill)
                DieselHandler.registerDrillFuel(LiquidManager.DIESEL);
            if (ModInfo.configUseRefinedFuelInDrill)
                DieselHandler.registerDrillFuel(LiquidManager.REFINED_FUEL);
            if (ModInfo.configAddBottlerRecipes) {
                BottlingMachineRecipe.addRecipe(new ItemStack(ItemIDs.diesel.item, 1),
                        new ItemStack(ItemIDs.emptyCanister.item, 1), new FluidStack(LiquidManager.DIESEL, 1000));
                BottlingMachineRecipe.addRecipe(new ItemStack(ItemIDs.refinedFuel.item, 1),
                        new ItemStack(ItemIDs.emptyCanister.item, 1), new FluidStack(LiquidManager.REFINED_FUEL, 1000));
            }
        }
    }
}