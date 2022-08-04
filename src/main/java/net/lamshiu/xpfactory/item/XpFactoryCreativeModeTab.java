package net.lamshiu.xpfactory.item;

import net.minecraft.world.item.ItemStack;

public class XpFactoryCreativeModeTab {

    public static final net.minecraft.world.item.CreativeModeTab XPFACTORY_TAB = new net.minecraft.world.item.CreativeModeTab("xp_factory_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.TIER7_XP_BOTTLE.get());
        }
    };
}
