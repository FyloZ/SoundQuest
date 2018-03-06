package net.fyloz.soundquest.ui.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.fyloz.soundquest.ui.StringUIPart;

public class TextRoundedRectangle extends RoundedRectangle implements ShapedUIPart {

	private ShapeRenderer renderer;

	private float width;
	private float height;
	private float x;
	private float y;
	private float radius;

	private Color color;

	private StringUIPart text;
	private boolean isTextCentered = false;

	public TextRoundedRectangle(ShapeRenderer renderer, float width, float height, float x, float y, float radius,
			Color color, StringUIPart text) {
		super(renderer, width, height, x, y, radius, color);
		this.renderer = renderer;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.text = text;
	}

	public void setTextCentered(boolean isTextCentered) {
		this.isTextCentered = isTextCentered;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
		super.setWidth(width);
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
		super.setHeight(height);
	}

	@Override
	public void setX(float x) {
		this.x = x;
		super.setX(x);

	}

	@Override
	public void setY(float y) {
		this.y = y;
		super.setY(y);
	}

	public void render() {
		super.render();
		if (isTextCentered)
			text.render((x + width / 2f) - text.getWidth() / 2f, (y + height / 2) + text.getHeight() / 2f);
		else
			text.render(x, y + text.getHeight() / 2f);
	}

	@Override
	public void render(float x, float y) {
		super.render();
		if (isTextCentered)
			text.render((x + width / 2f) - text.getWidth() / 2f, (y + height / 2) + text.getHeight() / 2f);
		else
			text.render(x, y + text.getHeight() / 2f);
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
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

}
