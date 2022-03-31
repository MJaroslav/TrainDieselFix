package com.github.mjaroslav.traindieselfix.lib;

public class ModInfo {
    public static final String MOD_ID = "traindieselfix";
    public static final String NAME = "Train Diesel Fix";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:ImmersiveEngineering;required-after:tc;after:arcane_engineering";

    public static final String CONFIG_ENABLE = "Enable mod";
    public static final String CONFIG_USE_DIESEL_IN_GENERATOR = "Use TC diesel in IE generator";
    public static final String CONFIG_USE_REFINED_FUEL_IN_GENERATOR = "Use TC refined fuel in IE generator";
    public static final String CONFIG_USE_DIESEL_IN_DRILL = "Use TC diesel as IE mining drill fuel";
    public static final String CONFIG_USE_REFINED_FUEL_IN_DRILL = "Use TC refined fuel as IE mining drill fuel";
    public static final String CONFIG_ADD_BOTTLER_RECIPES = "Add IE bottler recipes for TC canisters";
    public static final String CONFIG_DIESEL_VALUE = "Energy from one bucket of TC diesel";
    public static final String CONFIG_REFINED_FUEL_VALUE = "Energy from one bucket of TC refined fuel";
    public static final String CONFIG_ADD_FUEL_TYPE_TO_TOOLTIPS = "Add suffix \" (FUEL_NAME)\" after capacity of drill (IE) and impulse boots (from AE)";

    public static boolean configEnable;
    public static boolean configUseDieselInGenerator;
    public static boolean configUseRefinedFuelInGenerator;
    public static boolean configUseDieselInDrill;
    public static boolean configUseRefinedFuelInDrill;
    public static boolean configAddBottlerRecipes;
    public static int configDieselValue;
    public static int configRefinedFuelValue;
    public static boolean configAddFuelTypeToTooltips;
}
