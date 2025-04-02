package com.manuel.collegeLife.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.manuel.collegeLife.game.MainGame;

public class PauseScreen implements Screen {
    private MainGame game;
    private Screen previousScreen; // Guardamos la pantalla del juego para reanudarlo

    public PauseScreen(MainGame game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        // Aquí podríamos dibujar un mensaje "PAUSA" en pantalla
        game.batch.end();

        // Si presionamos ENTER, volvemos al juego
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ENTER)) {
            game.setScreen(previousScreen);
        }

        // Si presionamos M, volvemos al menú principal
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.M)) {
            game.setScreen(new MenuScreen(game));
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
    public void dispose() { }
}
