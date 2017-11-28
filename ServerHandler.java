import java.io.*;
import java.net.*;
import java.util.*;


class ServerHandler implements Runnable 
{
	private Socket socket1;					//connects to player 1

	private Socket socket2;					//connects to player 2

    	public ServerHandler(Socket socket1, Socket socket2) 	//constructor
	{
      		this.socket1 = socket1;
      		this.socket2 = socket2;
    	}

    	public void run()
	{
		try
		{
        		DataOutputStream output1 = new DataOutputStream(socket1.getOutputStream());	//creates output and input streams for players

        		DataOutputStream output2 = new DataOutputStream(socket2.getOutputStream());
			
        		DataInputStream input1 = new DataInputStream(socket1.getInputStream());

        		DataInputStream input2 = new DataInputStream(socket2.getInputStream());

			output1.writeChar('r');		//make player 1 red

			output2.writeChar('b');		//make player 2 blue

		
        		while (true) 			//keep swapping between players
			{
				//try
				//{
					output1.writeBoolean(true);		//player 1 has first turn
					System.out.println("sent turn to c1");


					input1.readBoolean();			//waits for player 1 to end turn
				

					output2.writeBoolean(true);		//sends player 2 the turn
					System.out.println("sent turn to c2");

	
					input2.readBoolean();			//waits for player 2 to end turn
			

					output1.flush();
					output2.flush();

				//}
				//catch(ClassNotFoundException e)
				//{
				//	System.err.println(e);
				//}
        		}
		}
		catch(IOException e)
		{
			System.err.println(e);	
		}
    	}
}