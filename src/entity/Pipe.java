package entity;

import java.awt.Image;

import utils.Constants;

public class Pipe {

    public int x, y, width, height;
    public Image img;
    public boolean passed;

    public Pipe(Image img) {
        this.x = Constants.PIPE_X;
        this.y = Constants.PIPE_Y;
        this.width = Constants.PIPE_WIDTH;
        this.height = Constants.PIPE_HEIGHT;
        this.passed = false;
        this.img = img;
    }
}
