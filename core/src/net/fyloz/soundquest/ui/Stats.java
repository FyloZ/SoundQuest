package net.fyloz.soundquest.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.utils.ResourceManager;

public class Stats {
	private SoundQuest game;

	private Player player;

	private Sprite coinSprite;
	private BitmapFont font24;

	public Stats(SoundQuest game) {
		this.game = game;

		player = (Player) ResourceManager.getInstance().getEntitie("player");

		coinSprite = new Sprite((Texture) game.manager.get("textures/collectables/note.png"));

		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PoetsenOne-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		font24 = fontgen.generateFont(parameter);

		fontgen.dispose();
	}

	public void render() {
		game.batch.setProjectionMatrix(game.staticCamera.combined);
		game.batch.begin();
		game.batch.draw(coinSprite, Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 60, 30, 30);
		font24.draw(game.batch, player.getCoinAmount() + "", Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight() - 40);
		game.batch.end();
	}

	public void dispose() {
		font24.dispose();
	}
}
