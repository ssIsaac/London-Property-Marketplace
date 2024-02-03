import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.text.Text;

/**
 * Popup for a selected listing
 *
 * @author Max Chang
 * @version 30.03.2022
 */
public class PopupList
{
    private VBox listLayout;
    public PopupList(VBox listLayout) 
    {
        this.listLayout = listLayout;
    }
    
    /**
     * Displays listing
     */
    public void display()
    {
        Stage popupList = new Stage();
        
        
        popupList.getIcons().add(new Image("images/air-bnb-logo.png"));
        // Does not allow clicking of other screens without closing first
        popupList.initModality(Modality.APPLICATION_MODAL);
        popupList.setTitle("Listings");
        popupList.setMinWidth(250);
        popupList.setMinHeight(100);
        popupList.setResizable(false);
        
        Button close = new Button("Close");
        close.setOnAction(s ->popupList.close());
        
        VBox layout = new VBox(20);
        layout.getChildren().add(close);
        layout.setAlignment(Pos.CENTER_RIGHT);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(listLayout);
        pane.setBottom(layout);
        pane.setMargin(pane, new Insets(4,4,4,4));
        pane.setPadding(new Insets(10,10,10,10));
        
        Scene scene = new Scene(pane);
        popupList.setScene(scene);
        popupList.showAndWait();
    }
}
