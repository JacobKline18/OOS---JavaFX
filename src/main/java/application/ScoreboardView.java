package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScoreboardView implements Observer{

    private ObservableList<Team> teamList;

    final int ROW_HEIGHT = 24;

    ScoreboardModelView scoreboardModelView = new ScoreboardModelView();


    @FXML
    private ListView<Team> myListView;
    
    
    public void initialize(){

        scoreboardModelView.registerObserver(this);

        update();

        myListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Team teamToEdit = myListView.getSelectionModel().getSelectedItem();

                if (teamToEdit != null) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("Editor.fxml"));
                        EditorView editorView = new EditorView();
                        fxmlLoader.setController(editorView);
                        editorView.setTeamData(teamToEdit);
                        editorView.setModelView(scoreboardModelView);

                        Scene scene = new Scene(fxmlLoader.load(), 400, 150);
                        Stage stage = new Stage();
                        stage.setTitle("Team Editor");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public void update() {
        teamList = FXCollections.observableArrayList();
        for (Team newTeam : scoreboardModelView.getTeamList()) {
            teamList.add(newTeam);
        }
        myListView.setItems(teamList);
        myListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        myListView.setPrefHeight(teamList.size() * ROW_HEIGHT + 2);
    }
    
}

