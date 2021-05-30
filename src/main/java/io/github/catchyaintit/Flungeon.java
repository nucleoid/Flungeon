package io.github.catchyaintit;

import io.github.catchyaintit.game.FlungeonConfig;
import io.github.catchyaintit.game.FlungeonGame;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.game.GameType;

public class Flungeon implements ModInitializer {
	public static String ID = "flungeon";
	public static GameType TYPE =GameType.register(
			new Identifier(ID, "standard"),
			FlungeonGame::open,
			FlungeonConfig.CODEC
	);

	@Override
	public void onInitialize() {
	}
}
