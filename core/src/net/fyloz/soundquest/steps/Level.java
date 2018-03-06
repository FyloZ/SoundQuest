package net.fyloz.soundquest.steps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import net.fyloz.soundquest.Camera;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;
import net.fyloz.soundquest.utils.ResourceManager;

public class Level {
	private Player player;
	private Camera camera;
	private SpriteBatch batch;
	private PhysicWorld world;
	private SoundQuest game;

	private Sprite bg;

	private Box2DDebugRenderer dren;
	private boolean debug = false;

	public Level(String levelName, SoundQuest game) {
		this.batch = game.batch;
		this.game = game;
		Box2D.init();

		loadSprites();
		loadWorld(levelName);
		loadCollidingEntities();

		camera = ResourceManager.getInstance().getDynamicCamera();

		dren = new Box2DDebugRenderer();
	}

	public void loadSprites() {
		bg = new Sprite((Texture) game.manager.get("textures/backgrounds/forest_bg.gif"));
	}

	public void loadWorld(String levelName) {
		world = new PhysicWorld(game, levelName);
		world.getMap().load();
	}

	public void loadCollidingEntities() {
		player = new Player(game);
		world.addCollisions();
	}

	public void render() {
		batch.setProjectionMatrix(camera.combined);
		if (Gdx.input.isKeyJustPressed(Keys.Q))
			debug = !debug;
		camera.updateCamera();
		player.updatekeys();
		player.update();
		game.batch.setProjectionMatrix(game.staticCamera.combined);
		game.batch.begin();
		game.batch.draw(bg, 0, 0, bg.getWidth() * (Gdx.graphics.getHeight() / bg.getHeight()),
				Gdx.graphics.getHeight());
		game.batch.end();
		camera.update();
		world.update();
		ResourceManager.getInstance().getCurrentWorld().render();
		player.render();
		if (debug)
			dren.render(ResourceManager.getInstance().getCurrentWorld().getWorld(), camera.combined);
	}

	public void dispose() {
		player.clear();
		world.dispose();
	}

	public Player getPlayer() {
		return player;
	}

}
