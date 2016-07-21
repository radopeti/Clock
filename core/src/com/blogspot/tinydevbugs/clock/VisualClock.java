package com.blogspot.tinydevbugs.clock;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import static com.blogspot.tinydevbugs.clock.Constants.*;
import java.time.LocalTime;

/**
 * Created by Peter Rado on 2016. 07. 18..
 * Represent a visual clock build by simple shapes
 */
public class VisualClock {
    //the middle point of the clock
    private Vector2 midPoint;
    //the second coordinates of the lines which represent the hands of the clock
    private float hourX;
    private float hourY;
    private float minuteX;
    private float minuteY;
    private float secondX;
    private float secondY;
    //the class uses java.time.LocalTime the determine the current system time
    private LocalTime localTimeNow;

    /**
     * Constructor with test middle point coordinates
     */
    public VisualClock(){
        midPoint = new Vector2(150, 150);
    }

    /**
     * Update the current position of the hands based on the current system time
     */
    public void update(){
        localTimeNow = LocalTime.now();
        int currentHour = localTimeNow.getHour();
        int hour = currentHour > 12 ? currentHour - 12 : currentHour;
        int minute = localTimeNow.getMinute();
        int second = localTimeNow.getSecond();
        hourX = (float) calculateHandEndPointX(hour, minute);
        hourY = (float) calculateHandEndPointY(hour, minute);
        minuteX = (float) calculateHandEndPointX(MINUTE_HAND_SIZE, MINUTE_HAND_ANGLE, minute);
        minuteY = (float) calculateHandEndPointY(MINUTE_HAND_SIZE, MINUTE_HAND_ANGLE, minute);
        secondX = (float) calculateHandEndPointX(SECOND_HAND_SIZE, SECOND_HAND_ANGLE, second);
        secondY = (float) calculateHandEndPointY(SECOND_HAND_SIZE, SECOND_HAND_ANGLE, second);
    }

    /**
     * Draw and set the color of the visual components of the clock
     * @param renderer ShapeRenderer
     */
    public void render(ShapeRenderer renderer){
        renderer.setColor(HAND_COLOR);
        renderer.circle(midPoint.x, midPoint.y, CIRCLE_RADIUS);
        renderer.line(midPoint.x, midPoint.y, hourX, hourY);
        renderer.line(midPoint.x, midPoint.y, minuteX, minuteY);
        renderer.setColor(SECOND_HAND_COLOR);
        renderer.line(midPoint.x, midPoint.y, secondX, secondY);
    }

    /**
     * Calculate the second x coordinate of the hand
     * @param handSize the size of the hand (line) we want to see on the screen
     * @param angle one unit of a hand step in degrees
     * @param clockParam the current time in hour, minute or second depends on which hand's
     *                   coordinate we want to calculate
     * @return the second x coordinate of the hand
     */
    private double calculateHandEndPointX(int handSize, double angle, int clockParam){
        return midPoint.x + handSize * Math.sin(MathUtils.degreesToRadians * (clockParam * angle));
    }

    /**
     * Calculate the second y coordinate of the hand
     * @param handSize the size of the hand (line) we want to see on the screen
     * @param angle one unit of a hand step in degrees
     * @param clockParam the current time in hour, minute or second depends on which hand's
     *                   coordinate we want to calculate
     * @return the second y coordinate of the hand
     */
    private double calculateHandEndPointY(int handSize, double angle, int clockParam){
        return midPoint.y + handSize * Math.cos(MathUtils.degreesToRadians * (clockParam * angle));
    }

    /**
     * Calculate the hour hand's second x coordinate
     * @param clockParam the current hour
     * @param minute the current minute
     * @return the second x coordinate of the hour hand
     */
    private double calculateHandEndPointX(int clockParam, int minute){
        return midPoint.x + HOUR_HAND_SIZE * Math.sin(MathUtils.degreesToRadians * (clockParam * HOUR_HAND_ANGLE + (HOUR_HAND_ANGLE / 60 * minute)));
    }

    /**
     * Calculate the hour hand's second y coordinate
     * @param clockParam the current hour
     * @param minute the current minute
     * @return the second y coordinate of the hour hand
     */
    private double calculateHandEndPointY(int clockParam, int minute){
        return midPoint.y + HOUR_HAND_SIZE * Math.cos(MathUtils.degreesToRadians * (clockParam * HOUR_HAND_ANGLE + (HOUR_HAND_ANGLE / 60 * minute)));
    }
}
