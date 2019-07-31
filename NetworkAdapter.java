package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** 
 * An abstraction of a TCP/IP socket for sending and receiving 
 * Tetris game messages. This class allows two players to communicate 
 * with each other through a socket.
 * It is assumed that a socket connection is already established between 
 * the players.
 * 
 * <p>
 * This class supports a few different types of messages. 
 * Each message is one line of text, a sequence of characters
 * ended by the end-of-line character, and consists of a header and a body.
 * A message header identifies a message type and ends with a ":", e.g.,
 * "fill:". A message body contains the content of a message. If it 
 * contains more than one element, they are separated by a ",",
 * e.g., "1,2,3". There are seven different messages as defined below.
 * </p>
 * 
 * <ul>
 *     <li>new:s requests to start a new game, where s is the name of the player</li>
 *     <li>new_ack:s,x -- ack new game request, where  s is the other player's name and n (response) is
 *         either 0 (declined) or 1 (accepted).</li>
 *     <li>fill: x -- fill lines on the others player board, where  x is the number of lines to add</li>
 *     <li>status: x, y, other -- to send status message about your game to the other player (so you may see the board of the other player).
 *     		where x is your score, y is an indicator if the game is over yet, and other is an array containing the board informations   </li>
 *     <li>quit: -- leaves a game by ending the connection.</li>
 * </ul>
 *
 *<p>
 * Two players communicate with each other as follows. 
 * One of the players (client) connects to the other (server) 
 * and requests to create a new game; the player who 
 * initiates the connection must send a the new game message, 
 * as the other player will be waiting for it.
 * If the server accepts the request, the server and client send each other its board configuration, 
 * score, level number, and lines cleared triggered on changes.
 * Now, both players know about the status of the other player. When a player clears multiple lines at once,
 * a fill message is sent to the other player, and new lines are added to the other player as punishment using
 * fill and fill_ack messages. A player may quit a shared game or make a request
 * to play a new shared game by sending a new message.
 * </p>
 *

 * 1. Starting a new game (accepted)
 * <pre>
 *  Client        Server
 *    |------------&gt;| "new:player name 1" -- request a new game
 *    |&lt;------------| "new_ack:player name 2,1" -- accept the request
 *    ...
 * </pre>
 * 
 * 2. Starting a new game (declined)
 * <pre>
 *  Client        Server
 *    |------------&gt;| "new:player name 1" -- request for a new game
 *    |&lt;------------| "new_ack:player name 2,0" -- decline the request (disconnected!)
 * </pre>
 *
 *
 * 3.- Fill (send new lines to other user)
 * <pre>
 *  Client        Server
 *    |&lt;------------| fill:3 -- server fill
 * </pre>
 * 
 * 4.- Status (sends score, indicator if game is over, and the squares on the board
 * <pre>
 *  Client        Server
 *    |&lt;------------| status:500,0,1,2,3,4,5,6... -- server status
 * </pre>
 *    
 * 5. Quitting a game
 * <pre>
 *  Client        Server
 *    |------------&gt;| quit: -- quit the game (disconnected!)
 * </pre>
 * 
 * <p>
 * To receive messages from the peer, register a {@link MessageListener}
 * and then call the {@link #receiveMessagesAsync()} method as shown below.
 * This method creates a new thread to receive messages asynchronously.
 * </p>
 * 
 * <pre>
 *  Socket socket = ...;
 *  NetworkAdapter network = new NetworkAdapter(socket);
 *  network.setMessageListener(new NetworkAdapter.NetworkMessageListener() {
 *      public void messageReceived(NetworkAdapter.MessageType type, String s int x, int y, int z, int[] others) {
 *        switch (type) {
 *          case NEW: ...      // s (player name)
 *          case NEW_ACK: ...  // s (other player name) x (response)
 *          case FILL: ...     // x (number of new lines)
 *			case STATUS: ...   // x (score), y (indicator if game is over yet), other (array containing board information)
 *          case QUIT: ...
 *          ...
 *        }
 *      }
 *    });
 *
 *  // receive messages asynchronously
 *  network.receiveMessagesAsync();
 * </pre>

 * <p>
 * To send messages to the peer, call the <code>writeXXX</code> methods. 
 * These methods run asynchronously, and messages are sent
 * in the order they are received by the <code>writeXXX</code> methods.
 * </p>
 * 
 * <pre>
 *  network.writeNew("Player 1");
 *  network.writeFill(3);
 *  ...
 *  network.close();
 * </pre>
 *
 * @author cheon & epadilla2
 * @see MessageType
 * @see MessageListener
 */

public class NetworkAdapter implements NetworkMessageListener{

	/** Different type of game messages. */
	public enum MessageType { 

		/** Quit the game. This message has the form "quit:". */
		QUIT ("quit:"), 


		/** 
		 * Request to play a new game. This message has the form "new: size,board",
		 * size is the board size and board is a sequence of non-empty squares 
		 * of the board, each encoded as: x,y,v,f (where x, y: 0-based column/row indexes, 
		 * v: number, f: 1 if the value is given/fixed or 0 if entered by the user.
		 */
		NEW ("new:"), 

		/** 
		 * Acknowledgement of a new game request. This message has the form "new_ack: n",
		 * where n (response) is either 0 (declined) or 1 (accepted).
		 */
		NEW_ACK ("new_ack:"),

		/**
		 * Sends score, whether game is over, and the encoded board as an array.
		 */
		STATUS("status:"),

		/** 
		 * Request to fill a number in the board. This message has the form "fill: x,y,v",
		 * where x and y are 0-based column/row indexes of a square and v is a number to fill
		 * in the square.
		 */
		FILL ("fill:"), 

		/** Unknown message received. */
		UNKNOWN (null);

		/** Message header. */
		private final String header;

		MessageType(String header) {
			this.header = header;
		}

	};
	private static final int[] EMPTY_INT_ARRAY = new int[0];

	/** To be notified when a message is received. */
	private NetworkMessageListener listener;

	/** Asynchronous message writer. */
	private MessageWriter messageWriter;

	/** Reader connected to the peer to read messages from it. */
	private BufferedReader in;

	/** Writer connected to the peer to write messages to it. */
	private PrintWriter out;

	/** If not null, log all messages sent and received. */
	private PrintStream logger;

	/** Associated socket to communicate with the peer. */
	private Socket socket;

	/** Holds reference to server socket (this is not use to write)**/
	private ServerSocket serverSocket;
	/** 
	 * Create a new network adapter to read messages from and to write
	 * messages to the given socket.
	 * 
	 * @param socket Socket to read and write messages.
	 */
	public NetworkAdapter(Socket socket) {
		this(socket, null);
	}
	/** 
	 * Create a new network adapter. Messages are to be read from and 
	 * written to the given socket. All incoming and outgoing 
	 * messages will be logged on the given logger.
	 * 
	 * @param socket Socket to read and write messages.
	 * @param logger Log all incoming and outgoing messages.
	 */
	public NetworkAdapter(Socket socket, PrintStream logger) {
		this.socket = socket;
		this.logger = logger;
		messageWriter = new MessageWriter();
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	/** Return the associated socket.
	 * @return Socket associated with this adapter.
	 */
	public Socket socket() {
		return socket;
	}
	/**
	 *  Returns server socket.
	 * @return
	 */
	public ServerSocket serverSocket()
	{
		return serverSocket;
	}
	/**
	 * Holds server socket of server
	 * @param serverSocket
	 */
	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}
	/** Close the IO streams of this adapter. */
	public void close() {
		try 
		{
			// close "out" first to break the circular dependency
			// between peers.
			out.close();  
			in.close();
			messageWriter.stop();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		try 
		{
			if (socket != null && !socket.isClosed())
				socket.close();
			socket = null;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if (serverSocket != null && !serverSocket.isClosed())
		{
			try
			{  serverSocket.close();}
			catch (Exception se){	}
			serverSocket = null;
		}
	}
	/**
	 * Register the given messageListener to be notified when a message
	 * is received.
	 * 
	 * @param listener To be notified when a message is received.
	 *
	 * @see MessageListener
	 * @see #receiveMessages()
	 * @see #receiveMessagesAsync()
	 */
	public void setMessageListener(NetworkMessageListener listener) {
		this.listener = listener;
	}
	/**
	 * Start accepting messages from this network adapter and
	 * notifying them to the registered listener. This method blocks
	 * the caller. To receive messages synchronously, use the
	 * {@link #receiveMessagesAsync()} method that creates a new
	 * background thread.
	 *
	 * @see #setMessageListener(MessageListener)
	 * @see #receiveMessagesAsync()
	 */
	public void receiveMessages() {
		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				if (logger != null) {
					logger.format(" < %s\n", line);
				}
				//System.out.println("Received: " + line);
				parseMessage(line);
			}
		} catch (IOException e) {
		}
	}
	/**
	 * Start accepting messages asynchronously from this network
	 * adapter and notifying them to the registered listener.
	 * This method doesn't block the caller. Instead, a new
	 * background thread is created to read incoming messages.
	 * To receive messages synchronously, use the
	 * {@link #receiveMessages()} method.
	 *
	 * @see #setMessageListener(MessageListener)
	 * @see #receiveMessages()
	 */
	public void receiveMessagesAsync() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				receiveMessages();
			}
		}).start();
	}
	/** Parse the given message and notify to the registered listener. */
	private void parseMessage(String msg) {
		if (msg.startsWith(MessageType.QUIT.header)) {
			notifyMessage(MessageType.QUIT);
		} else if (msg.startsWith(MessageType.NEW_ACK.header)) {
			parseNewAckMessage(msgBody(msg));
		} else if (msg.startsWith(MessageType.NEW.header)) {
			parseNewMessage(msgBody(msg));
		} else if (msg.startsWith(MessageType.STATUS.header)) {
			parseStatusMessage(msgBody(msg));
		} else if (msg.startsWith(MessageType.FILL.header)){
			parseFillMessage(msgBody(msg));
		} else {
			notifyMessage(MessageType.UNKNOWN);
		}
	}
	/** Parse and return the body of the given message. */
	private String msgBody(String msg) {
		int i = msg.indexOf(':');
		if (i > -1) {
			msg = msg.substring(i + 1);
		}
		return msg;
	}
	/** Parse and notify the given play_ack message body. */
	private void parseNewMessage(String msgBody) {
		String[] parts = msgBody.split(",");
		if (parts.length >= 1) {
			// message: new size squares
			String playerName = parts[0].trim();
			notifyMessage(MessageType.NEW, playerName);
			return;

		}
		notifyMessage(MessageType.UNKNOWN);
	}  
	/** Parse and notify the given new_ack message body. */
	private void parseNewAckMessage(String msgBody) {
		String[] parts = msgBody.split(",");
		if (parts.length >= 2) {
			// message: new_ack response
			String playerName = parts[0].trim();
			int response = parseInt(parts[1].trim());
			notifyMessage(MessageType.NEW_ACK, playerName, response);
			return;
		}
		notifyMessage(MessageType.UNKNOWN);
	} 
	/** Parse and notify the given status message body. */
	private void parseStatusMessage(String msgBody) {
		String[] parts = msgBody.split(",");
		if (parts.length >= 2) {
			// message: new size squares
			int score = parseInt(parts[0].trim());
			int isGameActive = parseInt(parts[1].trim());
			int[] others = new int[parts.length - 2];
			for (int i = 2; i < parts.length; i++) {
				others[i-2] = parseInt(parts[i]);
			}
			notifyMessage(MessageType.STATUS, score, isGameActive, others);
			return;

		}
		notifyMessage(MessageType.UNKNOWN);
	}
	/** 
	 * Parse the given string as an int; return -1 if the input
	 * is not well-formed. 
	 */
	private int parseInt(String txt) {
		try {
			return Integer.parseInt(txt);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	/** Parse and notify the given a fill message. */
	private void parseFillMessage(String msgBody) {
		notifyMessage(MessageType.FILL, Integer.parseInt(msgBody));
		return;

	}
	/** Write the given message asynchronously. */
	private void writeMsg(String msg) {
		//System.out.println("Writing: " + msg);
		messageWriter.write(msg);
	}
	/**
	 * Write a new game message asynchronously.
	 * @param Player's name
	 *
	 * @see #writeNewAck(boolean)
	 */
	public void writeNew(String playerName) {
		StringBuilder builder = new StringBuilder(MessageType.NEW.header);
		builder.append(playerName);
		writeMsg(builder.toString());    	
	}
	/**
	 * Write an new_ack message asynchronously. 
	 * @param playerName
	 * @param response
	 */
	public void writeNewAck(String playerName, int response) {
		writeMsg(MessageType.NEW_ACK.header + playerName + "," + response);
	}
	/**
	 *  Write an status message asynchronously. 
	 * @param score
	 * @param isGameActive
	 * @param boardPositions
	 */
	public void writeStatus(int score, int isGameActive, int[] boardPositions) {
		StringBuilder builder = new StringBuilder(MessageType.STATUS.header);
		builder.append(score);
		builder.append(",");
		builder.append(isGameActive);

		for (int b: boardPositions) {
			builder.append(",");
			builder.append(b);
		}
		writeMsg(builder.toString());    	
	}
	/**
	 * Write a fill message asynchronously. 
	 * 
	 * @param number Filled-in number
	 *
	 * @see #writeFillAck(int, int, int)
	 */
	public void writeFill(int number) {
		writeMsg(MessageType.FILL.header + number);
	}
	/** Write a quit message (to quit the game) asynchronously. */
	public void writeQuit() {
		writeMsg(MessageType.QUIT.header);
	}
	/** Notify the listener the receipt of the given message type. 
	 * @param playerName 
	 * @param b */
	private void notifyMessage(MessageType type, String s, int x) {
		listener.messageReceived(type, s, x, 0, 0, EMPTY_INT_ARRAY);
	}
	/** Notify the listener the receipt of the given message type. */
	private void notifyMessage(MessageType type) {
		listener.messageReceived(type, null,0, 0, 0, EMPTY_INT_ARRAY);
	}
	/** Notify the listener the receipt of the given message type. */
	private void notifyMessage(MessageType type, int x, int y, int[] others) {
		listener.messageReceived(type, null, x, y, 0, others);
	}
	/** Notify the listener the receipt of the given message type. */
	private void notifyMessage(MessageType type, String s) {
		listener.messageReceived(type, s, 0, 0, 0, EMPTY_INT_ARRAY);
	}
	/** Notify the listener the receipt of the given message type. */
	private void notifyMessage(MessageType type, int x) {
		listener.messageReceived(type, null, x, 0, 0, EMPTY_INT_ARRAY);
	}
	/** 
	 * Write messages asynchronously. This class uses a single 
	 * background thread to write messages asynchronously in a FIFO
	 * fashion. To stop the background thread, call the stop() method.
	 */
	private class MessageWriter {

		/** Background thread to write messages asynchronously. */
		private Thread writerThread;

		/** Store messages to be written asynchronously. */
		private BlockingQueue<String> messages = new LinkedBlockingQueue<>();

		/** Write the given message asynchronously on a new thread. */
		public void write(final String msg) {
			if (writerThread == null) {
				writerThread = new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							try {
								String m = messages.take();
								out.println(m);
								out.flush();
							} catch (InterruptedException e) {
								return;
							}
						}
					}
				});
				writerThread.start();
			}

			synchronized (messages) {
				try {
					messages.put(msg);
					if (logger != null) {
						logger.format(" > %s\n", msg);
					}
				} catch (InterruptedException e) {
				}
			}
		}

		/** Stop this message writer. */
		public void stop() {
			if (writerThread != null) {
				writerThread.interrupt();
			}
		}
	}
	@Override
	public void messageReceived(MessageType type, String s, int x, int y, int z, int[] others) {
		switch (type) {
		case NEW:     // s (player name)
			
		case NEW_ACK:   // s (other player name) x (response)
			
		case FILL:   // x (number of new lines)
			
		case STATUS:  // x (score), y (indicator if game is over yet), other (array containing board information)
			
		case QUIT:
			
		default:
			break; 
		 
	      }
		
	}
}
