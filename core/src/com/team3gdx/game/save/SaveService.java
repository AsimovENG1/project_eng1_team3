package com.team3gdx.game.save;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class SaveService {

    private JsonService json;
    private PreferenceService prefs;

    public SaveService() {
        json = new JsonService();
        prefs = new PreferenceService();
    }

    public SaveService(JsonService json, PreferenceService prefs) {
        this.json = json;
        this.prefs = prefs;
    }

    public Array<GameInfo> getSavedGames() {

        Map<String, String> saves = prefs.getValues("saves");

        Array<GameInfo> result = new Array<>();

        for (String id : saves.keySet()) {
            try {
                result.add(json.fromJson(saves.get(id), GameInfo.class));
            } catch (Exception ignored) {
                System.out.println(ignored);
            }
        }

        return result;
    }

    public void saveGame(GameInfo game) throws Exception {
        String id = UUID.randomUUID().toString();

        String json = null;
        try {
            json = this.json.toJson(game);
        } catch (Exception e) {
            throw new Exception("Game data serialisation failed.");
        }

        prefs.putValue("saves", id, json);
    }
}
