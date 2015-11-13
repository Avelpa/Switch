/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        View view = new View();
        while (true)
        {
            
            if (view.isPressed())
            {
                view.move();
            }
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e){}
        }
    }
    
}
