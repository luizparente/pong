package Pong;

import java.awt.Color;

public class Paddle
{
    // Variables
    private int paddleWidth;
    private int paddleHeight;
    private Color color;

    // Constructors
    public Paddle(int paddleWidth, int paddleHeight, Color color)
    {
        this.paddleWidth = paddleWidth;
        this.paddleHeight = paddleHeight;
        this.color = color;
    }

    // Getters and Setters
    public int getPaddleWidth()
    {
        return paddleWidth;
    }

    public void setPaddleWidth(int paddleWidth)
    {
        this.paddleWidth = paddleWidth;
    }

    public int getPaddleHeight()
    {
        return paddleHeight;
    }

    public void setPaddleHeight(int paddleHeight)
    {
        this.paddleHeight = paddleHeight;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
