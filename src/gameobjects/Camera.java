package gameobjects;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import utils.UtilMath;

public class Camera {

    Vector3f pos;
    Vector3f rot;

    float speed = 0.05f;
    float moveAtX = 0;
    float moveAtZ = 0;
    float ySpeed = 0.75f;
    public float mouseSensitivity = 0.1f;

    public Vector3f getPos() {
        return pos;
    }

    public Vector3f getRot() {
        return rot;
    }

    public Camera(Vector3f pos, Vector3f rot) {
        this.pos = pos;
        this.rot = rot;
    }

    public void move() {

        if(Mouse.isGrabbed()) {

            float lrL;
            float lrR;

            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                lrL = speed;
            } else {
                lrL = 0;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                lrR = -speed;
            } else {
                lrR = 0;
            }

            float udU;
            float udD;

            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                udU = speed;
            } else {
                udU = 0;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                udD = -speed;
            } else {
                udD = 0;
            }

            moveAtX = (float) ((lrL + lrR) * Math.sin(Math.toRadians(rot.y - 90))) + (float) ((udU + udD) * Math.sin(Math.toRadians(rot.y)));
            moveAtZ = (float) -((lrL + lrR) * Math.cos(Math.toRadians(rot.y - 90))) + (float) -((udU + udD) * Math.cos(Math.toRadians(rot.y)));


            rot.x += -Mouse.getDY() * mouseSensitivity;

            rot.y += Mouse.getDX() * mouseSensitivity;

        }


        rot.x = UtilMath.clamp(rot.x, -90, 90);

        Vector3f d = new Vector3f(moveAtX, 0, moveAtZ);

        pos.x += d.x;
        pos.y += d.y;
        pos.z += d.z;

    }

}
