package com.team3gdx.game.tests.save;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3gdx.game.save.GameInfo;
import com.team3gdx.game.save.JsonService;
import com.team3gdx.game.save.PreferenceService;
import com.team3gdx.game.save.SaveService;
import com.team3gdx.game.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(GdxTestRunner.class)
public class SaveServiceTests {

    @Test
    public void testGetSavedGamesLoadsValidData() {
        GameInfo game1 = new GameInfo();
        GameInfo game2 = new GameInfo();
        GameInfo game3 = new GameInfo();

        JsonService json = mock(JsonService.class);
        when(json.fromJson(eq("json1"), any())).thenReturn(game1);
        when(json.fromJson(eq("json2"), any())).thenReturn(game2);
        when(json.fromJson(eq("json3"), any())).thenReturn(game3);

        HashMap<String, String> values = new HashMap<>();
        values.put("game1", "json1");
        values.put("game2", "json2");
        values.put("game3", "json3");

        PreferenceService prefs = mock(PreferenceService.class);
        when(prefs.getValues("saves")).thenReturn(values);

        SaveService sut = new SaveService(json, prefs);

        Array<GameInfo> result = sut.getSavedGames();

        assertTrue(result.contains(game1, true));
        assertTrue(result.contains(game2, true));
        assertTrue(result.contains(game3, true));
    }

    @Test
    public void testSaveGameWritesValidGame() throws Exception {
        GameInfo game = new GameInfo();

        JsonService json = mock(JsonService.class);
        when(json.toJson(game)).thenReturn("json");

        PreferenceService prefs = mock(PreferenceService.class);

        SaveService sut = new SaveService(json, prefs);

        sut.saveGame(game);

        verify(prefs, times(1)).putValue(eq("saves"), anyString(), eq("json"));
    }

}
