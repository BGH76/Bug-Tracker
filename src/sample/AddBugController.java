package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddBugController {

    @FXML
    private TextField projectField;
    @FXML
    private TextField versionField;
    @FXML
    private TextField companyField;
    @FXML
    private TextField statusField;
    @FXML
    private TextArea descriptionField;

    DdHelper db = new DdHelper();

    public Bug newBug() {
        FXMLLoader fxmlLoader = new FXMLLoader();

        String project = projectField.getText();
        String version = versionField.getText();
        String company = companyField.getText();
        String status = statusField.getText();
        String description = descriptionField.getText();

        db.addNewBug(project,version,company,status,description);


//        Bug newBug = new Bug(project, version, company, status, description);
//        return newBug;
        return null;
    }

}
