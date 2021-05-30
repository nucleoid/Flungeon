package io.github.catchyaintit.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class FlungeonConfig {
    public static final Codec<FlungeonConfig> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("min").forGetter(config -> config.min)
        ).apply(instance, FlungeonConfig::new);
    });

    public final int min;
    public FlungeonConfig(int min) {
        this.min = min;
    }
}
