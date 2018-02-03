package net.fyloz.soundquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.fyloz.soundquest.Camera;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.utils.ResourceManager;

public class MainScreen implements Screen, InputProcessor {
	private ShapeRenderer sr;

	private BitmapFont font36;
	private BitmapFont font52;

	private Music music;

	private SoundQuest game;
	private GameScreen gamescreen;
	
	private Camera camera;

	private boolean isMuted = false;

	private int width = Gdx.graphics.getWidth();
	private int height = Gdx.graphics.getHeight();

	public MainScreen(SoundQuest game) {
		this.game = game;
		this.game.mainscreen = this;
		sr = new ShapeRenderer();
		
		camera = ResourceManager.getInstance().getStaticCamera();

		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/actionj.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 36;
		font36 = fontgen.generateFont(parameter);
		parameter.size = 52;
		font52 = fontgen.generateFont(parameter);

		music = (Music) game.manager.get("musics/dubstep.mp3");
		music.setLooping(true);
	}

	@Override
	public void show() {
		if (gamescreen != null) {
			gamescreen = null;
		}

		gamescreen = new GameScreen(game);

		game.batch.setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(this);
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
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.YELLOW);
		sr.circle((width / 2 - 100) + 10, (height / 2 - height / 3) + 10, 10);
		sr.circle((width / 2 - 100) + 10, (height / 2 - height / 3) + 100, 10);
		sr.circle((width / 2 + 100) - 10, (height / 2 - height / 3) + 10, 10);
		sr.circle((width / 2 + 100) - 10, (height / 2 - height / 3) + 100, 10);
		sr.rect(width / 2 - 100, (height / 2 - height / 3) + 10, 200, 90);
		sr.rect(width / 2 - 95, (height / 2 - height / 3), 190, 110);
		sr.end();
		game.batch.begin();
		font52.setColor(Color.PURPLE);
		font52.draw(game.batch, "Sound Quest", (width / 4) - 20, height - (height / 5));
		font36.setColor(Color.BLACK);
		font36.draw(game.batch, "Jouer", width / 2 - 60, (height / 2 - height / 3) + 70);
		game.batch.end();
		
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

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			if (screenX >= (width / 2 - 100) && screenX <= (width / 2 + 100)
					&& screenY <= (height / 2 - height / 3) + 310 && screenY >= (height / 2 - height / 3) + 230) {
				game.batch.setProjectionMatrix(game.dynamicCamera.combined);
				Gdx.input.setInputProcessor(null);
				game.setScreen(gamescreen);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
