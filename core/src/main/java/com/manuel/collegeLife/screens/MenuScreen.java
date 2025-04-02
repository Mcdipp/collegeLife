package com.manuel.collegeLife.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.manuel.collegeLife.entities.Player;
import com.manuel.collegeLife.game.MainGame;
import com.manuel.collegeLife.screens.GameScreen;

public class MenuScreen implements Screen {
    private MainGame game;
    private SpriteBatch batch;
    private Player player;

    public MenuScreen(MainGame game) {
        this.game = game;
        this.batch = game.batch;
//        this.player = new Player();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //player.update(delta);

        batch.begin();
        //batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        player.dispose();
    }
}
