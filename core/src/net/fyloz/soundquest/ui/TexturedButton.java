package net.fyloz.soundquest.ui;

import com.badlogic.gdx.Input.Buttons;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.Interactables.InteractType;
import net.fyloz.soundquest.core.Interactables.Interactable;
import net.fyloz.soundquest.core.drawables.Drawable;

public class TexturedButton implements Interactable {

	private Drawable defaultTexture;
	private Drawable onMouseAboveTexture;
	private Drawable texture;

	private SoundQuest game;

	private float width;
	private float height;
	private float x;
	private float y;

	private InteractType[] interactions = new InteractType[] { InteractType.TOUCHDOWN, InteractType.MOUSEPOSITION };

	public TexturedButton(SoundQuest game, Drawable texture, float x, float y) {
		this.game = game;
		this.defaultTexture = texture;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
		this.x = x;
		this.y = y;

		this.texture = this.defaultTexture;
	}

	public TexturedButton(SoundQuest game, Drawable texture, float x, float y, float width, float height) {
		this.game = game;
		this.defaultTexture = texture;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		this.texture = this.defaultTexture;
	}

	public void show() {
		game.inputs.registerInteractable(this);
	}
	
	public void hide() {
		game.inputs.unregisterInteractable(this);
	}

	public void setOnMouseAboveTexture(Drawable onMouseAboveTexture) {
		this.onMouseAboveTexture = onMouseAboveTexture;
	}

	public void render() {
		texture.render(game, x, y, width, height);
		texture = defaultTexture;
	}

	public void render(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		texture.render(game, x, y, width, height);
		texture = defaultTexture;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public InteractType[] getAcceptedInteractions() {
		return interactions;
	}

	@Override
	public void onTouch(InteractType type, float x, float y, int pointer, int button) {
	}

	@Override
	public void onMouseAbove(float x, float y) {
		if (onMouseAboveTexture != null)
			texture = onMouseAboveTexture;
	}

	@Override
	public void onScroll(float amount) {
		return;
	}
}
