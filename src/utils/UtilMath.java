package utils;

import gameobjects.Camera;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class UtilMath {

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rot, Vector3f scale) {
        
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();

        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rot.x), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rot.y), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rot.z), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, scale.z), matrix, matrix);

        return matrix;

    }

    public static Matrix4f createViewMatrix(Camera camera) {

        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();

        Matrix4f.rotate((float) Math.toRadians(camera.getRot().x), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRot().y), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRot().z), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.translate(new Vector3f(-camera.getPos().x, -camera.getPos().y, -camera.getPos().z), matrix, matrix);

        return matrix;

    }

    public static float clamp(float input, float min, float max) {

        if(input > max) {
            input = max;
        } else if(input < min) {
            input = min;
        }

        return input;

    }

}
