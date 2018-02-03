package net.fyloz.soundquest.entities;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.Direction;
import net.fyloz.soundquest.core.Point;
import net.fyloz.soundquest.core.drawables.AnimationDrawable;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.resources.Bits;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class Elevator extends LivingEntitie {

	private SoundQuest game;

	private LivingEntitieBody body;
	private FixtureProperties properties;

	private AnimationDrawable animation;

	private Vector2 pos;
	private Point maxPoint;
	private Point minPoint;
	private Direction direction = Direction.UP;

	public Elevator(SoundQuest game, Vector2 pos) {
		super(game);
		this.game = game;
		this.pos = pos;

		maxPoint = new Point(944 / game.PPM, 528 / game.PPM);
		minPoint = new Point(pos);

		animation = new AnimationDrawable((TextureAtlas) game.manager.get("textures/entities/elevator.txt"),
				CameraType.Dynamic, PlayMode.LOOP, 10);

		properties = new FixtureProperties(UserDatas.ENTITY_UD.toString(), UserDatas.ENTITY_UD.toString(), this);
		properties.setIsGround(true);

		body = B2DUtils.createLEBody(properties, BodyType.StaticBody, pos, animation.getWidth() / game.PPM / 2,
				animation.getHeight() / game.PPM / 2, UserDatas.ENTITY_UD, Bits.PHYSIC_ENTITY, Bits.PHYSIC_ENTITY_MASK);
		body.getBody().setFixedRotation(true);
		body.getBody().setGravityScale(0);
		setBody(body);
	}

	@Override
	public void update() {
		animation.update();
	}

	@Override
	public void render() {
		// Distance parcourue en 1ms
		float speed = (maxPoint.getY() - minPoint.getY()) / (15f * 50);

		if (maxPoint.isUpper(pos) && direction.equals(Direction.UP))
			pos.y += speed;
		else
			direction = Direction.DOWN;

		if (minPoint.isUnder(pos) && direction.equals(Direction.DOWN))
			pos.y -= speed;
		else
			direction = Direction.UP;

		this.getBody().getBody().setTransform(pos, 0f);
		animation.render(game, pos);
	}
}
