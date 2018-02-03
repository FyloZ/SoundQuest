package net.fyloz.soundquest.physics.events.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class WorldContactListener implements Listener {

	@Override
	public void beginContact(Contact contact, FixtureProperties properties) {

		if (properties.getType().equals(UserDatas.PLAYER_UD)) {
			Player player = (Player) ResourceManager.getInstance().getEntitie("player");
			player.setIsTouchingGround(true);
		}
	}

	@Override
	public void endContact(Contact contact, FixtureProperties properties) {

	}

	@Override
	public void preSolve(Contact contact, Manifold manifold, FixtureProperties properties) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impluse, FixtureProperties properties) {

	}

}
