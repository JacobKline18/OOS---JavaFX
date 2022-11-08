package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class EditorView implements Observer{
    ScoreboardModelView scoreboardModelView;
    Team team;
    @FXML
    private TextField myDate;

    @FXML
    private TextField myScore;

    @FXML
    private TextField myName;

    @FXML
    private Button mySave;
    
    public void initialize() {
        scoreboardModelView.registerObserver(this);
        update();
    }

    @FXML
    void onSaveClick(ActionEvent event) {
        scoreboardModelView.updateTeam(team, myName.getText(), myScore.getText());
    }
    
    public Team getTeamData() {
        return this.team;
    }

    public void setTeamData(Team newTeam) {
        this.team = newTeam;
    }

    public void setModelView(ScoreboardModelView scoreboardModelView) {
        this.scoreboardModelView = scoreboardModelView;
    }

    public void update() {
        this.myName.setText(this.team.getTeamName());
        this.myScore.setText(Integer.toString(this.team.getTeamScore()));
        this.myDate.setText(this.team.getDate());
    }

}