package io.github.catchyaintit.game.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public class FlungeonMapConfig {
    public static final Codec<FlungeonMapConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(config -> config.id)
    ).apply(instance, FlungeonMapConfig::new));

    public final Identifier id;
    public FlungeonMapConfig(Identifier id) {
        this.id = id;
    }
}
