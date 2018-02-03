package net.fyloz.soundquest.ui;

import net.fyloz.soundquest.SoundQuest;

public class GameGUI {
	private SoundQuest game;

	private LifeBar lb;
	private Stats stats;

	public GameGUI(SoundQuest game) {
		this.game = game;
		lb = new LifeBar(this.game);
		stats = new Stats(this.game);
	}

	public void render() {
		lb.render();
		stats.render();
	}

	public void dispose() {
		lb.dispose();
		stats.dispose();
	}
}
