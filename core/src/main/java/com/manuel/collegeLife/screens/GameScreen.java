package com.manuel.collegeLife.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.manuel.collegeLife.game.MainGame;
import com.manuel.collegeLife.entities.Player;

public class GameScreen implements Screen {
    private MainGame game;
    private Player player;

    public GameScreen(MainGame game) {
        this.game = game;
        this.player = new Player();
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);

        game.batch.begin();
        game.batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        game.batch.end();

        // Si presionas ESC, vuelve al menú
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(game, this));
        }
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        player.dispose();
    }
}
