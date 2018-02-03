package net.fyloz.soundquest.physics.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class StaticEntitieBody implements PhysicBody{
	private BodyDef bd;
	private Body body;
	private FixtureDef fd;
	private Fixture fixture;
	private float width;
	private float height;
	private Vector2 position;
	
	public StaticEntitieBody(Body body) {
		this.body = body;
	}

	@Override
	public void setBodyDef(BodyDef bd) {
		this.bd = bd;
	}

	@Override
	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public void setFixtureDef(FixtureDef fd) {
		this.fd = fd;
	}

	@Override
	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	@Override
	public void setX(float x) {
		this.position.x = x;
	}

	@Override
	public void setY(float y) {
		y -= 10;
		this.position.y = y;
	}

	@Override
	public void update() {
	}

	@Override
	public void clear() {
	}

	@Override
	public BodyDef getBodyDef() {
		return bd;
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public FixtureDef getFixtureDef() {
		return fd;
	}

	@Override
	public Fixture getFixture() {
		return fixture;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public Vector2 getPosition() {
		return getBody().getPosition();
	}

	@Override
	public float getX() {
		return (float) position.x;
	}

	@Override
	public float getY() {
		return (float) position.y;
	}

}
