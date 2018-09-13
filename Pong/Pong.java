package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong
{
    // VARIABLES
    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 500;
    public static int PLAYER_SPEED = 7;
    private GameState gameStatus = GameState.PAUSED;
    private Timer refresher;
    private int refreshRate = 15;

    // INNER CLASSES
    private class PongWindow extends JFrame
    {
        // VARIABLES
        private final int OFFSET_X = 0;
        private final int OFFSET_Y = 20;

        // Messages
        private JLabel message;

        // Scores
        private JLabel score;

        // Ball
        private Ball ball;

        // Paddles
        private Paddle bluePaddle;
        private Paddle greenPaddle;

        // Players
        private Player playerOne;
        private Player playerTwo;

        // CONSTRUCTORS
        public PongWindow()
        {
            // Drawing window
            super("Amazing Pong");
            setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);

            // Creating objects
            ball = new Ball(20, WINDOW_WIDTH/2 - 20 + OFFSET_X, WINDOW_HEIGHT/2 - 20 + OFFSET_Y,10, 10, new Color(204, 0, 102));

            bluePaddle = new Paddle(20, 100, new Color(0, 153, 255));
            greenPaddle = new Paddle(20, 100, new Color(102, 255, 102));

            // Creating players
            playerOne = new Player(PlayerType.HUMAN,
                    WINDOW_WIDTH - bluePaddle.getPaddleWidth() - 20,
                    WINDOW_HEIGHT/2 - bluePaddle.getPaddleHeight()/2,
                    bluePaddle);

            playerTwo = new Player(PlayerType.CPU,
                    greenPaddle.getPaddleWidth(),
                    WINDOW_HEIGHT/2 - greenPaddle.getPaddleHeight()/2,
                    greenPaddle);

            // Drawing background
            JPanel background = new JPanel();
            background.setBackground(new Color(26, 26, 26));
            background.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            background.addMouseMotionListener(new PaddleHandler());
            background.addMouseListener(new GameStart());
            background.setLayout(null);
            add(background);

            // Drawing score and message holders
            score = new JLabel();
            score.setFont(new Font("Sans-Serif", Font.BOLD, 20));
            score.setForeground(Color.WHITE);
            score.setText(String.format("%d | %d", playerTwo.getScore(), playerOne.getScore()));
            score.setBounds(0, 10, WINDOW_WIDTH, 30);
            score.setHorizontalAlignment(JLabel.CENTER);
            background.add(score);

            message = new JLabel();
            message.setFont(new Font("Sans-Serif", Font.BOLD, 20));
            message.setForeground(Color.WHITE);
            message.setHorizontalAlignment(JLabel.CENTER);
            message.setText("Click anywhere to start.");
            message.setBounds(100, 75, WINDOW_WIDTH - 200, 30);
            background.add(message);

            // Starting refresher
            refresher = new Timer(refreshRate, new MovementHandler());
        }

        // METHODS
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);

            // Drawing ball
            Graphics2D ballLayer = (Graphics2D)g;
            ballLayer.setColor(ball.getColor());
            ballLayer.fillOval(ball.getxPosition(),
                    ball.getyPosition(),
                    2*ball.getRadius(),
                    2*ball.getRadius());

            // Player One
            Graphics2D playerOneLayer = (Graphics2D)g;
            playerOneLayer.setColor(bluePaddle.getColor());
            playerOneLayer.fillRect(playerOne.getxPosition(),
                    playerOne.getyPosition(),
                    playerOne.getPaddle().getPaddleWidth(),
                    playerOne.getPaddle().getPaddleHeight());

            // Player Two
            Graphics2D playerTwoLayer = (Graphics2D)g;
            playerTwoLayer.setColor(greenPaddle.getColor());
            playerTwoLayer.fillRect(playerTwo.getxPosition(),
                    playerTwo.getyPosition(),
                    playerTwo.getPaddle().getPaddleWidth(),
                    playerTwo.getPaddle().getPaddleHeight());
        }

        // EVENT HANDLERS
        // Menu movement
        private class MovementHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                // Handling window borders and scoring
                if (ball.getxPosition() > WINDOW_WIDTH - 2*ball.getRadius())
                {
                    ball.bounce(Bounce.X);
                    playerTwo.addPoint(1);
                }
                if (ball.getxPosition() < 0)
                {
                    ball.bounce(Bounce.X);
                    playerOne.addPoint(1);
                }

                score.setText(String.format("%d | %d", playerTwo.getScore(), playerOne.getScore()));

                if (ball.getyPosition() > WINDOW_HEIGHT - 2*ball.getRadius() || ball.getyPosition() < OFFSET_Y)
                {
                    ball.bounce(Bounce.Y);
                }

                // Handling Player One
                if (ball.getxPosition() + 2*ball.getRadius() == playerOne.getxPosition()
                        && ball.getxSpeed() > 0
                        && ball.getyPosition() + ball.getRadius() >= playerOne.getyPosition() - ball.getRadius()
                        && ball.getyPosition() + ball.getRadius() <= playerOne.getyPosition() + playerOne.getPaddle()
                        .getPaddleHeight() + ball.getRadius())
                {
                    ball.bounce(Bounce.X);
                }

                // Handling Player Two
                if (ball.getyPosition() > playerTwo.getyPosition() + playerTwo.getPaddle().getPaddleHeight()/2)
                    playerTwo.move(playerTwo.getSpeed());
                else
                    playerTwo.move(-playerTwo.getSpeed());

                if (ball.getxPosition() == playerTwo.getxPosition() + playerTwo.getPaddle().getPaddleWidth()
                        && ball.getxSpeed() < 0
                        && ball.getyPosition() + ball.getRadius() >= playerTwo.getyPosition() - ball.getRadius()
                        && ball.getyPosition() + ball.getRadius() <= playerTwo.getyPosition() + playerTwo.getPaddle()
                        .getPaddleHeight() + ball.getRadius())
                {
                    ball.bounce(Bounce.X);
                }

                // Ball movement
                ball.move(ball.getxSpeed(), ball.getySpeed());

                // ******************************************************************************************************
                // HANDLING BONUSES
                // ******************************************************************************************************

                // Paddle Height Bonus
                activatePaddleHeightBonus(playerOne);
                activatePaddleHeightBonus(playerTwo);

                // ******************************************************************************************************

                // Refreshing frame
                Pong.PongWindow.this.repaint();
            }

            // BONUSES
            private void activatePaddleHeightBonus(Player player)
            {
                if (player.getScore()%5 == 0 && player.getScore() != 0)
                    player.getPaddle().setPaddleHeight(200);
                if (player.getScore()%5 == 0 && player.getScore() != 0)
                    player.getPaddle().setPaddleHeight(200);

                if (player.getScore()%6 == 0 && player.getScore() != 0)
                    player.getPaddle().setPaddleHeight(100);
                if (player.getScore()%6 == 0 && player.getScore() != 0)
                    player.getPaddle().setPaddleHeight(100);
            }
        }

        // Paddles movement
        private class PaddleHandler implements MouseMotionListener
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {

            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                playerOne.setyPosition(e.getY());
            }
        }

        // Menu start
        private class GameStart implements MouseListener
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (gameStatus == GameState.PAUSED)
                {
                    message.setVisible(false);
                }
                else
                {
                    message.setText("Click anywhere to resume.");
                    message.setVisible(true);
                }

                togglePause();
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        }

    }

    // METHODS
    public void start()
    {
        new PongWindow().setVisible(true);
    }

    public void togglePause()
    {
        if (gameStatus == GameState.PAUSED)
        {
            gameStatus = GameState.ONGOING;
            refresher.start();
        }
        else
        {
            gameStatus = GameState.PAUSED;
            refresher.stop();
        }
    }
}
