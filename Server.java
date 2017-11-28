import java.io.*;
import java.net.*;
import java.util.*;


public class Server
{
  	public static void main(String[] args) 
	{
    		new Server();							//starts server when started
  	}

	public Server() 
	{
		ServerHandler task;						//sets up task

		try 
		{
      			ServerSocket serverSocket = new ServerSocket(8000);	//used to connect to client

      			while (true) 
			{
        			Socket socket1 = serverSocket.accept();		//opens connection to player 1

        			Socket socket2 = serverSocket.accept();		//opens connection to player 2

        			task = new ServerHandler(socket1,socket2);	//handles 1 two player game
				
      				new Thread(task).start();			//creates thread for handling game
			}
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
}