import java.util.*;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * @author  Jonathan Lim
 * 
 * OpeningScreen class - class to simulate opening of chess game
 */
public class OpeningScreen extends World
{
    private Button singlePlayer = new Button("Single Player");
    private Button twoPlayer = new Button("Two Player");
    private Button howToPlay = new Button("How To Play");
    private Button returnToTitle = new Button("Return to Title Page");
    
    private boolean firstRun = true;
    
    private Textbox name1 = new Textbox(1);
    private Textbox name2 = new Textbox(2);
    private Title rules = new Title("How to Play", 50);
    private Title description = new Title( "Chess is a strategy game played on an 8 x 8 square grid by two players.\n"  + 
    "There are 32 total game pieces, divided evenly between the two players.\n" + 
    "During each player’s turn, he or she may move\none piece according to to the rules of the game.\n" + 
    "The goal is to capture the opponent’s pieces and trap the opposing player’s king.\n" + 
    "When a player manages to pin down his or her opponent’s king,\n then that player has checkmated the opponent and won the game.\n\n" + 
    "To select the piece you want to move, click on that piece.\n" + 
    "Immediately following, click on the tile that you want to move the piece to.\n" + 
    "Press 1 to see who's turn it is again.\n" + 
    "Press 2 to request a draw with the opponent.\n" + 
    "Press 3 to resign.\n" + 
    "Press 4 to check your captured pieces.\n" + 
    "After requesting a draw, press 1 to agree\n or press 2 to disagree and continue the game.\n", 25);

    /**
     * Constructor for objects of class OpeningScreen.
     * 
     */
    public OpeningScreen()
    {    
        super(800, 800, 1); 
        addObject(singlePlayer, getWidth() / 2, getWidth() / 2);
        addObject(new Title("Chess", 100), getWidth() / 2, getWidth() / 4);
        addObject(new Title("By Andrew Le, Tommy Tran, Arjun Agrawal, Brian Qiu, Jonathan Lim", 25), getWidth() / 2, getWidth() / 3);
        addObject(twoPlayer, getWidth() / 2, 3* getWidth() / 5);
        addObject(howToPlay, getWidth() / 2, 3* getWidth() / 4);
    }
    
    /**
     * Method to check if buttons are clicked and act accordingly
     */
    public void act(){
        if(twoPlayer.clicked()){
            if(firstRun){
                startPlayer(name1);
                firstRun = false;
            }
            else if(name1.enter()){
                name1.setEnter(false);
                startPlayer(name2);
            }
            else if(name2.enter()){
                twoPlayer.setClicked(false);
                Greenfoot.setWorld(new Game(name1.getValue(), name2.getValue()));
            }
        }
        else if(singlePlayer.clicked()){
            if(firstRun){
                startPlayer(name1);
                firstRun = false;
            }
            else if(name1.enter()){
                singlePlayer.setClicked(false);
                Greenfoot.setWorld(new Game(name1.getValue()));
            }
        }
        else if(howToPlay.clicked()){
            removeObjects(getObjects(Title.class));
            removeObjects(getObjects(Button.class));
            addObject(rules, getWidth() / 2, getWidth() / 8);
            addObject(description, getWidth() / 2, getWidth() / 2);
            addObject(returnToTitle, getWidth() / 2, 15 * getWidth() / 16);
        }
        if(returnToTitle.clicked()){
            Greenfoot.setWorld(new OpeningScreen());
        }
    }
    
    /**
     * Method to get name of player
     * @param t - textbox with name of player to be inputted
     */
    public void startPlayer(Textbox t){
        removeObjects(getObjects(Actor.class));
        addObject(t, getWidth() / 2, getWidth() / 2);
    }
}
