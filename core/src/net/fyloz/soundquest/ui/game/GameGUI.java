package net.fyloz.soundquest.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.drawables.SpriteDrawable;
import net.fyloz.soundquest.ui.TexturedButton;

public class GameGUI {
	private SoundQuest game;

	private LifeBar lb;
	private Stats stats;

	private TextureAtlas optionsAtlas;
	private SpriteDrawable optionSprite;
	private SpriteDrawable optionOMASprite;
	private TexturedButton options;

	public GameGUI(SoundQuest game) {
		this.game = game;
		lb = new LifeBar(this.game);
		stats = new Stats(this.game);

		optionSprite = new SpriteDrawable(
				((TextureAtlas) game.manager.get("textures/gui/options.txt")).createSprite("default"),
				CameraType.Static);
		optionOMASprite = new SpriteDrawable(
				((TextureAtlas) game.manager.get("textures/gui/options.txt")).createSprite("onMouseAbove"),
				CameraType.Static);
		options = new TexturedButton(game, optionSprite, Gdx.graphics.getWidth() - 30, Gdx.graphics.getHeight() - 30,
				optionSprite.getWidth() * 2f, optionSprite.getHeight() * 2f);
		options.setOnMouseAboveTexture(optionOMASprite);
		
		options.show();
	}

	public void render() {
		lb.render();
		stats.render();
		options.render(Gdx.graphics.getWidth() - 30f, Gdx.graphics.getHeight() - 30f, optionSprite.getWidth() * 2f,
				optionSprite.getHeight() * 2f);
	}

	public void dispose() {
		lb.dispose();
		stats.dispose();
	}
}
