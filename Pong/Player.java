package Pong;

public class Player
{
    // Variables
    private PlayerType playerType;
    private int xPosition;
    private int yPosition;
    private int speed;
    private Paddle paddle;
    private int score = 0;

    // Constructors
    public Player(PlayerType playerType, int xPosition, int yPosition, Paddle paddle)
    {
        this.playerType = playerType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.paddle = paddle;

        if (playerType == PlayerType.CPU)
            this.speed = Pong.PLAYER_SPEED;
    }

    // Getters and Setters
    public PlayerType getPlayerType()
    {
        return playerType;
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

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public Paddle getPaddle()
    {
        return paddle;
    }

    public void setPaddle(Paddle paddle)
    {
        this.paddle = paddle;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int points)
    {
        this.score = points;
    }

    public void addPoint(int points)
    {
        this.score++;
    }

    public void move(int ySpeed)
    {
        this.yPosition += ySpeed;
    }
}