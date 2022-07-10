package avaj_launcher.Weather;

import avaj_launcher.Flyable.Flyable;

import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers;

    protected Tower() {
        observers = new ArrayList<>();
    }

    public void register(Flyable flyable) { this.observers.add(flyable); }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionsChanged() {
        ArrayList<Flyable> temp = new ArrayList<>(observers);
        for(Flyable i : temp) {
            i.updateCondition();
        }
    }
}
