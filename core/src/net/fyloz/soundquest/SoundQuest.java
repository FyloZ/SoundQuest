package net.fyloz.soundquest;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.fyloz.soundquest.core.Interactables;
import net.fyloz.soundquest.screens.LoadingBar;
import net.fyloz.soundquest.screens.MainScreen;
import net.fyloz.soundquest.utils.ResourceManager;

public class SoundQuest extends Game {
	private FrameRate fps;
	public SpriteBatch batch;
	public AssetManager manager;
	public ShapeRenderer shapeRenderer;
	public Interactables inputs;

	public MainScreen mainscreen;

	public Camera staticCamera;
	public Camera dynamicCamera;

	private int wWidth;
	private int wHeight;

	public float PPM = 16f;

	private boolean isFullscreen = false;

	public HashMap<String, BitmapFont> fonts;

	public SoundQuest(int width, int height) {
		this.wWidth = width;
		this.wHeight = height;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();

		staticCamera = new Camera(this, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		dynamicCamera = new Camera(this, 24, 19);
		dynamicCamera.setDiviser(25);
		dynamicCamera.zoom = 0.8f;

		ResourceManager.getInstance().setStaticCamera(staticCamera);
		ResourceManager.getInstance().setDynamicCamera(dynamicCamera);

		inputs = new Interactables();
		Gdx.input.setInputProcessor(inputs);

		batch.begin();

		fps = new FrameRate();
		fps.resize(wWidth, wHeight);

		batch.end();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(staticCamera.combined);
		setScreen(new LoadingBar(this));
		;
	}

	@Override
	public void render() {
		if (Gdx.input.isKeyJustPressed(Keys.F11)) {
			isFullscreen = !isFullscreen;
			if (isFullscreen)
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			else
				Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);
			// Gdx.graphics.setWindowedMode(640, 480);
		}

		inputs.checkMousePosition();

		super.render();

		fps.update();
		fps.render();

		staticCamera.updateCamera();
		dynamicCamera.updateCamera();
	}

	@Override
	public void dispose() {
		fps.dispose();
		if (manager != null) {
			manager.dispose();
		}
	}

	public void updateKeys() {
	}

	public void update() {
	}

	public void setManager(AssetManager manager) {
		this.manager = manager;
	}

	public void addFont(String name, BitmapFont font) {
		if (fonts == null)
			fonts = new HashMap<>();
		fonts.put(name, font);
	}

	public AssetManager getManager() {
		return manager;
	}
}
