package net.fyloz.soundquest.steps;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.activation.MimetypesFileTypeMap;

import com.badlogic.gdx.Gdx;

import net.fyloz.soundquest.Logger;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.screens.LoadingBar;
import net.fyloz.soundquest.utils.ResourceManager;

public class MainLoader {
	private SoundQuest game;
	private HashMap<String, File> assets;
	private LoadingBar bar;

	private ArrayList<File> files;

	public MainLoader(SoundQuest game, LoadingBar bar) {
		this.game = game;
		this.bar = bar;
		assets = new HashMap<String, File>();
	}

	public void init() {
		File textures = Gdx.files.internal("textures").exists() && Gdx.files.internal("textures").isDirectory()
				? Gdx.files.internal("textures").file()
				: null;
		File fonts = Gdx.files.internal("fonts").exists() && Gdx.files.internal("fonts").isDirectory()
				? Gdx.files.internal("fonts").file()
				: null;
		File maps = Gdx.files.internal("maps").exists() && Gdx.files.internal("maps").isDirectory()
				? Gdx.files.internal("maps").file()
				: null;

		if (textures == null || fonts == null || maps == null) {
			Logger.log(Logger.FATAL, "Can't find all assets folders!");
		}

		assets.put("textures", textures);
		assets.put("fonts", fonts);
		assets.put("maps", maps);
	}

	public void loadTextures() {
		ArrayList<File> allFiles = new ArrayList<File>();

		allFiles.add(assets.get("textures"));
		/**
		 * allFiles.add(openDir(allFiles.get(0), "entities"));
		 * allFiles.add(openDir(allFiles.get(0), "others"));
		 * allFiles.add(openDir(allFiles.get(0), "traps"));
		 **/

		files = new ArrayList<File>();

		for (int i = 0; i < allFiles.size(); i++) {
			for (File file : allFiles.get(i).listFiles()) {
				ArrayList<File> childs = getChilds(file);
				if (childs != null) {
					for (File child : childs) {
						files.add(child);
					}
				}
			}
		}

		bar.setMax(files.size());

		for (File png : files) {
			bar.setBarPosition(bar.getBarPosition() + 1);
			loadSprite(png);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void loadSprite(File file) {
		ResourceManager.getInstance().loadSprite(file.getName(), file.getPath());
	}
	
	private File openDir(File baseDir, String dir) {
		File file = new File(baseDir.getPath() + "/" + dir);
		if (!file.exists() || file == null || !file.isDirectory() || !file.canRead()) {
			Logger.log(Logger.FATAL, "Can't find or access to entities assets folders!");
		}
		return file;
	}

	private ArrayList<File> getChilds(File dir) {
		if (dir.isFile()) {
			return null;
		} else {
			File[] childrens = dir.listFiles();
			ArrayList<File> pngs = new ArrayList<File>();

			MimetypesFileTypeMap mime = new MimetypesFileTypeMap();
			mime.addMimeTypes("image png tif jpg jpeg bmp");

			for (File file : childrens) {
				if (file != null && file.isFile() && mime.getContentType(file).split("/")[0].equals("image")) {
					pngs.add(file);
				}
			}
			return pngs;
		}

	}
}
