package net.fyloz.soundquest.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.fyloz.soundquest.Logger;
import net.fyloz.soundquest.SoundQuest;

public class DesktopLauncher {
	public static void main(String[] arg) {

		Logger.logSystemInfos();
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		cfg.title = "Sound Quest";
		cfg.useGL30 = false;
		cfg.width = 640;
		cfg.height = 480;
		cfg.addIcon("textures/others/SoundQuest.png", Files.FileType.Internal);
		cfg.vSyncEnabled = true; // Setting to false disables vertical sync

		new LwjglApplication(new SoundQuest(cfg.width, cfg.height), cfg);
	}
}