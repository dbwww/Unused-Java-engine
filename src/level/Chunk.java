package level;

import gameobjects.GameObject;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class Chunk {

    public List<GameObject> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<GameObject> blocks) {
        this.blocks = blocks;
    }

    public Vector3f getCenterBlockLoc() {
        return centerBlockLoc;
    }

    private List<GameObject> blocks;
    private Vector3f centerBlockLoc;

    public Chunk(List<GameObject> blocks, Vector3f defBlockLoc) {

        this.setBlocks(blocks);
        this.centerBlockLoc = defBlockLoc;

    }

}
