import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.image.*;

public class territory
{
	private int terrNum;		//number associated with each territory

	private boolean selected;	//whether or not this specific territory is selected

	private int numDice;		//the number of dice that the specific territory instance has

	private String owner;		//owner of this territory instance

	private int adjacent[];		//array of adjacent territory numbers

	public territory(int num)	//constructor
	{
		this.terrNum = num;	//sets territory number

		selected = false;	//territory starts off not being selected

		switch(num)		//territory number determines which territories are adjacent
		{
			case 0: adjacent = new int[1];	//territory 0 = portugal, which borders only one nation
				adjacent[0] = 1;	//territory 1 = spain, the only nation that borders portugal
				break;

			case 1: adjacent = new int[2];	//territory 1 is spain, which borders two nations
				adjacent[0] = 0;	//spain borders 0 (portugal), and 2 (france)
				adjacent[1] = 2;
				break;

			case 2: adjacent = new int[6];	//territory 2 is france, which borders 6 nations
				adjacent[0] = 1;	//etc.....
				adjacent[1] = 3;
				adjacent[2] = 5;
				adjacent[3] = 9;
				adjacent[4] = 8;
				adjacent[5] = 10;
				break;

			case 3: adjacent = new int[2];
				adjacent[0] = 2;
				adjacent[1] = 4;
				break;

			case 4: adjacent = new int[1];
				adjacent[0] = 3;
				break;

			case 5: adjacent = new int[3];
				adjacent[0] = 2;
				adjacent[1] = 6;
				adjacent[2] = 9;
				break;

			case 6: adjacent = new int[2];
				adjacent[0] = 5;
				adjacent[1] = 9;
				break;

			case 7: adjacent = new int[1];
				adjacent[0] = 9;
				break;

			case 8: adjacent = new int[4];
				adjacent[0] = 2;
				adjacent[1] = 9;
				adjacent[2] = 10;
				adjacent[3] = 14;
				break;

			case 9: adjacent = new int[8];
				adjacent[0] = 7;
				adjacent[1] = 6;
				adjacent[2] = 5;
				adjacent[3] = 2;
				adjacent[4] = 8;
				adjacent[5] = 14;
				adjacent[6] = 13;
				adjacent[7] = 12;
				break;

			case 10:adjacent = new int[5];
				adjacent[0] = 2;
				adjacent[1] = 8;
				adjacent[2] = 14;
				adjacent[3] = 15;
				adjacent[4] = 11;
				break;

			case 11:adjacent = new int[1];
				adjacent[0] = 10;
				break;

			case 12:adjacent = new int[7];
				adjacent[0] = 9;
				adjacent[1] = 13;
				adjacent[2] = 16;
				adjacent[3] = 30;
				adjacent[4] = 31;
				adjacent[5] = 32;
				adjacent[6] = 33;
				break;

			case 13:adjacent = new int[4];
				adjacent[0] = 9;
				adjacent[1] = 12;
				adjacent[2] = 14;
				adjacent[3] = 16;
				break;

			case 14:adjacent = new int[7];
				adjacent[0] = 16;
				adjacent[1] = 13;
				adjacent[2] = 9;
				adjacent[3] = 8;
				adjacent[4] = 10;
				adjacent[5] = 15;
				adjacent[6] = 17;
				break;

			case 15:adjacent = new int[4];
				adjacent[0] = 10;
				adjacent[1] = 14;
				adjacent[2] = 17;
				adjacent[3] = 18;
				break;

			case 16:adjacent = new int[5];
				adjacent[0] = 12;
				adjacent[1] = 13;
				adjacent[2] = 14;
				adjacent[3] = 17;
				adjacent[4] = 30;
				break;


			case 17:adjacent = new int[7];
				adjacent[0] = 30;
				adjacent[1] = 16;
				adjacent[2] = 14;
				adjacent[3] = 15;
				adjacent[4] = 18;
				adjacent[5] = 20;
				adjacent[6] = 28;
				break;

			case 18:adjacent = new int[4];
				adjacent[0] = 15;
				adjacent[1] = 17;
				adjacent[2] = 19;
				adjacent[3] = 20;
				break;

			case 19:adjacent = new int[3];
				adjacent[0] = 18;
				adjacent[1] = 20;
				adjacent[2] = 21;
				break;

			case 20:adjacent = new int[8];
				adjacent[0] = 17;
				adjacent[1] = 18;
				adjacent[2] = 19;
				adjacent[3] = 21;
				adjacent[4] = 22;
				adjacent[5] = 24;
				adjacent[6] = 27;
				adjacent[7] = 28;
				break;

			case 21:adjacent = new int[4];
				adjacent[0] = 19;
				adjacent[1] = 20;
				adjacent[2] = 22;
				adjacent[3] = 23;
				break;

			case 22:adjacent = new int[4];
				adjacent[0] = 20;
				adjacent[1] = 21;
				adjacent[2] = 23;
				adjacent[3] = 24;
				break;

			case 23:adjacent = new int[4];
				adjacent[0] = 21;
				adjacent[1] = 22;
				adjacent[2] = 24;
				adjacent[3] = 25;
				break;

			case 24:adjacent = new int[5];
				adjacent[0] = 20;
				adjacent[1] = 22;
				adjacent[2] = 23;
				adjacent[3] = 25;
				adjacent[4] = 27;
				break;

			case 25:adjacent = new int[4];
				adjacent[0] = 23;
				adjacent[1] = 24;
				adjacent[2] = 27;
				adjacent[3] = 26;
				break;

			case 26:adjacent = new int[2];
				adjacent[0] = 25;
				adjacent[1] = 27;
				break;


			case 27:adjacent = new int[5];
				adjacent[0] = 28;
				adjacent[1] = 20;
				adjacent[2] = 24;
				adjacent[3] = 25;
				adjacent[4] = 26;
				break;

			case 28:adjacent = new int[5];
				adjacent[0] = 27;
				adjacent[1] = 20;
				adjacent[2] = 17;
				adjacent[3] = 30;
				adjacent[4] = 29;
				break;

			case 29:adjacent = new int[2];
				adjacent[0] = 28;
				adjacent[1] = 30;
				break;

			case 30:adjacent = new int[7];
				adjacent[0] = 28;
				adjacent[1] = 29;
				adjacent[2] = 17;
				adjacent[3] = 16;
				adjacent[4] = 12;
				adjacent[5] = 31;
				adjacent[6] = 36;
				break;

			case 31:adjacent = new int[5];
				adjacent[0] = 30;
				adjacent[1] = 12;
				adjacent[2] = 33;
				adjacent[3] = 34;
				adjacent[4] = 36;
				break;

			case 32:adjacent = new int[2];
				adjacent[0] = 12;
				adjacent[1] = 33;
				break;

			case 33:adjacent = new int[4];
				adjacent[0] = 32;
				adjacent[1] = 12;
				adjacent[2] = 31;
				adjacent[3] = 34;
				break;

			case 34:adjacent = new int[4];
				adjacent[0] = 33;
				adjacent[1] = 31;
				adjacent[2] = 35;
				adjacent[3] = 36;
				break;

			case 35:adjacent = new int[2];
				adjacent[0] = 34;
				adjacent[1] = 36;
				break;

			case 36:adjacent = new int[6];
				adjacent[0] = 30;
				adjacent[1] = 31;
				adjacent[2] = 34;
				adjacent[3] = 35;
				adjacent[4] = 37;
				adjacent[5] = 39;
				break;

			case 37:adjacent = new int[3];
				adjacent[0] = 36;
				adjacent[1] = 38;
				adjacent[2] = 39;
				break;


			case 38:adjacent = new int[2];
				adjacent[0] = 37;
				adjacent[1] = 39;
				break;


			case 39:adjacent = new int[3];
				adjacent[0] = 37;
				adjacent[1] = 38;
				adjacent[2] = 36;
				break;	
		}
	}

