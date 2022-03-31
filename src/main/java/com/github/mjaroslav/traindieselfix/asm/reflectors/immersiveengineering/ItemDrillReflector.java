package com.github.mjaroslav.traindieselfix.asm.reflectors.immersiveengineering;

import blusunrize.immersiveengineering.common.items.ItemDrill;
import com.github.mjaroslav.traindieselfix.lib.ModInfo;
import com.github.mjaroslav.traindieselfix.util.Utils;
import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDrillReflector {
    public static int fill(@NotNull ItemDrill instance, @NotNull ItemStack container,
                           @Nullable FluidStack resource, boolean doFill) {
        return Utils.fill(instance, container, resource, doFill);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void addInformation(@NotNull ItemDrill instance, @NotNull ItemStack itemStack,
                                      @Nullable EntityPlayer player, @NotNull List list, boolean adv) {
        val shader = instance.getShaderItem(itemStack);
        if (shader != null)
            list.add(EnumChatFormatting.DARK_GRAY + shader.getDisplayName());

        val fluidStack = instance.getFluid(itemStack);
        if (fluidStack != null) {
            val fuelTypeSuffix = ModInfo.configEnable && ModInfo.configAddFuelTypeToTooltips ? " " +
                    Utils.formatFluidStackName(fluidStack) : "";
            list.add(StatCollector.translateToLocal("desc.ImmersiveEngineering.flavour.drill.fuel") + " " +
                    fluidStack.amount +
                    "/" + instance.getCapacity(itemStack) + "mB" + fuelTypeSuffix);
        } else
            list.add(StatCollector.translateToLocal("desc.ImmersiveEngineering.flavour.drill.empty"));

        if (instance.getHead(itemStack) == null)
            list.add(StatCollector.translateToLocal("desc.ImmersiveEngineering.flavour.drill.noHead"));
        else {
            val maxDmg = instance.getMaxHeadDamage(itemStack);
            val dmg = maxDmg - instance.getHeadDamage(itemStack);
            val quote = (float) dmg / (float) maxDmg;
            val status = "" + ((double) quote < 0.1D ? EnumChatFormatting.RED : ((double) quote < 0.3D ?
                    EnumChatFormatting.GOLD : ((double) quote < 0.6D ? EnumChatFormatting.YELLOW : EnumChatFormatting.GREEN)));
            list.add(StatCollector.translateToLocal("desc.ImmersiveEngineering.flavour.drill.headDamage") + " " +
                    status + dmg + "/" + maxDmg);
        }

    }
}
