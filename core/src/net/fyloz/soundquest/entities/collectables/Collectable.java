package net.fyloz.soundquest.entities.collectables;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Collectable {
	public void setSprite(Sprite texture);
	public void setCollectAnimation(Animation<Sprite> animation);
	
	public void render();
	public void dispose();
}
