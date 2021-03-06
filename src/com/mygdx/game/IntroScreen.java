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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.*;
//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

public class IntroScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private Game game;
    private AppPreferences preferences;
    private int i;

    public IntroScreen(Game game){

        this.game = game;
        viewport = new FitViewport(MyGame.V_WIDTH, MyGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MyGame) game).batch);
        preferences = new AppPreferences();
        i = preferences.getLevel();

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label nameLabel = new Label("DEATH OPS", font);
        Label playLabel = new Label("PRESS ENTER TO START A NEW GAME", font);
        Label aboutLabel = new Label("PRESS A FOR ABOUT", font);
        Label optionsLabel = new Label("PRESS Z FOR OPTIONS", font);

        table.add(nameLabel).expandX();
        table.row();
        if(i != 1){
            Label continueLabel = new Label("PRESS SPACE TO CONTINUE", font);

            table.add(continueLabel).expandX().padTop(10f);
            table.row();
        }
        table.add(playLabel).expandX();
        table.row();
        table.add(aboutLabel).expandX().padTop(5f);
        table.row();
        table.add(optionsLabel).expandX();

        stage.addActor(table);

        nameLabel.setVisible(true);
        playLabel.setVisible(true);
        aboutLabel.setVisible(true);
        optionsLabel.setVisible(true);

        MyGame.setScreenID(0);


    }

    @Override
    public void show() {
        if(preferences.isMusicEnabled()) {
            MyGame.music.setVolume(preferences.getMusicVolume());
            MyGame.music.play();
            MyGame.music.setLooping(true);
        }
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            i = 1;
            preferences.setLevel(i);
            game.setScreen(new PlayState((MyGame) game, i));
            dispose();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.SPACE)) && (i != 1)){
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

        /*
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayState((MyGame) game));
            dispose();
        }
         */
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
