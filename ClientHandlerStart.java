import java.io.*;
import java.net.*;
import java.util.*;



class ClientHandlerStart implements Runnable 
{
    	public ClientHandlerStart() 
	{

    	}

    	public void run()
	{
		char color = 'a';

		try
		{
		
			color = Client.input.readChar();

		}
		catch(IOException ex)
		{
			System.err.print(ex);
		}

		if(color == 'r')
		{
			Client.myColor = "red";

		}
		else if(color == 'b')
		{
			Client.myColor = "blue";

		}
		else if(color == 'g')
		{
			Client.myColor = "green";

		}
		else if(color == 'y')
		{

			Client.myColor = "yellow";
		}

			try
			{
				Client.myTurn = Client.input.readBoolean();	
			}
			catch(IOException e)
			{
				System.err.print(e);
			}
    	}
}