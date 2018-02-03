package net.fyloz.soundquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.fyloz.soundquest.SoundQuest;

public class LoadingBar implements Screen {
	private SoundQuest game;
	private BitmapFont font52;

	private AssetManager manager;

	private ShapeRenderer sr;

	private float max = 0;
	private float pos = 0;

	public LoadingBar(SoundQuest game) {
		this.game = game;
	}

	@Override
	public void show() {

		manager = new AssetManager();
		game.manager = manager;

		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PoetsenOne-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 52;
		font52 = fontgen.generateFont(parameter);

		fontgen.dispose();

		sr = new ShapeRenderer();

		manager.load("textures/others/SoundQuest.png", Texture.class);
		manager.load("textures/collectables/note.png", Texture.class);
		manager.load("textures/backgrounds/bg.png", Texture.class);
		manager.load("textures/backgrounds/forest_bg.gif", Texture.class);
		manager.load("textures/gui/heart.png", Texture.class);
		manager.load("textures/gui/heartlost.png", Texture.class);
		
		manager.load("textures/entities/player/idle.png", Texture.class);
		manager.load("textures/entities/player/jump.txt", TextureAtlas.class);
		manager.load("textures/entities/player/walk.txt", TextureAtlas.class);
		
		manager.load("textures/traps/spikes.png", Texture.class);
		manager.load("textures/traps/doublespikes.png", Texture.class);
		manager.load("textures/traps/canon_base.png", Texture.class);
		manager.load("textures/traps/canon.png", Texture.class);
		manager.load("textures/traps/canon_bullet.png", Texture.class);
		manager.load("textures/collectables/note.png", Texture.class);

		manager.load("textures/entities/elevator.txt", TextureAtlas.class);

		manager.load("musics/dubstep.mp3", Music.class);
		manager.load("musics/detective.wav", Music.class);

	}

	@Override
	public void render(float delta) {

		manager.update();

		pos = manager.getProgress();

		sr.setProjectionMatrix(game.batch.getProjectionMatrix());
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 2 - 52,
				(float) (pos * (Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()
						/ 2.4)))/** (float) (Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 2.4)) **/
				, 20);
		sr.end();
		game.batch.begin();
		font52.setColor(Color.PURPLE);
		font52.draw(game.batch, "SOUND QUEST", Gdx.graphics.getWidth() / 4 - 10, Gdx.graphics.getHeight() / 2 + 52);
		game.batch.end();

		if (pos == 1) {
			game.setScreen(new MainScreen(game));
		}
	}

	public void setMax(float max) {
		this.max = max;
	}

	public void setBarPosition(float pos) {
		this.pos = pos;
	}

	public float getMax() {
		return max;
	}

	public float getBarPosition() {
		return pos;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		sr.dispose();
		font52.dispose();
	}

}
