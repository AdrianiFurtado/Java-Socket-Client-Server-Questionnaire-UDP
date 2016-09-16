import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client{

	DatagramSocket socket;
	static String theMessage = "hi server";
	public static String please = "A";
	public String Screen = "This is wrong";
	String out;

	public static void main(String[] args){
		
		ClientGUI frame = new ClientGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		System.out.println();
		System.out.println("UDP Client");
		System.out.println("=============");
	}
	Client() throws IOException {

        // Attach to any local socket
	socket = new DatagramSocket();
	}
	
	void sendString(InetAddress host, short port, String message) 
	throws IOException{

	// A packet is the 'envelope' to be addressed
	DatagramPacket packet;
	
	// This buffer will contain the message
	byte [] buf;

	// set buffer
	
	ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
	PrintWriter pw = new PrintWriter(os,true);// set autoflush
	
	pw.println(message);
	
	// Create the buffer containing the message
	buf = os.toByteArray();
	
	// Create a packet and put the message buffer in it and
	// address it too.
	packet = new DatagramPacket(buf,buf.length,host,port);
	
	socket.send(packet);
    }

    String receiveString() throws IOException
    {
	// This buffer will contain the message
	byte [] buf = new byte[1024];

	// Create an unaddressed packet to receive the message
	DatagramPacket packet = new DatagramPacket(buf, buf.length);

	socket.receive(packet);

        // Convert the message in the buffer to a String
        return new String(buf);
    }

    void close() {
	socket.close();
    }
    
    static void showArgs(){
	System.out.println("Format: UDPClient <Host> <PortNo>");
    }
}
