package com.blogspot.tinydevbugs.clock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxClock extends ApplicationAdapter {

    ShapeRenderer renderer;
    VisualClock visualClock;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        visualClock = new VisualClock();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        visualClock.update();

        renderer.begin(ShapeRenderer.ShapeType.Line);
            visualClock.render(renderer);
        renderer.end();
    }
}
