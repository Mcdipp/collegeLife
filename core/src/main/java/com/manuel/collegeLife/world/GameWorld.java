package com.manuel.collegeLife.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.manuel.collegeLife.entities.Player;

public class GameWorld {

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private float width;
    private float height;

    private Player player;

    public GameWorld() {
        map = new TmxMapLoader().load("MapaTest.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);

        width = mapWidth * tileWidth;
        height = mapHeight * tileHeight;

        player = new Player();
    }

    public void update(float delta) {
        player.update(delta);

        float maxX = width - player.getWidth();
        float maxY = height - player.getHeight();

        player.getPosition().x = MathUtils.clamp(player.getPosition().x, 0, maxX);
        player.getPosition().y = MathUtils.clamp(player.getPosition().y, 0, maxY);
    }

    /** Renderiza SOLO entidades */
    public void renderEntities(SpriteBatch batch) {
        batch.draw(
            player.getTexture(),
            player.getPosition().x,
            player.getPosition().y
        );
    }

    /** Renderiza SOLO el mapa */
    public void renderMap(OrthographicCamera camera) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public Player getPlayer() {
        return player;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        player.dispose();
    }
}
