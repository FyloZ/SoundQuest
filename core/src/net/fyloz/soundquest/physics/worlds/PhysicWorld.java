package net.fyloz.soundquest.physics.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.entities.mapping.Map;
import net.fyloz.soundquest.physics.WorldGenerator;
import net.fyloz.soundquest.physics.events.ContactManager;
import net.fyloz.soundquest.utils.ResourceManager;

public class PhysicWorld implements ContactListener {
	private World world;
	private Map map;

	private String id;

	public PhysicWorld(SoundQuest game, String id) {
		this.id = id;
		world = new World(new Vector2(0, -20f), true);
		world.setContactListener(new ContactManager());

		map = new Map(game, id);
		updateCurrentWorld();
	}

	public void addCollisions() {
		WorldGenerator.generateWorldBody(this);
	}

	public Body createBody(BodyDef bd) {
		return world.createBody(bd);
	}

	public World getWorld() {
		return world;
	}

	public TiledMap getTiledMap() {
		return map.getTiledMap();
	}

	public Map getMap() {
		return map;
	}

	public void updateCurrentWorld() {
		ResourceManager.getInstance().setCurrentWorld(this.id, this);
	}

	public void update() {
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}

	public void render() {
		map.render();
	}

	public void dispose() {
		map.dispose();
	}

	@Override
	public void beginContact(Contact contact) {
		Object uda = (contact.getFixtureA().getBody().getUserData() != null)
				? contact.getFixtureA().getBody().getUserData()
				: null;
		Object udb = (contact.getFixtureB().getBody().getUserData() != null)
				? contact.getFixtureB().getBody().getUserData()
				: null;
		if (udb != null && udb.equals("player")) {
			((Player) ResourceManager.getInstance().getEntitie("player")).setIsTouchingGround(true);
		}
		if (udb != null && udb.equals("spike")) {
			Player player = (Player) ResourceManager.getInstance().getEntitie("player");
			player.setLife(player.getLife() - 50);
		}
		if (udb != null && udb.equals("coin") && uda != null && uda.equals("player")) {
			Player player = ((Player) ResourceManager.getInstance().getEntitie("player"));
			player.setCoinAmount(player.getCoinAmount() + 1);
			// contact.getFixtureB().getBody().destroyFixture(contact.getFixtureB());
			// contact.getFixtureB().getBody().setGravityScale(0);
			contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(0, 20),
					contact.getFixtureB().getBody().getWorldCenter(), true);
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}
}
