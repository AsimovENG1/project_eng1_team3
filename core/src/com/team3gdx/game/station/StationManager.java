package com.team3gdx.game.station;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.entity.Customer;
import com.team3gdx.game.food.Ingredient;
import com.team3gdx.game.food.Ingredients;
import com.team3gdx.game.food.Menu;
import com.team3gdx.game.screen.GameScreen;
import com.team3gdx.game.screen.GameScreen.STATE;

public class StationManager {

	public static Map<Vector2, Station> stations = new HashMap<Vector2, Station>();

	public void handleStations() {
		for (Station station : stations.values()) {
			if (!station.slots.empty() && !station.infinite) {
				for (int i = 0; i < station.slots.size(); i++) {
					// Handle each ingredient in slot
					Ingredient currentIngredient = station.slots.get(i);
					if (station instanceof PrepStation) {
						currentIngredient.pos = new Vector2(station.pos.x * 64 + 16, i * 8 + station.pos.y * 64);
						if (currentIngredient.slicing)
							((PrepStation)station).updateProgress(.01f);
					} else {
						currentIngredient.pos = new Vector2(station.pos.x * 64 + ((i * 32) % 64),
								Math.floorDiv((i * 32), 64) * 32 + station.pos.y * 64);
					}

					if (currentIngredient.cooking && station instanceof CookingStation) {
						((CookingStation) station).drawParticles(MainGameClass.batch, i);
						currentIngredient.cook(.0005f, MainGameClass.batch);
					} else {
						currentIngredient.draw(MainGameClass.batch);
					}

				}

			}

		}
	}

	String[] possibleOrders = new String[] { "Burger", "Salad" };

	public void checkInteractedTile(int id, Vector2 pos) {
		switch (id) {
		case 50:
			takeIngredientStation(pos, Ingredients.bun);
			break;
		case 51:
			takeIngredientStation(pos, Ingredients.unformedPatty);
			break;
		case 33:
			takeIngredientStation(pos, Ingredients.lettuce);
			break;
		case 39:
			takeIngredientStation(pos, Ingredients.tomato);
			break;
		case 46:
			// Frying station
			checkCookingStation(pos, new FryingStation(pos));
			break;
		case 47:
			// Preparation station

			if (!stations.containsKey(pos)) {
				stations.put(pos, new PrepStation(pos));
			}

			placeIngredientStation(pos);
			PrepStation station = ((PrepStation) stations.get(pos));

			if (!station.slots.isEmpty() && station.slotsToRecipe()) {
				if (station.lockedCook == null) {
					GameScreen.cook.locked = true;
					station.lockedCook = GameScreen.cook;
				} else {
					station.lockedCook.locked = true;
				}
				station.slots.peek().slicing = true;

			} else if (station.lockedCook != null) {
				station.lockedCook.locked = false;
				station.lockedCook = null;
//				if (!station.slots.empty()) station.slots.peek().slicing = false;
			}

			break;

		case 49:
			// Baking station
			checkCookingStation(pos, new BakingStation(pos));
			break;
		case 52:
			// Service station
			if (!stations.containsKey(pos)) {
				stations.put(pos, new ServingStation(pos));
			}
			Customer waitingCustomer = GameScreen.cc.isCustomerAtPos(new Vector2(pos.x - 1, pos.y));
			if (waitingCustomer != null && waitingCustomer.locked) {
				if (GameScreen.currentOrder.equals(""))
					GameScreen.currentOrder = possibleOrders[new Random().nextInt(possibleOrders.length)];
				else if (!stations.get(pos).slots.empty()
						&& stations.get(pos).slots.peek().equals(Menu.RECIPES.get(GameScreen.currentOrder))) {
					stations.get(pos).slots.pop();
					waitingCustomer.locked = false;
					GameScreen.cc.delCustomer(0);
				}

			}
			placeIngredientStation(pos);

			break;
		case 6:
			// Bin to dispose of unwanted ingredients.
			if (GameScreen.control.drop && !GameScreen.cook.heldItems.empty())
				GameScreen.cook.dropItem();
			break;
		default:
			placeIngredientStation(pos);
			break;
		}

	}

	private void drawTakeText(Vector2 pos) {
		if (!stations.get(pos).slots.empty() && !GameScreen.cook.full()) {
			drawText("Take [q]", new Vector2(pos.x * 64, pos.y * 64 - 16));
		}

	}

	private void drawDropText(Vector2 pos) {
		if (GameScreen.cook.heldItems.size() > 0 && stations.get(pos).isAllowed(GameScreen.cook.heldItems.peek())) {
			drawText("Drop [e]", new Vector2(pos.x * 64, pos.y * 64));
		}
	}

	private void drawText(String text, Vector2 pos) {
		MainGameClass.batch.begin();
		(new BitmapFont()).draw(MainGameClass.batch, text, pos.x, pos.y);
		MainGameClass.batch.end();
	}

	private boolean checkStationExists(Vector2 pos, Station station) {
		if (!stations.containsKey(pos)) {
			stations.put(pos, station);
			return false;
		}

		return true;
	}

	private void checkCookingStation(Vector2 pos, Station station) {
		checkStationExists(pos, station);
		if (!stations.get(pos).slots.empty() && !GameScreen.cook.full() && stations.get(pos).slots.peek().flipped)
			drawText("Take [q]", new Vector2(pos.x * 64, pos.y * 64 - 16));
		else
			drawDropText(pos);

		if (GameScreen.control.interact) {
			if (!stations.get(pos).slots.empty() && !GameScreen.cook.full()) {
				if (stations.get(pos).slots.peek().flipped)
					GameScreen.cook.pickUpItem(stations.get(pos).take());

				return;
			}
		}
		if (GameScreen.control.drop) {

			if (!GameScreen.cook.heldItems.empty() && stations.get(pos).place(GameScreen.cook.heldItems.peek())) {
				GameScreen.cook.dropItem();
				stations.get(pos).slots.peek().cooking = true;
			}
		}
		if (!stations.get(pos).slots.empty() && GameScreen.control.flip)
			stations.get(pos).slots.peek().flip();
	}

	private void placeIngredientStation(Vector2 pos) {
		checkStationExists(pos, new Station(pos, 4, false, null));
		drawTakeText(pos);
		drawDropText(pos);
		if (GameScreen.control.interact) {
			if (!stations.get(pos).slots.empty() && !GameScreen.cook.full()) {
				GameScreen.cook.pickUpItem(stations.get(pos).take());
				return;
			}
		}

		if (GameScreen.cook.heldItems.empty())
			return;
		if (GameScreen.control.drop)
			if (stations.get(pos).place(GameScreen.cook.heldItems.peek()))
				GameScreen.cook.dropItem();
	}

	private void takeIngredientStation(Vector2 pos, Ingredient ingredient) {
		checkStationExists(pos, new IngredientStation(pos, ingredient));
		drawTakeText(pos);

		if (GameScreen.control.interact) {
			GameScreen.cook.pickUpItem(stations.get(pos).take());
		}

	}

}
