package net.fyloz.soundquest.utils;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.fyloz.soundquest.Logger;

public class TiledMapUtils {
	public static TiledMap getTiledMap(String path) {
		Logger.log(Logger.RESOURCE_LOADER, " Loaded map:" + path);
		return new TmxMapLoader().load(path);
	}

	public static Polyline getPolyline(TiledMap map, String layerName, int id) {
		MapLayer layer = getLayer(map, layerName);
		MapObject object = getObject(layer, id);
		if (object instanceof PolylineMapObject) {
			return ((PolylineMapObject) object).getPolyline();
		} else {
			System.out.println(
					"[ERROR] the object with the id " + id + " in the layer " + layerName + " isn't a polyline!");
			return null;
		}
	}

	public static Rectangle getRectangle(TiledMap map, String layerName, int id) {
		MapLayer layer = getLayer(map, layerName);
		MapObject object = getObject(layer, id);
		if (object instanceof RectangleMapObject) {
			return ((RectangleMapObject) object).getRectangle();
		} else {
			System.out.println(
					"[ERROR] the object with the id " + id + " in the layer " + layerName + " isn't a rectangle!");
			return null;
		}
	}

	public static float[] getPolylineVertices(Polyline polyline) {
		polyline.scale(1 / 16f);
		return polyline.getTransformedVertices();
	}

	public static MapLayer getLayer(TiledMap map, String layerName) {
		return map.getLayers().get(layerName);
	}

	public static MapObject getObject(MapLayer layer, int id) {
		return layer.getObjects().get(id);
	}

	public static Vector2 getRectanglePosition(Rectangle rect) {
		return new Vector2((rect.getX() + rect.width / 2) / 16f, (rect.getY() + rect.height / 2) / 16f);
	}
}
