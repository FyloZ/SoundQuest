package net.fyloz.soundquest.entities.traps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.TrapEntitie;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.resources.Bits;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class SpikeTrap extends TrapEntitie {

	private SoundQuest game;
	private StaticEntitieBody body;
	private FixtureProperties properties;

	private int touchDamages = 50;
	private boolean isGround = true;
	private boolean throwDamages = true;

	public SpikeTrap(SoundQuest game, Vector2 position) {
		this.game = game;

		properties = new FixtureProperties(UserDatas.TRAP_UD.toString(), UserDatas.SPIKES_UD.toString(), this);
		properties.setTouchDamages(touchDamages);
		properties.setIsGround(isGround);
		properties.setThrowDamages(throwDamages);

		body = B2DUtils.createSEBody(properties, BodyType.StaticBody, position, 1, 3 / 16f, UserDatas.SPIKES_UD,
				Bits.PHYSIC_ENTITY, Bits.PHYSIC_ENTITY_MASK);
		this.setBody(body);
	}
	
	public void dispose() {
		body.clear();
	}
}
