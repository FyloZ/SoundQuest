package net.fyloz.soundquest.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.drawables.SpriteDrawable;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.utils.ResourceManager;

public class LifeBar {
	private SoundQuest game;

	private Sprite hs;
	private SpriteDrawable heartSprite;
	private Sprite lhs;
	private SpriteDrawable lostHeartSprite;

	private Player player;

	public LifeBar(SoundQuest game) {
		this.game = game;

		hs = new Sprite((Texture) this.game.manager.get("textures/gui/heart.png"));
		heartSprite = new SpriteDrawable(hs, CameraType.Static);
		lhs = new Sprite((Texture) this.game.manager.get("textures/gui/heartlost.png"));
		lostHeartSprite = new SpriteDrawable(lhs, CameraType.Static);
		player = (Player) ResourceManager.getInstance().getEntitie("player");
	}

	public void render() {

		int nbHeart = player.getLife();

		for (int i = 5; i > 0; i--) {
			if (i * 20 > nbHeart) {
				lostHeartSprite.render(game, Gdx.graphics.getWidth() - 35 - (i * 20), Gdx.graphics.getHeight() - 20,
						lostHeartSprite.getWidth() - 1, lostHeartSprite.getHeight() - 1);
			} else {
				heartSprite.render(game, Gdx.graphics.getWidth() - 35 - (i * 20), Gdx.graphics.getHeight() - 20,
						heartSprite.getWidth() - 1, heartSprite.getHeight() - 1);
			}
		}

	}

	public void dispose() {

	}
}
