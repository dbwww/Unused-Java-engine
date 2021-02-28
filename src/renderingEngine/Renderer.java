package renderingEngine;

import gameobjects.Camera;
import gameobjects.GameObject;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import shader.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

	Matrix4f projectionMatrix;

	private static final float fov = 70f;
	private static final float nearplane = 0.1f;
	private static final float farplane = 1000f;

	StaticShader shader = new StaticShader();
	ModelRenderer renderer = new ModelRenderer();
	Map<TexturedModel, List<GameObject>> gameObjects = new HashMap<TexturedModel, List<GameObject>>();

	public Renderer() {

		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();

	}

	public void prepare() {

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
	}

	public void render(Camera camera) {
		
		prepare();
		shader.start();
		shader.loadViewMatrix(camera);
		renderer.render(gameObjects);
		shader.stop();

		gameObjects.clear();
		
	}

	public void addGameObject(GameObject gameObject) {

		TexturedModel model = gameObject.getModel();

		List<GameObject> batch = gameObjects.get(model);

		if(batch != null) {
			batch.add(gameObject);
		} else {
			List<GameObject> newBatch = new ArrayList<GameObject>();
			newBatch.add(gameObject);
			gameObjects.put(model, newBatch);
		}

	}

	public void createProjectionMatrix() {

		projectionMatrix = new Matrix4f();

		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));
		float xScale = yScale / aspectRatio;
		float zp = farplane + nearplane;
		float zm = farplane - nearplane;

		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -zp/zm;
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -(2 * farplane * nearplane) / zm;
		projectionMatrix.m33 = 0;

	}

}
