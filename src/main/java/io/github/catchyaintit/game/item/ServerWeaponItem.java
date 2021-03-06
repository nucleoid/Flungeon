package io.github.catchyaintit.game.item;

import io.github.catchyaintit.game.item.ServerItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import xyz.nucleoid.plasmid.util.ItemStackBuilder;

import java.util.UUID;

public class ServerWeaponItem extends ServerItem {
    private ServerWeaponItemSettings settings;
    public ServerWeaponItem(Item model, ServerWeaponItemSettings settings) {
        super(model, settings);
        this.settings = settings;
    }

    @Override
    public void init(String id) {
        super.init(id);
        EntityAttribute attack = EntityAttributes.GENERIC_ATTACK_DAMAGE;
        CompoundTag attackTag = new CompoundTag();
        attackTag.putUuid("UUID", UUID.randomUUID());
        attackTag.putInt("Operation", 0);
        attackTag.putString("Name", "generic.attack_damage");
        attackTag.putDouble("Amount", settings.getAttackDamage());
        EntityAttributeModifier modifer = EntityAttributeModifier.fromTag(attackTag);
        modelStack.addAttributeModifier(attack, modifer, EquipmentSlot.MAINHAND);
    }
}
