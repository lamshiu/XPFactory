package net.lamshiu.xpfactory.item.xpbottle;

import java.util.List;

import javax.annotation.Nonnull;


import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class XpBottle extends Item {

    public int xpValue;
    private static final int DRINK_DURATION = 13;
    public XpBottle(Item.Properties properties,int value) {

        super(properties);
        xpValue = value;
    }


    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            componentList.add(Component.literal("Total XP in stack: "+ stack.getCount()*xpValue).withStyle(ChatFormatting.AQUA));
        }else{
            componentList.add(Component.literal("Press SHIFT to view total XP").withStyle(ChatFormatting.YELLOW));
        }
    }

    @Nonnull
    @Override
    public ItemStack finishUsingItem(@Nonnull ItemStack stack, @Nonnull Level world, @Nonnull LivingEntity entity) {
        super.finishUsingItem(stack,world,entity);
        if (entity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
        }
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (xpValue > 0)
                if (!world.isClientSide()) {
                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (stack.getCount() > 1 && entity.isShiftKeyDown()) {
                        addPlayerXP(player, xpValue * stack.getCount());
                        stack.shrink(stack.getCount());
                    } else
                        addPlayerXP(player, xpValue);
                    world.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 0.8F + world.random.nextFloat() * 0.4F);
                    stack.shrink(1);
                }
        }
        return stack;
    }
    public static void addPlayerXP(Player player, int amount) {
        int experience = getPlayerXP(player) + amount;
        player.totalExperience = experience;
        player.experienceLevel = getLevelForExperience(experience);
        int expForLevel = getExperienceForLevel(player.experienceLevel);
        player.experienceProgress = (float)(experience - expForLevel) / (float)player.getXpNeededForNextLevel();
    }

    public static int getPlayerXP(Player player) {
        return (getExperienceForLevel(player.experienceLevel) + (int)(player.experienceProgress * player.getXpNeededForNextLevel()));
    }

    public static int getLevelForExperience(int experience) {
        int i = 0;
        while (getExperienceForLevel(i) <= experience) {
            i++;
        }
        return i - 1;
    }

    public static int getExperienceForLevel(int level) {
        if (level == 0)
            return 0;
        if (level > 0 && level < 17)
            return (int) (Math.pow(level, 2) + 6 * level);
        else if (level > 16 && level < 32)
            return (int) (2.5 * Math.pow(level, 2) - 40.5 * level + 360);
        else
            return (int) (4.5 * Math.pow(level, 2) - 162.5 * level + 2220);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return DRINK_DURATION;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }
    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public int getXpValue(){
        return xpValue;
    }
}
