import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignUpForm{
    private Stage signUp;
    //@Override
    /**
     * 
     */   
    public void display(){
        signUp = new Stage();
        signUp.setTitle("Sign up here!!");
        
        GridPane gridPane = createSignUpForm();
        
        Scene scene = new Scene(gridPane, 600, 400);
        
        controller (gridPane);
        signUp.setScene(scene);
        signUp.show();
    }
    
    /**
     * 
     */
    private GridPane createSignUpForm()
        {
         GridPane gridpane= new GridPane();
         gridpane.setHgap(12);
         gridpane.setVgap(12);
         gridpane.setAlignment(Pos.CENTER);
         gridpane.setPadding(new Insets(30, 30, 30, 30));
         ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
         columnOneConstraints.setHalignment(HPos.RIGHT);
         ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
         columnTwoConstrains.setHgrow(Priority.ALWAYS);
    
         gridpane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        
         return gridpane;
    }
    
    /**
     * 
     */
    private void controller(GridPane gridPane) {
         // Add Header
        Label Label1 = new Label("THIS IS YOUR SIGNUP FORM:)");
        Label1.setFont(Font.font("Serif", FontWeight.BOLD, 22));
        gridPane.add(Label1, 0,0,2,1);
        GridPane.setHalignment(Label1, HPos.CENTER);
        GridPane.setMargin(Label1, new Insets(30, 0,30,0));
    
        Label nameLabel = new Label("Your Full Name:");
        gridPane.add(nameLabel, 0,1);
    
        TextField name = new TextField();
        name.setPrefHeight(50);
        gridPane.add(name, 1,1);
    
        Label Label2 = new Label("Your Email:");
        gridPane.add(Label2, 0, 2);
    
        TextField email = new TextField();
        email.setPrefHeight(50);
        gridPane.add(email, 1, 2);
    
        Label Label3 = new Label("Your Password : ");
        gridPane.add(Label3, 0, 3);
    
        PasswordField password = new PasswordField();
        password.setPrefHeight(50);
        gridPane.add(password, 1, 3);
         
        Label Label4 = new Label("Your Telephone Number:");
        gridPane.add(Label4, 0, 4);
         
        TextField phoneNumber = new TextField();
        phoneNumber.setPrefHeight(50);
        gridPane.add(phoneNumber, 1, 4);
    
        Button submitBtn = new Button("Submit");
        submitBtn.setDefaultButton(true);
        submitBtn.setPrefHeight(80);
        submitBtn.setPrefWidth(80);
        gridPane.add(submitBtn, 0, 6, 2, 2);
        GridPane.setHalignment(submitBtn, HPos.CENTER);
        GridPane.setMargin(submitBtn, new Insets(20, 0,30,0));
    
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
            public void handle(ActionEvent event) {
                if(name.getText().isEmpty()) {
                    displayAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please type in your full name");
                    return;
                }
                if(email.getText().isEmpty() | !email.getText().contains("@")) {
                    displayAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(password.getText().isEmpty()) {
                    displayAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }
                if(phoneNumber.getText().isEmpty()) {
                    displayAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your phone number");
                    return;
                }

                displayAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Well Done! You have just signed up!", "Welcome " + name.getText()+":)");
                System.out.println(name.getText()+"has successfully signed up!");
            }
        });
        }
        
    private void displayAlert(Alert.AlertType alertType, Window owner, String title, String message) {
          Alert alert = new Alert(alertType);
          alert.setTitle(title);
          alert.setHeaderText(null);
          alert.setContentText(message);
          alert.initOwner(owner);
          alert.show();
    }
  }
