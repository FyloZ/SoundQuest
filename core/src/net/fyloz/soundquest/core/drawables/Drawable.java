package net.fyloz.soundquest.core.drawables;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;

public interface Drawable {

	void update();

	void render(SoundQuest game, Vector2 pos);
	
	void render(SoundQuest game, float x, float y, float width, float height);

	TextureRegion getTextureRegion();

	CameraType getCameraType();

	float getWidth();

	float getHeight();
}
