package com.manuel.collegeLife.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Vector2 position;
    private float speed = 200f;

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
        if (Gdx.input.isKeyPressed(Input.Keys.W)) position.y += speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) position.y -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) position.x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) position.x += speed * delta;
    }

    public void render(SpriteBatch batch){
    }


    public float getWidth() {
        return texture.getWidth();
    }

    public float getHeight() {
        return texture.getHeight();
    }

    public void dispose() {
        texture.dispose();
    }

}
