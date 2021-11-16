package fsm;

import jm.Cart;

/**
 * The Context class for keeping data between state changes.
 * All variables declared here are necessary for the context
 * 
 * This class is handled as a singleton so it can be accessed anywhere
 * 
 * @author Vannessa
 */
public class Context {

    private static Context instance;

    public static Context get(){
        if (instance == null) instance = new Context();
        return instance;
    }
    
    public int clientID = -1;

    public int currentState = 0;
    public int previousState = 0;

    private Cart clientCart;
    public Cart getCart(){
        if (clientCart == null) clientCart = new Cart();
        return clientCart;
    }
}
