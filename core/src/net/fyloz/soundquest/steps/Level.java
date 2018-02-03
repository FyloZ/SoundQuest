package net.fyloz.soundquest.steps;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import net.fyloz.soundquest.Camera;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Elevator;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.entities.collectables.Coin;
import net.fyloz.soundquest.entities.traps.CanonTrap;
import net.fyloz.soundquest.entities.traps.SpikeTrap;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.TiledMapUtils;

public class Level {
	private Player player;
	private Camera camera;
	private SpriteBatch batch;
	private PhysicWorld world;
	private SoundQuest game;

	// TRAPS
	private SpikeTrap spikes;

	private Sprite bg;

	private ArrayList<Coin> coins;
	private ArrayList<CanonTrap> canons;
	private ArrayList<Elevator> elevators;

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
		world = new PhysicWorld(levelName);
	}

	public void loadCollidingEntities() {
		player = new Player(game);
		world.addCollisions();
		spikes = new SpikeTrap();

		coins = new ArrayList<Coin>();
		for (MapObject object : TiledMapUtils.getLayer(world.getTiledMap(), "Coins").getObjects()) {
			if (object instanceof RectangleMapObject)
				coins.add(new Coin(game,
						TiledMapUtils.getRectanglePosition(((RectangleMapObject) object).getRectangle())));
			else
				throw new IllegalStateException(
						"[ERROR] The world contain illegal coins object. Coins need to be Rectangle objects");
		}
		canons = new ArrayList<CanonTrap>();
		for (MapObject object : TiledMapUtils.getLayer(world.getTiledMap(), "Canons").getObjects()) {
			if (object instanceof RectangleMapObject)
				canons.add(new CanonTrap(game,
						TiledMapUtils.getRectanglePosition(((RectangleMapObject) object).getRectangle())));
			else
				throw new IllegalStateException(
						"[ERROR] The world contain illegal canons object. Canons need to be Rectangle objects");
		}
		elevators = new ArrayList<Elevator>();
		for (MapObject object : TiledMapUtils.getLayer(world.getTiledMap(), "Elevators").getObjects()) {
			if (object instanceof RectangleMapObject)
				elevators.add(new Elevator(game,
						TiledMapUtils.getRectanglePosition(((RectangleMapObject) object).getRectangle())));
			else
				throw new IllegalStateException(
						"[ERROR] The world contain illegal elevators object. Elevators need to be Rectangle objects");
		}
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
		ResourceManager.getInstance().getCurrentWorld().render(camera, batch);
		player.render();
		if (debug)
			dren.render(ResourceManager.getInstance().getCurrentWorld().getWorld(), camera.combined);
		for (Coin coin : coins) {
			coin.update();
			coin.render();
		}
		for (CanonTrap canon : canons)
			canon.render();
		for (Elevator elevator : elevators) {
			elevator.update();
			elevator.render();
		}
	}

	public void dispose() {
		player.clear();
		for (Coin coin : coins)
			coin.dispose();
		for (CanonTrap canon : canons)
			canon.dispose();
	}

	public Player getPlayer() {
		return player;
	}

}
