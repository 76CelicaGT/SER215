import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.animation.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Client extends Application
{
	public boolean music = false;



	public static boolean myTurn;		//whether it is this client's turn

	public static String myColor = null;	//what this client's color is

	public static Label chainB;		//land chain labels

	public static Label chainR;

	public static int turnCount = 1;





	public Socket socket;

	public static DataOutputStream output;

	public static DataInputStream input;

	public static boolean selected;		//whether or not any territory has been selected

	public static territory current;	//the current selected territory

	public static MediaPlayer mediaPlayer;	//for playing music and sfx
		
	public static Group root;		//for adding elements to screen

  	public Button bt;			//end turn button

	public static Label col;		//color label

	public static Label turn;		//turn label

	public static ClientHandlerStart handler;	//threads for Server

	public static ClientHandlerTurn handler2;


	//all territories are public


	//initializes all 40 territory object references
	public static territory portugal, spain, france, britain, ireland, belgium, netherlands, denmark, switzerland, germany;

	public static territory italy, sicily, poland, czechRep, austria, slovenia, slovakia, hungary, croatia, bosnia;
	
	public static territory serbia, montenegro, kosovo, albania, macedonia, greece, turkey, bulgaria, romania, moldova;
	
	public static territory ukraine, belarus, russiaMin, lithuania, latvia, estonia, russia, finland, sweden, norway;
	

	//main method only used to launch overriden start method
	public static void main(String[] args)
	{
		launch(args);		//launches application
	}

	//start method, from extending Application class
	public void start(Stage stage) throws IOException
	{
     	 	socket = new Socket("localhost", 8000);			//connect to server

      		output =  new DataOutputStream(socket.getOutputStream());

      		input = new DataInputStream(socket.getInputStream());
		
		myTurn = false;





		handler = new ClientHandlerStart();			//determines client color and whose turn it is

      		new Thread(handler).start();



		bt = new Button("End Turn");

		selected = false;			//no territories selected

       		root = new Group();			//used to add items to stage

		stage.setTitle("Map");			//sets name of window as Map, should be changed to name of game

         	ImageView view = new ImageView();	//creates view for background image
         
		view.setImage(new Image("bin/background.png"));		//sets background image to view

         	Rectangle2D rec = new Rectangle2D(0, 0, 1210, 1210);	//ensures that entire map can be viewed

		view.setViewport(rec);			//adds wide rectangle view to background image
       					
		root.getChildren().add(view);		//adds background image to root


		if(music)	//plays music if music is on
		{
			Media med = new Media(new File("bin/sound/music.mp3").toURI().toString());

			mediaPlayer = new MediaPlayer(med);

			mediaPlayer.play();

 			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		}



         	ImageView view2 = new ImageView();			//creates view for pane
         
		view2.setImage(new Image("bin/pane.png"));		//sets pane image to view

         	Rectangle2D rec2 = new Rectangle2D(-739, 0, 1210, 1210);	//ensures that pane can be moved

		view2.setViewport(rec2);				//adds wide rectangle view to pane
       					
		root.getChildren().add(view2);				//adds pane to root


	

		initTerritory();			//initializes territory

		displayMap();				//initializes the map




		bt.setLayoutX(750);		//sets button location and style

		bt.setLayoutY(605);

		bt.setMinSize(180,90);

		bt.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");

		bt.setOnAction(e -> {try{endTurn();}catch(IOException ex){System.err.print(ex);}});	//sets button action as endturn()

		root.getChildren().add(bt);		//adds end turn button to root	




		//sets up land chain labels
		chainR = new Label("Red Land Chain: " + Integer.toString(largestChain("red")));

		chainR.setMouseTransparent(true);

		chainR.setTranslateX(765);

		chainR.setTranslateY(380);

		chainR.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		root.getChildren().add(chainR);

		chainB = new Label("Blue Land Chain: " + Integer.toString(largestChain("blue")));

		chainB.setMouseTransparent(true);

		chainB.setTranslateX(765);

		chainB.setTranslateY(400);

		chainB.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		root.getChildren().add(chainB);




		//sets up color and turn labels
		col = new Label(" ");

		col.setMouseTransparent(true);

		col.setTranslateX(765);

		col.setTranslateY(30);

		col.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		root.getChildren().add(col);

		turn = new Label("It is not your turn");

		turn.setMouseTransparent(true);

		turn.setTranslateX(765);

		turn.setTranslateY(70);

		turn.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

		root.getChildren().add(turn);



		//displays stage
		Scene scene = new Scene(root, 400, 250);	//sets the stage
    
		stage.setScene(scene);

        	stage.setWidth(945);				//determines size of screen
         		
		stage.setHeight(727);

		stage.setResizable(false);			//prevents resizing
    
		stage.show();					//displays window
	}



	//ends turn
	public static void endTurn() throws IOException
	{
		if(myTurn)			//if it is not your turn, do not end turn
		{
			myTurn = false;	

			turn.setText("It is not your turn");

			output.writeBoolean(true);	//send server end turn signal

			handler2 = new ClientHandlerTurn();	//awaits turn

      			new Thread(handler2).start();

			output.flush();
		}
	}


	
	private void initTerritory()
	{
		//initializes territories with proper numbers		

		portugal 	= new territory(0);
		spain 		= new territory(1);
		france 		= new territory(2);
		britain 	= new territory(3);
		ireland 	= new territory(4);
		belgium 	= new territory(5);
		netherlands 	= new territory(6);
		denmark 	= new territory(7);
		switzerland 	= new territory(8);
		germany 	= new territory(9);
		italy 		= new territory(10);
		sicily 		= new territory(11);
		poland 		= new territory(12);
		czechRep 	= new territory(13);
		austria 	= new territory(14);
		slovenia 	= new territory(15);
		slovakia 	= new territory(16);
		hungary 	= new territory(17);
		croatia 	= new territory(18);
		bosnia 		= new territory(19);
		serbia 		= new territory(20);
		montenegro 	= new territory(21);
		kosovo 		= new territory(22);
		albania 	= new territory(23);
		macedonia 	= new territory(24);
		greece 		= new territory(25);
		turkey 		= new territory(26);
		bulgaria 	= new territory(27);
		romania 	= new territory(28);
		moldova 	= new territory(29);
		ukraine 	= new territory(30);
		belarus 	= new territory(31);
		russiaMin	= new territory(32);
		lithuania 	= new territory(33);
		latvia 		= new territory(34);
		estonia 	= new territory(35);
		russia 		= new territory(36);
		finland 	= new territory(37);
		sweden 		= new territory(38);
		norway 		= new territory(39);


		//sets up ownership

			portugal.setOwner("red");
			spain.setOwner("blue");
			france.setOwner("red");
			britain.setOwner("blue");
			ireland.setOwner("red");
			belgium.setOwner("blue");
			netherlands.setOwner("red");
			denmark.setOwner("red");
			switzerland.setOwner("red");
			germany.setOwner("blue");
			italy.setOwner("red");
			sicily.setOwner("blue");
			poland.setOwner("red");
			czechRep.setOwner("blue");
			austria.setOwner("red");
			slovenia.setOwner("blue");
			slovakia.setOwner("blue");
			hungary.setOwner("blue");
			croatia.setOwner("red");
			bosnia.setOwner("blue");
			serbia.setOwner("red");
			montenegro.setOwner("blue");
			kosovo.setOwner("red");
			albania.setOwner("blue");
			macedonia.setOwner("red");
			greece.setOwner("blue");
			turkey.setOwner("red");
			bulgaria.setOwner("blue");
			romania.setOwner("red");
			moldova.setOwner("blue");
			ukraine.setOwner("red");
			belarus.setOwner("blue");
			russiaMin.setOwner("red");
			lithuania.setOwner("blue");
			latvia.setOwner("red");
			estonia.setOwner("blue");
			russia.setOwner("red");
			finland.setOwner("blue");
			sweden.setOwner("red");
			norway.setOwner("blue");
	}






	public void displayMap()
	{
		//shows all territories on map

		root.getChildren().add(portugal.getView());
		root.getChildren().add(spain.getView());
		root.getChildren().add(france.getView());
		root.getChildren().add(britain.getView());
		root.getChildren().add(ireland.getView());
		root.getChildren().add(belgium.getView());
		root.getChildren().add(netherlands.getView());
		root.getChildren().add(denmark.getView());
		root.getChildren().add(switzerland.getView());
		root.getChildren().add(germany.getView());
		root.getChildren().add(italy.getView());
		root.getChildren().add(sicily.getView());
		root.getChildren().add(poland.getView());
		root.getChildren().add(czechRep.getView());
		root.getChildren().add(austria.getView());
		root.getChildren().add(slovenia.getView());
		root.getChildren().add(slovakia.getView());
		root.getChildren().add(hungary.getView());
		root.getChildren().add(croatia.getView());
		root.getChildren().add(bosnia.getView());
		root.getChildren().add(serbia.getView());
		root.getChildren().add(montenegro.getView());
		root.getChildren().add(kosovo.getView());
		root.getChildren().add(albania.getView());
		root.getChildren().add(macedonia.getView());
		root.getChildren().add(greece.getView());
		root.getChildren().add(turkey.getView());
		root.getChildren().add(bulgaria.getView());
		root.getChildren().add(romania.getView());
		root.getChildren().add(moldova.getView());
		root.getChildren().add(ukraine.getView());
		root.getChildren().add(belarus.getView());
		root.getChildren().add(russiaMin.getView());
		root.getChildren().add(lithuania.getView());
		root.getChildren().add(latvia.getView());
		root.getChildren().add(estonia.getView());
		root.getChildren().add(russia.getView());
		root.getChildren().add(finland.getView());
		root.getChildren().add(sweden.getView());
		root.getChildren().add(norway.getView());



		root.getChildren().add(portugal.getLabel());
		root.getChildren().add(spain.getLabel());
		root.getChildren().add(france.getLabel());
		root.getChildren().add(britain.getLabel());
		root.getChildren().add(ireland.getLabel());
		root.getChildren().add(belgium.getLabel());
		root.getChildren().add(netherlands.getLabel());
		root.getChildren().add(denmark.getLabel());
		root.getChildren().add(switzerland.getLabel());
		root.getChildren().add(germany.getLabel());
		root.getChildren().add(italy.getLabel());
		root.getChildren().add(sicily.getLabel());
		root.getChildren().add(poland.getLabel());
		root.getChildren().add(czechRep.getLabel());
		root.getChildren().add(austria.getLabel());
		root.getChildren().add(slovenia.getLabel());
		root.getChildren().add(slovakia.getLabel());
		root.getChildren().add(hungary.getLabel());
		root.getChildren().add(croatia.getLabel());
		root.getChildren().add(bosnia.getLabel());
		root.getChildren().add(serbia.getLabel());
		root.getChildren().add(montenegro.getLabel());
		root.getChildren().add(kosovo.getLabel());
		root.getChildren().add(albania.getLabel());
		root.getChildren().add(macedonia.getLabel());
		root.getChildren().add(greece.getLabel());
		root.getChildren().add(turkey.getLabel());
		root.getChildren().add(bulgaria.getLabel());
		root.getChildren().add(romania.getLabel());
		root.getChildren().add(moldova.getLabel());
		root.getChildren().add(ukraine.getLabel());
		root.getChildren().add(belarus.getLabel());
		root.getChildren().add(russiaMin.getLabel());
		root.getChildren().add(lithuania.getLabel());
		root.getChildren().add(latvia.getLabel());
		root.getChildren().add(estonia.getLabel());
		root.getChildren().add(russia.getLabel());
		root.getChildren().add(finland.getLabel());
		root.getChildren().add(sweden.getLabel());
		root.getChildren().add(norway.getLabel());
	}


	//plays animation at location x,y
	public static void playAnimation(int x, int y)
	{
         	ImageView view3 = new ImageView();	

		view3.setMouseTransparent(true);			

         	Rectangle2D rec3 = new Rectangle2D( (96 - x), (96 - y), 1210, 1210);	

		view3.setViewport(rec3);

		view3.setImage(new Image("bin/explosions/" + "test" + ".gif"));
       					
		root.getChildren().add(view3);		
	}



	//determines largest chain of land for parameter owner
	public static int largestChain(String owner)
	{

		//Establishes an array of all territories, plus whether they have been visited
		Entry all[] = new Entry[40];

		all[0] = new Entry(portugal);
		all[1] = new Entry(spain);	
		all[2] = new Entry(france);
		all[3] = new Entry(britain);
		all[4] = new Entry(ireland);
		all[5] = new Entry(belgium);
		all[6] = new Entry(netherlands);
		all[7] = new Entry(denmark);
		all[8] = new Entry(switzerland);
		all[9] = new Entry(germany);
		all[10] = new Entry(italy);
		all[11] = new Entry(sicily);
		all[12] = new Entry(poland);
		all[13] = new Entry(czechRep);
		all[14] = new Entry(austria);
		all[15] = new Entry(slovenia);
		all[16] = new Entry(slovakia);
		all[17] = new Entry(hungary);
		all[18] = new Entry(croatia);
		all[19] = new Entry(bosnia);
		all[20] = new Entry(serbia);
		all[21] = new Entry(montenegro);
		all[22] = new Entry(kosovo);
		all[23] = new Entry(albania);
		all[24] = new Entry(macedonia);
		all[25] = new Entry(greece);
		all[26] = new Entry(turkey);
		all[27] = new Entry(bulgaria);
		all[28] = new Entry(romania);
		all[29] = new Entry(moldova);
		all[30] = new Entry(ukraine);
		all[31] = new Entry(belarus);
		all[32] = new Entry(russiaMin);
		all[33] = new Entry(lithuania);
		all[34] = new Entry(latvia);
		all[35] = new Entry(estonia);
		all[36] = new Entry(russia);
		all[37] = new Entry(finland);
		all[38] = new Entry(sweden);
		all[39] = new Entry(norway);
		
		int totalLink = subChain(all, owner);	//find chain size

		return totalLink;
	}

	//recursive method that determines chain size
	private static int subChain(Entry[] terrs, String owner)
	{
		int result;					//keeps track of each chain size

		int currentChain = 0;				//keeps track of current largest chain size

		for(int i = 0; i < terrs.length; i++)		//loops over all territories
		{
			result = 0;				//starts result as 0

			if(!terrs[i].isMarked() && terrs[i].getTerr().getOwner().equals(owner))	//if the currently observed territory is not marked and has right owner,
			{
				terrs[i].mark();					//mark that territory

				result ++;						//augment result

				Entry nextTerrs[] = new Entry[terrs[i].getTerr().adjacent.length];	//create array big enough to store current territory's adjacents

				for(int j = 0; j < nextTerrs.length; j ++)				//populates above array with adjacent territories
				{
					nextTerrs[j] = terrs[terrs[i].getTerr().adjacent[j]];
				
				}

				result += subChain2(terrs, nextTerrs, owner);				//finds the chains of the adjacent territories
			}

			if(result > currentChain)							//replaces currentchain if result is larger than previous currentChain
			{
				currentChain = result;					
			}
		}

		return currentChain;
	}


	//helped method for finding chain size, same as above, but without currentChain
	private static int subChain2(Entry[] all, Entry[] terrs, String owner)
	{
		int result = 0;

		for(int i = 0; i < terrs.length; i++)
		{
			if(!terrs[i].isMarked() && terrs[i].getTerr().getOwner().equals(owner))
			{
				terrs[i].mark();

				result ++;

				Entry nextTerrs[] = new Entry[terrs[i].getTerr().adjacent.length];

				for(int j = 0; j < nextTerrs.length; j ++)
				{
					nextTerrs[j] = all[terrs[i].getTerr().adjacent[j]];
				
				}

				result += subChain2(all, nextTerrs, owner);
			}
		}

		return result;
	}



//stores territories and whether they have been marked
private static class Entry
{
	private territory stored;

	private boolean marked;

	public Entry(territory terr)
	{
		this.stored = terr;

		marked = false;
	}

	public territory getTerr()
	{
		return stored;
	}

	public boolean isMarked()
	{
		return marked;
	}

	public void mark()
	{
		marked = true;
	}
}


}















