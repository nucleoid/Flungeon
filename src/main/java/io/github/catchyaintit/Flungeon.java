package io.github.catchyaintit;

import io.github.catchyaintit.game.FlungeonConfig;
import io.github.catchyaintit.game.FlungeonGame;
import io.github.catchyaintit.game.item.ServerItem;
import io.github.catchyaintit.game.item.ServerItemSettings;
import io.github.catchyaintit.game.item.ServerWeaponItem;
import io.github.catchyaintit.game.item.ServerWeaponItemSettings;
import io.github.catchyaintit.game.item.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.game.GameType;

public class Flungeon implements ModInitializer {
	public static String ID = "flungeon";
	public static GameType TYPE = GameType.register(
			new Identifier(ID, "standard"),
			FlungeonGame::open,
			FlungeonConfig.CODEC
	);
	static ServerWeaponItem killSword = new ServerWeaponItem(Items.BOOK, new ServerWeaponItemSettings().attackDamage(90000).lore(new LiteralText("kill book")));

	@Override
	public void onInitialize() {
		ItemRegistry.register("item", killSword);
	}
}
