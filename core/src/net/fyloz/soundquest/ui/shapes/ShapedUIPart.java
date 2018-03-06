package net.fyloz.soundquest.ui.shapes;

public interface ShapedUIPart {
	void setWidth(float width);

	void setHeight(float height);

	void setX(float x);

	void setY(float y);

	void render(float x, float y);

	float getWidth();

	float getHeight();

	float getX();

	float getY();
}
