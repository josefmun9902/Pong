//done, at least I think so....
/*Master's Notes
//
//
-90 degrees is down, 270 is up, 0 is right, 180 is left
//
//
//
WALLS
-figure out where the ball hits the wall(not where the ball is, where the ball should have hit)
-use hit y, (the wall height)
-delta y=sy-ey
-delta s=sx-ex
-delta y to hit=sy-hy
-%2h = delta y 2 hit/ delta y
-delta x to hit = delta a *%2h
-hx=sx+delta x hit
-usedPeed=sqrt of (delta x to hit)squared+(delta y to hit)squared
-leftOverSpeed=speed-usedSpeed
-then the bottom wall is the ball height and y of bottom wall
PADDLES
-hit x=px-bw
-for the other paddle x is y and y is x
//
//
//
*/
import java.awt.*;
public class Ball
{
    public static final double MAX_SPEED=8.5;
    public static final int WIDTH=20;
    public static final int HEIGHT=20;
    public double x;
    public double y;
    public double speed;
    public double angle;
    public Ball(double x, double y)
    {
        this.x=x;
        this.y=y;
        speed=10;
        //make the ball go a random direction to test walls
        //angle=(int)(Math.random()*360+1);
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getAngle()
    {
        return angle;
    }
    public double getSpeed()
    {
        return speed;
    }
    public Rectangle getRectangle()
    {
        java.awt.Rectangle r1=new java.awt.Rectangle((int)(x),(int)(y),WIDTH,HEIGHT);
        return r1;
    }
    public void setX(double x)
    {
        this.x=x;
    }
    public void setY(double y)
    {
        this.y=y;
    }
    public void setAngle(double angle)
    {
        this.angle=angle;
    }
    public void setSpeed(double speed)
    {
        if(speed<=MAX_SPEED)
            this.speed=speed;
    }
}
