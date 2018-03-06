package net.fyloz.soundquest.entities.mapping;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.Logger;
import net.fyloz.soundquest.SoundQuest;
import net.fyloz.soundquest.entities.Elevator;
import net.fyloz.soundquest.entities.collectables.Coin;
import net.fyloz.soundquest.entities.traps.CanonTrap;
import net.fyloz.soundquest.entities.traps.SpikeTrap;

public class Map {

	private SoundQuest game;

	private String mapName;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer renderer;

	private HashMap<Integer, Coin> coins;
	private HashMap<Integer, SpikeTrap> spikes;
	private HashMap<Integer, CanonTrap> canons;
	private HashMap<Integer, Elevator> elevators;

	public Map(SoundQuest game, String mapName) {
		this.game = game;
		this.mapName = mapName;
		tiledMap = new TmxMapLoader().load("maps/" + mapName);
		if (tiledMap == null)
			throw new NullPointerException("[FATAL] Inexistant map: " + mapName);
		renderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / 16f);
	}

	public void load() {
		loadCoins();
		Logger.log(Logger.INFO, "[MAP] Coins loaded!");
		loadSpikes();
		Logger.log(Logger.INFO, "[MAP] Spikes loaded!");
		loadCanons();
		Logger.log(Logger.INFO, "[MAP] Canons loaded!");
		loadElevators();
		Logger.log(Logger.INFO, "[MAP] Elevators loaded!");
	}

	public void render() {
		for (Coin coin : coins.values()) {
			coin.update();
			coin.render();
		}
		for (CanonTrap canon : canons.values())
			canon.render();
		for (Elevator elevator : elevators.values()) {
			elevator.update();
			elevator.render();
		}
		renderer.setView(game.dynamicCamera);
		renderer.render();
	}

	public void dispose() {
		for (Coin coin : coins.values())
			coin.dispose();
		for (CanonTrap canon : canons.values())
			canon.dispose();
		for (SpikeTrap spike : spikes.values())
			spike.dispose();
		for (Elevator elevator : elevators.values())
			elevator.dispose();
	}

	private void loadCoins() {
		coins = new HashMap<>();
		for (MapObject object : tiledMap.getLayers().get("Coins").getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				coins.put(Integer.parseInt(object.getProperties().get("id").toString()),
						new Coin(game, new Vector2((rect.getX()) / 16f + 1, (rect.getY() + rect.height / 2) / 16f)));
			} else {
				throw new IllegalStateException(
						"[FATAL] Invalid map: " + mapName + ". Coins need to be RectangleObjects!");
			}
		}
	}

	private void loadSpikes() {
		spikes = new HashMap<>();
		for (MapObject object : tiledMap.getLayers().get("Spikes").getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				spikes.put(Integer.parseInt(object.getProperties().get("id").toString()), new SpikeTrap(game,
						new Vector2((rect.getX()) / 16f + 1, (rect.getY() + rect.height / 2) / 16f)));
			} else {
				throw new IllegalStateException(
						"[FATAL] Invalid map: " + mapName + ". Spikes need to be RectangleObjects!");
			}
		}
	}

	private void loadCanons() {
		canons = new HashMap<>();
		for (MapObject object : tiledMap.getLayers().get("Canons").getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				canons.put(Integer.parseInt(object.getProperties().get("id").toString()), new CanonTrap(game,
						new Vector2((rect.getX()) / 16f + 1, (rect.getY() + rect.height / 2) / 16f)));
			} else {
				throw new IllegalStateException(
						"[FATAL] Invalid map: " + mapName + ". Canons need to be RectangleObjects!");
			}
		}
	}

	private void loadElevators() {
		elevators = new HashMap<>();
		for (MapObject object : tiledMap.getLayers().get("Elevators").getObjects()) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				elevators.put(Integer.parseInt(object.getProperties().get("id").toString()), new Elevator(game,
						new Vector2((rect.getX()) / 16f + 1, (rect.getY() + rect.height / 2) / 16f)));
			} else {
				throw new IllegalStateException(
						"[FATAL] Invalid map: " + mapName + ". Elevators need to be RectangleObjects!");
			}
		}
	}

	public HashMap<Integer, Coin> getCoins() {
		return coins;
	}

	public HashMap<Integer, SpikeTrap> getSpikes() {
		return spikes;
	}

	public HashMap<Integer, CanonTrap> getCanons() {
		return canons;
	}

	public HashMap<Integer, Elevator> getElevators() {
		return elevators;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}
}
