package net.lamshiu.xpfactory.block;


import net.lamshiu.xpfactory.Xpfactory;
import net.lamshiu.xpfactory.item.Items;
import net.lamshiu.xpfactory.item.XpFactoryCreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Xpfactory.MOD_ID);

    public static final RegistryObject<Block> TRASH_BLOCK = registryBlock("trash_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()),
            XpFactoryCreativeModeTab.XPFACTORY_TAB);

    private static <T extends Block>RegistryObject<T> registryBlock(
            String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registryBlockItem(name, toReturn, tab);
        return  toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(
            String name, Supplier<T> block, CreativeModeTab tab){

        return Items.ITEMS.register(name ,
                ()-> new BlockItem(block.get(),new Item.Properties().tab(tab)));

    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

