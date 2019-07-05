package view.animation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class DoubleBufferPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private Graphics offScreen;
	protected Dimension dim;

    protected static final int DEFAULT_WIDTH = 550;
    protected static final int DEFAULT_HEIGHT = 650;
	
	public DoubleBufferPanel() 
	{
		dim = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}

	public DoubleBufferPanel(Dimension dim) 
	{
		this.dim = dim; 
	}

	public void init() {
		image = new BufferedImage(dim.width, dim.height,BufferedImage.TYPE_INT_RGB);
		offScreen = image.getGraphics();
	}

	public final void update(Graphics g) {
		paintFrame(offScreen);
		g.drawImage(image, 0, 0, this);
	}

	public final void paint(Graphics g) {
		update(g); 
	}

	protected abstract void paintFrame(Graphics g);
}
