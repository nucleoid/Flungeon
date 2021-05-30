package io.github.catchyaintit.game.map;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
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
        return new TemplateChunkGenerator(server, template);
    }
}
