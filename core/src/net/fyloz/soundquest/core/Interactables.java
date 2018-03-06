package net.fyloz.soundquest.core;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Interactables implements InputProcessor {

	public List<Interactable> interactables;
	public List<Interactable> workingList;

	public Interactables() {
		interactables = new ArrayList<>();
		workingList = new ArrayList<>();
	}

	public void registerInteractable(Interactable interactable) {
		interactables.add(interactable);
	}

	public void unregisterInteractable(Interactable interactable) {
		interactables.remove(interactable);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int y = Gdx.graphics.getHeight() - screenY;
		for (int i = 0; i < interactables.size(); i++) {
			for (InteractType type : interactables.get(i).getAcceptedInteractions()) {
				if (type.equals(InteractType.TOUCHDOWN)
						&& (screenX >= interactables.get(i).getX()
								&& screenX <= interactables.get(i).getX() + interactables.get(i).getWidth())
						&& (y >= interactables.get(i).getY()
								&& y <= interactables.get(i).getY() + interactables.get(i).getHeight())) {
					// TODO Fait une erreur!!!
					interactables.get(i).onTouch(InteractType.TOUCHDOWN, screenX, y, pointer, button);
				}
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int y = Gdx.graphics.getHeight() - screenY;
		for (int i = 0; i < interactables.size(); i++) {
			for (InteractType type : interactables.get(i).getAcceptedInteractions()) {
				if (type.equals(InteractType.TOUCHUP) && screenX >= interactables.get(i).getX()
						&& screenX <= interactables.get(i).getX() + interactables.get(i).getWidth()
						&& y >= interactables.get(i).getY()
						&& y <= interactables.get(i).getY() + interactables.get(i).getHeight()) {
					interactables.get(i).onTouch(InteractType.TOUCHUP, screenX, y, pointer, button);
				}
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int y = Gdx.graphics.getHeight() - screenY;
		for (int i = 0; i < interactables.size(); i++) {
			for (InteractType type : interactables.get(i).getAcceptedInteractions()) {
				if (type.equals(InteractType.TOUCHDRAGGED) && screenX >= interactables.get(i).getX()
						&& screenX <= interactables.get(i).getX() + interactables.get(i).getWidth()
						&& y >= interactables.get(i).getY()
						&& y <= interactables.get(i).getY() + interactables.get(i).getHeight()) {
					interactables.get(i).onTouch(InteractType.TOUCHDRAGGED, screenX, y, pointer, 0);
				}
			}
		}
		return true;

	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		int y = Gdx.graphics.getHeight() - screenY;
		for (int i = 0; i < interactables.size(); i++) {
			for (InteractType type : interactables.get(i).getAcceptedInteractions()) {
				if (type.equals(InteractType.MOUSEMOVED) && screenX >= interactables.get(i).getX()
						&& screenX <= interactables.get(i).getX() + interactables.get(i).getWidth()
						&& y >= interactables.get(i).getY()
						&& y <= interactables.get(i).getY() + interactables.get(i).getHeight()) {
					interactables.get(i).onMouseAbove(screenX, y);
				}
			}
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		for (int i = 0; i < interactables.size(); i++) {
			interactables.get(i).onScroll(amount);
		}
		return true;
	}

	public void checkMousePosition() {
		int x = Gdx.input.getX();
		int y = Gdx.graphics.getHeight() - Gdx.input.getY();

		for (int i = 0; i < interactables.size(); i++) {
			for (InteractType type : interactables.get(i).getAcceptedInteractions()) {
				if (type.equals(InteractType.MOUSEPOSITION) && x >= interactables.get(i).getX()
						&& x <= interactables.get(i).getX() + interactables.get(i).getWidth()
						&& y >= interactables.get(i).getY()
						&& y <= interactables.get(i).getY() + interactables.get(i).getHeight()) {
					interactables.get(i).onMouseAbove(x, y);
				}
			}
		}
	}

	public enum InteractType {
		TOUCHDOWN, TOUCHUP, TOUCHDRAGGED, KEYDOWN, KEYUP, KEYTYPED, MOUSEMOVED, MOUSEPOSITION, SCROLLED
	}

	public interface Interactable {
		public float getX();

		public float getY();

		public float getWidth();

		public float getHeight();

		public InteractType[] getAcceptedInteractions();

		public void onTouch(InteractType type, float x, float y, int pointer, int button);

		public void onMouseAbove(float x, float y);

		public void onScroll(float amount);
	}
}
