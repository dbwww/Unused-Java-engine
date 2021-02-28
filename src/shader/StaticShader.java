package shader;

import gameobjects.Camera;
import org.lwjgl.util.vector.Matrix4f;
import utils.UtilMath;

public class StaticShader extends ShaderMaster{

    private static final String vertexFile = "/shader/vertexShader.txt";
    private static final String fragmentFile = "/shader/fragmentShader.txt";

    int location_transformationMatrix;
    int location_projectionMatrix;
    int location_viewMatrix;

    public StaticShader() {

        super(vertexFile, fragmentFile);

    }

    @Override
    protected void getAllUniformLoc() {

        location_transformationMatrix = super.getUniformLoc("transformationMatrix");
        location_projectionMatrix = super.getUniformLoc("projectionMatrix");
        location_viewMatrix = super.getUniformLoc("viewMatrix");

    }

    @Override
    protected void bindAttributes() {

        super.bindAttribute("position",0);
        super.bindAttribute("textureCoords", 1);

    }

    public void loadTransformationMatrix(Matrix4f matrix) {

        super.loadMatrix(location_transformationMatrix, matrix);

    }

    public void loadProjectionMatrix(Matrix4f matrix) {

        super.loadMatrix(location_projectionMatrix, matrix);

    }

    public void loadViewMatrix(Camera camera) {

        super.loadMatrix(location_viewMatrix, UtilMath.createViewMatrix(camera));

    }

}
