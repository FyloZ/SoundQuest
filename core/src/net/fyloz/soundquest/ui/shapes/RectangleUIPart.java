package net.fyloz.soundquest.ui.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RectangleUIPart implements ShapedUIPart {

	private ShapeRenderer renderer;

	private float width;
	private float height;
	private float x = 0;
	private float y = 0;

	private Color color = Color.WHITE;

	public RectangleUIPart(ShapeRenderer renderer, float width, float height) {
		this.renderer = renderer;
		this.width = width;
		this.height = height;
	}

	public RectangleUIPart(ShapeRenderer renderer, float width, float height, Color color) {
		this.renderer = renderer;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public RectangleUIPart(ShapeRenderer renderer, float width, float height, float x, float y) {
		this.renderer = renderer;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public RectangleUIPart(ShapeRenderer renderer, float width, float height, float x, float y, Color color) {
		this.renderer = renderer;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	public void render() {
		render(x, y);
	}

	@Override
	public void render(float x, float y) {
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.rect(x, y, width, height);
		renderer.end();
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

	public Color getColor() {
		return color;
	}
}
