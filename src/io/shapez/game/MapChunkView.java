package io.shapez.game;

public class MapChunkView extends MapChunk {
    int renderIteration = 0;
    String renderKey;
    MapChunkView(GameRoot root, int x, int y) {
        super(root, x, y);
        this.markDirty();
    }

    private void markDirty() {
        ++this.renderIteration;
        this.renderKey = this.x + "/" + this.y + "@" + this.renderIteration;
    }
}
