import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.image.*;
import java.io.*;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import javafx.geometry.Insets;

/**
 * This window is designed to hold a number of different panels. User can
 * navigate through different panels to get information and statistics of
 * the Airbnb they are looking for. The window contains two drop-down boxes
 * which allows the user to select their desired price range of Airbnb and
 * two buttons to move through the panels.
 *
 * @author Shih-Hung Lin
 * @version 30.3.2022
 */
public class AirbnbViewer extends Application
{
    private Stage stage;
    private ChoiceBox<String> choiceBox1;
    private ChoiceBox<String> choiceBox2;
    private Button nextButton;
    private Button prevButton;
    private ImageView imgView;
    private int panelNum = 1;
    private Pane mapPanel;
    private Pane listMapPanel;
    private AirbnbDataLoader loader; // load all data from csv file.
    private MapEngine mapEngine;
    private MapGUI map;
    private StackPane mainMap;
    private ArrayList<AirbnbListing> allListings, pricedListings, highStreets;
    private ShoppingPageMain shoppingPage;
    private AnchorPane shoppingPagePanel;

    /**
     * Constructor for objects of class AirbnbViewer
     */

    public AirbnbViewer()
    {
        loader = new AirbnbDataLoader();
        allListings = new ArrayList<>();
        pricedListings= new ArrayList<>();
        highStreets = new ArrayList<>();
        loadAllListings();
        mapEngine = new MapEngine(allListings, pricedListings, highStreets);
        setPriceQuota(0,100);
    }

    /**
     * Build the main application window GUI for the AirbnbViewer.
     * @param  stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) throws Exception,IOException
    {
        this.stage = stage;
        VBox root = new VBox();
        StackPane contentPane = new StackPane();

        map = new MapGUI(mapEngine);
        mapPanel = map.buildMapPanel();
        mapPanel.setVisible(false);
        
        shoppingPage = new ShoppingPageMain();
        shoppingPagePanel = shoppingPage.buildShoppingPage();
        shoppingPagePanel.setPadding(new Insets(20,20,20,20));
        shoppingPagePanel.setVisible(false);

        Label fromLabel = new Label("From:");
        Label toLabel = new Label("To:");

        nextButton = new Button(">");
        prevButton = new Button("<");
        nextButton.setDisable(true);
        prevButton.setDisable(true);
        nextButton.setOnAction(this::nextButtonClick);
        prevButton.setOnAction(this::prevButtonClick);

        Image welcomeImg = new Image("London.jpg");
        imgView = new ImageView(welcomeImg);
        imgView.setFitHeight(600);
        imgView.setPreserveRatio(true);

        choiceBox1 = new ChoiceBox();
        choiceBox2 = new ChoiceBox();
        choiceBox1.getItems().addAll("£10", "£20", "£50","£100", "£500",
        "£1000", "£5000", "£10000");
        choiceBox2.getItems().addAll("£10", "£20", "£50","£100", "£500",
        "£1000", "£5000", "£10000");
        choiceBox1.setOnAction(this::checkPriceRange);
        choiceBox2.setOnAction(this::checkPriceRange);

        HBox hboxTop = new HBox();
        hboxTop.setId("top");
        Region filler1 = new Region();
        HBox.setHgrow(filler1, Priority.ALWAYS);
        hboxTop.getChildren().addAll(filler1, fromLabel, choiceBox1, toLabel, choiceBox2);

        Region filler2 = new Region();
        HBox hboxBot = new HBox();
        hboxBot.setId("bottom");
        HBox.setHgrow(filler2, Priority.ALWAYS);
        hboxBot.getChildren().addAll(prevButton, filler2, nextButton);

        contentPane.getChildren().addAll(imgView, mapPanel, shoppingPagePanel);
        root.getChildren().addAll(hboxTop, hboxBot, contentPane);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("mystyle.css");

        stage.setTitle("London Airbnb Viewer");
        stage.getIcons().add(new Image("images/air-bnb-logo.png"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This will be executed when the nextButton is clicked.
     * Shows the next panel by making it visible.
     */
    private void nextButtonClick(ActionEvent event)
    {
        if(panelNum % 3 == 0){
            shoppingPagePanel.setVisible(false);
            imgView.setVisible(true);
        }
        else if(panelNum % 3 == 1){
            imgView.setVisible(false);
            mapPanel.setVisible(true);
        }
        else if(panelNum % 3 == 2){
            mapPanel.setVisible(false);
            shoppingPagePanel.setVisible(true);
        }
        panelNum++;
    }

    /**
     * This will be executed when the prevButton is clicked.
     * Shows the previous panel by making it visible.
     */
    private void prevButtonClick(ActionEvent event)
    {
        if(panelNum == -1){
                panelNum += 3;
        }
        if(panelNum % 3 == 0){
            shoppingPagePanel.setVisible(false);
            mapPanel.setVisible(true);
        }
        else if(panelNum % 3 == 1){
            imgView.setVisible(false);
            shoppingPagePanel.setVisible(true);
        }
        else if(panelNum % 3 == 2){
            mapPanel.setVisible(false);
            imgView.setVisible(true);
        }
        panelNum--;
    }

    /**
     * Alerts the user that the price range they selected is invalid.
     */
    private void showInvalidPriceRange()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Price Range Error");
        alert.setHeaderText(null);
        alert.setContentText("The price range you selected is invalid, please select a new range");
        alert.showAndWait();
    }

    /**
     * Check if the price range is valid, enable the buttons if it is.
     */
    private void checkPriceRange(ActionEvent event)
    {
        int fromPrice = getPriceNum(choiceBox1.getValue());
        int toPrice = getPriceNum(choiceBox2.getValue());
        if(fromPrice > toPrice){
            showInvalidPriceRange();
            nextButton.setDisable(true);
            prevButton.setDisable(true);
        }
        else{
            nextButton.setDisable(false);
            prevButton.setDisable(false);
            setPriceQuota(fromPrice, toPrice);
            map.refreshPriceRange();
        }
    }

    /**
     * Converts the price in string to int.
     * @param str the prcie string to be converted to int
     * @return the Integer value of the price range selected
     */
    private int getPriceNum(String str){
        return Integer.parseInt(str.substring(1,str.length()));
    }

    /**
     * Loads all listings from the CSV file and saves it to local variable
     * allListings.
     */
    public void loadAllListings()
    {
        allListings = loader.load();
        highStreets = loader.loadHighStreet();
    }

    /**
     * Find and display a list of all the properties within the price
     * range that the user selected.
     */
    public void setPriceQuota(int min, int max)
    {
        pricedListings.clear(); //clear list from previous quota
        for (AirbnbListing listing : allListings) {
            if (listing.getPrice() <= max && listing.getPrice() >= min) { // if the price is within the priced quota
                pricedListings.add(listing);
            }
        }
        mapEngine.setPricedListings(pricedListings);
    }

    public ArrayList<AirbnbListing> getPricedListings()
    {
        return pricedListings;
    }

    public ArrayList<AirbnbListing> getAllListings()
    {
        return allListings;
    }

    public ArrayList<AirbnbListing> getHighStreet()
    {
        return highStreets;
    }
}
