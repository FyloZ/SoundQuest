package net.fyloz.soundquest.physics.events;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.entities.collectables.Coin;
import net.fyloz.soundquest.entities.traps.CanonBall;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.events.listeners.CollectableContactManager;
import net.fyloz.soundquest.physics.events.listeners.EntitiesContactListener;
import net.fyloz.soundquest.physics.events.listeners.Listener;
import net.fyloz.soundquest.physics.events.listeners.TrapContactListener;
import net.fyloz.soundquest.physics.events.listeners.WorldContactListener;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class ContactManager implements ContactListener {

	Map<String, Listener> listeners = new HashMap<String, Listener>();

	Object userDataA;
	Object userDataB;
	FixtureProperties propertiesA;
	FixtureProperties propertiesB;

	public ContactManager() {
		listeners.put(UserDatas.ENTITY_UD.toString(), new EntitiesContactListener());
		listeners.put(UserDatas.TRAP_UD.toString(), new TrapContactListener());
		// TODO changer le nom du listener
		listeners.put(UserDatas.COLLECTABLE_UD.toString(), new CollectableContactManager());
		listeners.put(UserDatas.WORLD_UD.toString(), new WorldContactListener());
	}

	@Override
	public void beginContact(Contact contact) {
		userDataA = (contact.getFixtureA().getBody().getUserData() != null)
				? contact.getFixtureA().getBody().getUserData()
				: null;
		userDataB = (contact.getFixtureB().getUserData() != null) ? contact.getFixtureB().getUserData() : null;

		if (!(contact.getFixtureA().getUserData() instanceof FixtureProperties)
				|| !(contact.getFixtureB().getUserData() instanceof FixtureProperties)) {
			System.out.println("[FATAL] Contact error: UserData of Fixture need to be FixtureProperties");
			System.exit(1);
		}
		propertiesA = (FixtureProperties) contact.getFixtureA().getUserData();
		propertiesB = (FixtureProperties) userDataB;

		// listeners.get(userDataA.toString()).beginContact(contact, (FixtureProperties)
		// userDataB);

		Player player = (Player) ResourceManager.getInstance().getEntitie("player");

		if ((propertiesA.throwDamages() || propertiesB.throwDamages())
				&& (propertiesA.getType().equals(UserDatas.PLAYER_UD)
						|| propertiesB.getType().equals(UserDatas.PLAYER_UD))) {
			player.setLife(player.getLife() - propertiesB.getTouchDamages());
		}
		if ((propertiesA.isGround() || propertiesB.isGround()) && (propertiesA.getType().equals(UserDatas.PLAYER_UD)
				|| propertiesB.getType().equals(UserDatas.PLAYER_UD))) {
			player.setIsTouchingGround(true);
		}
		if (propertiesA.getType().equals(UserDatas.COIN_UD) || propertiesB.getType().equals(UserDatas.COIN_UD)) {
			player.setCoinAmount(player.getCoinAmount() + 1);
			if (propertiesA.getType().equals(UserDatas.COIN_UD))
				((Coin) propertiesA.getObject()).remove();
			else
				((Coin) propertiesB.getObject()).remove();
		}
		if ((propertiesA.getType().equals(UserDatas.CANONBALL_UD) && propertiesB.getType().equals(UserDatas.WORLD_UD))
				|| (propertiesA.getType().equals(UserDatas.WORLD_UD)
						&& propertiesB.getType().equals(UserDatas.CANONBALL_UD))) {
			if (propertiesA.getType().equals(UserDatas.CANONBALL_UD)) {
				((CanonBall) propertiesA.getObject()).getBody().getBody().setGravityScale(1);
			} else {
				((CanonBall) propertiesB.getObject()).getBody().getBody().setGravityScale(1);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		userDataA = (contact.getFixtureA().getBody().getUserData() != null)
				? contact.getFixtureA().getBody().getUserData()
				: null;
		userDataB = (contact.getFixtureB().getUserData() != null) ? contact.getFixtureB().getUserData() : null;

		if (!(contact.getFixtureA().getUserData() instanceof FixtureProperties)
				|| !(contact.getFixtureB().getUserData() instanceof FixtureProperties)) {
			System.out.println("[FATAL] Contact error: UserData of Fixture need to be FixtureProperties");
			System.exit(1);
		}
		propertiesA = (FixtureProperties) contact.getFixtureA().getUserData();
		propertiesB = (FixtureProperties) userDataB;

		Player player = (Player) ResourceManager.getInstance().getEntitie("player");

		// listeners.get(userDataA.toString()).endContact(contact, (FixtureProperties)
		// userDataB);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		userDataA = (contact.getFixtureA().getBody().getUserData() != null)
				? contact.getFixtureA().getBody().getUserData()
				: null;
		userDataB = (contact.getFixtureB().getBody().getUserData() != null)
				? contact.getFixtureB().getBody().getUserData()
				: null;

		if (!(contact.getFixtureA().getUserData() instanceof FixtureProperties)
				|| !(contact.getFixtureB().getUserData() instanceof FixtureProperties)) {
			System.out.println("[FATAL] Contact error: UserData of Fixture need to be FixtureProperties");
			System.exit(1);
		}
		propertiesA = (FixtureProperties) contact.getFixtureA().getUserData();
		propertiesB = (FixtureProperties) contact.getFixtureB().getUserData();

		Player player = (Player) ResourceManager.getInstance().getEntitie("player");

		if (!propertiesA.collideWithEntities() && !propertiesB.collideWithEntities()) {
			contact.setEnabled(false);
		}
		// listeners.get(userDataA.toString()).preSolve(contact, oldManifold,
		// (FixtureProperties) userDataB);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		userDataA = (contact.getFixtureA().getBody().getUserData() != null)
				? contact.getFixtureA().getBody().getUserData()
				: null;
		userDataB = (contact.getFixtureB().getUserData() != null) ? contact.getFixtureB().getUserData() : null;

		if (!(contact.getFixtureA().getUserData() instanceof FixtureProperties)
				|| !(contact.getFixtureB().getUserData() instanceof FixtureProperties)) {
			System.out.println("[FATAL] Contact error: UserData of Fixture need to be FixtureProperties");
			System.exit(1);
		}
		propertiesA = (FixtureProperties) contact.getFixtureA().getUserData();
		propertiesB = (FixtureProperties) userDataB;

		Player player = (Player) ResourceManager.getInstance().getEntitie("player");

		// listeners.get(userDataA.toString()).postSolve(contact, impulse,
		// (FixtureProperties) userDataB);
	}

}
