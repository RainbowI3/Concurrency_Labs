package org.example;
import java.util.concurrent.atomic.AtomicInteger;
public class Order {

    private final Client sender;
    private final Client receiver;
    private final Package pkg;
    private final PostOffice postOffice;
    private final ParcelLocker destinationLocker;

    public Order(Client sender, Client receiver, Package pkg, PostOffice postOffice, ParcelLocker destinationLocker) {
        this.sender = sender;
        this.receiver = receiver;
        this.pkg = pkg;
        this.postOffice = postOffice;
        this.destinationLocker = destinationLocker;
    }

    public Client getSender() {
        return sender;
    }

    public Client getReceiver() {
        return receiver;
    }

    public Package getPackage() {
        return pkg;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public ParcelLocker getDestinationLocker() {
        return destinationLocker;
    }

    @Override
    public String toString() {
        return "Order from " + sender.getName() + " to " + receiver.getName() +
                " through " + postOffice.getName() + " to " + destinationLocker.getLocation();
    }
}
