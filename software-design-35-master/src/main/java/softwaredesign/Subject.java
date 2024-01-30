package softwaredesign;

public interface Subject {
    public void attach(Observer obs);
    public void detach();
    public void notifyObservers();
}
