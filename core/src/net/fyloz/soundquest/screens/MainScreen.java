package net.fyloz.soundquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.fyloz.soundquest.Camera;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.ui.ShapedButton;
import net.fyloz.soundquest.ui.StringUIPart;
import net.fyloz.soundquest.ui.shapes.TextRoundedRectangle;
import net.fyloz.soundquest.utils.ResourceManager;

public class MainScreen implements Screen {
	private ShapeRenderer sr;

	private Music music;

	private SoundQuest game;
	private GameScreen gamescreen;

	private Camera camera;

	private boolean isMuted = false;

	private int width = Gdx.graphics.getWidth();
	private int height = Gdx.graphics.getHeight();

	private BitmapFont font;

	private ShapedButton button;
	private StringUIPart title;

	public MainScreen(SoundQuest game) {
		this.game = game;
		this.game.mainscreen = this;
		sr = new ShapeRenderer();

		camera = ResourceManager.getInstance().getStaticCamera();

		font = game.fonts.get("PoetsenOne-Regular");

		music = game.manager.get("musics/dubstep.mp3");
		music.setLooping(true);

		TextRoundedRectangle rectangle = new TextRoundedRectangle(game.shapeRenderer, width / 2 - 100,
				height / 2 - height / 3, 210, 95, 10, Color.YELLOW,
				new StringUIPart(game.batch, "Play", font, 36f / 72f, Color.BLACK));
		rectangle.setTextCentered(true);
		button = new ShapedButton(game, rectangle, new GameScreen(game), new int[] { Buttons.LEFT });
		title = new StringUIPart(game.batch, "Sound Quest", font, 52f / 72f, Color.PURPLE, (width / 4),
				height - (height / 5));
	}

	@Override
	public void show() {
		if (gamescreen != null) {
			gamescreen = null;
		}
		button.show();

		gamescreen = new GameScreen(game);

		game.batch.setProjectionMatrix(camera.combined);
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

		Gdx.gl.glClearColor(0.1f, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.draw((Texture) game.manager.get("textures/backgrounds/bg.png"), 0, 0, width, height);
		game.batch.end();

		sr.setProjectionMatrix(game.batch.getProjectionMatrix());
		button.render();
		title.render();

		camera.updateCamera();
		camera.update();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		music.stop();
	}

	@Override
	public void dispose() {
		sr.dispose();
		music.stop();
		music.dispose();
	}
}
