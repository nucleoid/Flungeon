package io.github.catchyaintit.game.map;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import xyz.nucleoid.plasmid.map.template.MapTemplate;
import xyz.nucleoid.plasmid.map.template.TemplateChunkGenerator;

public class FlungeonMap {
    public FlungeonMapConfig config;
    public MapTemplate template;
    public Vec3d spawn;
    public FlungeonMap(FlungeonMapConfig config, MapTemplate template, Vec3d spawn) {
        this.config = config;
        this.template = template;
        this.spawn = spawn;
    }

    public TemplateChunkGenerator asGenerator(MinecraftServer server) {
        long seed = server.getOverworld().getSeed();
        BiomeSource source = new VanillaLayeredBiomeSource(seed, false, false, server.getRegistryManager().get(Registry.BIOME_KEY));
        TemplateChunkGenerator generator = new TemplateChunkGenerator(server, template);
        return generator;
    }
}
