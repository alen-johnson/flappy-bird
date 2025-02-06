package utils;
import java.awt.Image;
import javax.swing.*;

public class ImageLoader {
    public static final Image BG_IMAGE = new ImageIcon(ImageLoader.class.getResource("../assets/flappybirdbg.png")).getImage();
    public static final Image BIRD_IMAGE = new ImageIcon(ImageLoader.class.getResource("../assets/flappybird.png")).getImage();
    public static final Image TOP_PIPE_IMAGE = new ImageIcon(ImageLoader.class.getResource("../assets/toppipe.png")).getImage();
    public static final Image BOTTOM_PIPE_IMAGE = new ImageIcon(ImageLoader.class.getResource("../assets/bottompipe.png")).getImage();

}
