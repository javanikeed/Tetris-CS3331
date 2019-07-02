import java.awt.*;

public class TetrominoJ {

    TetrominoJ(){
    }
    public void draw_J(Graphics g){
        g.setColor(Color.MAGENTA);
        g.fillRect(105, 25, 30, 30);
        g.fillRect(105, 55, 30, 30);
        g.fillRect(105, 85, 30, 30);
        g.fillRect(75, 85, 30, 30);
    }
}
