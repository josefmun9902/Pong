import javafx.scene.shape.Rectangle;
//
//
//
//how do you double buffer
//Why does the comment below paddles int he paint method not work
//
//
//
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.applet.*;
import java.net.URL;
import java.io.*;

public class PongPanel extends JPanel implements MouseListener, KeyListener, Runnable {
    public static final int UPS = 60;
    public static final int FPS = 35;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    BufferedImage buffer = new BufferedImage(900, 600, BufferedImage.TYPE_4BYTE_ABGR);
    Graphics bg = buffer.getGraphics();
    private PongGame game;
    public PongPanel(int width, int height) {
        super();
        Thread t = new Thread(this);
        setSize(900, 600);
        //add the mouse listener
        addMouseListener(this);
        addKeyListener(this);
        game = new PongGame();
        t.start();
    }

    public void paint(Graphics g) {
        //black background
        bg.setColor(Color.BLACK);
        //black background
        bg.fillRect(0, 0, getWidth(), getWidth());
        //white borders and dashed separation line
        bg.setColor(Color.WHITE);
        //top wall
        bg.fillRect(0, 0, WIDTH, 20);
        //bottom wall
        bg.fillRect(0, 580, WIDTH, 20);
        //dashes
        bg.fillRect(445, 40, 10, 60);
        bg.fillRect(445, 120, 10, 60);
        bg.fillRect(445, 200, 10, 60);
        bg.fillRect(445, 280, 10, 60);
        bg.fillRect(445, 360, 10, 60);
        bg.fillRect(445, 440, 10, 60);
        bg.fillRect(445, 520, 10, 40);
        //paddles
        //java.awt.Rectangle p1=game.getPaddle1().getRectangle();
        //g.fillRect(p1);
        bg.fillRect((int) (game.getPaddle1().getX()), (int) (game.getPaddle1().getY()), 15, 90);
        bg.fillRect((int) (game.getPaddle2().getX()), (int) (game.getPaddle2().getY()), 15, 90);
        //scores
        bg.setFont(new Font("Courier", Font.BOLD, 36));
        bg.drawString("" + game.getPlayerOneScore(), 350, 50);
        bg.drawString("" + game.getPlayerTwoScore(), 525, 50);
        //ball
        bg.fillRect((int) (game.ball.getX()), (int) game.ball.getY(), game.ball.WIDTH, game.ball.HEIGHT);
        //win text
        if(game.status()==game.PLAYER_1_WINS)
            bg.drawString("Player one wins, \"n \" for new game.", 180, 200);
        else if(game.status()==game.PLAYER_2_WINS)
            bg.drawString("Player two wins, \"n \" for new game.", 180, 200);
        g.drawImage(buffer, 0, 0, null);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            game.getPaddle1().setUpPressed(true);
        } else if (e.getKeyChar() == 's') {
            game.getPaddle1().setDownPressed(true);
        } else if (e.getKeyChar() == 'i') {
            game.getPaddle2().setUpPressed(true);
        } else if (e.getKeyChar() == 'k') {
            game.getPaddle2().setDownPressed(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            game.getPaddle1().setUpPressed(false);
        } else if (e.getKeyChar() == 's') {
            game.getPaddle1().setDownPressed(false);
        } else if (e.getKeyChar() == 'i') {
            game.getPaddle2().setUpPressed(false);
        } else if (e.getKeyChar() == 'k') {
            game.getPaddle2().setDownPressed(false);
        }
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'n' && game.status() != game.PLAYING)
            game.reset();
        if (e.getKeyChar()=='p')
        {
            if(game.getPause()==false)
                game.setPause(true);
            else
                game.setPause(false);
        }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void run() {
        int sleepTime = 1000 / UPS;
        while (true) {
            update();
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(game.status()==game.PLAYING&&game.getPause()!=true) {
            game.update();
            game.getPaddle1().update();
            game.getPaddle2().update();
            repaint();
        }
        else
            repaint();
    }
}

