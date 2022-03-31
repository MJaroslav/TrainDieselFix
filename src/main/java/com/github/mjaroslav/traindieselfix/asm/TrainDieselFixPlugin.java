package com.github.mjaroslav.traindieselfix.asm;

import com.github.mjaroslav.reflectors.v0.Reflectors;
import com.github.mjaroslav.traindieselfix.asm.reflectors.arcaneengineering.ItemImpulseBootsReflector;
import com.github.mjaroslav.traindieselfix.asm.reflectors.immersiveengineering.ItemDrillReflector;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.IClassTransformer;

@IFMLLoadingPlugin.Name("TrainDieselFixPlugin")
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.TransformerExclusions({"com.github.mjaroslav.reflectors.v0",
        "com.github.mjaroslav.traindieselfix.asm"})
public class TrainDieselFixPlugin extends Reflectors.FMLLoadingPluginAdapter
        implements IFMLLoadingPlugin, IClassTransformer {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{getClass().getName()};
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("arcane_engineering.items.ItemImpulseBoots"))
            return Reflectors.reflectClass(basicClass, transformedName, ItemImpulseBootsReflector.class.getName());
        if (transformedName.equals("blusunrize.immersiveengineering.common.items.ItemDrill"))
            return Reflectors.reflectClass(basicClass, transformedName, ItemDrillReflector.class.getName());
        return basicClass;
    }

}
