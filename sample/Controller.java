package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML Button button1, button2;

    @FXML TableView<WordCount> tableView;

    @FXML TableColumn<WordCount, String> wordColumn;
    @FXML TableColumn<WordCount, Integer> countColumn;

    ArrayList<String> wordList;
    ArrayList<Integer> countList;

    Scanner scr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

    }

    public void chooseFile() throws IOException {

        //File Chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File file = fileChooser.showOpenDialog(null);

        wordList = new ArrayList<>();
        countList = new ArrayList<>();

        scr = new Scanner(file);

        //Read through file and find the words
        while (scr.hasNext()) {

            String nextWord = scr.next().toLowerCase();

            //Determine if the word is in the ArrayList
            if (wordList.contains(nextWord)) {
                int index = wordList.indexOf(nextWord);
                countList.set(index, countList.get(index) + 1);
            }
            else {
                wordList.add(nextWord);
                countList.add(1);
            }
        }

        scr.close();
    }

    public void show() {

        ObservableList<WordCount> data = FXCollections.observableArrayList();

        for (int i = 0; i < wordList.size(); i++) {
            data.add(new WordCount(wordList.get(i), countList.get(i)));
        }

        tableView.setItems(data);
    }

}
