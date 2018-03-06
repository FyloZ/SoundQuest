package net.fyloz.soundquest.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.drawables.SpriteDrawable;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.utils.ResourceManager;

public class Stats {
	private SoundQuest game;

	private Player player;

	private SpriteDrawable coinSprite;
	private BitmapFont font24;

	public Stats(SoundQuest game) {
		this.game = game;

		player = (Player) ResourceManager.getInstance().getEntitie("player");

		Sprite sprite = new Sprite((Texture) game.manager.get("textures/collectables/note.png"));
		coinSprite = new SpriteDrawable(sprite, CameraType.Static);

		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PoetsenOne-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		font24 = fontgen.generateFont(parameter);

		fontgen.dispose();
	}

	public void render() {
		coinSprite.render(game, Gdx.graphics.getWidth() - 95, Gdx.graphics.getHeight() - 50, coinSprite.getWidth() * 1.5f + 1,
				coinSprite.getHeight() * 1.5f);
		game.batch.begin();
		font24.draw(game.batch, player.getCoinAmount() + "", Gdx.graphics.getWidth() - 70,
				Gdx.graphics.getHeight() - 38);
		game.batch.end();
	}

	public void dispose() {
		font24.dispose();
	}
}
