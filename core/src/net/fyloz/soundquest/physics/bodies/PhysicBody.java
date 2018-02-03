package net.fyloz.soundquest.physics.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public interface PhysicBody {
	public void setBodyDef(BodyDef bd);

	public void setBody(Body body);

	public void setFixtureDef(FixtureDef fd);

	public void setFixture(Fixture fixture);

	public void setWidth(float width);

	public void setHeight(float height);

	public void setPosition(Vector2 position);

	public void setX(float x);

	public void setY(float y);
	
	public void update();
	
	public void clear();

	public BodyDef getBodyDef();

	public Body getBody();

	public FixtureDef getFixtureDef();

	public Fixture getFixture();

	public float getWidth();

	public float getHeight();

	public Vector2 getPosition();

	public float getX();

	public float getY();
}
