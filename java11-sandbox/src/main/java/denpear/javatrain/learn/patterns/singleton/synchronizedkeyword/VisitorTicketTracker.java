package denpear.javatrain.learn.patterns.singleton.synchronizedkeyword;

public class VisitorTicketTracker {
    private static VisitorTicketTracker instance;
    public static synchronized VisitorTicketTracker getInstance() {
        if(instance == null) {
            synchronized(VisitorTicketTracker.class) {
                if(instance == null) {
                    instance = new VisitorTicketTracker();
                }
            }
        }
        return instance;
    }
// Data access methods
//...
}
