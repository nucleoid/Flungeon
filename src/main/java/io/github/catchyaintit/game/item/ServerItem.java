package io.github.catchyaintit.game.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ServerItem {
    private final Item model;
    private final ItemStack modelStack;
    public ServerItem(Item model) {
        this.model = model;
        this.modelStack = model.getDefaultStack();
        init();
    }

    public ActionResult onUse(ServerPlayerEntity player, Hand hand) {
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

    public void init() {
        CompoundTag test = new CompoundTag();
        test.putBoolean("custom", true);
        modelStack.setTag(test);
    }

}
