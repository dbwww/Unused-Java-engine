package openblocks;

import gameobjects.Camera;
import gameobjects.GameObject;
import level.Chunk;
import models.FullBlock;
import models.TexturedModel;
import org.lwjgl.opengl.Display;

import models.RawModelData;
import org.lwjgl.util.vector.Vector3f;
import renderingEngine.DisplayManager;
import renderingEngine.Loader;
import renderingEngine.Renderer;
import shader.StaticShader;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLoop {
	
	public static Loader loader1 = null;
	public static StaticShader shader1 = null;

	static List<Chunk> chunks = Collections.synchronizedList(new ArrayList<Chunk>());
	static Vector3f camPos = new Vector3f(0, 2, 0);
	static List<Vector3f> usedPos = new ArrayList<>();

	public static void main (String[] args) {

		DisplayManager.makeDisplay();

		Loader loader = new Loader();
		loader1 = loader;
		StaticShader shader = new StaticShader();
		shader1 = shader;
		Renderer renderer = new Renderer();

		RawModelData model = loader.loadToVAO(FullBlock.vertices, FullBlock.indices, FullBlock.uv);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stone"));
		TexturedModel object = new TexturedModel(model, texture);

		Camera camera = new Camera(new Vector3f(0, 2 ,0), new Vector3f(0, 0 ,0));

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				List<GameObject> blocks = new ArrayList<GameObject>();
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						blocks.add(new GameObject(object, new Vector3f((x * 16) + i, 0, (z * 16) + j), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));
					}
				}

				chunks.add(new Chunk(blocks, new Vector3f(x * 16, 0 * 16, z * 16)));
			}
		}
		
		while(!Display.isCloseRequested()) {

			camera.move();
			camPos = camera.getPos();

			for (int i = 0; i < chunks.size(); i++) {

				Vector3f centerBlockLoc = chunks.get(i).getCenterBlockLoc();

				int distX = (int) (camPos.x - centerBlockLoc.x);
				int distZ = (int) (camPos.z - centerBlockLoc.z);

				if (distX < 0) { distX = -distX; }
				if (distZ < 0) { distZ = -distZ; }


				if ((distX <= 256) && (distZ <= 256)) {

					for (int j = 0; j < chunks.get(i).getBlocks().size(); j++) {
						renderer.addGameObject(chunks.get(i).getBlocks().get(j));
					}

				}

			}

			renderer.render(camera);

			
			DisplayManager.updateDisplay();
			
		}
		DisplayManager.killDisplay();
	}
	
}
