package net.fyloz.soundquest.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;

public class B2DUtils {

	private B2DUtils() {

	}

	public static BodyDef createBodyDef() {
		return createBodyDef(BodyType.StaticBody);
	}

	public static BodyDef createBodyDef(BodyType type) {
		return createBodyDef(type, 0, 0);
	}

	public static BodyDef createBodyDef(BodyType type, float x, float y) {
		return createBodyDef(type, new Vector2(x, y));
	}

	public static BodyDef createBodyDef(BodyType type, Vector2 position) {
		BodyDef bd = new BodyDef();
		bd.type = type;
		bd.position.set(position);

		return bd;
	}

	public static LivingEntitieBody createLEBody(FixtureProperties properties, BodyType bodyType, Vector2 position,
			float shapew, float shapeh, Object bodyUD, short category, short mask) {
		LivingEntitieBody body;
		PhysicWorld world = ResourceManager.getInstance().getCurrentWorld();

		BodyDef bd = B2DUtils.createBodyDef(bodyType, position);

		Body b = world.createBody(bd);
		b.setUserData(bodyUD);

		body = new LivingEntitieBody(b);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(shapew, shapeh);
		body.setWidth(shapew);
		body.setHeight(shapeh);

		Fixture f = B2DUtils.createFixture(b, shape, 0.6f, category, mask);
		f.setUserData(properties);
		body.setFixture(f);
		shape.dispose();

		return body;
	}

	public static StaticEntitieBody createSEBody(FixtureProperties properties, BodyType bodyType, Vector2 position,
			float shapew, float shapeh, Object bodyUD, short category, short mask) {
		StaticEntitieBody body;
		PhysicWorld world = ResourceManager.getInstance().getCurrentWorld();

		BodyDef bd = B2DUtils.createBodyDef(bodyType, position);

		Body b = world.createBody(bd);
		b.setUserData(bodyUD);

		body = new StaticEntitieBody(b);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(shapew, shapeh);
		body.setWidth(shapew);
		body.setHeight(shapeh);

		Fixture f = B2DUtils.createFixture(b, shape, 0, category, mask);
		f.setUserData(properties);
		body.setFixture(f);
		shape.dispose();

		return body;
	}

	public static Fixture createFixture(Body body) {
		return createFixture(body, new PolygonShape());
	}

	public static Fixture createFixture(Body body, Shape shape) {
		return createFixture(body, shape, 0f);
	}

	public static Fixture createFixture(Body body, Shape shape, float density) {
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;

		return body.createFixture(fd);
	}

	public static Fixture createFixture(Body body, Shape shape, float density, short category, short mask) {
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.filter.categoryBits = category;
		fd.filter.maskBits = mask;
		fd.friction = 0.7f;

		return body.createFixture(fd);
	}

	/**
	 * public static Fixture createFixture(Body body, Shape shape, float density,
	 * short category, short[] notColliding) { FixtureDef fd = new FixtureDef();
	 * fd.shape = shape; fd.density = density; fd.filter.categoryBits = category;
	 * 
	 * return body.createFixture(fd); }
	 **/

}
