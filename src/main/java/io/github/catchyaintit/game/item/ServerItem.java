package io.github.catchyaintit.game.item;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import xyz.nucleoid.plasmid.util.ItemStackBuilder;

public class ServerItem {
    public   Item model;
    public   ItemStack modelStack;
    public ServerItem(Item model) {
        this.model = model;
        this.modelStack = model.getDefaultStack();
        ItemStackBuilder.of().
    }

    public ActionResult onUse(ServerPlayerEntity player, Hand hand) {
        System.out.println("Test");
        return ActionResult.SUCCESS;
    }

    public ItemStack getCustomModelStack() {
        return modelStack;
    }

    public ItemStack getModelStack() {
        return model.getDefaultStack();
    }

    public Item getModel() {
        return model;
    }

    public void init(String id) {
        CompoundTag test = new CompoundTag();
        test.putBoolean("custom", true);
        test.putString("id", id);
        modelStack.setTag(test);
    }

    public void addLore() {

    }

}
