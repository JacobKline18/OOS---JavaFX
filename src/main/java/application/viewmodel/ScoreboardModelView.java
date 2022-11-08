package application.viewmodel;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import application.model.*;
import application.view.*;

public class ScoreboardModelView implements Subject{
    private ArrayList<Observer> observers;
    ArrayList<Team> teamList;
    
    public ScoreboardModelView() {
        observers = new ArrayList<Observer>();
        teamList = new ArrayList<Team>();
        populate();
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
            if ((observer instanceof EditorView) && (((EditorView) observer).getTeamData().getIsUpdated())){
                observer.update();
                ((EditorView) observer).getTeamData().setIsUpdated(false);
            }
            else if (observer instanceof ScoreboardView) {
                observer.update();
            }
        }
    }
    
    public ArrayList<Team> getTeamList() {
        return teamList;
    }
    
    public void updateTeam(Team teamToUpdate, String teamName, String teamScore) {

        try {
            if (checkTeamName(teamName)) {
                teamToUpdate.setTeamName(teamName);
                teamToUpdate.setIsUpdated(true);
                teamToUpdate.setDate();
            }
            if (checkTeamScore(teamScore)) {
                teamToUpdate.setTeamScore(Integer.parseInt(teamScore));
                teamToUpdate.setIsUpdated(true);
                teamToUpdate.setDate();
            }
        } catch(Exception e) {
        }

        notifyObservers();
    }

    public Boolean checkTeamName(String teamName) {

        int bottomNameLimit = 5;
        int topNameLimit = 50;

        Pattern nameCondition = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher match = nameCondition.matcher(teamName);
        Boolean patternMatch = match.find();

        if ((teamName.length() < bottomNameLimit) || (teamName.length() > topNameLimit))
            return false;
        
        if (patternMatch)
            return false;
        
        return true;
    }

    public Boolean checkTeamScore(String score) {
        int bottomScoreLimit = 0;
        int topScoreLimit = 2000;
        int teamScore;

        try {
            teamScore = Integer.parseInt(score);

            if ((teamScore > topScoreLimit) || (teamScore < bottomScoreLimit))
            return false;
        }
        catch (Exception e) {
            return false;
        }

        return true;

    }



    private void populate() {
        teamList.add(new Team());
        teamList.add(new Team());
        teamList.add(new Team());
        teamList.add(new Team());
        teamList.add(new Team());
    }

}
