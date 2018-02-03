package net.fyloz.soundquest.physics.events.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import net.fyloz.soundquest.physics.FixtureProperties;

public class TrapContactListener implements Listener {
	Object userDataA;
	Object userDataB;

	@Override
	public void beginContact(Contact contact, FixtureProperties properties) {

	/**if (userDataA.equals("player") && userDataB.equals("spike")) {
			Player player = (Player) ResourceManager.getInstance().getEntitie("player");
			Object spikeFix = contact.getFixtureB().getUserData();
			player.setLife(player.getLife() - 50);
			if (spikeFix.equals(true)) {
				player.setIsTouchingGround(true);
			}
		}**/
	}

	@Override
	public void endContact(Contact contact, FixtureProperties properties) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold, FixtureProperties properties) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse, FixtureProperties properties) {
		// TODO Auto-generated method stub

	}

}
