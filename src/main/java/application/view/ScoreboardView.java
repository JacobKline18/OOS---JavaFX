package application.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import application.model.Observer;
import application.model.Team;
import application.viewmodel.ScoreboardModelView;
import application.Main;

public class ScoreboardView implements Observer{

    private ObservableList<Team> teamList;

    private final int ROW_HEIGHT = 24;

    private ScoreboardModelView scoreboardModelView = new ScoreboardModelView();

    @FXML
    private ListView<Team> myListView;
    
    public void initialize(){
        scoreboardModelView.registerObserver(this);
        update();
    }

    @FXML
    void clickTeam(MouseEvent event) {
        Team teamToEdit = myListView.getSelectionModel().getSelectedItem();
        showTeamEditor(teamToEdit);
    }

    public void showTeamEditor(Team teamToEdit) {
        if (teamToEdit != null) {
            try {
                EditorView editorView = createEditorView(teamToEdit, scoreboardModelView);
                FXMLLoader fxmlLoader = createLoader(editorView);
                Stage stage = createStage(fxmlLoader);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public FXMLLoader createLoader(EditorView editorView) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("Editor.fxml"));
            fxmlLoader.setController(editorView);
            return fxmlLoader;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public EditorView createEditorView(Team teamToEdit, ScoreboardModelView scoreboardModelView) {
        EditorView editorView = new EditorView();
        editorView.setTeamData(teamToEdit);
        editorView.setModelView(scoreboardModelView);
        return editorView;
    }

    public Stage createStage(FXMLLoader fxmlLoader) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 400, 150);
            Stage stage = new Stage();
            stage.setTitle("Team Editor");
            stage.setScene(scene);
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
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

