package com.team3gdx.game.station;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.screen.GameScreen;

public class CuttingStation extends Station {

	private final static Ingredient[] ALLOWED_INGREDIENTS = { Ingredients.lettuce, Ingredients.tomato,
			Ingredients.onion, Ingredients.cheese, Ingredients.cookedPotato };

	public float currentCutTime;

	public CuttingStation(Vector2 pos, float timeBetweenCuts, boolean active) {
		super(pos, 1, false, ALLOWED_INGREDIENTS, "audio/soundFX/chopping.mp3", active);
	}

	/**
	 * Slices the ingredient in slot if cook is interacting.
	 * 
	 * @param batch
	 * @param time
	 */
	public void interact(MainGameClass game, float dT) {
		currentCutTime += dT;
		if (slots.peek().slice(game, currentCutTime)) {
			currentCutTime = 0;
		}
	}

	/**
	 * Lock interacting cook at station.
	 * 
	 * @return A boolean indicating if the cook was successfully locked.
	 */
	public boolean lockCook() {
		if (!slots.isEmpty()) {
			if (lockedCook == null) {
				GameScreen.cook.locked = true;
				lockedCook = GameScreen.cook;
			} else {
				lockedCook.locked = true;
			}
			slots.peek().slicing = true;

			return true;
		}
		if (lockedCook != null) {
			lockedCook.locked = false;
			lockedCook = null;
			currentCutTime = 0;
		}

		return false;
	}

}
