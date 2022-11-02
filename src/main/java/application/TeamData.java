package application;

import java.util.ArrayList;

public class TeamData implements Subject{
    private ArrayList<Observer> observers;
    ArrayList<Team> teamList;
    
    public TeamData(ArrayList<Team> teamList) {
        observers = new ArrayList<Observer>();
        this.teamList = teamList;
    }
    
    @Override
    public void registerObserver(Observer o) {
       observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    
    public ArrayList<Team> getTeamList() {
        return teamList;
    }
    
    public void updateTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

}
