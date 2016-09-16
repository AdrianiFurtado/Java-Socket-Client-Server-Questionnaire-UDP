import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public  class ClientGUI extends JFrame{

	public ClientGUI(){

	setTitle("Questionnaire");
        setSize(WIDTH, HEIGHT);
         
        Container contentPane = getContentPane();
        clientPanel panel = new clientPanel();
        contentPane.add(panel);
	
	}
	public static final int WIDTH = 450;
	public static final int HEIGHT = 300;
}


class clientPanel extends JPanel{

	
	private JTextArea display;
	private JTextField input;
	private JLabel label;
	String fack;
	public static String screen ;
	public static Boolean send = true;
	public Boolean Q1b = false;
	public String Answer = "A";
	
	
	public clientPanel(){
		
		InetAddress host;
		short port = 8888;
		Color color = Color.BLACK;
		Font font = new Font("Verdana", Font.BOLD, 12);
		int i = 1;

		display = new JTextArea(10,35);
		JScrollPane scrollPane = new JScrollPane(display);
		add(scrollPane);
		display.setFont(font);
		display.setForeground(Color.WHITE);
		display.setEditable(false);
		display.setBackground(color);
		add(display ,BorderLayout.NORTH);
		label = new JLabel("Please Choose A B or C :");
		add(label, BorderLayout.SOUTH);
		input = new JTextField(5);
		input.setFont(font);
		add(input, BorderLayout.SOUTH);
		Handler handler = new Handler();
		input.addActionListener(handler);

		int y = 0;
		while(y == 0){
			try{
		
				Client client = new Client();
				host = InetAddress.getByName("localhost");
				client.sendString(host, port, Answer);
				//String Q0 = client.receiveString();
				//while(x==0){					
				
					//if(Q1b == true){
					client.sendString(host, port, Answer);
					String Q1 = client.receiveString();
					//display.setText(Q1);
					
					//}
				//}
				System.out.println(Answer);
				//send = false;
				//if(send == true){
				client.sendString(host, port, Answer);
				String Q2 = client.receiveString();
				//display.setText(Q2);
System.out.println("hello");
				//}

				client.sendString(host, port, Answer);
				String Q3 = client.receiveString();
				display.setText(Q1+Q2+Q3);

				client.sendString(host, port, Answer);
				//String Q4 = client.receiveString();
				//display.setText(Q3);
				
				
				
				//client.close();
			}catch(IOException e){
				System.out.println("Error");
			}
		y++;
		}
	}
	public class Handler implements ActionListener{
	
		public void actionPerformed(ActionEvent event){
		
			
			String string ="Please choose from a to c";
			if(event.getSource()==input){
				Answer = (event.getActionCommand());
				if((Answer.equals("a")||Answer.equals("A")) || (Answer.equals("b")||Answer.equals("B")) || (Answer.equals("c")||Answer.equals("C"))){
					JOptionPane.showMessageDialog(null, Answer);
					Q1b = true;
				
				}else{
					JOptionPane.showMessageDialog(null, string);
				}
			}
		}
	}

}
