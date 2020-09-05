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
