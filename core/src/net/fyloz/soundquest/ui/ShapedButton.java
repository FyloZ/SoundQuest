package net.fyloz.soundquest.ui;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.Initializable;
import net.fyloz.soundquest.core.Interactables.InteractType;
import net.fyloz.soundquest.core.Interactables.Interactable;
import net.fyloz.soundquest.ui.shapes.ShapedUIPart;

public class ShapedButton implements Interactable {

	private SoundQuest game;
	private ShapedUIPart shape;
	private Initializable init;
	private float width;
	private float height;
	private float x;
	private float y;

	private int[] inputs;
	private InteractType[] interactions = new InteractType[] { InteractType.TOUCHDOWN };

	public ShapedButton(SoundQuest game, ShapedUIPart shape, Initializable init, int[] inputs) {
		this.game = game;
		this.shape = shape;
		this.width = shape.getWidth();
		this.height = shape.getHeight();
		this.x = shape.getX();
		this.y = shape.getY();
		this.init = init;
		this.inputs = inputs;
	}

	public void show() {
		game.inputs.registerInteractable(this);
	}

	public void render() {
		shape.render(x, y);
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
		for (int input : inputs) {
			if (button == input)
				init.initialize();
		}
	}

	@Override
	public void onMouseAbove(float x, float y) {
		return;
	}

	@Override
	public void onScroll(float amount) {
		return;
	}

}
