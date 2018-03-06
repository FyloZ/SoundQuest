package net.fyloz.soundquest.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.drawables.AnimationDrawable;
import net.fyloz.soundquest.core.drawables.SpriteDrawable;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.TiledMapUtils;
import net.fyloz.soundquest.utils.resources.Bits;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class Player extends LivingEntitie {
	private LivingEntitieBody body;
	private SoundQuest game;

	private FixtureProperties properties;

	private int life = 100;
	private boolean hasLife = true;
	private boolean collideWithEntities = false;
	private boolean takeDamages = true;

	private SpriteDrawable idleSprite;
	private AnimationDrawable jumpAnimation;
	private AnimationDrawable walkAnimation;

	private Moves state = Moves.IDLE;
	private boolean facingRight = true;

	private int coinAmount = 0;

	public Player(SoundQuest game) {
		super(game);
		this.game = game;

		idleSprite = new SpriteDrawable((Texture) game.manager.get("textures/entities/player/idle.png"),
				CameraType.Dynamic);
		jumpAnimation = new AnimationDrawable((TextureAtlas) game.manager.get("textures/entities/player/jump.txt"),
				CameraType.Dynamic, PlayMode.NORMAL);
		walkAnimation = new AnimationDrawable((TextureAtlas) game.manager.get("textures/entities/player/walk.txt"),
				CameraType.Dynamic);

		properties = new FixtureProperties(UserDatas.ENTITY_UD.toString(), UserDatas.PLAYER_UD.toString(), this);
		properties.setLife(life);
		properties.setHasLife(hasLife);
		properties.setCollideWithEntities(collideWithEntities);
		properties.setTakeDamages(takeDamages);

		body = B2DUtils.createLEBody(properties, BodyType.DynamicBody,
				TiledMapUtils.getRectanglePosition(TiledMapUtils
						.getRectangle(ResourceManager.getInstance().getCurrentWorld().getTiledMap(), "Spawnpoint", 0)),
				0.5f, 0.9f, UserDatas.ENTITY_UD, Bits.PLAYER_ENTITY, Bits.PLAYER_MASK);
		body.getBody().setFixedRotation(true);
		setBody(body);

		ResourceManager.getInstance().addEntitie("player", this);
	}

	public enum Moves {
		IDLE, WALK, JUMP
	}

	public void updatekeys() {
		walkAnimation.update();
		// Key handling
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			state = Moves.WALK;
			facingRight = true;
			move(new Vector2(0.2f, 0));
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			state = Moves.WALK;
			facingRight = false;
			move(new Vector2(-0.2f, 0));
		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.SPACE)) {
			state = Moves.JUMP;
			jump();
		}

		if (Gdx.input.isKeyJustPressed(Keys.K)) {
			this.setLife(0);
		}

		ResourceManager.getInstance().getDynamicCamera().followPlayer(this);

	}

	@Override
	public void render() {
		if(this.getBody().getBody().getPosition().y <= 0)
			this.setLife(0);
		// Requis parce que la Sprite est en 32x42px au lieu de 16x16
		float ratio = getBody().getHeight() / (idleSprite.getHeight() / (game.PPM * 2));
		if (state == Moves.IDLE)
			if (facingRight) {
				idleSprite.render(game, getBody().getPosition().x + 0.3f, getBody().getPosition().y + 0.4f,
						(idleSprite.getWidth() / game.PPM) * ratio, (idleSprite.getHeight() / game.PPM) * ratio);
			} else {
				idleSprite.render(game, getBody().getPosition().x + (idleSprite.getWidth() / game.PPM / 1.2f) * 1.015f,
						getBody().getPosition().y + 0.4f, (idleSprite.getWidth() / game.PPM) * -ratio,
						(idleSprite.getHeight() / game.PPM) * ratio);
			}
		if (state == Moves.WALK)
			if (facingRight) {
				walkAnimation.render(game, getBody().getPosition().x + 0.3f, getBody().getPosition().y + 0.4f,
						(walkAnimation.getWidth() / game.PPM) * ratio, (walkAnimation.getHeight() / game.PPM) * ratio);
			} else {
				walkAnimation.render(game,
						getBody().getPosition().x + (walkAnimation.getWidth() / game.PPM / 1.2f) * 1.015f,
						getBody().getPosition().y + 0.4f, (walkAnimation.getWidth() / game.PPM) * -ratio,
						(walkAnimation.getHeight() / game.PPM) * ratio);
			}
		if (state == Moves.JUMP)
			if (facingRight) {
				jumpAnimation.render(game, getBody().getPosition().x + 0.3f, getBody().getPosition().y + 0.4f,
						(jumpAnimation.getWidth() / game.PPM) * ratio, (jumpAnimation.getHeight() / game.PPM) * ratio);
			} else {
				jumpAnimation.render(game,
						getBody().getPosition().x + (jumpAnimation.getWidth() / game.PPM / 1.2f) * 1.015f,
						getBody().getPosition().y + 0.4f, (walkAnimation.getWidth() / game.PPM) * -ratio,
						(jumpAnimation.getHeight() / game.PPM) * ratio);
			}

		if (state != Moves.JUMP && this.isTouchingGround())
			state = Moves.IDLE;
	}

	public void setCoinAmount(int amount) {
		this.coinAmount = amount;
	}

	public void setState(Moves move) {
		this.state = move;
	}

	public int getCoinAmount() {
		return coinAmount;
	}
}
