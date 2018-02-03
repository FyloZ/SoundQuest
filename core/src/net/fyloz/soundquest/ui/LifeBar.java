package net.fyloz.soundquest.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.utils.ResourceManager;

public class LifeBar {
	private SoundQuest game;

	private Sprite heartSprite;
	private Sprite lostHeartSprite;

	private Player player;
	private int nbHeart;

	public LifeBar(SoundQuest game) {
		this.game = game;

		heartSprite = new Sprite((Texture) this.game.manager.get("textures/gui/heart.png"));
		lostHeartSprite = new Sprite((Texture) this.game.manager.get("textures/gui/heartlost.png"));
		player = (Player) ResourceManager.getInstance().getEntitie("player");
	}

	public void render() {

		nbHeart = player.getLife();

		game.batch.setProjectionMatrix(game.staticCamera.combined);

		game.batch.begin();

		for (int i = 5; i > 0; i--) {
			if (i * 20 > nbHeart) {
				game.batch.draw(lostHeartSprite, Gdx.graphics.getWidth() - 15 - (i * 20),
						Gdx.graphics.getHeight() - 20);
			} else {
				game.batch.draw(heartSprite, Gdx.graphics.getWidth() - 15 - (i * 20), Gdx.graphics.getHeight() - 20);
			}
		}

		game.batch.end();
	}

	public void dispose() {

	}
}
