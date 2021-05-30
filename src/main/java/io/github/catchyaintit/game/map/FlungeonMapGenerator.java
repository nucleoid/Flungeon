package io.github.catchyaintit.game.map;

import xyz.nucleoid.plasmid.map.template.MapTemplate;
import xyz.nucleoid.plasmid.map.template.MapTemplateSerializer;

public class FlungeonMapGenerator {

    public FlungeonMap generate() {

        try {
            FlungeonMap map;
            MapTemplate template = MapTemplateSerializer.INSTANCE.loadFromResource();

            return map;
        }catch() {

        }

        return null;
    }
}
