package net.fyloz.soundquest.entities.traps;

import com.badlogic.gdx.physics.box2d.PolygonShape;

public interface Trap {
	public void load();
	public PolygonShape[] getShapes();
	public float getWidth();
	public float getHeight();
}
