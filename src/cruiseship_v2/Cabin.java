package cruiseship_v2;

/* Array of passenger objects in cabin */
public class Cabin {
    public static Passenger[] occupant = new Passenger[4];
    
//    public static Queue[] queue = new Queue[4];
    
    public int cabinNum;
    public String empty;
    
    /* Constructor method - creates cabin objects */
    public Cabin(int cabinNum) {
        this.cabinNum = cabinNum;
    }
    
}
