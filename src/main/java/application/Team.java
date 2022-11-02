package application;

public class Team {
    String teamName;
    int teamScore;
    
    public Team(String teamName, int teamScore) {
        setTeamName(teamName);
        setTeamScore(teamScore);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public String toString() {
        return String.format("%-30s\t% 20d", teamName, teamScore);
    }
}
