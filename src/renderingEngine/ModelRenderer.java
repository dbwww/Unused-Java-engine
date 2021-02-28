package renderingEngine;

import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import org.lwjgl.util.vector.Matrix4f;
import gameobjects.GameObject;
import shader.StaticShader;
import utils.UtilMath;

import java.util.List;
import java.util.Map;

public class ModelRenderer {

	StaticShader shader = new StaticShader();

	public void render(Map<TexturedModel, List<GameObject>> gameObjects) {

		for (TexturedModel model : gameObjects.keySet()) {

			GL30.glBindVertexArray(model.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);

			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTextures().getTextureID());

			List<GameObject> batch = gameObjects.get(model);

			for (GameObject gameObject : batch) {

				Matrix4f transformationMatrix = UtilMath.createTransformationMatrix(gameObject.getPosition(), gameObject.getRot(), gameObject.getScale());
				shader.loadTransformationMatrix(transformationMatrix);

				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

			}

			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL30.glBindVertexArray(0);

		}

	}
	
}
