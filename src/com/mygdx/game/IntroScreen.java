package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class IntroScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private Game game;
    private AppPreferences preferences;
    private int i = 1;

    public IntroScreen(Game game){

        this.game = game;
        viewport = new FitViewport(MyGame.V_WIDTH, MyGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MyGame) game).batch);
        preferences = new AppPreferences();

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label nameLabel = new Label("NOME GIOCO", font);
        Label playLabel = new Label("PRESS ENTER TO PLAY", font);
        Label aboutLabel = new Label("PRESS A FOR ABOUT", font);
        Label optionsLabel = new Label("PRESS Z FOR OPTIONS", font);
        Label exitLabel = new Label("PRESS ESC FOR EXIT THE GAME", font);

        table.add(nameLabel).expandX();
        table.row();
        table.add(playLabel).expandX().padTop(10f);
        table.row();
        table.add(aboutLabel).expandX();
        table.row();
        table.add(optionsLabel).expandX();
        table.row();
        table.add(exitLabel).expandX();

        stage.addActor(table);

        nameLabel.setVisible(true);
        playLabel.setVisible(true);
        aboutLabel.setVisible(true);
        optionsLabel.setVisible(true);
        exitLabel.setVisible(true);

        MyGame.setScreenID(0);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new PlayState((MyGame) game, i));
            dispose();
        }


        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            game.setScreen(new About((MyGame) game));
            dispose();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.Z)){
            game.setScreen(new Options((MyGame) game));
            dispose();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.exit(0);
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
