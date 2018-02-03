package net.fyloz.soundquest.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ObjectSet;

import net.fyloz.soundquest.Camera;
import net.fyloz.soundquest.Logger;
import net.fyloz.soundquest.entities.Entitie;
import net.fyloz.soundquest.physics.worlds.PhysicWorld;

public class ResourceManager {
	private static ResourceManager instance = new ResourceManager();
	private static HashMap<String, TextureAtlas> atlas = new HashMap<String, TextureAtlas>();
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	private static HashMap<String, PhysicWorld> worlds = new HashMap<String, PhysicWorld>();
	private static HashMap<String, Entitie> entities = new HashMap<String, Entitie>();
	private static HashMap<String, TiledMap> tiledMaps = new HashMap<String, TiledMap>();
	private static HashMap<String, Animation> animations = new HashMap<String, Animation>();
	private static PhysicWorld currentWorld;
	
	private static Camera staticCamera;
	private static Camera dynamicCamera;

	public ResourceManager() {
	}

	public void loadTexture(String id, String path) {
		Texture t = new Texture(Gdx.files.internal(path));
		t.setFilter(TextureFilter.Linear, TextureFilter.Nearest);
		textures.put(id, t);
		Logger.log(Logger.RESOURCE_LOADER, " Loaded texture:" + path);
	}
	
	public void loadAtlas(String id, String path) {
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal(path));
		atlas.put(id, ta);
	}

	public void loadSprite(String id, String path) {
		Sprite s = new Sprite(new Texture(Gdx.files.internal(path)));
		s.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		sprites.put(id, s);
	}
	
	public void loadMapSprites(String id, String path) {
		/**TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(path));
		atlas.get**/
	}
	
	public void loadAnimation(String id, String path) {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(path));
		ObjectSet<Texture> textures = new ObjectSet<Texture>();
		textures.addAll(atlas.getTextures());
		Animation animation = new Animation(1/5f, atlas.getRegions());
		animations.put(id, animation);
	}

	public void addWorld(String id, PhysicWorld world) {
		worlds.put(id, world);
	}

	public void addEntitie(String id, Entitie entitie) {
		entities.put(id, entitie);
	}
	
	public void addTiledMap(String id, TiledMap map) {
		tiledMaps.put(id, map);
	}
	
	public void setStaticCamera(Camera camera) {
		this.staticCamera = camera;
	}
	
	public void setDynamicCamera(Camera camera) {
		this.dynamicCamera = camera;
	}
	
	public void setCurrentWorld(String id, PhysicWorld world) {
		currentWorld = world;
	}
	
	public Sprite[] getSpriteFromAtlas(String id, String[] a) {
		Sprite[] sprites = new Sprite[a.length];
		for(int i = 0; i < a.length; i++) {
			sprites[i] = (atlas.get(id)).createSprite(a[i]);
		}
		return sprites;
	}
	
	public TextureAtlas getAtlas(String id) {
		return atlas.get(id);
	}
	
	public Animation getAnimation(String id) {
		return animations.get(id);
	}

	public Texture getTexture(String id) {
		return textures.get(id);
	}

	public Sprite getSprite(String id) {
		return sprites.get(id);
	}

	public PhysicWorld getWorld(String id) {
		return worlds.get(id);
	}

	public Entitie getEntitie(String id) {
		return entities.get(id);
	}
	
	public TiledMap getTiledMap(String id) {
		return tiledMaps.get(id);
	}
	
	public Camera getStaticCamera() {
		return staticCamera;
	}
	
	public Camera getDynamicCamera() {
		return dynamicCamera;
	}
	
	public PhysicWorld getCurrentWorld() {
		return currentWorld;
	}

	public void dispose() {
		Iterator it = textures.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry pair = (Map.Entry) it.next();

			((Texture) pair.getValue()).dispose();

			it.remove(); // avoids a ConcurrentModificationException

		}
	}

	public static ResourceManager getInstance() {
		return instance;
	}
}
