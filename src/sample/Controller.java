package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    DdHelper db = new DdHelper();

    @FXML
    private BorderPane mainPanel;
    @FXML
    private TableView<Bug> centerTableInfoView;
    @FXML
    private TableColumn<Bug, String> idValue;
    @FXML
    private TableColumn<Bug, String> dateEnteredValue;
    @FXML
    private TableColumn<Bug, String> projectValue;
    @FXML
    private TableColumn<Bug, String> versionValue;
    @FXML
    private TableColumn<Bug, String> companyValue;
    @FXML
    private TableColumn<Bug, String> lastUpdatedValue;
    @FXML
    private TableColumn<Bug, String> statusValue;
    @FXML
    private TableColumn<Bug, String> descriptionValue;
    @FXML
    private RadioButton radioAllBugs, radioFixed, radioUnfixed;

    @FXML
    public void addNewBug(){
        LocalDate date = LocalDate.now();
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Bug");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addBugDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e) {
            System.out.println("Could not load dialog: ");
            e.printStackTrace();
        }
        AddBugController abc = fxmlLoader.getController();
        abc.updateDateEnteredOnAddBug(date.toString());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            AddBugController addBugController = fxmlLoader.getController();
            addBugController.newBug();
            upDateTableView();
        }
    }

    @FXML
    public void updateBug() throws IOException {
        if(centerTableInfoView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Select A Row");
            alert.show();
        } else {
            int row = centerTableInfoView.getSelectionModel().selectedIndexProperty().get();
            Bug bug = centerTableInfoView.getItems().get(row);
            String id = bug.getId();
            String dateEntered = bug.getDateEntered();
            String project = bug.getProject();
            String company = bug.getProject();
            String version = bug.getVersion();
            String lastUpdate = bug.getLastUpdate();
            String status = bug.getStatus();
            String description = bug.getDescription();

            Dialog<ButtonType> updateDialogBox = new Dialog<>();
            updateDialogBox.initOwner(mainPanel.getScene().getWindow());
            updateDialogBox.setTitle("UPDATE RECORD");
            updateDialogBox.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateDialogBox.fxml"));
            try {
                updateDialogBox.getDialogPane().setContent(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateBugController update = loader.getController();
            update.display(id, dateEntered, project, company, version, lastUpdate, status, description);

            updateDialogBox.getDialogPane().getButtonTypes().add(ButtonType.OK);
            updateDialogBox.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = updateDialogBox.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                update.updateBugSandD();
                upDateTableView();
            }
        }
    }

    public void upDateTableView(){
        try {
            ObservableList<Bug> os = FXCollections.observableArrayList(db.getAllData());
            if(radioFixed.isSelected()){
                os = FXCollections.observableArrayList(db.getFixedData());
            }
            else if(radioUnfixed.isSelected()){
                os = FXCollections.observableArrayList(db.getUnfixedData());
            }
            idValue.setCellValueFactory(new PropertyValueFactory<>("id"));
            dateEnteredValue.setCellValueFactory(new PropertyValueFactory<>("dateEntered"));
            projectValue.setCellValueFactory(new PropertyValueFactory<>("project"));
            versionValue.setCellValueFactory(new PropertyValueFactory<>("version"));
            companyValue.setCellValueFactory(new PropertyValueFactory<>("company"));
            lastUpdatedValue.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
            statusValue.setCellValueFactory(new PropertyValueFactory<>("status"));
            descriptionValue.setCellValueFactory(new PropertyValueFactory<>("description"));
            centerTableInfoView.getColumns().clear();
            centerTableInfoView.getColumns().addAll(idValue,dateEnteredValue,projectValue,companyValue,versionValue,lastUpdatedValue,statusValue,descriptionValue);
            centerTableInfoView.setItems(os);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates data in TableView when All Bugs, Fixed, and UnFixed radio buttons are selected.
    public void radioButtonSelected(){
        upDateTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        upDateTableView();

    }
}
