package net.fyloz.soundquest.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StringUIPart {

	private String str;

	private SpriteBatch batch;
	private BitmapFont font;
	private GlyphLayout layout;
	private Color color;

	private float x = 0;
	private float y = 0;
	private float scale = 1;

	public StringUIPart(SpriteBatch batch, String str, BitmapFont font, float scale, Color color) {
		this.batch = batch;
		this.str = str;
		this.font = font;
		this.color = color;
		this.scale = scale;
		this.font.getData().setScale(this.scale);
		this.layout = new GlyphLayout(font, str);
	}

	public StringUIPart(SpriteBatch batch, String str, BitmapFont font, float scale, Color color, float x, float y) {
		this.batch = batch;
		this.str = str;
		this.font = font;
		this.color = color;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.layout = new GlyphLayout(font, str);
	}

	public void render() {
		render(x, y);
	}

	public void render(float x, float y) {
		font.setColor(color);
		font.getData().setScale(scale);
		batch.begin();
		font.draw(batch, str, x, y);
		batch.end();
	}

	public float getWidth() {
		return layout.width;
	}

	public float getHeight() {
		return layout.height;
	}

	public GlyphLayout getLayout() {
		return layout;
	}

	@Override
	public String toString() {
		return str;
	}
}
