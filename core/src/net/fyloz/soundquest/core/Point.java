package net.fyloz.soundquest.core;

import com.badlogic.gdx.math.MathUtils;
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

	public boolean isUnder(Point point) {
		return y < point.y ? true : false;
	}

	public boolean isUpper(Point point) {
		return y > point.y ? true : false;
	}

	public boolean isAtRight(Point point) {
		return x < point.x ? true : false;
	}

	public boolean isAtLeft(Point point) {
		return x > point.x ? true : false;
	}

	public double angle(Point point) {
		float deltaX = x - point.getX();
		float deltaY = y - point.getY();
		
		if(deltaX == 0)
			return 270;
		// Calculer l'angle avec les deux cathètes -> arctan(y/x)
		return Math.atan(deltaY / deltaX) * MathUtils.radiansToDegrees;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Vector2 getVector() {
		return new Vector2(x, y);
	}
}
