package homework1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Naz Wright
 */

// takes in max value (int), numGuesses int, serverIP String, port String from command line

// connects to server

// send the integers -> max value (random int) and number of guesses (random)

// wait for client to send back guess

// output guess and flag  too high too low 0 if correct

// if last response
	// send the max number and if isGuessed yes or no

public class Client {
	public Client(int maxVal, int numGuesses, String serverIp, int port){
		   try {
            //connect to the server
            // create stream and add to on specified host (string) and port (int)
            int counter = 5;
            Socket socket = new Socket(serverIp, port);

            DataInputStream fromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
            System.out.println("Sending " + maxVal + " to server.");
            
            //send equation to server
            toServer.writeInt(maxVal);

            System.out.println("Sending " + numGuesses + " to server.");

            toServer.writeInt(numGuesses);

          while(numGuesses > 0){

            

             numGuesses--;
            
            //get result from server
            //int result = fromServer.readInt();

            //System.out.print(" The server guess was " + result);

           /* if(result == maxVal){
            	toServer.writeInt(0);
            }
            else if( result < maxVal ){
            	toServer.writeInt(-1);
            }
            else{
            	toServer.writeInt(1);
            }
            
            numGuesses--
*/
          }



        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException ex) {
            System.out.println("IOException on Client: " + ex.getMessage());
        }

	}

	public static void main(String[] args) {
        new Client(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
    }
}

