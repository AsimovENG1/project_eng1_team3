package com.team3gdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.save.GameInfo;
import com.team3gdx.game.save.SaveService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LoadScreen extends ScreenAdapter {

    private final MainGameClass game;
    private final SaveService save;
    private final Stage stage;
    private final Table table;
    private Texture background;

    private Array<GameInfo> saves = new Array<>();

    public LoadScreen(final MainGameClass game) {

        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        save = new SaveService();
    }

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("uielements/MainScreenBackground.jpg"));

        saves = save.getSavedGames();

        Table menuTable = new Table();

        Button menuButton = new Button(new TextureRegionDrawable(
                new Texture(Gdx.files.internal("uielements/exitmenu.png"))
        ));

        menuButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                game.setScreen(game.getMainScreen());
            }
        });

        menuTable.add(menuButton).padTop(75).padRight(75).expandX().right();

        table.add(menuTable).expandX().top().right();

        table.row().expandY();

        Table listTable = new Table();

        Skin skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        style.up = skin.newDrawable("white", Color.DARK_GRAY);
        style.down = skin.newDrawable("white", Color.DARK_GRAY);
        style.checked = skin.newDrawable("white", Color.BLUE);
        style.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        style.font = new BitmapFont();

        skin.add("default", style);

        for (GameInfo save : saves) {
            TextButton button = new TextButton(save.gameMode.name + " Mode | " + new SimpleDateFormat("HH:mm dd/MM/yyyy").format(save.createdAt), skin);

            button.addListener(new ClickListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new GameScreen(game, save));
                    super.touchUp(event, x, y, pointer, button);
                }
            });

            listTable.add(button).height(75).padBottom(10);
            listTable.row();
        }

        table.add(listTable);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        stage.act();
        stage.draw();
    }
}
