package net.fyloz.soundquest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;

public class LivingEntitie implements Entitie {
	private Sprite sprite;
	private SoundQuest game;

	private int i = 1;
	private LivingEntitieBody body;

	private float maxSpeed = 3f;
	private float touchDamages = 0;

	private int life = 100;

	private boolean isTouchingGround = false;

	public LivingEntitie(SoundQuest game) {
		this.game = game;
	}

	@Override
	public void setX(float x) {
		body.setX(x);
	}

	@Override
	public void setY(float y) {
		body.setY(y);
	}

	@Override
	public void setPosition(Vector2 position) {
		body.setPosition(position);
	}

	public void setBody(LivingEntitieBody body) {
		this.body = body;
	}

	@Override
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		this.sprite.setSize(sprite.getWidth() / 16f, sprite.getHeight() / 16f);
	}

	public void setIsTouchingGround(boolean isTouchingGround) {
		this.isTouchingGround = isTouchingGround;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setTouchDamages(float touchDamages) {
		this.touchDamages = touchDamages;
	}

	@Override
	public void update() {
		float x = body.getPosition().x;
		float y = body.getPosition().y;
		setPosition(new Vector2(x, y));

		body.update();
	}

	@Override
	public void render() {
		game.batch.setProjectionMatrix(game.dynamicCamera.combined);
		game.batch.begin();
		game.batch.draw(sprite, getBody().getPosition().x, getBody().getPosition().y);
		game.batch.end();
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void move(float x, float y) {
		move(new Vector2(x, y));
	}

	public void move(Vector2 vector) {
		if (getBody().getBody().getLinearVelocity().x <= maxSpeed
				|| getBody().getBody().getLinearVelocity().x >= -maxSpeed)
			getBody().getBody().applyLinearImpulse(vector, getBody().getBody().getWorldCenter(), true);
	}

	public void jump() {
		if (isTouchingGround) {
			getBody().getBody().applyLinearImpulse(new Vector2(0, 11f), getBody().getBody().getWorldCenter(), true);
			isTouchingGround = false;
		}
	}

	@Override
	public void clear() {
	}

	public float getSpeed() {
		return maxSpeed;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public float getX() {
		return (float) body.getPosition().x;
	}

	@Override
	public float getY() {
		return (float) body.getPosition().y;
	}

	@Override
	public Vector2 getPosition() {

		return body.getPosition();
	}

	public LivingEntitieBody getBody() {
		return body;
	}

	public int getLife() {
		return life;
	}

	public float getTouchDamages() {
		return touchDamages;
	}

	public boolean isTouchingGround() {
		return isTouchingGround;
	}
}
