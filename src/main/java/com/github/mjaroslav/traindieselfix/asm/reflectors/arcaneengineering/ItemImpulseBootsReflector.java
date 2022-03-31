package com.github.mjaroslav.traindieselfix.asm.reflectors.arcaneengineering;

import arcane_engineering.items.ItemImpulseBoots;
import com.github.mjaroslav.traindieselfix.lib.ModInfo;
import com.github.mjaroslav.traindieselfix.util.Utils;
import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemImpulseBootsReflector {
    public static int fill(@NotNull ItemImpulseBoots instance, @NotNull ItemStack container, @Nullable FluidStack resource,
                           boolean doFill) {
        return Utils.fill(instance, container, resource, doFill);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void addInformation(@NotNull ItemImpulseBoots instance, @NotNull ItemStack stack,
                                      @Nullable EntityPlayer player, @NotNull List list, boolean adv) {
        val fluidStack = instance.getFluid(stack);
        if (fluidStack != null) {
            val fuelTypeSuffix = ModInfo.configEnable && ModInfo.configAddFuelTypeToTooltips ? " " + Utils.formatFluidStackName(fluidStack) : "";
            list.add(StatCollector.translateToLocal("desc.ImmersiveEngineering.flavour.drill.fuel") + " " +
                    fluidStack.amount + "/" + instance.getCapacity(stack) + "mB" + fuelTypeSuffix);
        } else
            list.add(StatCollector.translateToLocal("desc.ImmersiveEngineering.flavour.drill.fuel") + " 0/" +
                    instance.getCapacity(stack) + "mB");
    }
}
