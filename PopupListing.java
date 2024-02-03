import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.text.Text;

/**
 * Popup for ListView of all listings with sorting functionalities.
 */
public class PopupListing
{
    private Text detailedText;
    public PopupListing(Text detailedText) 
    {
        this.detailedText = detailedText;
    }
    
    /**
     * Displays listings
     */
    public void display()
    {
        Stage popupBox = new Stage();
        
        popupBox.getIcons().add(new Image("images/air-bnb-logo.png"));
        // Does not allow clicking of other screens without closing first
        popupBox.initModality(Modality.APPLICATION_MODAL);
        popupBox.setTitle("Current Listing");
        popupBox.setMinWidth(250);
        popupBox.setMinHeight(100);
        popupBox.setResizable(false);
        
        Button close = new Button("Close");
        close.setOnAction(s ->popupBox.close());
        
        VBox layout = new VBox(20);
        layout.getChildren().add(close);
        layout.setAlignment(Pos.CENTER_RIGHT);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(detailedText);
        pane.setBottom(layout);
        pane.setMargin(pane, new Insets(4,4,4,4));
        pane.setPadding(new Insets(13,13,13,13));
        
        
        Scene scene = new Scene(pane);
        popupBox.setScene(scene);
        popupBox.showAndWait();
    }
}
