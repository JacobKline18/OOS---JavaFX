package application;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
                if (teamName.isEmpty()) {
                    teamToUpdate.setTeamName("No name provided");
                }
                else {
                    teamToUpdate.setTeamName(teamName);
                }
                teamToUpdate.setIsUpdated(true);
                teamToUpdate.setDate();
            }
            if (checkTeamScore(teamScore)) {
                if (teamScore.isEmpty()) {
                    teamToUpdate.setTeamScore(0);
                }
                else {
                    teamToUpdate.setTeamScore(Integer.parseInt(teamScore));
                }
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

        if (teamName.isEmpty())
            return true;

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

        if (score.isEmpty())
            return true;

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
        teamList.add(new Team("Highly Irresistable Lions", 45));
        teamList.add(new Team("Immovable Tigers", 75));
        teamList.add(new Team("Super Duper Bears", 100));
        teamList.add(new Team("Incomparable Otters", 30));
        teamList.add(new Team("Resplendent Ocelots", 8));
    }

}
