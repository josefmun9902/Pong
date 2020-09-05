import java.applet.*;
import java.net.URL;
import java.io.*;

public class PongGame
{
    public static final int PLAYING = 0;
    public static final int PLAYER_1_WINS = 1;
    public static final int PLAYER_2_WINS = 2;
    private int UTI=75;
    Paddle p1;
    Paddle p2;
    java.awt.Rectangle topWall;
    java.awt.Rectangle bottomWall;
    Ball ball;
    private int status;
    private int playerOneScore;
    private int playerTwoScore;
    private double sx;
    private double sy;
    private double deltaX;
    private double deltaY;
    private double deltaYtoH;
    private double deltaXtoH;
    private double percentToH;
    private boolean pause=false;
    private AudioClip ding = null;
    public PongGame()
    {
        reset();
        sx=ball.getX();
        sy=ball.getY();
        try {
            URL dingDong = new URL("File:ding.wav");
            ding = Applet.newAudioClip(dingDong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reset()
    {
        status=PLAYING;
        ball=new Ball(440, 290);
        int ran=(int)(Math.random()*2+1);
        if(ran==1)
            ball.setAngle(0);
        else
            ball.setAngle(180);
        playerOneScore=0;
        playerTwoScore=0;
        p1=new Paddle(0,255);
        p2=new Paddle(885, 255);
    }
    public void update()
    {
        if(UTI==0) {
            sx = ball.getX();
            sy = ball.getY();
            moveBall(ball.getSpeed());
            if (ball.getX() < 0) {
                playerTwoScore++;
                ball.setX(440);
                ball.setY(290);
                int ran = (int) (Math.random() * 2 + 1);
                if (ran == 1)
                    ball.setAngle(0);
                else
                    ball.setAngle(180);
                ball.setSpeed(20);
                UTI = 75;
            }
            if (ball.getX() > 900) {
                playerOneScore++;
                ball.setX(440);
                ball.setY(290);
                int ran = (int) (Math.random() * 2 + 1);
                if (ran == 1)
                    ball.setAngle(0);
                else
                    ball.setAngle(180);
                UTI = 75;
                ball.setSpeed(20);
            }
            if (playerOneScore == 7) {
                status = PLAYER_1_WINS;
            }
            if (playerTwoScore == 7) {
                status = PLAYER_2_WINS;
            }
            if (hitTopWall()) {
                ding.play();
                double hitY = 20;
                deltaX = sx - ball.getX();
                deltaY = sy - ball.getY();
                deltaYtoH = sy - hitY;
                percentToH = deltaYtoH / deltaY;
                deltaXtoH = deltaX * percentToH;
                double hitX = sx + deltaXtoH;
                ball.setX(hitX);
                ball.setY(hitY);
                double angle = ball.getAngle();
                double usedSpeed = Math.sqrt(Math.pow(deltaXtoH, 2) + Math.pow(deltaYtoH, 2));
                double leftOver = ball.getSpeed() - usedSpeed;
                ball.setAngle(360 - angle);
                moveBall(leftOver);
            }
            if (hitBottomWall()) {
                ding.play();
                double hitY = 560;
                deltaX = sx - ball.getX();
                deltaY = sy - ball.getY();
                deltaYtoH = sy - hitY;
                percentToH = deltaYtoH / deltaY;
                deltaXtoH = deltaX * percentToH;
                double hitX = sx + deltaXtoH;
                ball.setX(hitX);
                ball.setY(hitY);
                double angle = ball.getAngle();
                double usedSpeed = Math.sqrt(Math.pow(deltaXtoH, 2) + Math.pow(deltaYtoH, 2));
                double leftOver = ball.getSpeed() - usedSpeed;
                ball.setAngle(360 - angle);
                moveBall(leftOver);
            }
            if (hitRightPaddle()) {
                System.out.println("RIGHT");
                ding.play();
                double hitX = 865;
                deltaX = sx - ball.getX();
                deltaY = sy - ball.getY();
                double deltaXtoH = hitX - sx;
                percentToH = deltaXtoH / deltaX;
                deltaXtoH = hitX - sx;
                double hitY = sy + deltaYtoH;
                ball.setX(hitX);
                ball.setY(hitY);
                double angle = ball.getAngle();
                double usedSpeed = Math.sqrt(Math.pow(deltaXtoH, 2) + Math.pow(deltaYtoH, 2));
                double leftOver = ball.getSpeed() - usedSpeed;
                double paddleMid = getPaddle2().getY() + (getPaddle1().HEIGHT / 2);
                double ballMid = ball.getY() + (ball.HEIGHT / 2);
                double max_distance = (getPaddle2().HEIGHT + ball.HEIGHT) / 2;
                double actual_distance = Math.abs(ballMid - paddleMid);
                double percentDist = actual_distance / max_distance;
                System.out.println("Ball Mid: " + ballMid);
                if (percentDist == 0)
                    ball.setAngle(180);
                else if (percentDist == 1 && ballMid > paddleMid)
                    ball.setAngle(100);
                else if (percentDist == 1 && ballMid < paddleMid)
                    ball.setAngle(260);
                else if (ballMid > paddleMid)
                    ball.setAngle(180 - Math.abs(percentDist * 70));
                else if (ballMid < paddleMid)
                    ball.setAngle(180 + Math.abs(percentDist * 70));
                System.out.println("Middle of Paddle: " + paddleMid + "\nPercent to Mid: " + percentDist);
                moveBall(leftOver);
                ball.setSpeed(ball.getSpeed() + .25);
                System.out.println("Ball Speed: " + ball.getSpeed());
                System.out.println("Ball Angle: " + ball.getAngle());
            }
            if (hitLeftPaddle()) {
                System.out.println("LEFT");
                ding.play();
                double hitX = 15;
                deltaX = sx - ball.getX();
                deltaY = sy - ball.getY();
                double deltaXtoH = hitX - sx;
                percentToH = deltaXtoH / deltaX;
                deltaXtoH = hitX - sx;
                double hitY = sy + deltaYtoH;
                ball.setX(hitX);
                ball.setY(hitY);
                double angle = ball.getAngle();
                double usedSpeed = Math.sqrt(Math.pow(deltaXtoH, 2) + Math.pow(deltaYtoH, 2));
                double leftOver = ball.getSpeed() - usedSpeed;
                double paddleMid = getPaddle1().getY() + (getPaddle1().HEIGHT / 2);
                double ballMid = ball.getY() + (ball.HEIGHT / 2);
                double max_distance = (getPaddle1().HEIGHT + ball.HEIGHT) / 2;
                double actual_distance = Math.abs(ballMid - paddleMid);
                double percentDist = actual_distance / max_distance;
                System.out.println("Ball Mid: " + ballMid);
                if (percentDist == 0)
                    ball.setAngle(0);
                else if (percentDist == 1 && ballMid > paddleMid)
                    ball.setAngle(80);
                else if (percentDist == 1 && ballMid < paddleMid)
                    ball.setAngle(280);
                else if (ballMid > paddleMid)
                    ball.setAngle(Math.abs((percentDist)) * 70);
                else if (ballMid < paddleMid)
                    ball.setAngle(360 - (percentDist) * 70);
                System.out.println("Middle of Paddle: " + paddleMid + "\nPercent to Mid: " + percentDist);
                moveBall(leftOver);
                ball.setSpeed(ball.getSpeed() + .25);
                System.out.println("Ball Speed: " + ball.getSpeed());
                System.out.println("Ball Angle: " + ball.getAngle());
            }
        }
        else
            UTI--;
    }
    public int status()
    {
        return status;
    }
    public Paddle getPaddle1()
    {
        return p1;
    }
    public Paddle getPaddle2()
    {
        return p2;
    }
    public Ball getBall()
    {
        return ball;
    }
    public void moveBall(double distance)
    {
        ball.setX(ball.getX() + Math.cos(Math.toRadians(ball.getAngle()))*distance);
        ball.setY(ball.getY() + Math.sin(Math.toRadians(ball.getAngle()))*distance);
    }
    public void resetBall()
    {

    }
    public int getPlayerOneScore()
    {
        return playerOneScore;
    }
    public int getPlayerTwoScore()
    {
        return playerTwoScore;
    }
    public boolean hitTopWall()
    {
        if(ball.getRectangle().intersects(new java.awt.Rectangle(0, 0, 900, 20)))
            return true;
        return false;
    }
    public boolean hitBottomWall()
    {
        if(ball.getRectangle().intersects(new java.awt.Rectangle(0, 580, 900, 20)))
            return true;
        return false;
    }
    public boolean hitLeftPaddle()
    {
        if(getBall().getRectangle().intersects(new java.awt.Rectangle((int)(getPaddle1().getX()), (int)(getPaddle1().getY()), Paddle.WIDTH, Paddle.HEIGHT)))
            return true;
        return false;
    }
    public boolean hitRightPaddle()
    {
        if(getBall().getRectangle().intersects(new java.awt.Rectangle((int)(getPaddle2().getX()), (int)(getPaddle2().getY()), Paddle.WIDTH, Paddle.HEIGHT)))
            return true;
        return false;
    }
    public void setPause(boolean pause)
    {
        this.pause=pause;
    }
    public boolean getPause()
    {
        return pause;
    }
}
