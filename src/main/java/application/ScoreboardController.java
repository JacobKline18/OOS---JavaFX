package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class ScoreboardController {
    private final ObservableList<Team> teamList = FXCollections.observableArrayList();
    
    @FXML
    private ListView<Team> myListView;
    
    
    public void initialize(){
        myListView.setItems(teamList);
        myListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        myListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Team teamToEdit = myListView.getSelectionModel().getSelectedItem();
                System.out.println("clicked on " + teamToEdit);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Editor.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 360, 250);
                    scene.setUserData(teamList);
                    Stage stage = new Stage();
                    stage.setTitle("Edit Team");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
            }
        });
        populate();
    }
    
    
    private void populate() {
        teamList.add(new Team("Highly Irresistable Lions", 45));
        teamList.add(new Team("Immovable Tigers", 75));
        teamList.add(new Team("Super Duper Bears", 100));
        teamList.add(new Team("Incomparable Otters", 30));
        teamList.add(new Team("Resplendent Ocelots", 8));
    }
    
}

