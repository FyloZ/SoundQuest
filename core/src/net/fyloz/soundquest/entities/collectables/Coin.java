package net.fyloz.soundquest.entities.collectables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.drawables.SpriteDrawable;
import net.fyloz.soundquest.entities.StaticEntitie;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.resources.Bits;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class Coin extends StaticEntitie implements Collectable {
	private SoundQuest game;

	private SpriteDrawable sprite;

	private FixtureProperties properties;
	private StaticEntitieBody body;

	boolean collideWithEntities = false;
	Vector2 position;

	private boolean toDestroy = false;

	public Coin(SoundQuest game, Vector2 position) {
		super(game);
		this.game = game;
		this.position = position;

		sprite = new SpriteDrawable((Texture) game.manager.get("textures/collectables/note.png"), CameraType.Dynamic);

		properties = new FixtureProperties(UserDatas.COLLECTABLE_UD.toString(), UserDatas.COIN_UD.toString(), this);
		properties.setCollideWithEntities(collideWithEntities);

		body = B2DUtils.createSEBody(properties, BodyType.StaticBody, position, sprite.getWidth() / game.PPM / 2,
				sprite.getHeight() / game.PPM / 2, UserDatas.COLLECTABLE_UD, Bits.COLLECTABLE_ENTITY, Bits.COIN_MASK);

		body.getBody().setFixedRotation(true);
		body.getBody().setGravityScale(0);

		super.setBody(body);
		super.setPosition(position);

	}

	@Override
	public void update() {
		if (toDestroy)
			getBody().getBody().setTransform(0, 0, 0);
	}

	@Override
	public void render() {
		sprite.render(game, getBody().getPosition());
	}

	@Override
	public void dispose() {
		getBody().clear();
	}

	@Override
	public void setCollectAnimation(Animation<Sprite> animation) {
		throw new UnsupportedOperationException();
	}

	public void remove() {
		// Obliger car le monde est verrouillé durant les contacts
		toDestroy = true;
	}

}
