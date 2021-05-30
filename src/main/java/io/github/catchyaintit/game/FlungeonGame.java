package io.github.catchyaintit.game;

import net.minecraft.world.GameMode;
import xyz.nucleoid.fantasy.BubbleWorldConfig;
import xyz.nucleoid.plasmid.game.GameOpenContext;
import xyz.nucleoid.plasmid.game.GameOpenProcedure;
import xyz.nucleoid.plasmid.map.template.TemplateChunkGenerator;

public class FlungeonGame {
    public static GameOpenProcedure open(GameOpenContext context) {

        BubbleWorldConfig worldConfig = new BubbleWorldConfig()
                .setGenerator()
                .setDefaultGameMode(GameMode.SURVIVAL)
                .setSpawnAt(0, 1, 1);

        return context.createOpenProcedure(worldConfig, logic -> {
            logic.
        });
    }
}
