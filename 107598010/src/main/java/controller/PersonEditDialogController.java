package controller;

import entity.Course;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.CourseServices;

import java.awt.*;
import java.util.regex.Pattern;


/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {

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
    private CourseServices courseServices =new CourseServices();
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public static boolean isNumeric (String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    @FXML
    private void initialize() {
        coursePrice.setOnKeyReleased(new EventHandler<KeyEvent> () {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(coursePrice.getText());
                if (!isNumeric(coursePrice.getText()))
                {
                    final Alert alert2 = new Alert(Alert.AlertType.INFORMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
                    alert2.setTitle("輸入錯誤"); //設定對話框視窗的標題列文字
                    alert2.setHeaderText("請輸入數字"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
                    alert2.setContentText("請按下「確定」按鈕。並重新填寫"); //設定對話框的訊息文字
                    alert2.showAndWait();
                }
            }

     });
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setPerson(String courseName) {

        this.courseName.setText(courseName);
        Course courseTemp =courseServices.selectCourse(courseName);
        if(courseTemp!=null)
        {
            this.courseName.setDisable(true);
            courseDescription.setText(courseTemp.getCourseDescription());
            courseTarget.setText(courseTemp.getCourseTarget());
            coursePrice.setText(Integer.toString(courseTemp.getCoursePrice()));
            courseAttentionNote.setText(courseTemp.getCourseAttentionNote());
            courseNote.setText(courseTemp.getCourseNote());
        }

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        course =new Course();

        System.out.println(courseName.getText());
            course.setCourseName(courseName.getText());
            course.setCourseDescription(courseDescription.getText());
            course.setCourseTarget(courseTarget.getText());
            course.setCoursePrice(Integer.parseInt(coursePrice.getText().toString()));
            course.setCourseAttentionNote(courseAttentionNote.getText());
            course.setCourseNote(courseNote.getText());
            okClicked = true;
        if(courseServices.selectCourse(courseName.getText())!=null)
        {
            courseServices.updateCourse(course);
        }else
        {
            courseServices.addCourse(course);
        }
        dialogStage.close();

    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (courseName.getText() == null || courseName.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (courseDescription.getText() == null || courseDescription.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (courseTarget.getText() == null || courseTarget.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (coursePrice.getText() == null || coursePrice.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(coursePrice.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (courseAttentionNote.getText() == null || courseAttentionNote.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (courseNote.getText() == null || courseNote.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.

            return false;
        }
    }
}
