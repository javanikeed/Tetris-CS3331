package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;






public class NetworkDialog extends JDialog{
  private static final long serialVersionUID = 1L;
  private JTextArea logTextArea;
  private final Dimension DIMENSION = new Dimension(575, 400);
  public JButton connectButton;
  public JButton hostButton;
  public JTextField hostnameTextField;
  public JTextField portTextField;
  private JPanel connectPanel;
  
  public NetworkDialog() {}
  
  public void run() {
    configureGui();
    setSize(DIMENSION);
  }
  
  public void setActionListener(ActionListener alController) {
    hostButton.addActionListener(alController);
    connectButton.addActionListener(alController);
  }
  
  public void appendToLogTextArea(String logEntry) {
    logTextArea.append(logEntry);
    logTextArea.repaint();
  }
  
  private void configureGui() {
    connectPanel = new JPanel(new FlowLayout(3));
    connectButton = new JButton("Connect:");
    connectButton.setFocusPainted(false);
    hostButton = new JButton("Host Game");
    hostButton.setFocusPainted(false);
    hostnameTextField = new JTextField("localhost", 15);
    portTextField = new JTextField("4545", 5);
    connectPanel.add(hostButton);
    connectPanel.add(connectButton);
    connectPanel.add(hostnameTextField);
    connectPanel.add(portTextField);
    
    logTextArea = new JTextArea(10, 30);
    logTextArea.setEditable(false);
    JScrollPane logScrollPane = new JScrollPane(logTextArea);
    
    setLayout(new BorderLayout());
    add(connectPanel, "North");
    add(logScrollPane, "Center");
  }
}