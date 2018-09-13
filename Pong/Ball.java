package Pong;

import java.awt.Color;

public class Ball
{
    // Variables
    private int xSpeed;
    private int ySpeed;
    private int radius;
    private int xPosition;
    private int yPosition;
    private Color color;

    // Constructors
    public Ball(int radius, int xPosition, int yPosition, Color color)
    {
        this.radius = radius;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    public Ball(int radius, int xPosition, int yPosition, int xSpeed, int ySpeed, Color color)
    {
        this(radius, xPosition, yPosition, color);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    // Getters and Setters
    public int getxSpeed()
    {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed)
    {
        this.xSpeed = xSpeed;
    }

    public int getySpeed()
    {
        return ySpeed;
    }

    public void setySpeed(int ySpeed)
    {
        this.ySpeed = ySpeed;
    }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    public int getxPosition()
    {
        return xPosition;
    }

    public void setxPosition(int xPosition)
    {
        this.xPosition = xPosition;
    }

    public int getyPosition()
    {
        return yPosition;
    }

    public void setyPosition(int yPosition)
    {
        this.yPosition = yPosition;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void move(int xSpeed, int ySpeed)
    {
        this.xPosition += xSpeed;
        this.yPosition += ySpeed;
    }

    public void bounce(Bounce direction)
    {
        if (direction == Bounce.X)
            this.xSpeed = -this.xSpeed;
        if (direction == Bounce.Y)
            this.ySpeed = -this.ySpeed;
        if (direction == Bounce.XY)
        {
            this.xSpeed = -this.xSpeed;
            this.ySpeed = -this.ySpeed;
        }
    }
}
