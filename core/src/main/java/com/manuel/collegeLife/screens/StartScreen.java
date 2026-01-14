package com.manuel.collegeLife.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.manuel.collegeLife.game.MainGame;

//La pagina principal del juego. La carpeta screens permite de controlar que se ve y que se ejecuta
/*Una Screen es una escena del juego:

StartScreen → pantalla de inicio

MenuScreen → menú principal

GameScreen → juego

PauseScreen → pausa

LibGDX llama automáticamente a:

screen.render(delta); ---> cada frame.
*/

public class StartScreen implements Screen {

    private static final float WORLD_WIDTH = 800;
    private static final float WORLD_HEIGHT = 800;

    private MainGame game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture background;
    private BitmapFont font;
    private GlyphLayout layout;

    private float elapsedTime;
    private boolean showText;

    public StartScreen(MainGame game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();


        this.background = new Texture("collegeLifeStartScreen.png");
        this.font = new BitmapFont(); // Usa fuente por defecto
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale(2f);

        layout = new GlyphLayout(font, "Haz clic para continuar");

        this.elapsedTime = 0f;
        this.showText = false;
    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;

        if (elapsedTime >= 1f) {
            showText = true;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        SpriteBatch batch = game.batch;
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        if (showText) {
            float alpha = Math.min(elapsedTime - 1f, 1f);
            font.setColor(1, 1, 1, alpha);

            float textX = (WORLD_WIDTH - layout.width) / 2f;
            float textY = 100f;

            font.draw(batch, layout, textX, textY);
        }

        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        background.dispose();
        font.dispose();
    }
}
