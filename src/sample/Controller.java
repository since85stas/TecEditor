package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button btn;

    @FXML
    private void click(ActionEvent event) {
        btn.setText("You've clicked!");
        int num = 0;
        if (Utils.checkTimestep(num)) {
            FilesWorker filesWorker = new FilesWorker(num);
        } else {
            System.out.println("wrong number");
        }
    }

}
