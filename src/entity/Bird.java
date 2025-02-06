package entity;

import java.awt.*;

import utils.Constants;
import utils.ImageLoader;

public class Bird {

    public int x, y, width, height;
    public Image img;

    public Bird() {
        this.x = Constants.BIRD_X;
        this.y = Constants.BIRD_Y;
        this.width = Constants.BIRD_WIDTH;
        this.height = Constants.BIRD_HEIGHT;
        this.img = ImageLoader.BIRD_IMAGE;
    }
}
