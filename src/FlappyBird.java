import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;

import utils.Constants;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = Constants.BOARD_WIDTH;
    int boardHeight = Constants.BOARD_HEIGHT;

    Image bgImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // bird
    int birdX = Constants.BIRD_X;
    int birdY = Constants.BIRD_Y;
    int birdWidth = Constants.BIRD_WIDTH;
    int birdHeight = Constants.BIRD_HEIGHT;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    // pipes
    int pipeX = Constants.PIPE_X;
    int pipeY = Constants.PIPE_Y;
    int pipeWidth = Constants.PIPE_WIDTH;
    int pipeHeight = Constants.PIPE_HEIGHT;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img) {
            this.img = img;
        }

    }

    // logic
    Bird bird;
    int velocityX = Constants.VELOCITY_X;
    int velocityY = Constants.VELOCITY_Y;
    int gravity = Constants.GRAVITY;

    ArrayList<Pipe> pipes;
    Random random = new Random();


    Timer gameloop;
    Timer placePipesTimer;

    boolean gameOver = false;
    double score = 0;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        setFocusable(true);
        addKeyListener(this);

        bgImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        gameloop = new Timer(1000 / 60, this);
        gameloop.start();
    }

    public void placePipes() {

        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        // bg
        g.drawImage(bgImg, 0, 0, boardWidth, boardHeight, null);

        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        // pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        }

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        if(gameOver){
            g.drawString("GAME OVER :" + String.valueOf((int) score), 10,35);
        }
        else{
            g.drawString(String.valueOf((int) score), 10, 35);
        }

    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for( int i = 0; i< pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipeWidth){
                pipe.passed = true;
                score += 0.5; // two pipes passed at one time so total will be 1
            }

            if (collission(bird, pipe)){
                gameOver = true;
            }
        }

        if(bird.y > boardHeight ){
            gameOver = true;
        }
    }

    public boolean collission(Bird b, Pipe p){
        return b.x < p.x + p.width &&
               b.x + b.width > p.x &&
               b.y < p.y + p.height &&
               b.y + b.height >p.y ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver){
            placePipesTimer.stop();
            gameloop.stop();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
            //restart game
            if (gameOver){

                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score =0;
                gameOver = false;
                gameloop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
