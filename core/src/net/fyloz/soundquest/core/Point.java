package net.fyloz.soundquest.core;

import com.badlogic.gdx.math.Vector2;

public class Point {
	private float x;
	private float y;

	public Point(Vector2 position) {
		this.x = position.x;
		this.y = position.y;
	}

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isUnder(Vector2 point) {
		return y < point.y ? true : false;
	}

	public boolean isUpper(Vector2 point) {
		return y > point.y ? true : false;
	}

	public boolean isAtRight(Vector2 point) {
		return x < point.x ? true : false;
	}

	public boolean isAtLeft(Vector2 point) {
		return x > point.x ? true : false;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