	//determines whether a territory is adjacent to this territory instance
	public boolean isAdjacent(territory other)
	{
		boolean adj = false;				//initially assumes no adjacency

		for(int i = 0; i < adjacent.length; i++)	//loops over this instances array of adjacent territories
		{
			if(adjacent[i] == other.getTerrNum())	//if the other territory is in array...
			{
				adj = true;			//adjacency is true
			}
		}

		return adj;
	}

	//sets owner to new owner
	public void setOwner(String own)
	{
		this.owner = own;
	}

	//retrieves owner of this territory
	public String getOwner()
	{
		return this.owner;
	}

	//gets link to this territory's image file
	public String getImg()
	{
		return this.owner + "/" + this.terrNum + String.valueOf(this.owner.charAt(0)) + ".png";
	}

	//creates territory view with listener classes
	public ImageView getView()
	{
	        ImageView view = new ImageView();		//creates new image view for this territory		

		view.setImage(new Image(this.getImg()));	//sets view to this territory's image

		view.setViewport(new Rectangle2D(0, 0, 1210, 1210));	//sets size of view


		//add class to view that listens for mouse clicks
		view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     			@Override
     			public void handle(MouseEvent event) 		//if this territory is clicked on
			{
				if(isSelected())			//if this territory is selected
				{
					view.setImage(new Image(getImg()));	//returns territory image to original

					toggleSelect();				//unselects territory

					map.selected = false;			//makes sure that map knows that no territory is selected
				}
				else if(map.selected)			//if another territory is selected
				{
				}
				else					//if no territory is selected
				{
					view.setImage(new Image(getImgSelected()));	//sets this territory's image to white

					toggleSelect();					//selects territory

					map.selected = true;				//map knows that a territory is selected
				}
         			event.consume();
     			}
		});


		//add class to view that listens for a mouse entrance
    		view.setOnMouseEntered(new EventHandler<MouseEvent>() 
		{
        		@Override
        		public void handle(MouseEvent event) 
			{
				if(map.selected)
				{
				}
				else
				{
					view.setImage(new Image(getImgSelected()));
				}
         			event.consume();
        		}	
    		});


		//add class to view that listens for mouse exit
    		view.setOnMouseExited(new EventHandler<MouseEvent>() 
		{
        		@Override
        		public void handle(MouseEvent event) 
			{
				if(map.selected)
				{
				}
				else
				{
					view.setImage(new Image(getImg()));
				}

         			event.consume();
        		}
    		});

		return view;
	}

	//gets whitened image of this territory instance
	public String getImgSelected()
	{
		return "white" + "/" + this.terrNum + "w.png";
	}

	//sets dice number of this territory instance
	public void setDice(int dice)
	{
		this.numDice = dice;
	}

	//returns this territory's dice number
	public int getDice()
	{
		return this.numDice;
	}

	//returns this territory's number
	public int getTerrNum()
	{
		return this.terrNum;
	}

	//toggles whether this territory instance is selected
	public void toggleSelect()
	{
		if(selected)
		{
			selected = false;
		}
		else
		{
			selected = true;
		}
	}

	//queries whether this territory instance has been selected
	public boolean isSelected()
	{
		return selected;
	}

	//launch attack on other territory
	public void attack(territory other)
	{
		Random rand = new Random();		//create random number generator

		int our = 0;				//this territory's total starts as 0

		int their = 0;				//other territory's number starts as 0

		for(int i = 0; i < this.getDice(); i++)	//loops the same number of times as this territory has dice
		{
			our += (rand.nextInt(6) + 1);	//generates random number and adds to running total
		}

		for(int i = 0; i < other.getDice(); i++)	//repeat process for other territory
		{
			their += (rand.nextInt(6) + 1);
		}

		this.setDice(1);			//regardless of which territory wins, this's dice will be set to 0

		if(our > their)				//this territory wins if it rolls a higher total number
		{
			other.setOwner(this.getOwner());
		}
	}
}