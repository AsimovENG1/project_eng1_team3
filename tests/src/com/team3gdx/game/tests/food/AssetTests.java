package com.team3gdx.game.tests.food;

import com.badlogic.gdx.Gdx;
import com.team3gdx.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetTests {

    @Test
    public void testBurgerAssetExists() {
        assertTrue(Gdx.files.internal("items/burger.png").exists());
        assertTrue(Gdx.files.internal("items/burger_burned.png").exists());
        assertTrue(Gdx.files.internal("items/burger_cooked.png").exists());
    }

    @Test
    public void testBunAssetExists() {
        assertTrue(Gdx.files.internal("items/burger_bun.png").exists());
        assertTrue(Gdx.files.internal("items/burger_bun_burned.png").exists());
        assertTrue(Gdx.files.internal("items/burger_bun_cooked.png").exists());
    }

    @Test
    public void testMeatAssetExists() {
        assertTrue(Gdx.files.internal("items/Meat.png").exists());
        assertTrue(Gdx.files.internal("items/Meat overcooked.png").exists());
        assertTrue(Gdx.files.internal("items/Meat undercooked.png").exists());
    }

    @Test
    public void testLettuceAssetExists() {
        assertTrue(Gdx.files.internal("items/lettuce.png").exists());
        assertTrue(Gdx.files.internal("items/lettuce_chopped.png").exists());
    }

    @Test
    public void testOnionAssetExists() {
        assertTrue(Gdx.files.internal("items/onion.png").exists());
        assertTrue(Gdx.files.internal("items/onion_chopped.png").exists());
    }

    @Test
    public void testPattyAssetExists() {
        assertTrue(Gdx.files.internal("items/patty.png").exists());
        assertTrue(Gdx.files.internal("items/patty_burned.png").exists());
        assertTrue(Gdx.files.internal("items/patty_cooked.png").exists());
    }

    @Test
    public void testTomatoAssetExists() {
        assertTrue(Gdx.files.internal("items/tomato.png").exists());
        assertTrue(Gdx.files.internal("items/tomato_chopped.png").exists());
        assertTrue(Gdx.files.internal("items/tomato_cooked.png").exists());
        assertTrue(Gdx.files.internal("items/tomato_burned.png").exists());
    }

    @Test
    public void testPotatoAssetExists(){
        assertTrue(Gdx.files.internal("items/cheese.png").exists());
        assertTrue(Gdx.files.internal("items/jacket_potato.png").exists());
        assertTrue(Gdx.files.internal("items/potato_cooked.png").exists());
        assertTrue(Gdx.files.internal("items/potato_cooked_chopped.png").exists());
        assertTrue(Gdx.files.internal("items/potato_chopped.png").exists());
        assertTrue(Gdx.files.internal("items/cheese_chopped.png").exists());
        assertTrue(Gdx.files.internal("items/cheese_mushy.png").exists());
        assertTrue(Gdx.files.internal("items/potato.png").exists());
        assertTrue(Gdx.files.internal("items/potato_burned.png").exists());
        assertTrue(Gdx.files.internal("items/potato_mushy.png").exists());
    }

    @Test
    public void testPizzaAssetExists() {
        assertTrue(Gdx.files.internal("items/dough.png").exists());
        assertTrue(Gdx.files.internal("items/dough_burned.png").exists());
        assertTrue(Gdx.files.internal("items/dough_cooked.png").exists());
        assertTrue(Gdx.files.internal("items/raw_pizza.png").exists());
        assertTrue(Gdx.files.internal("items/unformed_dough.png").exists());
        assertTrue(Gdx.files.internal("items/tomato_sauce.png").exists());
    }
}
