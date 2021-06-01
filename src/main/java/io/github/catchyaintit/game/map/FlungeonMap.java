package io.github.catchyaintit.game.map;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import xyz.nucleoid.plasmid.map.template.MapTemplate;
import xyz.nucleoid.plasmid.map.template.TemplateChunkGenerator;
import xyz.nucleoid.plasmid.util.BlockBounds;

import java.util.LinkedList;

public class FlungeonMap {
    public FlungeonMapConfig config;
    public MapTemplate template;
    public BlockBounds spawn;
    public BlockBounds floor;
    public FlungeonMap(FlungeonMapConfig config, MapTemplate template, BlockBounds spawn, BlockBounds floor) {
        this.config = config;
        this.template = template;
        this.spawn = spawn;
        this.floor = floor;
    }

    public TemplateChunkGenerator asGenerator(MinecraftServer server) {
        template.setBiome(BiomeKeys.PLAINS);
        TemplateChunkGenerator generator = new TemplateChunkGenerator(server, template);
        return generator;
    }
}
