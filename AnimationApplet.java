package view.animation;

import javax.swing.*;

public class AnimationApplet extends NoApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Timer timer = null;  
	protected int delay;
	
//	public AnimationApplet() { // add this not sure if fine :/
		
	//}
	public AnimationApplet(String[] args) {
		super(args);
	}
	public void init() {
		super.init();
		setTimer(new Timer(delay, e -> periodicTask()));
	}
	public void start() {
		getTimer().start();
	}

	public void stop() {
		getTimer().stop();
	}
	
	public void periodicTask()
	{
		repaint();
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}