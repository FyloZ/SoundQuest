package net.fyloz.soundquest.core.drawables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;

public class SpriteDrawable implements Drawable {

	private Sprite sprite;
	private CameraType cameraType;

	public SpriteDrawable(Texture texture, CameraType cameraType) {
		this.sprite = new Sprite(texture);
		this.cameraType = cameraType;
	}

	public SpriteDrawable(Sprite sprite, CameraType cameraType) {
		this.sprite = sprite;
		this.cameraType = cameraType;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(SoundQuest game, Vector2 pos) {
		render(game, pos.x, pos.y, getWidth() / game.PPM, getHeight() / game.PPM);
	}

	@Override
	public void render(SoundQuest game, float x, float y, float width, float height) {
		if (cameraType == CameraType.Static)
			game.batch.setProjectionMatrix(game.staticCamera.combined);
		if (cameraType == CameraType.Dynamic)
			game.batch.setProjectionMatrix(game.dynamicCamera.combined);

		game.batch.begin();
		game.batch.draw(getTextureRegion(), x - (getWidth() / game.PPM / 2), y - (getHeight() / game.PPM / 2), width, height);
		game.batch.end();
	}

	@Override
	public TextureRegion getTextureRegion() {
		return sprite;
	}

	@Override
	public CameraType getCameraType() {
		return cameraType;
	}

	@Override
	public float getWidth() {
		return sprite.getRegionWidth();
	}

	@Override
	public float getHeight() {
		return sprite.getRegionHeight();
	}
}
