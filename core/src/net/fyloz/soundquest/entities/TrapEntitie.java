package net.fyloz.soundquest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.Logger;
import net.fyloz.soundquest.physics.bodies.LivingEntitieBody;
import net.fyloz.soundquest.physics.bodies.PhysicBody;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;

public class TrapEntitie implements Entitie{
	private Vector2 position;
	private Sprite sprite;
	private StaticEntitieBody body;
	
	private float touchDamages = 100;
	
	public void addTrap() {
		
	}

	@Override
	public void setX(float x) {
		this.position.x = x;
	}

	@Override
	public void setY(float y) {
		this.position.y = y;
	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setBody(StaticEntitieBody body) {
		this.body = body;
	}

	@Override
	public void setSprite(Sprite texture) {
		this.sprite = sprite;
		this.sprite.setSize(sprite.getWidth() / 16f, sprite.getHeight() / 16f);
	}
	
	public void setTouchDamages(float damages) {
		this.touchDamages = damages;
	}

	@Override
	public float getX() {
		return position.x;
	}

	@Override
	public float getY() {
		return position.y;
	}

	public StaticEntitieBody getBody() {
		return body;
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
