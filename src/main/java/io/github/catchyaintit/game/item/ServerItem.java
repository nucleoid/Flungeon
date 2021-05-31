package io.github.catchyaintit.game.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import xyz.nucleoid.plasmid.util.ItemStackBuilder;

public class ServerItem {
    public Item model;
    public ItemStack modelStack;
    private ServerItemSettings settings;
    public ServerItem(Item model, ServerItemSettings settings) {
        this.model = model;
        this.modelStack = model.getDefaultStack().copy();
        this.settings = settings;
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

    public void init(String id) {
        for (Enchantment enchant : settings.getEnchantments().keySet()) {
            modelStack.addEnchantment(enchant, settings.getEnchantments().get(enchant));
        }

        CompoundTag data = modelStack.getOrCreateSubTag("display");
        CompoundTag mainData = modelStack.getOrCreateTag();
        ListTag loreList = new ListTag();
        if (data.contains("Lore", 9)) {
            loreList = data.getList("Lore", 8);
        }else {
            data.put("Lore", loreList);
        }
        loreList.add(StringTag.of(Text.Serializer.toJson(settings.getLore())));
        mainData.putBoolean("custom", true);
        mainData.putString("id", id);
    }

    public void addLore() {

    }

}
