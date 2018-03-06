package net.fyloz.soundquest.core;

public class Movement {

	private Point maxPoint;
	private float speed;
	private Point currentPoint;

	private float x;
	private float y;

	private boolean isCompleted = false;
	private Direction direction;

	public Movement(Point maxPoint, Point currentPoint, float speed) {
		this.maxPoint = maxPoint;
		// Pour savoir quelle distance par ms
		this.speed = speed / 1000f;
		this.currentPoint = currentPoint;
		this.x = currentPoint.getX();
		this.y = currentPoint.getY();

		if (currentPoint.isUpper(maxPoint))
			direction = Direction.DOWN;
		if (currentPoint.isUnder(maxPoint))
			direction = Direction.UP;
	}

	public void update() {
		speed = 200f;
		x = currentPoint.getX();
		y = currentPoint.getY();

		if ((x >= maxPoint.getX() - 0.4f && y >= (maxPoint.getY() - 0.25f) && direction == Direction.UP)
				|| (x <= maxPoint.getX() - 0.4f && y <= maxPoint.getY() && direction == Direction.DOWN))
			isCompleted = true;
		// speed est l'hypoténuse du triangle x a y
		// On veut la cathète X, donc cos(angle) = x/speed -> x = cos(angle) * speed
		x += Math.cos(maxPoint.angle(currentPoint)) * speed;
		// On veut la cathète Y, donc sin(angle) = y/speed -> y = sin(angle) * speed
		y += Math.sin(maxPoint.angle(currentPoint)) * speed;

		currentPoint.setX(x);
		currentPoint.setY(y);
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public Point getMaxPoint() {
		return maxPoint;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void reset(Point currentPoint) {
		this.currentPoint = currentPoint;
		isCompleted = false;
	}
}
