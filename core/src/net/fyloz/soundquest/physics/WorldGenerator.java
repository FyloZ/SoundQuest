package net.fyloz.soundquest.physics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;

import net.fyloz.soundquest.physics.worlds.PhysicWorld;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class WorldGenerator {
	public static void generateWorldBody(PhysicWorld world) {
		MapObjects borders = ResourceManager.getInstance().getCurrentWorld().getTiledMap().getLayers()
				.get("TerrainCollisions").getObjects();

		for (MapObject object : borders) {
			if (!(object instanceof PolylineMapObject)) {
				throw new IllegalStateException("TerrainCollisions need to be polylines!");
			}

			float x = Float.parseFloat(object.getProperties().get("x").toString()) / 16f;
			float y = Float.parseFloat(object.getProperties().get("y").toString()) / 16f;
			Polyline border = ((PolylineMapObject) object).getPolyline();
			float[] borderLength = border.getVertices();
			float x1 = 0;
			float y1 = 0;
			float x2 = 0;
			float y2 = 0;

			int j = 0;
			for (float f : borderLength) {
				if (j == 0)
					x1 = x + (f / 16f);
				if (j == 1)
					y1 = y + (f / 16f);
				if (j == 2)
					x2 = x + (f / 16f);
				if (j == 3)
					y2 = y + (f / 16f);
				j++;
			}

			boolean isGround = false;
			if (y1 == y2) {
				x1 += 1 / 16f;
				x2 -= 1 / 16f;
				isGround = true;
			} else {
				isGround = false;
			}

			EdgeShape shape = new EdgeShape();
			shape.set(x1, y1, x2, y2);

			FixtureProperties properties = new FixtureProperties(UserDatas.WORLD_UD.toString(),
					UserDatas.WORLD_UD.toString(), world);
			properties.setCollideWithEntities(true);
			properties.setHasLife(false);
			properties.setIsGround(isGround);
			properties.setTakeDamages(false);
			properties.setThrowDamages(false);

			BodyDef bd = B2DUtils.createBodyDef(BodyType.StaticBody);
			Body body = world.createBody(bd);
			body.setUserData(UserDatas.WORLD_UD);
			Fixture f = B2DUtils.createFixture(body, shape);
			f.setUserData(properties);
		}
	}
}
