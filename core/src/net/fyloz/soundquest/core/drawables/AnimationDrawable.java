package net.fyloz.soundquest.core.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;

public class AnimationDrawable implements Drawable {

	private Animation<TextureRegion> animation;

	private float fps = 10;
	private float elapsedTime = 0f;
	private boolean isLooping = true;

	private CameraType cameraType;

	public AnimationDrawable(TextureAtlas atlas, CameraType cameraType) {
		animation = new Animation<TextureRegion>(1 / this.fps, atlas.getRegions());
		animation.setPlayMode(PlayMode.LOOP);
		this.cameraType = cameraType;
	}

	public AnimationDrawable(TextureAtlas atlas, CameraType cameraType, PlayMode mode) {
		animation = new Animation<TextureRegion>(1 / this.fps, atlas.getRegions());
		animation.setPlayMode(mode);
		this.cameraType = cameraType;
	}

	public AnimationDrawable(TextureAtlas atlas, CameraType cameraType, PlayMode mode, int fps) {
		this.fps = fps;
		animation = new Animation<TextureRegion>(1 / this.fps, atlas.getRegions());
		animation.setPlayMode(mode);
		this.cameraType = cameraType;
	}

	public AnimationDrawable(TextureAtlas atlas, CameraType cameraType, PlayMode mode, int fps, boolean isLooping) {
		this.fps = fps;
		this.isLooping = isLooping;
		animation = new Animation<TextureRegion>(1 / this.fps, atlas.getRegions());
		animation.setPlayMode(mode);
		this.cameraType = cameraType;
	}

	@Override
	public TextureRegion getTextureRegion() {
		return animation.getKeyFrame(elapsedTime, isLooping);
	}

	@Override
	public void update() {
		elapsedTime += Gdx.graphics.getDeltaTime();
	}

	@Override
	public void render(SoundQuest game, Vector2 pos) {
		render(game, pos.x, pos.y, getWidth() / game.PPM, getHeight() / game.PPM);
	}

	@Override
	public void render(SoundQuest game, float x, float y, float width, float height) {
		if (cameraType == CameraType.Dynamic)
			game.batch.setProjectionMatrix(game.dynamicCamera.combined);
		if (cameraType == CameraType.Static)
			game.batch.setProjectionMatrix(game.staticCamera.combined);
		game.batch.begin();
		game.batch.draw(getTextureRegion(), x - (getWidth() / game.PPM / 2), y - (getHeight() / game.PPM / 2), width, height);
		game.batch.end();
	}

	@Override
	public CameraType getCameraType() {
		return cameraType;
	}

	@Override
	public float getWidth() {
		return getTextureRegion().getRegionWidth();
	}

	@Override
	public float getHeight() {
		return getTextureRegion().getRegionHeight();
	}

}
