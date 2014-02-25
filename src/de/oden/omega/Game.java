package de.oden.omega;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.oden.omega.states.StartMenu;
import de.oden.omega.states.Tutorial;

/**
 *
 * @author Soulwax
 */
public class Game extends StateBasedGame {

    //Settings
    public static final String TITLE = "A R I A D N E";
    public static final int HEIGHT = 720;
    public static final int WIDTH = HEIGHT * 16 / 9;
    public static final int SCALE = 2; //not yet implemented
    public static final int UPS = 60;
    public static final int FPS = 60;
    public static boolean fullScreen = false;

    public StartMenu startMenu;
    public Tutorial tutorial;
    
    public Game(String title) {
        super(title);
        startMenu = new StartMenu(State.MAIN_MENU, this);
        tutorial = new Tutorial(State.GAME_1);
        this.addState(startMenu);
        this.addState(tutorial);
        
        //TODO: add more states
    }
    
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
    	Art.init();
        this.getState(State.MAIN_MENU);       
        this.enterState(State.MAIN_MENU);        
    }
    
    public void enterStateId(int id) {
    	this.getState(id);
    	this.enterState(id);
    }
    
    public static void main(String[] args) {
        AppGameContainer container;
        
        try {
            container = new AppGameContainer(new Game(TITLE));
            container.setDisplayMode(WIDTH, HEIGHT, fullScreen);
            container.setVerbose(false);
            container.setMinimumLogicUpdateInterval(1000/UPS);
            container.setMaximumLogicUpdateInterval(1000/UPS);
            container.setTargetFrameRate(FPS);
            container.setAlwaysRender(true);
            container.start();
        } catch (SlickException e) {
            throw new RuntimeException("Failed to initialize the game container."
                    + " Reason: " + e.getMessage());
        }
    }

}
