package com.team3gdx.game.tests;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.screen.LeaderBoard;
import com.team3gdx.game.screen.MainScreen;
import com.team3gdx.game.util.GameMode;
//import org.junit.jupiter.api.Test;
import com.team3gdx.game.util.ScenarioMode;
import org.junit.runner.RunWith;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(GdxTestRunner.class)
public class LeaderBoardTests {
    public MainGameClass game;
    public MainScreen ms;
    public GameMode gameMode;
    public LeaderBoard leaderboard;

    public LeaderBoard leaderboard1;
    public MainGameClass game1;
    public MainScreen ms1;
    public GameMode gameMode1;



    @Test
    public void imagine() {
        game = new MainGameClass();
        ms = new MainScreen(game);
        gameMode = new ScenarioMode(5, 3, 1, 60000);
        leaderboard = new LeaderBoard(game, ms, gameMode);



    }



    @Test
    public void testSortPlayerData() {
        game = new MainGameClass();
        ms = new MainScreen(game);
        gameMode = new ScenarioMode(5, 3, 1, 60000);
        leaderboard = new LeaderBoard(game, ms, gameMode);
        leaderboard.sortPlayerData();
        for (int i = 1; i < leaderboard.playerData.size(); i++) {
            int higherScore = Integer.parseInt(leaderboard.playerData.get(i).get(1));
            int score = Integer.parseInt(leaderboard.playerData.get(i - 1).get(1));
            if ( higherScore - score >= 0 ){
                assertTrue(higherScore >= score);
            }
        }
    }

}
