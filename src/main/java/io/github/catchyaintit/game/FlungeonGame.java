package io.github.catchyaintit.game;

import io.github.catchyaintit.Flungeon;
import io.github.catchyaintit.game.item.ServerItem;
import io.github.catchyaintit.game.item.registry.ItemRegistry;
import io.github.catchyaintit.game.map.FlungeonMap;
import io.github.catchyaintit.game.map.FlungeonMapConfig;
import io.github.catchyaintit.game.map.FlungeonMapGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import xyz.nucleoid.fantasy.BubbleWorldConfig;
import xyz.nucleoid.plasmid.game.GameOpenContext;
import xyz.nucleoid.plasmid.game.GameOpenProcedure;
import xyz.nucleoid.plasmid.game.event.PlayerAddListener;
import xyz.nucleoid.plasmid.game.event.UseItemListener;
import xyz.nucleoid.plasmid.game.rule.GameRule;
import xyz.nucleoid.plasmid.game.rule.RuleResult;
import xyz.nucleoid.plasmid.map.template.TemplateChunkGenerator;

import javax.sound.sampled.FloatControl;

public class FlungeonGame {
    public static GameOpenProcedure open(GameOpenContext<FlungeonConfig> context) {
        FlungeonConfig config = context.getConfig();
        FlungeonMapConfig mapConfig = new FlungeonMapConfig(config.map);
        FlungeonMap map = new FlungeonMapGenerator(mapConfig).generate();
        BubbleWorldConfig worldConfig = new BubbleWorldConfig()
                .setGenerator(map.asGenerator(context.getServer()))
                .setDefaultGameMode(GameMode.SURVIVAL)
                .setSpawnAt(map.spawn);

        return context.createOpenProcedure(worldConfig, logic -> {
            FlungeonGame game = new FlungeonGame();
            logic.setRule(GameRule.PORTALS, RuleResult.DENY);
            logic.setRule(GameRule.PVP, RuleResult.DENY);
            logic.setRule(GameRule.PLACE_BLOCKS, RuleResult.DENY);
            logic.on(UseItemListener.EVENT, game::onItemUse);
            logic.on(PlayerAddListener.EVENT, game::onPlayerAdded);
        });
    }

    private void onPlayerAdded(ServerPlayerEntity player) {
        player.giveItemStack(Flungeon.test.getCustomModelStack());
    }

    private TypedActionResult<ItemStack> onItemUse(ServerPlayerEntity player, Hand hand) {
        if (player.getMainHandStack().getTag().contains("custom")) {
            ItemRegistry.getItems().forEachRemaining(serverItem -> {
                if (player.getMainHandStack().getItem() == serverItem.getModel()) {
                    serverItem.onUse(player, hand);
                }
            });
        }
        return TypedActionResult.success(player.getMainHandStack());
    }
}
