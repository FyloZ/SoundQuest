package net.fyloz.soundquest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;
import net.fyloz.soundquest.utils.ResourceManager;

public class StaticEntitie implements Entitie {
	private Sprite sprite;
	private SoundQuest game;

	private StaticEntitieBody body;

	private float touchDamages = 0;
	private boolean customRender = false;

	public StaticEntitie(SoundQuest game) {
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

	public void setBody(StaticEntitieBody body) {
		this.body = body;
	}

	@Override
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		this.sprite.setSize(sprite.getWidth() / 16f, sprite.getHeight() / 16f);
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
		if (!customRender) {
			game.batch.setProjectionMatrix(ResourceManager.getInstance().getDynamicCamera().combined);

			game.batch.begin();
			game.batch.draw(sprite, body.getPosition().x - (sprite.getWidth() / 2),
					body.getPosition().y - (sprite.getHeight() / 2), sprite.getWidth() / 1.5f,
					sprite.getHeight() / 1.5f);
			game.batch.end();
		}
	}

	@Override
	public void clear() {
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

	public StaticEntitieBody getBody() {
		return body;
	}

	public float getTouchDamages() {
		return touchDamages;
	}

	public void useCustomRendering(boolean customRender) {
		this.customRender = customRender;
	}
}
