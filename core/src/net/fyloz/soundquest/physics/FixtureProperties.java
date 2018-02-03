package net.fyloz.soundquest.physics;

public class FixtureProperties {
	private String group;
	private String type;

	private Object object;

	private int touchDamages = 0;
	private int life = 1;

	private boolean isGround = false;
	private boolean throwDamages = false;
	private boolean hasLife = false;
	private boolean collideWithEntities = true;
	private boolean takeDamages = false;

	public FixtureProperties(String group, String type, Object object) {
		this.group = group;
		this.type = type;
		this.object = object;
	}

	public void setTouchDamages(int touchDamages) {
		this.touchDamages = touchDamages;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setIsGround(boolean isGround) {
		this.isGround = isGround;
	}

	public void setThrowDamages(boolean throwDamages) {
		this.throwDamages = throwDamages;
	}

	public void setHasLife(boolean hasLife) {
		this.hasLife = hasLife;
	}

	public void setCollideWithEntities(boolean collideWithEntities) {
		this.collideWithEntities = collideWithEntities;
	}

	public void setTakeDamages(boolean takeDamages) {
		this.takeDamages = takeDamages;
	}

	public String getGroup() {
		return group;
	}

	public String getType() {
		return type;
	}

	public int getTouchDamages() {
		return touchDamages;
	}

	public int getLife() {
		return life;
	}

	public Object getObject() {
		return object;
	}

	public boolean isGround() {
		return isGround;
	}

	public boolean throwDamages() {
		return throwDamages;
	}

	public boolean hasLife() {
		return hasLife;
	}

	public boolean collideWithEntities() {
		return collideWithEntities;
	}

	public boolean takeDamages() {
		return takeDamages;
	}
}
