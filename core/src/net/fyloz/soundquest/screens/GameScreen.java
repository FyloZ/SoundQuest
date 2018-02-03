package net.fyloz.soundquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.steps.Level;
import net.fyloz.soundquest.ui.GameGUI;
import net.fyloz.soundquest.utils.ResourceManager;

public class GameScreen implements Screen {
	private SoundQuest game;
	private Level level;
	private Music music;
	private GameGUI gui;

	private boolean died = false;
	private boolean isMuted = false;

	public GameScreen(SoundQuest game) {
		this.game = game;
		level = new Level("test", game);

		gui = new GameGUI(this.game);

		music = (Music) game.manager.get("musics/detective.wav");
		music.setLooping(true);
	}

	@Override
	public void show() {
		music.play();
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			isMuted = !isMuted;

			if (isMuted) {
				music.pause();
			} else {
				music.play();
			}
		}
		if (((Player) ResourceManager.getInstance().getEntitie("player")).getLife() <= 0) {
			died = !died;
			this.dispose();

			game.setScreen(game.mainscreen);
		}

		Gdx.gl.glClearColor(0, 0, 0.5f, 0.3f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		level.render();
		gui.render();
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
		music.stop();
	}

	@Override
	public void dispose() {
		level.dispose();
		gui.dispose();
		music.stop();
		music.dispose();
	}

}
