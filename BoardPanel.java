import java.awt.*;

public class BoardPanel extends AnimationApplet{
    TetrominoJ tj = new TetrominoJ();
    public BoardPanel(String[] args){
        super(args);
    }

    private Color color = Color.BLUE;
    private int radius = 15;
    private int x, y;
    private int dx = 0, dy = -30;

    @Override
    public void init() {
        super.init();
        start();
        dim = getSize();
        x = 75;
        y = 25;
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
        g .setColor(Color.black);
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
        int x_coord = 75, y_coord = 25;
        g.setColor(color);
        g.fillRect(x_coord, y_coord, 300, 600);
        draw_grid(g, x_coord, y_coord);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Level:", 390, 100);

        g.setColor(Color.MAGENTA);
        g.fillRect(x_coord, y -dy, radius * 2, radius * 2);
        tj.draw_J(g);

    }
    private void draw_grid(Graphics g, int x, int y){
        g.setColor(Color.white);
        int reset_x = x;
        for(int i=0;i<11;i++){
            g.fillRect(x, y, 1, 600);
            x+=30;
        }
        x = reset_x;
        for(int j=0;j<21;j++){
            g.fillRect(x, y, 300, 1);
            y+=30;
        }
    }
    public static void main(String[] args) {
        new BoardPanel(args).run();
    }
}
