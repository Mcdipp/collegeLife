package com.manuel.collegeLife.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.manuel.collegeLife.entities.Player;
import com.manuel.collegeLife.game.MainGame;
import com.manuel.collegeLife.world.GameWorld;

public class MenuScreen implements Screen {

    private static final float WORLD_WIDTH = 800;
    private static final float WORLD_HEIGHT = 600;

    private MainGame game;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;

    private GameWorld world;

    private Player player;

    public MenuScreen(MainGame game) {
        this.game = game;

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.viewport.apply();

        this.world = new GameWorld();

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update(delta);

        Player player = world.getPlayer();

        float playerCenterX = player.getPosition().x + player.getTexture().getWidth() / 2f;
        float playerCenterY = player.getPosition().y + player.getTexture().getHeight() / 2f;

        float halfViewportWidth = viewport.getWorldWidth() / 2f;
        float halfViewportHeight = viewport.getWorldHeight() / 2f;

        float minX = halfViewportWidth;
        float maxX = world.getWidth() - halfViewportWidth;
        float minY = halfViewportHeight;
        float maxY = world.getHeight() - halfViewportHeight;

        float cameraX = MathUtils.clamp(playerCenterX, minX, maxX);
        float cameraY = MathUtils.clamp(playerCenterY, minY, maxY);

        camera.position.set(cameraX, cameraY, 0);
        camera.update();

        world.renderMap(camera);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        world.renderEntities(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

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
