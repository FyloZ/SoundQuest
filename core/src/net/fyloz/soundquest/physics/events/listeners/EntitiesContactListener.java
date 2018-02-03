package net.fyloz.soundquest.physics.events.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.utils.ResourceManager;

public class EntitiesContactListener implements Listener {

	@Override
	public void beginContact(Contact contact, FixtureProperties properties) {
		Player player = (Player) ResourceManager.getInstance().getEntitie("player");
		if (properties.throwDamages()) {
			player.setLife(player.getLife() - properties.getTouchDamages());
		}
	}

	@Override
	public void endContact(Contact contact, FixtureProperties properties) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold, FixtureProperties properties) {
		if (!properties.collideWithEntities()) {
			contact.setEnabled(false);
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse, FixtureProperties properties) {

	}
}
