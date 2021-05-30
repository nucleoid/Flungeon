package io.github.catchyaintit.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public class FlungeonConfig {
    public static final Codec<FlungeonConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("min").forGetter(config -> config.min),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("max").forGetter(config -> config.min),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("floors").forGetter(config -> config.floors),
                Identifier.CODEC.fieldOf("map").forGetter(config -> config.map)
        ).apply(instance, FlungeonConfig::new);
    });

    public final int min;
    public final int max;
    public final Identifier map;
    public final int floors;
    public FlungeonConfig(int min, int max,int floors, Identifier map) {
        this.min = min;
        this.map = map;
        this.max = max;
        this.floors = floors;
    }
}
