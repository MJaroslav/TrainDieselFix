package mjaroslav.mcmods.traindieselfix;

import blusunrize.immersiveengineering.api.crafting.BottlingMachineRecipe;
import blusunrize.immersiveengineering.api.energy.DieselHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidStack;
import train.common.api.LiquidManager;
import train.common.library.ItemIDs;

import static mjaroslav.mcmods.traindieselfix.lib.ModInfo.*;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

@Mod(modid = MOD_ID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class TrainDieselFix {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        configEnable = config.get(CATEGORY_GENERAL, CONFIG_ENABLE, true).getBoolean();
        configUseDieselInGenerator = config.get(CATEGORY_GENERAL, CONFIG_USE_DIESEL_IN_GENERATOR, true).getBoolean();
        configUseRefinedFuelInGenerator = config.get(CATEGORY_GENERAL, CONFIG_USE_REFINED_FUEL_IN_GENERATOR, true).getBoolean();
        configUseDieselInDrill = config.get(CATEGORY_GENERAL, CONFIG_USE_DIESEL_IN_DRILL, true).getBoolean();
        configUseRefinedFuelInDrill = config.get(CATEGORY_GENERAL, CONFIG_USE_REFINED_FUEL_IN_DRILL, true).getBoolean();
        configAddBottlerRecipes = config.get(CATEGORY_GENERAL, CONFIG_ADD_BOTTLER_RECIPES, true).getBoolean();
        configDieselValue = config.get(CATEGORY_GENERAL, CONFIG_DIESEL_VALUE, 175).getInt();
        configRefinedFuelValue = config.get(CATEGORY_GENERAL, CONFIG_REFINED_FUEL_VALUE, 375).getInt();
        if (config.hasChanged())
            config.save();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (configEnable) {
            if (configUseDieselInGenerator)
                DieselHandler.registerFuel(LiquidManager.DIESEL, configDieselValue);
            if (configUseRefinedFuelInGenerator)
                DieselHandler.registerFuel(LiquidManager.REFINED_FUEL, configRefinedFuelValue);
            if (configUseDieselInDrill)
                DieselHandler.registerDrillFuel(LiquidManager.DIESEL);
            if (configUseRefinedFuelInDrill)
                DieselHandler.registerDrillFuel(LiquidManager.REFINED_FUEL);
            if (configAddBottlerRecipes) {
                BottlingMachineRecipe.addRecipe(new ItemStack(ItemIDs.diesel.item, 1),
                        new ItemStack(ItemIDs.emptyCanister.item, 1), new FluidStack(LiquidManager.DIESEL, 1000));
                BottlingMachineRecipe.addRecipe(new ItemStack(ItemIDs.refinedFuel.item, 1),
                        new ItemStack(ItemIDs.emptyCanister.item, 1), new FluidStack(LiquidManager.REFINED_FUEL, 1000));
            }
        }
    }
}