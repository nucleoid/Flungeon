package io.github.catchyaintit.game.map;

import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.StructuresConfig;
import xyz.nucleoid.plasmid.game.world.generator.GameChunkGenerator;

public class FlungeonIntermediaryGenerator extends GameChunkGenerator {
    private boolean roomsCreated = false;
    private int rooms = 0;
    private ChunkPos lastChunkPos;
    public FlungeonIntermediaryGenerator(MinecraftServer server) {
        super(server);
    }

    public FlungeonIntermediaryGenerator(BiomeSource biomes, StructuresConfig structures) {
        super(biomes, structures);
    }

    @Override
    public void buildSurface(ChunkRegion region, Chunk chunk) {
        super.buildSurface(region, chunk);
    }

    @Override
    public void populateNoise(WorldAccess world, StructureAccessor structures, Chunk chunk) {
        if (!roomsCreated && rooms != 8 ) {
            if (lastChunkPos != null) {
                System.out.println(chunk.getPos().x);
                System.out.println(lastChunkPos.x);
                lastChunkPos = new ChunkPos(0, chunk.getPos().z);
                if (!(chunk.getPos().z == (lastChunkPos.z + 1)) && chunk.getPos().x == 0) {
                    return;
                }
                if (lastChunkPos == null) {
                    System.out.println("Null");
                }
            }else {
                lastChunkPos = chunk.getPos();
            }
            chunk.setBlockState(chunk.getPos().getStartPos(), Blocks.DIRT.getDefaultState(), false);
            rooms++;
        }else if (rooms == 8) {
            roomsCreated = true;
        }
    }

    @Override
    public void generateFeatures(ChunkRegion region, StructureAccessor structures) {
        super.generateFeatures(region, structures);
    }

    @Override
    public void populateEntities(ChunkRegion region) {
        super.populateEntities(region);
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return super.getHeight(x, z, heightmapType);
    }

    @Override
    public BlockView getColumnSample(int x, int z) {
        return super.getColumnSample(x, z);
    }

    @Override
    public void populateBiomes(Registry<Biome> biomeRegistry, Chunk chunk) {
        super.populateBiomes(biomeRegistry, chunk);
    }
}
