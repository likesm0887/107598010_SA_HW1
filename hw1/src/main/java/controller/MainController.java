package controller;

import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.CourseServices;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    private Button deleteButton ;
    @FXML
    private Button updateButton;

    @FXML
    private void handleAddCourse() throws IOException {
        showCourseEditDialog(" ");
    }

    //Update Course information
    @FXML
    private void handleUpdateCourse() throws IOException {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1)
            showCourseEditDialog(courseTable.getItems().get(selectedIndex).getCourseName());
    }

    //Delete Course
    @FXML
    private void handleDeleteCourse() {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            courseServices.deleteCourse(courseTable.getItems().get(selectedIndex).getCourseName());
            courseTable.getItems().remove(selectedIndex);
        }
    }

    private void updateUI() {
        data = FXCollections.observableArrayList(courseServices.allCourse());
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        courseTarget.setCellValueFactory(new PropertyValueFactory<>("courseTarget"));
        coursePrice.setCellValueFactory(new PropertyValueFactory<>("coursePrice"));
        courseAttentionNote.setCellValueFactory(new PropertyValueFactory<>("courseAttentionNote"));
        courseNote.setCellValueFactory(new PropertyValueFactory<>("courseNote"));
        courseTable.setItems(data);
        deleteButton.setDisable(true);
        updateButton.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //listener selection if selected then updateButton and deleteButton are enable
        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    updateButton.setDisable(false);
                    deleteButton.setDisable(false);
                }
            );
        updateUI();
    }

    //create Edit Dialog
    @FXML
    public boolean showCourseEditDialog(String ctemp) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SetCourseDialog.fxml"));
            Parent root = (Parent) loader.load();
            CourseEditorController controller = loader.getController();
            Stage dialogStage = new Stage();
            Scene scene = new Scene(root);
            dialogStage.setTitle("Edit Course");
            dialogStage.setScene(scene);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
            controller.setDialogStage(dialogStage);
            controller.setPerson(ctemp);
            dialogStage.showAndWait();
            updateUI();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
