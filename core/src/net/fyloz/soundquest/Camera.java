package net.fyloz.soundquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;

import net.fyloz.soundquest.entities.Player;

public class Camera extends OrthographicCamera {

	private int vw;
	private int vh;
	private int diviser = 1;

	private SoundQuest game;

	private Player player;

	public Camera(SoundQuest game, int vw, int vh) {
		super(vw, vh);
		this.game = game;
		this.vw = vw;
		this.vh = vh;
		// player = (Player) ResourceManager.getInstance().getEntitie("player");
		this.position.x = vw / 2;
		this.position.y = vh / 2;
		// this.player = (Player) ResourceManager.getInstance().getEntitie("player");
		super.update();
	}

	public enum CameraType {
		Dynamic, Static;
	}

	public void updateCamera() {

		super.update();
		setToOrtho(false, Gdx.graphics.getWidth() / diviser, Gdx.graphics.getHeight() / diviser);

		if (Gdx.input.isKeyPressed(Keys.O)) {
			this.zoom -= 0.01;
		}
		if (Gdx.input.isKeyPressed(Keys.P)) {
			this.zoom += 0.01;
		}
	}

	public void setDiviser(int diviser) {
		this.diviser = diviser;
	}

	public void followPlayer(Player player) {
		game.batch.setProjectionMatrix(game.dynamicCamera.combined);
		// System.out.println(player.getPosition().x + "/" + (Gdx.graphics.getWidth() /
		// 2) / 16);
		// if (player.getPosition().x >= (Gdx.graphics.getWidth() / 2) / 16) {
		this.position.x = (player.getPosition().x);
		this.position.y = (player.getPosition().y);
		// }
	}
}
