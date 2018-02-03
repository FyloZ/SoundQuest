package net.fyloz.soundquest.physics.events.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import net.fyloz.soundquest.physics.FixtureProperties;

public interface Listener {
	public void beginContact(Contact contact, FixtureProperties properties);
	public void endContact(Contact contact, FixtureProperties properties);
	public void preSolve(Contact contact, Manifold manifold, FixtureProperties properties);
	public void postSolve(Contact contact, ContactImpulse impluse, FixtureProperties properties);
}
