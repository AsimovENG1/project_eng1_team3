package com.team3gdx.game.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.HashMap;
import java.util.Map;

public class PreferenceService {

    public Map<String, String> getValues(String name) {
        Preferences prefs = Gdx.app.getPreferences(name);

        HashMap<String, String> result = new HashMap<>();

        for (String id : prefs.get().keySet()) {
            result.put(id, prefs.getString(id));
        }

        return result;
    }

    public void putValue(String name, String key, String value) {
        Preferences prefs = Gdx.app.getPreferences(name);

        prefs.putString(key, value);

        prefs.flush();
    }
}
