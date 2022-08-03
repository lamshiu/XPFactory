package net.lamshiu.xpfactory.item;

import net.minecraft.world.item.ItemStack;

public class XpFactoryCreativeModeTab {

    public static final net.minecraft.world.item.CreativeModeTab XPFACTORY_TAB = new net.minecraft.world.item.CreativeModeTab("XP Factory") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.TIER1_XP_BOTTLE.get());
        }
    };
}
