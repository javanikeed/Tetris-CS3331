package model;
import model.NetworkAdapter.MessageType;

/** Called when a message is received. */
public interface NetworkMessageListener {

    /** 
     * To be called when a message is received. 
     * The type of the received message along with optional content
     * (x, y, z and others) are provided as arguments.
     * 
     * @param type Type of the message received
     * @param x First argument
     * @param y Second argument
     * @param z Third argument
     * @param others Additional arguments
     */
    void messageReceived(MessageType type, String s, int x, int y, int z, int[] others);
}