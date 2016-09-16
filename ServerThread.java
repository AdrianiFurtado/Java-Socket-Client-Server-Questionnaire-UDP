import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {

	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
	protected boolean moreQuotes = true;
	StringBuilder lool = new StringBuilder();
	PrintWriter writer;
	String Q1, Q2, Q3;

    public ServerThread() throws IOException {
	this("QuoteServerThread");
    }

    public ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(8888);

        try {
            in = new BufferedReader(new FileReader("Questions.txt"));
	int i = 0;
		while(i < 15){
			lool.append(in.readLine());
			lool.append("\n");
			

			if(i == 4){
				
				Q1 = lool.toString();
				System.out.println(Q1);
				lool.setLength(0);
				
			}
			if(i == 8){
				
				Q2 = lool.toString();
				System.out.println(Q2);
				lool.setLength(0);

			}
			if(i == 13){
				in.close();
				Q3 = lool.toString();
				System.out.println(Q3);

				break;
			}
			i++;
		}
		
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
    }

    public void run() {

	String welcome = "Welcome User";
	int c =1;
	while (c <= 4) {
            try {
                byte[] buf = new byte[1024];

                    // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
				
		writer = new PrintWriter(new BufferedWriter(new FileWriter("Answers.txt", true)));
		if(c ==1){
		String Aout = "hello";
		//buf = Aout.getBytes();
		writer.println(Aout);
		writer.close();
		System.out.println(Aout);
		}else if(c ==2){
		//String Aout = "Shmnie";
		//writer.println(Aout);
		writer.close();
		}else if(c ==3){
		//String Aout = "niou";
		//writer.println(Aout);
		writer.close();
		}else if(c ==4){
		//String Aout = "nigga";
		//writer.println(Aout);
		writer.close();
		}
                    // figure out response
                String dString = null;
		if(c == 1){              
                buf = Q1.getBytes();

		}
		else if(c==2){
		buf = Q2.getBytes();
		}
		else if(c ==3){
		buf = Q3.getBytes();
		}
		else if(c == 4){
		String Bye = "Goodbye";
		buf = Bye.getBytes();
		}
		// send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
		
		
            } catch (IOException e) {
                e.printStackTrace();
		moreQuotes = false;
            }
		c++;
        }
        socket.close();
    }

}

