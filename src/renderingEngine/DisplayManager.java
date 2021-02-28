package renderingEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import openblocks.GameLoop;

import java.awt.*;
import javax.swing.JFrame;


public class DisplayManager {
	
	private static final int winSizeX = 1280;
	private static final int winSizeY = 720;
	private static final int maxFPS = 120;

	private static boolean isFullscreen = false;

	public static final String version = "0.0.1-2/9/21-1";
	
	public static void makeDisplay() {

		Canvas canvas = new Canvas();
		JFrame jFrame = new JFrame();

		jFrame.setTitle("OpenBlocks v" + version);
		jFrame.setSize(winSizeX, winSizeY);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().add(canvas);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);

		try {
			Display.setDisplayMode(new DisplayMode(winSizeX, winSizeY));
			Display.setParent(canvas);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Mouse.setGrabbed(true);
	}
	
	public static void updateDisplay() {

		Display.sync(maxFPS);
		Display.update();
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
				} else if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
			}
		}
		
	} 
	
	public static void killDisplay() {
		
		GameLoop.loader1.cleanUp();
		GameLoop.shader1.cleanUp();
		
		Display.destroy();
		System.exit(0);
		
	}

	public void fullscreen() {

		if(Keyboard.isKeyDown(Keyboard.KEY_F11) && isFullscreen) {
			isFullscreen = false;
			try {
				Display.setFullscreen(false);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		} else if(Keyboard.isKeyDown(Keyboard.KEY_F11) && !isFullscreen) {
			isFullscreen = true;
			try {
				Display.setFullscreen(true);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}

	}
	
}