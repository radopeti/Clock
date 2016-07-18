package com.blogspot.tinydevbugs.clock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.time.LocalTime;

/**
 * Created by hátén on 2016. 07. 18..
 */
public class VisualClock {

    private static final int HOUR_HAND_SIZE = 70;
    private static final int MINUTE_HAND_SIZE = 110;
    private static final int SECOND_HAND_SIZE = 100;
    private static final int HOUR_HAND_ANGLE = 30;
    private static final int MINUTE_HAND_ANGLE = 6;
    private static final int SECOND_HAND_ANGLE = 6;
    private static final Color HAND_COLOR = Color.WHITE;
    private static final Color SECOND_HAND_COLOR = Color.RED;
    private Vector2 midPoint;
    private float hourX;
    private float hourY;
    private float minuteX;
    private float minuteY;
    private float secondX;
    private float secondY;
    private LocalTime localTimeNow;

    public VisualClock(){
        midPoint = new Vector2(150, 150);

    }

    public void update(){
        localTimeNow = LocalTime.now();
        int currentHour = localTimeNow.getHour();

        int hour = currentHour > 12 ? currentHour - 12 : currentHour;
        int minute = localTimeNow.getMinute();
        int second = localTimeNow.getSecond();


        hourX = (float) calculateHandEndPointX(HOUR_HAND_SIZE, HOUR_HAND_ANGLE, hour, second);
        hourY = (float) calculateHandEndPointY(HOUR_HAND_SIZE, HOUR_HAND_ANGLE, hour, second);
        minuteX = (float) calculateHandEndPointX(MINUTE_HAND_SIZE, MINUTE_HAND_ANGLE, minute);
        minuteY = (float) calculateHandEndPointY(MINUTE_HAND_SIZE, MINUTE_HAND_ANGLE, minute);
        secondX = (float) calculateHandEndPointX(SECOND_HAND_SIZE, SECOND_HAND_ANGLE, second);
        secondY = (float) calculateHandEndPointY(SECOND_HAND_SIZE, SECOND_HAND_ANGLE, second);
    }

    public void render(ShapeRenderer renderer){
        renderer.setColor(HAND_COLOR);
        renderer.circle(midPoint.x, midPoint.y, 120);
        renderer.line(midPoint.x, midPoint.y, hourX, hourY);
        renderer.line(midPoint.x, midPoint.y, minuteX, minuteY);
        renderer.setColor(SECOND_HAND_COLOR);
        renderer.line(midPoint.x, midPoint.y, secondX, secondY);
    }

    private double calculateHandEndPointX(int handSize, int angle, int clockParam){
        return midPoint.x + handSize * Math.sin(MathUtils.degreesToRadians * (clockParam * angle));
    }

    private double calculateHandEndPointY(int handSize, int angle, int clockParam){
        return midPoint.y + handSize * Math.cos(MathUtils.degreesToRadians * (clockParam * angle));
    }

    private double calculateHandEndPointX(int handSize, int angle, int clockParam, int minute){
        return midPoint.x + handSize * Math.sin(MathUtils.degreesToRadians * (clockParam * angle + (angle / 60 * minute)));
    }

    private double calculateHandEndPointY(int handSize, int angle, int clockParam, int minute){
        return midPoint.y + handSize * Math.cos(MathUtils.degreesToRadians * (clockParam * angle + (angle / 60 * minute)));
    }
}
