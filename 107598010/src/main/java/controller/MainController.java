package controller;

import Factory.CourseFactory;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.CourseServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tanzhenyu on 2017/6/14.
 */
public class MainController implements Initializable {
    private CourseFactory cf = new CourseFactory();
    private CheckBox select;
    private CourseServices courseServices = new CourseServices();

    private ObservableList<Course> data
            = FXCollections.observableArrayList(courseServices.allCourse());
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, String> courseDescription;
    @FXML
    private TableColumn<Course, String> courseTarget;
    @FXML
    private TableColumn<Course, Integer> coursePrice;
    @FXML
    private TableColumn<Course, String> courseAttentionNote;
    @FXML
    private TableColumn<Course, String> courseNote;

    @FXML
    private void handleAddPerson() throws IOException {
        showPersonEditDialog(" ");
    }

    @FXML
    private void handleUpdatePerson() throws IOException {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1)
            showPersonEditDialog(courseTable.getItems().get(selectedIndex).getCourseName());
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            courseServices.deleteCourse(courseTable.getItems().get(selectedIndex).getCourseName());
            courseTable.getItems().remove(selectedIndex);
        }

    }

    public void updateUI() {
        data = FXCollections.observableArrayList(courseServices.allCourse());
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        courseTarget.setCellValueFactory(new PropertyValueFactory<>("courseTarget"));
        coursePrice.setCellValueFactory(new PropertyValueFactory<>("coursePrice"));
        courseAttentionNote.setCellValueFactory(new PropertyValueFactory<>("courseAttentionNote"));
        courseNote.setCellValueFactory(new PropertyValueFactory<>("courseNote"));
        courseTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateUI();
    }

    @FXML
    public boolean showPersonEditDialog(String ctemp) throws IOException {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PersonEditDialog.fxml"));
            // FileInputStream fileInputStream = new FileInputStream("controller/");
            Parent root = (Parent) loader.load();
            PersonEditDialogController controller = loader.getController();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            Scene scene = new Scene(root);
            dialogStage.setTitle("Edit Person");
            dialogStage.setScene(scene);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            // Set the person into the controller.

            controller.setDialogStage(dialogStage);
            controller.setPerson(ctemp);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            updateUI();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
