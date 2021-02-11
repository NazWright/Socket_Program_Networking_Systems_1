package homework1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Naz Wright
 */

public class Server {
	public static void main(String args[]) throws IOException { 
		DataOutputStream  dos = null;
		DataInputStream dis = null;
		boolean isGuessed = false;

		try{
			ServerSocket server = new ServerSocket(5000);
			System.out.println("......Server Started....");
			System.out.println("Waiting for a client\n\n");

			String line = "";
			
				Socket socket = null;
				try{
					// socket object to receive incoming client requests 
                    socket = server.accept();
						// obtaining input and out streams 
		            dis = new DataInputStream(socket.getInputStream());
		            dos = new DataOutputStream(socket.getOutputStream());

		           //handle client here....
		            int maxVal = dis.readInt();
		            int guesses = dis.readInt();
		            System.out.println("Received max value of " + maxVal + " and " + guesses + " guesses from client\n\n");

		            do{
		            	if(isGuessed){
		            		guesses = 0;
		            		break;
		            	}
		            	int inclusive = maxVal + 1; // add one to the max value to include it in the random gen
		            	int guess = (int)(Math.random() * ( maxVal - 1 + 1 ) + 1) ;
		            	dos.writeInt(guess);
		            	System.out.println("My Guess is " + guess + "\n\n");
		            	int answer = dis.readInt();
		            	if( answer == 0){
		            		isGuessed = true;
		            	}
		            	System.out.println("Client says: " + answer + "\n\n");
		            	guesses--;
		            }
		            while( guesses > 0 );
		            dis.close();
		            dos.close();
		            socket.close();
		            
		          
				}
				catch (Exception e) {
                    socket.close();
                    //e.printStackTrace();
                }
            

		}
		catch (IOException i) {
            System.out.println(i);
        }
	}
}