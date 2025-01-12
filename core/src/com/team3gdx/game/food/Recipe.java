package com.team3gdx.game.food;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.MainGameClass;

/**
 * Represents a recipe (combination of ingredients and states).
 * 
 */
public class Recipe extends Ingredient {

	MainGameClass game;

	/**
	 * Name of the recipe to get texture.
	 */
	public final String name;

	/**
	 * Map of ingredient and instruction on what to do with it.
	 */
	public Map<Ingredient, String> ingredientInstructions;

	/**
	 * Whether the recipe has to be made in order (specific order of ingredients).
	 */
	public boolean shouldBeOrdered;
	/**
	 * Additional instructions.
	 */
	private String initialSteps, finalSteps;

	private Ingredient initialIngredient;

	/**
	 * How much the menu item will cost.
	 */
	public int cost;

	/**
	 * Sets the appropriate recipe properties.
	 * 
	 * @param initialSteps           Preface to the recipe.
	 * @param initialIngredient      Ingredient to display with the initial step.
	 * @param ingredientInstructions Map of ingredient and instruction on what to do
	 *                               with it.
	 * @param finalSteps             Postface to the recipe.
	 * @param name                   Name of the recipe to find the correct texture.
	 * @param pos                    The (x, y) coordinates of the ingredient.
	 * @param width                  The recipe's texture width.
	 * @param height                 The recipe's texture height.
	 * @param cost                   How much the menu item will cost.
	 */
	public Recipe(String initialSteps, Ingredient initialIngredient, Map<Ingredient, String> ingredientInstructions,
			String finalSteps, String name, Vector2 pos, float width, float height,
			int cost) {
		super(pos, width, height, name, 0, 0);
		this.initialSteps = initialSteps;
		this.initialIngredient = initialIngredient;
		this.ingredientInstructions = ingredientInstructions;
		this.finalSteps = finalSteps;
		this.name = name;
		this.cost = cost;
	}

	/**
	 * Show the recipe as a list of instructions with the corresponding ingredient
	 * texture displayed next to it.
	 * 
	 * @param batch The {@link SpriteBatch} to render the textures.
	 * @param pos   The (x, y) coordinates for the first instruction.
	 */

	public void displayRecipe(MainGameClass game, Vector2 pos) {
		String completeRecipe = initialSteps + "\n\n";
		int i = -1;
		if (initialIngredient != null) {
			i = 0;
			Ingredient initial = new Ingredient(initialIngredient);
			initial.pos = new Vector2(pos);
			initial.pos.x -= 48;
			initial.pos.y += --i * 2 * 18 + 18;
			initial.draw(game.batch);
		}
		for (Ingredient ingredient : ingredientInstructions.keySet()) {
			completeRecipe += ingredientInstructions.get(ingredient) + " " + ingredient.name + "\n\n";
			ingredient.pos = new Vector2(pos);
			ingredient.pos.x -= 48;
			ingredient.pos.y += --i * 2 * 18 + 18;
			ingredient.draw(game.batch);
		}
		Ingredient result = new Ingredient(this);
		result.pos = new Vector2(pos);
		result.pos.x -= 48;
		result.pos.y += --i * 2 * 18 + 18;
		result.draw(game.batch);

		completeRecipe += finalSteps;

		// Display the instructions.
		game.batch.begin();
		game.font2.draw(game.batch, completeRecipe, pos.x - 16, pos.y);
		game.batch.end();
	}


	/**
	 * Check if there is a recipe with the given ingredients.
	 * 
	 * @param givenIngredients A Stack of ingredients to compare to recipes.
	 * @return A boolean to indicate if a recipe exists with the given ingredients.
	 */
	public boolean matches(Stack<Ingredient> givenIngredients) {
		ArrayList<Ingredient> toCheck = new ArrayList<Ingredient>(ingredientInstructions.keySet());
		if (givenIngredients.size() != toCheck.size())
			return false;
		for (Ingredient ingredient : givenIngredients) {
			if (contains(ingredient, toCheck) && ingredient.checkUsable(ingredient) == true) {
				toCheck.remove(ingredient);
			}
		}
		if (toCheck.isEmpty())
			return true;

		return false;
	}

	/**
	 * Check whether the list contains a match with the given ingredient (since we
	 * use {@link Ingredient#equals(Object)}).
	 * 
	 * @param checkIngredient The ingredient to check.
	 * @param ingredients     The list of ingredients to search through.
	 * @return A boolean to indicate if the ingredient matches one in the list.
	 */
	public boolean contains(Ingredient checkIngredient, ArrayList<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredientInstructions.keySet()) {
			if (ingredient.equals(checkIngredient) && ingredient.checkUsable(checkIngredient) == true)
					return true;
			}

		return false;
	}
	public int cost(){
		return cost;
	}
}
