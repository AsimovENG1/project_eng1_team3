package com.team3gdx.game.station;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;

public class BakingStation extends CookingStation {


	private final static Ingredient[] ALLOWED_INGREDIENTS = {Ingredients.bun, Ingredients.cooked_bun, Ingredients.potato ,Ingredients.cookedPotato, Ingredients.formedDough, Ingredients.cookedDough };

	public BakingStation(Vector2 pos, boolean active) {
		super(pos, 4, ALLOWED_INGREDIENTS, "particles/smokes.party", "audio/soundFX/frying.mp3", active);
	}

	@Override
	public boolean place(Ingredient ingredient) {
		if (super.place(ingredient)) {
			ingredient.flipped = true;
			return true;
		}

		return false;
	}

}
