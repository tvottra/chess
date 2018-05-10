import java.util.*;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OpeningScreen here.
 * 
 * @author  Jonathan Lim
 * 
 */
public class OpeningScreen extends World
{
    private Button singlePlayer = new Button(1);
    private Button twoPlayer = new Button(2);
    private Button howToPlay = new Button(3);
    private boolean firstRun = true;
    private Textbox name1 = new Textbox(1);
    private Textbox name2 = new Textbox(2);

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
    }
    
    public void startPlayer(Textbox t){
        removeObjects(getObjects(Actor.class));
        addObject(t, getWidth() / 2, getWidth() / 2);
    }
}
