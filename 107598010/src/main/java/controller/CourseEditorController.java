package controller;

import entity.Course;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.CourseServices;

import java.util.regex.Pattern;

public class CourseEditorController {

    @FXML
    private TextField courseName;
    @FXML
    private TextField courseDescription;
    @FXML
    private TextField courseTarget;
    @FXML
    private TextField coursePrice;
    @FXML
    private TextField courseAttentionNote;
    @FXML
    private TextField courseNote;
    private Stage dialogStage;
    private Course course;
    private boolean okClicked = false;
    private CourseServices courseServices = new CourseServices();
    private String selectButton;


    //judge input is a Number
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    @FXML
    private void initialize() {
        coursePrice.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(coursePrice.getText());
                if (!isNumeric(coursePrice.getText())) {
                    setAlert("請輸入數字", "重新輸入");
                    coursePrice.clear();
                }
            }
        });
    }

    //create Alert Dialog
    private void setAlert(String title, String header) {
        final Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle(title);
        alert2.setHeaderText(header);
        alert2.setContentText("請按下「確定」按鈕。並重新填寫");
        alert2.showAndWait();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //set text to list
    public void setPerson(String courseName) {
        this.courseName.setText(courseName);
        Course courseTemp = courseServices.selectCourse(courseName);
        if (courseTemp != null) {
            selectButton = "Update";
            this.courseName.setDisable(true);
            courseDescription.setText(courseTemp.getCourseDescription());
            courseTarget.setText(courseTemp.getCourseTarget());
            coursePrice.setText(Integer.toString(courseTemp.getCoursePrice()));
            courseAttentionNote.setText(courseTemp.getCourseAttentionNote());
            courseNote.setText(courseTemp.getCourseNote());
        } else {
            selectButton = "Add";
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    private void setText() {
        course.setCourseName(courseName.getText());
        course.setCourseDescription(courseDescription.getText());
        course.setCourseTarget(courseTarget.getText());
        if (coursePrice.getText().equals("")) {
            coursePrice.setText("0");
        }
        course.setCoursePrice(Integer.parseInt(coursePrice.getText().toString()));
        course.setCourseAttentionNote(courseAttentionNote.getText());
        course.setCourseNote(courseNote.getText());
    }

    @FXML
    private void handleOk() {
        course = new Course();
        setText();
        if (selectButton.equals("Add")) {
            if (courseName.getText().equals(" ")) {
                setAlert("課程名稱空白", "重新輸入");
            } else if (courseServices.selectCourse(courseName.getText()) != null) {
                setAlert("重複課程名稱", "請查明後再輸入");
                courseName.clear();
            } else {
                courseServices.addCourse(course);
                dialogStage.close();
            }
        } else if (courseServices.selectCourse(courseName.getText()) != null) {
            courseServices.updateCourse(course);
            dialogStage.close();
        }
        okClicked = true;
    }

    /// Cancel
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


}
