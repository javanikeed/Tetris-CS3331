import java.awt.Color;
import java.awt.Graphics;


/**
 * Sample NoApplet showing a ball moving inside a rectangular box.
 * See Section 5.5.3 on pages 195-197.
 */
@SuppressWarnings("serial")
public class BouncingBall extends AnimationApplet {

    public BouncingBall(String[] args) {
        super(args);
    }


    private Color color = Color.GREEN;
    private int radius = 30;
    private int x, y;
    private int dx = 0, dy = -30;

    @Override
    public void init() {
        super.init();
        start();
        dim = getSize();
        x = dim.width * 2 / 10;
        y = dim.height - radius;
    }
    public void periodicTask()
    {
        repaint();
    }

    @Override
    public void paintFrame(Graphics g)
    {
        if (g==null)
            return;
        // fill the background
        g .setColor(Color.white);
        g .fillRect(0, 0, dim.width, dim.height);

        // adjust the position of the ball
        if (x < radius || x > dim.width - radius) {
            dx = -dx;
        }
        if (y < radius || y > dim.height - radius) {
            dy = -dy;
        }
        x += dx;
        y += dy;

        // draw the ball and dump the off-screen image
        g.setColor(color);
        g.fillRect(x - radius, y - radius, radius * 2, radius * 2);
    }

    public static void main(String[] args) {
        new BouncingBall(args).run();
    }
}