import java.awt.*;



public class BoardPanel extends AnimationApplet{
    /**
	 * 
	 */
	GameState game = new GameState();
	Tetromino pieces = new Tetromino();
	Controller controller = new Controller();
	private static final long serialVersionUID = 8875916033497425768L;
    public BoardPanel(String[] args){
        super(args);
    }

    private Color bgColor = Color.blue;
    private Color gridColor = Color.white;
    
    public static int x;
	public static int y;

	


    @Override
    public void init() {
        super.init();
        start();
        dim = getSize();
        x = 25;
        y = 25;
        
        
    }

    //this is JUST A TEST to show that the game cycles through every tetromino every 500 milliseconds
    public void test01() {
    	Tetromino.TetrominoEnum val = Tetromino.TetrominoEnum.getRandomTetromino();
    	int[] currentPiece = pieces.get_tetromino(val);
    	controller.val = val;
    	controller.currentPiece = currentPiece;
    	Tetromino.TetrominoEnum val_next = Tetromino.TetrominoEnum.getRandomTetromino();
    	int[] nextPiece = pieces.get_tetromino(val_next);
    	controller.val_next = val_next;
    	controller.nextPiece = nextPiece;
    }
    
    
    public void periodicTask()
    {
    	
    	test01();
        repaint();
    }
    
    
    //sets background to blue and to the default size
    private void setBackground(Graphics g) {
    	g.setColor(bgColor);
    	g.fillRect(0, 0, dim.width, dim.height);
    }
    
    //draws the window where next tetromino will be displayed
    private void setNextTetrominoWindow(Graphics g) {
    	g.setColor(Color.white);
    	g.drawRect(350, 40, 175, 150);
    }
    
    //creates the 20x10 grid with dimensions 600x300.
    private void drawGrid(Graphics g, int x, int y){
        g.setColor(Color.blue);
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
    
    //sets it onto the background
    private void setTetrisGrid(Graphics g) {
    	g.setColor(gridColor);
        g.fillRect(x, y, 300, 600);
        drawGrid(g, x, y);
    }
    
    //sets lines, score and level, also "next tetromino" text
    private void setGameText(Graphics g) {
    	g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.PLAIN, 15));
        g.drawString("Next Tetromino: ", 350, 220);
        g.drawString("Score: ", 350, 300);
        g.drawString("Level: ", 350, 350);
        g.drawString("Lines Cleared: ", 350, 400);
    }
    
    //draws the tetromino (4 squares) onto the grid
    private void drawTetromino(Graphics g) {
        for(int i=0; i<7; i+=2) {
        		g.setColor(TetrisUI.getTetrominoColor(controller.val));
        		g.fillRect(controller.currentPiece[i], controller.currentPiece[i+1], 30, 30);
        		
        	}
    }
    
    //draws next tetromino in the box
    private void drawNextTetromino(Graphics g) {
        for(int i=0; i<7; i+=2) {
        		g.setColor(TetrisUI.getTetrominoColor(controller.val_next));
        		g.fillRect(controller.nextPiece[i]+350, controller.nextPiece[i+1]+38, 30, 30);
        		
        	}
    }
    
    
    @Override
    public void paintFrame(Graphics g){
        if (g==null)
            return;
        // fill the background
        setBackground(g);
        
        //next tetromino window
        setNextTetrominoWindow(g);
        
        // fill the grid
        setTetrisGrid(g);
        
        //write text to UI
        setGameText(g);
        

        
        //draw current tetromino to board
        drawTetromino(g);
        drawNextTetromino(g);

        

    
    }
    
    
    
    
    
    public static void main(String[] args) {
        new BoardPanel(args).run();
    }
}
