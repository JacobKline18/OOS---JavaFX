package application;
import java.time.LocalDate;
import java.time.LocalTime;

public class Team {
    String teamName;
    int teamScore;
    LocalDate lastUpdateDate;
    LocalTime lastUpdateTime;
    Boolean isUpdated;
    
    public Team(String teamName, int teamScore) {
        setTeamName(teamName);
        setTeamScore(teamScore);
        setIsUpdated(false);
        setDate();
    }

    public Boolean getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(Boolean value) {
        this.isUpdated = value;
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

    public String getDate() {
        return lastUpdateDate.toString() + " " + lastUpdateTime.toString().substring(0, 8);
    }

    public void setDate() {
        this.lastUpdateDate = LocalDate.now();
        this.lastUpdateTime = LocalTime.now();
    }

    public String toString() {
        return String.format("%-30s\t\t% 20d", teamName, teamScore);
    }
}
