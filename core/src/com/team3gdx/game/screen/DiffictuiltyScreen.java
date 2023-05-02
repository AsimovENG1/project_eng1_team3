package com.team3gdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team3gdx.game.MainGameClass;
import com.team3gdx.game.util.EndlessMode;
import com.team3gdx.game.util.GameMode;
import com.team3gdx.game.util.ScenarioMode;
import com.team3gdx.game.util.TutorialMode;

public class DiffictuiltyScreen implements Screen{
    final MainGameClass game;
	final MainScreen ms;
	float v = 0;
	float s = 0;

	int gameResolutionX;
	int gameResolutionY;

	float buttonwidth;
	float buttonheight;

	float xSliderMin;
	float xSliderMax;

	float sliderWidth;

	Button easy;
	Button mediaum;
	Button hard;
	Button endless;
	Button tutorial;
	Button exit;

	OrthographicCamera camera;
	Viewport viewport;

	Texture EButton;
	Texture MButton;
	Texture HButton;
	Texture tutorialButton;
	Texture EndButton;
	Texture exitScreen;
	Texture background;
	Stage stage;

	enum STATE {
		main, select, new_game;
	}

	STATE state;
	GameMode gameMode;

	/**
	 * Constructor for main menu screen
	 * 
	 * @param game - main entry point class
	 * * @param ms   - Title screen class
	 */
	public DiffictuiltyScreen(MainGameClass game, MainScreen ms) {
		this.game = game;
		this.ms = ms;
		this.gameResolutionX = Gdx.graphics.getWidth();
		this.gameResolutionY = Gdx.graphics.getHeight();
		this.buttonwidth = (float) gameResolutionX / 3;
		this.buttonheight = (float) gameResolutionY / 6;
	}

	/**
	 * What should be done when the screen is shown
	 */
	@Override
	public void show() {

		state = STATE.select;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, gameResolutionX, gameResolutionY);
		viewport = new FitViewport(gameResolutionX, gameResolutionY, camera);

		EButton = new Texture(Gdx.files.internal("uielements/button_easy.png"));
		MButton = new Texture(Gdx.files.internal("uielements/button_medium.png"));
		HButton = new Texture(Gdx.files.internal("uielements/button_hard.png"));
		tutorialButton = new Texture(Gdx.files.internal("uielements/tutorial.png"));
		EndButton = new Texture(Gdx.files.internal("uielements/button_endless.png"));
		background = new Texture(Gdx.files.internal("uielements/MainScreenBackground.jpg"));
		exitScreen = new Texture(Gdx.files.internal("uielements/exitmenu.png"));

		stage = new Stage(viewport, game.batch);
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		Table leftTable = new Table();
		Table rightTable = new Table();

		table.add(leftTable).expandY().padRight(10);
		table.add(rightTable).expandY().padLeft(10);

		tutorial = new Button(new TextureRegionDrawable(tutorialButton));

		tutorial.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				gameMode = new TutorialMode();
				super.touchUp(event, x, y, pointer, button);
			}
		});

		leftTable.add(tutorial).size(buttonwidth, buttonheight);

		leftTable.row().padTop(10);

		easy = new Button(new TextureRegionDrawable(EButton));

		easy.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				gameMode = new ScenarioMode(5, 3,1,60000);
				super.touchUp(event, x, y, pointer, button);
			}
		});

		leftTable.add(easy).size(buttonwidth, buttonheight);

		leftTable.row().padTop(10);

		mediaum = new Button(new TextureRegionDrawable(MButton));

		mediaum.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				gameMode = new ScenarioMode(10, 3,2,45000);
				super.touchUp(event, x, y, pointer, button);
			}
		});

		leftTable.add(mediaum).size(buttonwidth, buttonheight);

		leftTable.row().padTop(10);

		hard = new Button(new TextureRegionDrawable(HButton));

		hard.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				gameMode = new ScenarioMode(15, 3,3,30000);
				super.touchUp(event, x, y, pointer, button);
			}
		});

		leftTable.add(hard).size(buttonwidth, buttonheight);

		leftTable.row().padTop(10);

		endless = new Button(new TextureRegionDrawable(EndButton));

		endless.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.new_game;
				gameMode = new EndlessMode(3,30000);
				super.touchUp(event, x, y, pointer, button);
			}
		});

		leftTable.add(endless).size(buttonwidth, buttonheight);

		exit = new Button(new TextureRegionDrawable(exitScreen));

		exit.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				state = STATE.main;
				super.touchUp(event, x, y, pointer, button);
			}
		});

		rightTable.add(exit).size(buttonwidth, buttonheight);
	}

	/**
	 * Main menu render method
	 * 
	 * @param delta - some change in time
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0, 0, 0, 0);
		game.batch.setProjectionMatrix(camera.combined);
		game.mainScreenMusic.play();

		game.batch.begin();
		game.batch.draw(background, 0, 0, gameResolutionX, gameResolutionY);
		game.batch.end();
		stage.act();
		stage.draw();
		changeScreen(state);

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			state = STATE.main;
		}
	}

	/**
	 * Change screen to specified state
	 * 
	 * @param state - state to change screen to
	 */
	public void changeScreen(STATE state) {
		if (state == STATE.new_game) {
			game.mainScreenMusic.dispose();
			game.setScreen(new GameScreen(game, ms, gameMode));
		}
		if (state == STATE.main) {
			game.mainScreenMusic.dispose();
			game.setScreen(game.getMainScreen());
		}
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		viewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}