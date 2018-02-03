package net.fyloz.soundquest.entities.traps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import net.fyloz.soundquest.Camera.CameraType;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.core.drawables.SpriteDrawable;
import net.fyloz.soundquest.entities.Player;
import net.fyloz.soundquest.entities.StaticEntitie;
import net.fyloz.soundquest.physics.FixtureProperties;
import net.fyloz.soundquest.physics.bodies.StaticEntitieBody;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;
import net.fyloz.soundquest.utils.B2DUtils;
import net.fyloz.soundquest.utils.ResourceManager;
import net.fyloz.soundquest.utils.resources.UserDatas;

public class CanonTrap extends StaticEntitie {
	private SoundQuest game;

	private FixtureProperties properties;

	private StaticEntitieBody body;

	private SpriteDrawable canonBaseTexture;
	private SpriteDrawable canonTexture;

	private Player player;
	private CanonBall[] canonBalls;

	private int time = 0;
	private int i = 0;

	public CanonTrap(SoundQuest game, Vector2 position) {
		super(game);
		this.game = game;

		properties = new FixtureProperties(UserDatas.TRAP_UD.toString(), UserDatas.CANON_UD.toString(), this);

		super.useCustomRendering(true);
		canonBaseTexture = new SpriteDrawable((Texture) game.manager.get("textures/traps/canon_base.png"),
				CameraType.Dynamic);
		canonTexture = new SpriteDrawable((Texture) game.manager.get("textures/traps/canon.png"), CameraType.Dynamic);

		PhysicWorld world = ResourceManager.getInstance().getCurrentWorld();

		BodyDef bd = B2DUtils.createBodyDef(BodyType.StaticBody, position);

		Body b = world.createBody(bd);
		b.setUserData(UserDatas.TRAP_UD);

		body = new StaticEntitieBody(b);

		body.setBodyDef(bd);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(canonTexture.getWidth() / 32f, canonTexture.getHeight() / 32f);

		Fixture f = B2DUtils.createFixture(b, shape, 0);
		f.setUserData(properties);
		shape.dispose();
		body.setFixture(f);

		body.setWidth(1f);
		body.setHeight(1f);
		body.getBody().setGravityScale(0);

		super.setBody(body);
		super.setPosition(position);

		player = (Player) ResourceManager.getInstance().getEntitie("player");

		canonBalls = new CanonBall[10];
		for (int i = 0; i < canonBalls.length; i++) {
			canonBalls[i] = new CanonBall(game, new Vector2(0, 0), this);
		}
	}

	@Override
	public void render() {

		float canonPlayerWidth = getBody().getPosition().x - player.getPosition().x;
		float canonPlayerHeight = getBody().getPosition().y - player.getPosition().y;
		float angle = (float) Math.atan(canonPlayerHeight / canonPlayerWidth) * MathUtils.radiansToDegrees;

		if (canonPlayerWidth < 0)
			angle = angle - 180;

		// CANONBALLS
		/**
		 * if (time >= 180) { if (i >= 10) i = 0; int multiplierW = 1; int multiplierH =
		 * 1; float hyp = (float) Math.sqrt(Math.pow(getBody().getWidth(), 2) +
		 * Math.pow(getBody().getHeight() / 2, 2)); float bulletAngle = ((float)
		 * Math.atan((getBody().getHeight() / 2) / getBody().getWidth())) + angle; if
		 * (canonPlayerWidth < 0) { bulletAngle = bulletAngle - 180; multiplierW = 1; }
		 * if (canonPlayerWidth > 0) { multiplierW = -1; } if (canonPlayerHeight < 0) {
		 * multiplierH = 1; } if (canonPlayerHeight > 0) { multiplierH = -1; }
		 * System.out.println(bulletAngle); float h = Math.abs((float)
		 * Math.sin(bulletAngle) * hyp); float w = Math.abs((float)
		 * Math.cos(bulletAngle) * hyp);
		 * canonBalls[i].getBody().getBody().setTransform(getBody().getPosition().x + w
		 * * multiplierW, getBody().getPosition().y + h * multiplierH, 0); float forceW
		 * = 0; float forceH = 0; if (canonPlayerWidth < 0 && canonPlayerHeight < 0) {
		 * forceW = 1; forceH = 1; } if (canonPlayerWidth > 0 && canonPlayerHeight < 0)
		 * { forceW = -1; forceH = 1; } if (canonPlayerWidth > 0 && canonPlayerHeight >
		 * 0) { forceW = -1; forceH = -1; } if (canonPlayerWidth < 0 &&
		 * canonPlayerHeight > 0) { forceW = 1; forceH = -1; }
		 * canonBalls[i].getBody().getBody().applyLinearImpulse(new Vector2(forceW,
		 * forceH), getBody().getBody().getWorldCenter(), true);
		 * canonBalls[i].canBeRendered(true); i++; time = 0; } time++;
		 **/

		getBody().getBody().setTransform(getBody().getPosition(), angle / MathUtils.radiansToDegrees);

		canonTexture.render(game, body.getPosition());
		canonBaseTexture.render(game, body.getPosition());

		for (CanonBall ball : canonBalls)
			ball.render();
	}

	public void dispose() {
		getBody().clear();
		for (CanonBall ball : canonBalls)
			ball.clear();
	}

}
