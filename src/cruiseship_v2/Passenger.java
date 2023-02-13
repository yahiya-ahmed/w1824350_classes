package cruiseship_v2;


public class Passenger {
    public String forename;
    public String surname;
    public int expenses;
    
    public String[] setForename = new String[13];
    public String[] setSurname  = new String[13];
    public int[] setExpenses = new int[13];
    
    /* Constructor method - creates passenger objects */
    public Passenger(String forename, String surname, int expenses) {
        this.forename = forename;
        this.surname = surname;
        this.expenses = expenses;
    }
    
    public String setForename(String forename, int cabinNum) {
        if (forename.equals("e")) {
            setForename[cabinNum] = forename; /* Delete forename */
        } else {
            setForename[cabinNum] = forename;
        }
        return setForename[cabinNum];
    }
    
    public String getForename(int cabinNum) {
        return setForename[cabinNum];
    }
    
    
    public String setSurname(String surname, int cabinNum) {
        if (surname.equals("e")) {
            setSurname[cabinNum] = surname; /* Deleted surname */
        } else {
            setSurname[cabinNum] = surname;
        }
        return setSurname[cabinNum];
    }
    
    public String getSurname(int cabinNum) {
        return setSurname[cabinNum];
    }
    
    
    public int setExpenses (String forename, String surname, int cabinNum) {
        /*  */
        if (forename.equals("e") && surname.equals("e")) {
            setExpenses[cabinNum] = 0;
        }
        else {
            setExpenses[cabinNum] = 50;
        }
        return setExpenses[cabinNum];
    }
    
    public int getExpenses(int cabinNum) {
        return setExpenses[cabinNum];
    }
    
}