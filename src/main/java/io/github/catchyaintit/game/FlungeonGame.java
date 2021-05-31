package io.github.catchyaintit.game;

import io.github.catchyaintit.Flungeon;
import io.github.catchyaintit.game.item.ServerItem;
import io.github.catchyaintit.game.item.registry.ItemRegistry;
import io.github.catchyaintit.game.map.FlungeonMap;
import io.github.catchyaintit.game.map.FlungeonMapConfig;
import io.github.catchyaintit.game.map.FlungeonMapGenerator;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import xyz.nucleoid.fantasy.BubbleWorldConfig;
import xyz.nucleoid.plasmid.game.GameOpenContext;
import xyz.nucleoid.plasmid.game.GameOpenProcedure;
import xyz.nucleoid.plasmid.game.GameSpace;
import xyz.nucleoid.plasmid.game.event.*;
import xyz.nucleoid.plasmid.game.rule.GameRule;
import xyz.nucleoid.plasmid.game.rule.RuleResult;
import xyz.nucleoid.plasmid.map.template.TemplateChunkGenerator;

import javax.sound.sampled.FloatControl;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

public class FlungeonGame {
    public LinkedList<UUID> alivePlayers = new LinkedList<>();
    public LinkedList<UUID> deadPlayers = new LinkedList<>();
    public GameSpace space;
    public boolean gameStarted = false;
    public static GameOpenProcedure open(GameOpenContext<FlungeonConfig> context) {
        FlungeonConfig config = context.getConfig();
        FlungeonMapConfig mapConfig = new FlungeonMapConfig(config.map);
        FlungeonMap map = new FlungeonMapGenerator(mapConfig).generate();
        BubbleWorldConfig worldConfig = new BubbleWorldConfig()
                .setGenerator(map.asGenerator(context.getServer()))
                .setDefaultGameMode(GameMode.SURVIVAL)
                .setRaining(true)
                .setThundering(true)
                .setTimeOfDay(18000)
                .setSpawnAt(map.spawn);

        return context.createOpenProcedure(worldConfig, logic -> {
            FlungeonGame game = new FlungeonGame();
            game.space = logic.getSpace();
            logic.setRule(GameRule.PORTALS, RuleResult.DENY);
            logic.setRule(GameRule.PVP, RuleResult.DENY);
            logic.setRule(GameRule.PLACE_BLOCKS, RuleResult.DENY);
            logic.on(UseItemListener.EVENT, game::onItemUse);
            logic.on(PlayerAddListener.EVENT, game::onPlayerAdded);
            logic.on(PlayerRemoveListener.EVENT, game::onPlayerRemove);
            logic.on(GameTickListener.EVENT, game::tick);
            logic.on(PlayerDeathListener.EVENT, game::onDeath);
        });
    }

    private ActionResult onDeath(ServerPlayerEntity player, DamageSource source) {
        if (alivePlayers.contains(player.getUuid()) && gameStarted) {
            alivePlayers.remove(player.getUuid());
            deadPlayers.add(player.getUuid());
        }
        return ActionResult.FAIL;
    }


    private void onPlayerAdded(ServerPlayerEntity player) {
        if (!alivePlayers.contains(player.getUuid()) && !deadPlayers.contains(player.getUuid())) {
            alivePlayers.add(player.getUuid());
        }
    }
    private void onPlayerRemove(ServerPlayerEntity player) {
        if (alivePlayers.contains(player.getUuid()) || deadPlayers.contains(player.getUuid())) {
            if (gameStarted) {
                for (ServerPlayerEntity tempPlayer : space.getPlayers()) {
                    tempPlayer.sendMessage(new LiteralText(player.getName() + " Has give up").formatted(Formatting.RED), true);
                    tempPlayer.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
                }
            }else {
                alivePlayers.remove(player.getUuid());
                deadPlayers.remove(player.getUuid());
            }
        }
    }

    private TypedActionResult<ItemStack> onItemUse(ServerPlayerEntity player, Hand hand) {
        if (player.getMainHandStack().getTag().contains("custom")) {
            ItemStack itemStack = player.getMainHandStack();
            Iterator iterator = ItemRegistry.REGISTRY.iterator();
            while (iterator.hasNext()) {
                ServerItem item = (ServerItem) iterator.next();
                String id = ItemRegistry.REGISTRY.getIdentifier(item);
                CompoundTag tag = itemStack.getTag();
                String name = tag.getString("id");
                if (id == name) {
                    ActionResult result = ItemRegistry.REGISTRY.get(id).onUse(player, hand);
                    if (result == ActionResult.SUCCESS) {
                        return TypedActionResult.success(itemStack);
                    } else if (result == ActionResult.FAIL) {
                        return TypedActionResult.fail(itemStack);
                    }
                }
            }
        }
        return TypedActionResult.success(player.getMainHandStack());
    }

    private void tick() {
    }
}
