import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.FileInputStream;
import java.io.File;
import javafx.scene.layout.Pane;


/**
 *This is the main page that introduce the essential products that will be sell in the airbnb
 * @author 
 * @03/30/2022
 */
public class ShoppingPageMain
{
    @FXML
    private Button btn1;
    private AnchorPane root;
    
    public AnchorPane buildShoppingPage()throws Exception
    {
        URL url = getClass().getResource("products.fxml");
        AnchorPane root = FXMLLoader.load(url);
        
        return root;
    }
    
    public void buttonPressed(ActionEvent actionEvent)
    {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.display();
    }
}