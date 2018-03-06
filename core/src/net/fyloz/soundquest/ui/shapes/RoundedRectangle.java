package net.fyloz.soundquest.ui.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RoundedRectangle implements ShapedUIPart {

	private ShapeRenderer renderer;

	private RectangleUIPart largeRect;
	private RectangleUIPart highRect;

	private float width;
	private float height;
	private float x;
	private float y;
	private float radius;
	private Color color;

	public RoundedRectangle(ShapeRenderer renderer, float width, float height, float x, float y, float radius,
			Color color) {
		this.renderer = renderer;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.largeRect = new RectangleUIPart(this.renderer, this.width, this.height - this.radius * 2, this.x,
				this.y + radius, this.color);
		this.highRect = new RectangleUIPart(this.renderer, this.width - this.radius * 2, this.height,
				this.x + radius, this.y, this.color);
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
		largeRect.render();
		highRect.render();
		renderer.begin(ShapeType.Filled);
		renderer.setColor(color);
		renderer.circle(highRect.getX(), largeRect.getY(), radius);
		renderer.circle(highRect.getX(), largeRect.getY() + largeRect.getHeight(), radius);
		renderer.circle(highRect.getX() + highRect.getWidth(), largeRect.getY() + largeRect.getHeight(), radius);
		renderer.circle(highRect.getX() + highRect.getWidth(), largeRect.getY(), radius);
		renderer.end();
	}
	
	@Override
	public void render(float x, float y) {
		render();
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
