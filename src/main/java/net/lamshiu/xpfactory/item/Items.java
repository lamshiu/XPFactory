package net.lamshiu.xpfactory.item;
import net.lamshiu.xpfactory.Xpfactory;
import net.lamshiu.xpfactory.item.xpbottle.XpBottle;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static java.lang.Math.pow;

public class Items {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Xpfactory.MOD_ID);

    public static int baseXp = 7;
    public static final RegistryObject<Item> TIER1_XP_BOTTLE = ITEMS.register("tier1_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB),baseXp*4));
    public static final RegistryObject<Item> TIER2_XP_BOTTLE = ITEMS.register("tier2_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB), (int) (baseXp*pow(4,2))));
    public static final RegistryObject<Item> TIER3_XP_BOTTLE = ITEMS.register("tier3_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB),(int) (baseXp*pow(4,3))));
    public static final RegistryObject<Item> TIER4_XP_BOTTLE = ITEMS.register("tier4_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB),(int) (baseXp*pow(4,4))));
    public static final RegistryObject<Item> TIER5_XP_BOTTLE = ITEMS.register("tier5_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB),(int) (baseXp*pow(4,5))));
    public static final RegistryObject<Item> TIER6_XP_BOTTLE = ITEMS.register("tier6_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB),(int) (baseXp*pow(4,6))));
    public static final RegistryObject<Item> TIER7_XP_BOTTLE = ITEMS.register("tier7_xp_bottle",
            ()-> new XpBottle(new Item.Properties().tab(XpFactoryCreativeModeTab.XPFACTORY_TAB),(int) (baseXp*pow(5,7))));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}

