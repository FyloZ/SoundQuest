package net.fyloz.soundquest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.physics.bodies.PhysicBody;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;

public interface Entitie {

	public void setX(float x);

	public void setY(float y);

	public void setPosition(Vector2 position);

	public void setSprite(Sprite texture);

	public float getX();

	public float getY();

	public Vector2 getPosition();

	public Sprite getSprite();

	public void update();

	public void render();

	public void clear();
}
