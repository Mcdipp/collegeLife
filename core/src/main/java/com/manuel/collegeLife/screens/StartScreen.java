package com.manuel.collegeLife.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.manuel.collegeLife.game.MainGame;

public class StartScreen implements Screen {
    private MainGame game;
    private Texture background;
    private BitmapFont font;
    private float elapsedTime;
    private boolean showText;

    public StartScreen(MainGame game) {
        this.game = game;
        this.background = new Texture("collegeLifeStartScreen.png");
        this.font = new BitmapFont(); // Usa fuente por defecto
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale(2f);
        this.elapsedTime = 0f;
        this.showText = false;
    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;

        if (elapsedTime >= 1.0f) {
            showText = true;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = game.batch;
        batch.begin();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float imageWidth = background.getWidth();
        float imageHeight = background.getHeight();

        float scale = Math.min(screenWidth / imageWidth, screenHeight / imageHeight);
        float drawWidth = imageWidth * scale;
        float drawHeight = imageHeight * scale;

        float x = (screenWidth - drawWidth) / 2;
        float y = (screenHeight - drawHeight) / 2;

        batch.draw(background, x, y, drawWidth, drawHeight);

        if (showText) {
            float alpha = Math.min((elapsedTime - 1f), 1f); // Fade in de 1 segundo
            font.setColor(1, 1, 1, alpha);
            GlyphLayout layout = new GlyphLayout(font, "Haz clic para continuar");
            font.draw(batch, layout, (800 - layout.width) / 2, 100);
        }

        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        background.dispose();
        font.dispose();
    }
}
