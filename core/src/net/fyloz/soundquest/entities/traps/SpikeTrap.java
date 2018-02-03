package net.fyloz.soundquest.entities.traps;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import net.fyloz.soundquest.entities.TrapEntitie;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class SpikeTrap extends TrapEntitie {
	// TODO À revoir!!! Doit gérer un seul spike, pas tous!
	private PhysicWorld world;
	private MapObjects spikes;

	private BodyDef[] bds;
	private Body[] bodys;
	private Fixture[] fix;
	private FixtureProperties properties;

	private PolygonShape[] shapes;

	private int touchDamages = 50;
	private boolean isGround = true;
	private boolean throwDamages = true;

	public SpikeTrap() {
		properties = new FixtureProperties(UserDatas.TRAP_UD.toString(), UserDatas.SPIKES_UD.toString(), this);
		properties.setTouchDamages(touchDamages);
		properties.setIsGround(isGround);
		properties.setThrowDamages(throwDamages);

		world = ResourceManager.getInstance().getCurrentWorld();
		spikes = world.getTiledMap().getLayers().get("Spikes").getObjects();

		bds = new BodyDef[spikes.getCount()];
		bodys = new Body[spikes.getCount()];
		fix = new Fixture[spikes.getCount()];

		shapes = createShapes(spikes.getCount());

		for (int i = 0; i < spikes.getCount(); i++) {
			bds[i] = B2DUtils.createBodyDef(BodyType.StaticBody,
					Float.parseFloat(spikes.get(i).getProperties().get("x").toString()) / 16f + 0.5f,
					Float.parseFloat(spikes.get(i).getProperties().get("y").toString()) / 16f + 0.1f);
			bodys[i] = world.createBody(bds[i]);
			bodys[i].setUserData(UserDatas.SPIKES_UD);
			fix[i] = B2DUtils.createFixture(bodys[i], shapes[i]);
			bodys[i].getFixtureList().get(0).setUserData(properties);
		}
	}

	private PolygonShape[] createShapes(int count) {
		shapes = new PolygonShape[count];

		for (int i = 0; i < count; i++) {
			shapes[i] = new PolygonShape();
			shapes[i].setAsBox(Float.parseFloat(spikes.get(i).getProperties().get("width").toString()) / 32f,
					Float.parseFloat(spikes.get(i).getProperties().get("height").toString()) / 32f);
		}

		return shapes != null ? shapes : new PolygonShape[0];
	}
}
