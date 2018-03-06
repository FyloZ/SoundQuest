package net.fyloz.soundquest.entities;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.Direction;
import net.fyloz.soundquest.core.Movement;
import net.fyloz.soundquest.core.Point;
import net.fyloz.soundquest.core.drawables.AnimationDrawable;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.TiledMapUtils;
import net.fyloz.soundquest.utils.resources.Bits;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class Elevator extends LivingEntitie {

	private SoundQuest game;

	private LivingEntitieBody body;
	private FixtureProperties properties;

	private AnimationDrawable animation;

	private Point currentPos;
	private Point maxPoint;
	private Point minPoint;
	/**
	 * private Movement movementUp; private Movement movementDown;
	 **/
	private Direction direction = Direction.UP;
	private float speed = 30f;

	public Elevator(SoundQuest game, Vector2 currentPos) {
		super(game);
		this.game = game;
		this.currentPos = new Point(currentPos);

		this.maxPoint = new Point(TiledMapUtils.getRectanglePosition(
				TiledMapUtils.getRectangle(ResourceManager.getInstance().getCurrentWorld().getTiledMap(), "ElevatorsRoads", 0)));
		this.minPoint = new Point(this.currentPos.getVector());
		/**
		 * this.movementUp = new Movement(this.maxPoint, this.currentPos, speed);
		 * this.movementDown = new Movement(this.minPoint, this.currentPos, speed);
		 **/

		animation = new AnimationDrawable((TextureAtlas) game.manager.get("textures/entities/elevator.txt"),
				CameraType.Dynamic, PlayMode.LOOP, 10);

		properties = new FixtureProperties(UserDatas.ENTITY_UD.toString(), UserDatas.ENTITY_UD.toString(), this);
		properties.setIsGround(true);

		body = B2DUtils.createLEBody(properties, BodyType.StaticBody, currentPos, animation.getWidth() / game.PPM / 2,
				animation.getHeight() / game.PPM / 2, UserDatas.ENTITY_UD, Bits.PHYSIC_ENTITY, Bits.PHYSIC_ENTITY_MASK);
		body.getBody().setFixedRotation(true);
		body.getBody().setGravityScale(0);
		setBody(body);
	}

	@Override
	public void update() {

		animation.update();

		if (direction == Direction.UP) {
			currentPos.setY(currentPos.getY() + speed / 1000);
			if (currentPos.getY() >= maxPoint.getY())
				direction = Direction.DOWN;
		} else {
			currentPos.setY(currentPos.getY() - speed / 1000);
			if (currentPos.getY() <= minPoint.getY())
				direction = Direction.UP;
		}

		/**
		 * if (!movementUp.isCompleted()) { movementUp.update();
		 * 
		 * if (movementUp.isCompleted()) { movementDown.reset(currentPos); } } else if
		 * (!movementDown.isCompleted()) { movementDown.update(); } else {
		 * movementUp.reset(currentPos); }
		 **/
	}

	@Override
	public void render() {
		getBody().getBody().setTransform(currentPos.getVector(), 0f);
		animation.render(game, currentPos.getVector());
	}
	
	public void dispose() {
		body.clear();
	}
}
