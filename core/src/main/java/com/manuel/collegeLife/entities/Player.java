package com.manuel.collegeLife.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Vector2 position;

    public Player(){
        texture = new Texture("player1.png");
        position = new Vector2(100,100);
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta){
    }

    public void render(SpriteBatch batch){
    }

    public void dispose() {
        texture.dispose();
    }

}
