package io.github.catchyaintit;

import io.github.catchyaintit.game.FlungeonConfig;
import io.github.catchyaintit.game.FlungeonGame;
import io.github.catchyaintit.game.item.ServerItem;
import io.github.catchyaintit.game.item.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.game.GameType;

public class Flungeon implements ModInitializer {
	public static String ID = "flungeon";
	public static GameType TYPE = GameType.register(
			new Identifier(ID, "standard"),
			FlungeonGame::open,
			FlungeonConfig.CODEC
	);

	public static ServerItem test = new ServerItem(Items.BOOK);

	@Override
	public void onInitialize() {
		ItemRegistry.register("test", test);
	}
}
