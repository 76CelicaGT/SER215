import java.io.*;
import java.net.*;
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


public class map extends Application
{
	public static boolean selected;		//whether or not any territory has been selected

	//all territories are public


	//initializes all 40 territory object references
	public territory portugal, spain, france, britain, ireland, belgium, netherlands, denmark, switzerland, germany;

	public territory italy, sicily, poland, czechRep, austria, slovenia, slovakia, hungary, croatia, bosnia;
	
	public territory serbia, montenegro, kosovo, albania, macedonia, greece, turkey, bulgaria, romania, moldova;
	
	public territory ukraine, belarus, russiaMin, lithuania, latvia, estonia, russia, finland, sweden, norway;


	//main method only used to launch overriden start method
	public static void main(String[] args)
	{
		launch(args);		//launches application
	}

	//start method, from extending Application class
	public void start(Stage stage)
	{
		selected = false;			//no territories selected

       		Group root = new Group();		//used to add items to stage

		stage.setTitle("Map");			//sets name of window as Map, should be changed to name of game

         	ImageView view = new ImageView();	//creates view for background image
         
		view.setImage(new Image("background.png"));		//sets background image to view

         	Rectangle2D rec = new Rectangle2D(0, 0, 1210, 1210);	//ensures that entire map can be viewed

		view.setViewport(rec);			//adds wide rectangle view to background image
       					
		root.getChildren().add(view);		//adds background image to root
	


		initTerritory();			//initializes territory

		displayMap(root);			//initializes the map



		Scene scene = new Scene(root, 400, 250);	//sets the stage
    
		stage.setScene(scene);

        	stage.setWidth(850);				//determines size of screen
         		
		stage.setHeight(816);

		stage.setResizable(false);			//prevents resizing
    
		stage.show();					//displays window
	}




	public void initTerritory()
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


		//sets up temporary ownership, demo only

		portugal.setOwner("red");
		spain.setOwner("blue");
		france.setOwner("green");
		britain.setOwner("blue");
		ireland.setOwner("red");
		belgium.setOwner("green");
		netherlands.setOwner("red");
		denmark.setOwner("blue");
		switzerland.setOwner("green");
		germany.setOwner("blue");
		italy.setOwner("red");
		sicily.setOwner("green");
		poland.setOwner("red");
		czechRep.setOwner("blue");
		austria.setOwner("green");
		slovenia.setOwner("blue");
		slovakia.setOwner("red");
		hungary.setOwner("green");
		croatia.setOwner("red");
		bosnia.setOwner("blue");
		serbia.setOwner("green");
		montenegro.setOwner("blue");
		kosovo.setOwner("red");
		albania.setOwner("green");
		macedonia.setOwner("red");
		greece.setOwner("blue");
		turkey.setOwner("green");
		bulgaria.setOwner("blue");
		romania.setOwner("red");
		moldova.setOwner("green");
		ukraine.setOwner("red");
		belarus.setOwner("blue");
		russiaMin.setOwner("green");
		lithuania.setOwner("blue");
		latvia.setOwner("red");
		estonia.setOwner("green");
		russia.setOwner("red");
		finland.setOwner("blue");
		sweden.setOwner("green");
		norway.setOwner("blue");
	}






	public void displayMap(Group root)
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
	}
}