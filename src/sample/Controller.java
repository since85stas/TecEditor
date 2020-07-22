package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Driver;

public class Controller {

    @FXML
    private Button btn;

    @FXML
    private TextField edittext;

    @FXML
    private CheckBox all_check;

    @FXML
    private TextArea out_area;

    @FXML
    private void click(ActionEvent event) {
//        btn.setText("You've clicked!");
        out_area.clear();
//        int num = Integer.parseInt(edittext.getText());
        if (Utils.checkTimestep(edittext.getText())) {
            if (all_check.isSelected()) {
                FilesWorker filesWorker = new FilesWorker(Integer.parseInt(edittext.getText()), this);
            }
        } else {
            out_area.appendText("Wrong number");
            System.out.println("wrong number");
        }
    }

    protected void addText(String text) {
        out_area.appendText(text);
    }

    @FXML
    public void initialize() {
        System.out.println("second");
        addLstnrs();

        edittext.requestFocus();
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
