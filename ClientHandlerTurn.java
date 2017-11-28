import java.io.*;
import java.net.*;
import java.util.*;



class ClientHandlerTurn implements Runnable 
{
    	public ClientHandlerTurn() 
	{

    	}

    	public void run()
	{
		//while(!Client.myTurn)
		//{
			try
			{
				Client.myTurn = Client.input.readBoolean();

				System.out.println("got turn");	
			}
			catch(IOException e)
			{
				System.err.print(e);
			}
		//}
    	}
}