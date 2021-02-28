package gameobjects;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class GameObject {

    TexturedModel model;
    Vector3f pos;
    Vector3f rot;
    Vector3f scale;

    public GameObject(TexturedModel model, Vector3f pos, Vector3f rot, Vector3f scale) {
        this.model = model;
        this.pos = pos;
        this.rot = rot;
        this.scale = scale;
    }

    public void move(Vector3f d) {

        this.pos.x += d.x;
        this.pos.y += d.y;
        this.pos.z += d.z;

    }

    public void rotate(Vector3f d) {

        this.rot.x += d.x;
        this.rot.y += d.y;
        this.rot.z += d.z;

    }

    public void scale(Vector3f d) {

        this.scale.x += d.x;
        this.scale.y += d.y;
        this.scale.z += d.z;


    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return pos;
    }

    public void setPosition(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getRot() {
        return rot;
    }

    public void setRot(Vector3f rot) {
        this.rot = rot;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
