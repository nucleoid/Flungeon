package io.github.catchyaintit.game.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.Text;

import java.util.HashMap;

public class ServerWeaponItemSettings extends ServerItemSettings{
    private Integer attackDamage = 0;
    private Integer attackSpeed;
    public ServerWeaponItemSettings() {
        super();
    }

    @Override
    public ServerWeaponItemSettings lore(Text text) {
        lore = text;
        return this;
    }

    @Override
    public ServerWeaponItemSettings enchantment(Enchantment enchant, int level) {
        enchantments.put(enchant, level);
        return this;
    }

    public ServerWeaponItemSettings attackDamage(int damage) {
        attackDamage = damage;
        return this;
    }

    public ServerWeaponItemSettings attackSpeed(int speed) {
        attackSpeed = speed;
        return this;
    }

    public Integer getAttackDamage() {
        return attackDamage;
    }

    public Integer getAttackSpeed() {
        return attackSpeed;
    }
    public ServerWeaponItemSettings build() {
        return this;
    }

    @Override
    public Text getLore() {
        return super.getLore();
    }

    @Override
    public HashMap<Enchantment, Integer> getEnchantments() {
        return super.getEnchantments();
    }
}
