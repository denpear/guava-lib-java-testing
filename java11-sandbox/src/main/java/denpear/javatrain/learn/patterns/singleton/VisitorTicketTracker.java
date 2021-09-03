package denpear.javatrain.learn.patterns.singleton;

// Lazy instantiation
public class VisitorTicketTracker {
    private static VisitorTicketTracker instance; // не final, потому что не создается при загрузке
    private VisitorTicketTracker() {
    }
    public static VisitorTicketTracker getInstance() {
        if(instance == null) {
            instance = new VisitorTicketTracker(); // NOT THREAD-SAFE!
        }
        return instance;
    }
// Data access methods
//...
}

