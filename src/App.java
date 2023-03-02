import javadraw.*;

public class App extends Window {
    Oval player;
    double dx = 0;
    double dy = 0;
    double speed = 15;
    Oval bullet;

    public static void main(String[] args) throws Exception {
        Window.open();
    }

    public void start(){
        player = new Oval(screen, 50, 50, 50, 50);

        while(true){
            player.move(dx,dy);
            screen.update();
            screen.sleep(1/30.0);
        }

    }


    //Find out how much x movement and y movement should happen based on click location
    public void calculateMovement(Location clickSpot){
        //this finds the total distance between player and clickSpot location for X and Y
        double diffInX = clickSpot.x() - player.x();
        double diffInY = clickSpot.y() - player.y();
        //if we just moved player by diffInX and diffInY, we would teleport to the clicked spot


        //slope defines ratio between how much to move in X vs how much to move in Y
        double slope = diffInY/diffInX;

        //We are looking for dx and dy (how much to move in x and y direction each step)
        //We know two things:
        //1. Movement in dx and dy are two legs of a right triangle where speed is the hypotenuse so:
        // speed^2 = dx^2 + dy^2
        //2. The ratio of dy to dx should be the same as diffInY to diffInX, which is also our slope
        // dy/dx = slope OR dy = slope * dx

        //If we combine equations:
        // speed^2 = dx^2 + (slope*dx)^2 = dx^2 + slope^2 * dx^2 = dx^2 * (1 + slope^2)
        // speed^2 = dx^2 * (1 + slope^2)
        //Which means:
        // dx^2 = speed^2/(1 + slope^2)

        // dx = Math.sqrt(speed^2/(1 + slope^2))

        // And:

        // dy = slope * dx
        
        //In code:
        dx = Math.sqrt((speed*speed)/(1 + slope*slope));
        dy = slope*dx;

        if(diffInX < 0){
            dx = -dx;
            dy = -dy;
        }
        


    }
    //Whenever I click, figure out what dx and dy should be based on the location of the mouse
    public void mouseDown(int button, Location loc){
        calculateMovement(loc);

    }
}
