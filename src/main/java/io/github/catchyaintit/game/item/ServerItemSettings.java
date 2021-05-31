package io.github.catchyaintit.game.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.LinkedList;

public class ServerItemSettings {
    public Text lore;
    public HashMap<Enchantment, Integer> enchantments = new HashMap();
    public ServerItemSettings() {

    }

    public ServerItemSettings lore(Text text) {
        this.lore = text;
        return this;
    }

    public ServerItemSettings enchantment(Enchantment enchant, int level) {
        enchantments.put(enchant, level);
        return this;
    }

    public Text getLore() {
        return lore;
    }

    public HashMap<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }
}
