package com.blogspot.tinydevbugs.clock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class MyGdxClock extends ApplicationAdapter {

	ShapeRenderer renderer;
	LocalTime time;
	int second;
	float midPoint = 150;
	VisualClock visualClock;

	@Override
	public void create () {
		renderer = new ShapeRenderer();
		visualClock = new VisualClock();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		visualClock.update();

		renderer.begin(ShapeRenderer.ShapeType.Line);
			visualClock.render(renderer);
		renderer.end();
	}
}
