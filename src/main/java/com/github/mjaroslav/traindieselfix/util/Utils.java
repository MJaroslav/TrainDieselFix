package com.github.mjaroslav.traindieselfix.util;

import blusunrize.immersiveengineering.api.energy.DieselHandler;
import blusunrize.immersiveengineering.common.util.ItemNBTHelper;
import lombok.val;
import lombok.var;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Utils {
    public static int fill(@NotNull IFluidContainerItem container, @NotNull ItemStack itemsTack, @Nullable FluidStack resource,
                           boolean doFill) {
        var fluidStack = container.getFluid(itemsTack);
        if (resource != null && DieselHandler.isValidDrillFuel(resource.getFluid())
                && (fluidStack == null || resource.isFluidEqual(fluidStack))) {
            val space = fluidStack == null ? container.getCapacity(itemsTack) :
                    container.getCapacity(itemsTack) - fluidStack.amount;
            val accepted = Math.min(space, resource.amount);
            if (fluidStack == null)
                fluidStack = new FluidStack(resource, accepted);
            else
                fluidStack.amount += accepted;
            if (doFill)
                ItemNBTHelper.setFluidStack(itemsTack, "fuel", fluidStack);
            return accepted;
        } else
            return 0;
    }

    @NotNull
    public static String formatFluidStackName(@Nullable FluidStack fluidStack) {
        if (fluidStack == null)
            return "";
        return "(" + fluidStack.getLocalizedName() + ")";
    }
}
