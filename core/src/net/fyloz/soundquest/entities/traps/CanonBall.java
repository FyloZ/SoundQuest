package net.fyloz.soundquest.entities.traps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.LivingEntitie;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.resources.Bits;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class CanonBall extends LivingEntitie {

	private SoundQuest game;

	private FixtureProperties properties;
	private boolean isGround = true;
	private boolean throwDamages = true;
	private int touchDamages = 40;
	private boolean collideWithEntities = false;

	private LivingEntitieBody body;
	private CanonTrap motherCanon;

	private float angle;
	private boolean cbr = false;

	public CanonBall(SoundQuest game, Vector2 position, CanonTrap motherCanon) {
		super(game);
		this.game = game;
		this.motherCanon = motherCanon;
		setSprite(new Sprite((Texture) game.manager.get("textures/traps/canon_bullet.png")));

		properties = new FixtureProperties(UserDatas.TRAP_UD.toString(), UserDatas.CANONBALL_UD.toString(), this);
		properties.setIsGround(isGround);
		properties.setThrowDamages(throwDamages);
		properties.setTouchDamages(touchDamages);
		properties.setCollideWithEntities(collideWithEntities);

		PhysicWorld world = ResourceManager.getInstance().getCurrentWorld();

		BodyDef bd = B2DUtils.createBodyDef(BodyType.DynamicBody, position);

		Body b = world.createBody(bd);
		b.setUserData(UserDatas.TRAP_UD);

		body = new LivingEntitieBody(b);

		body.setBodyDef(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getSprite().getWidth() * 0.7f, getSprite().getHeight() * 0.7f);

		Fixture f = B2DUtils.createFixture(b, shape, 1, Bits.PHYSIC_ENTITY, Bits.CANONBALL_MASK);
		f.setUserData(properties);
		body.setFixture(f);
		shape.dispose();

		body.setWidth(0.7f);
		body.setHeight(0.7f);

		body.getBody().setGravityScale(0);

		setBody(body);
	}

	public void render() {
		if (cbr) {
			// float xPos = getBody().getPosition().x;
			// float yPos = motherCanon.getPosition().y + (motherCanon.getPosition().x -
			// xPos) / (float) Math.cos(angle);

			getBody().getBody().setTransform(
					new Vector2(getBody().getBody().getPosition().x, getBody().getBody().getPosition().y), 0);

			game.batch.setProjectionMatrix(game.dynamicCamera.combined);
			game.batch.begin();
			game.batch.draw(getSprite(), getBody().getPosition().x, getBody().getPosition().y, getBody().getWidth(),
					getBody().getHeight());
			game.batch.end();
		}
	}

	public void dispose() {
		getBody().clear();
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public void canBeRendered(boolean cbr) {
		this.cbr = cbr;
	}
}
