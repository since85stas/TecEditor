package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.sql.Driver;

public class Controller {

    @FXML
    private Button btn;

    @FXML
    private TextField edittext;

    @FXML
    private CheckBox all_checks;

    @FXML
    private void click(ActionEvent event) {
//        btn.setText("You've clicked!");
        int num = Integer.parseInt(edittext.getText());
        if (Utils.checkTimestep(num)) {
            if (all_checks.isSelected()) {
                FilesWorker filesWorker = new FilesWorker(num);
            }
        } else {
            System.out.println("wrong number");
        }
    }

    @FXML
    public void initialize() {
        System.out.println("second");
        addLstnrs();
    }

    private void addLstnrs() {
        edittext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue.matches("\\d*") && newValue.length() < 7) {
                    int value = Integer.parseInt(newValue);
                } else {
                    edittext.setText(oldValue);
                }
            }
        });
    }

}
