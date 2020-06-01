import javafx.scene.shape.Rectangle;

public class Paddle
{
    public static final int WIDTH=15;
    public static final int HEIGHT=90;
    public boolean upPressed;
    public boolean downPressed;
    private double x;
    private double y;
    private double speed;
    public Paddle(double x, double y)
    {
        this.x=x;
        this.y=y;
        upPressed=false;
        downPressed=false;
        speed=10;
    }
    public void update ()
    {
        //boolean hit =rectangle1.intersects(rectangle2);
        if(upPressed==true&&y>=21)
            y-=speed;
        if(downPressed==true&&y<=489)
            y+=speed;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public java.awt.Rectangle getRectangle()
    {
        java.awt.Rectangle r1=new java.awt.Rectangle((int)(x),(int)(y),WIDTH,HEIGHT);
        return r1;
    }
    public boolean getDownPressed()
    {
        return downPressed;
    }
    public boolean getUpPressed()
    {
        return upPressed;
    }
    public void setX(double x)
    {
        this.x=x;
    }
    public void setY(double y)
    {
        this.y=y;
    }
    public void setDownPressed(boolean downPressed)
    {
        this.downPressed=downPressed;
    }
    public void setUpPressed(boolean upPressed)
    {
        this.upPressed=upPressed;
    }
}
