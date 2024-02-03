   import javafx.application.Application;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.transform.*;
import javafx.geometry.Insets;
import java.text.DecimalFormat;
import javafx.scene.input.*;

/**
 * A class that generates the Map and statistics GUI of London
 * and all its boroughs.
 *
 * @author Max Chang
 * @version 30.03.2022
 */
public class MapGUI  
{
    // Loads all underlying engines
    private MapEngine mapEngine;
    private Panel4Engine distanceEngine;
    private GUIBuild statsEngine;
    
    // AirbnbListings that gets updated for each button press
    private List<AirbnbListing> listings;
    private ListView<Text> guiListings;
    
    // Label that displays the currently selected borough's name
    private Label boroughName;
    // List of displayed text on the GUI
    private ArrayList<Text> textList;
    private ArrayList<Text> detailedTextList;
    // List that constantly reads for update
    private ObservableList<Text> observableTextList;
    
    // List of sorting options and its order (Asc, Dsc)
    private Button toggleAsc;
    private ChoiceBox<String> sortBy;
    
    // GUI that displays all statistics
    private StatisticsGUI statsGUI;
    
    // Popup Boxes for when a borough is selected
    private PopupList listingsBox;
    private PopupListing singleListingBox;
    
    // Storage for all images of boroughs and their corresponding buttons
    private ArrayList<ImageView> boroughImages;
    private ArrayList<Button> mapButtons;
    
    // Panes that layout the Map, its boroughs and its buttons
    private StackPane map;
    private AnchorPane boroughsImg;
    private AnchorPane fullMap;
    private AnchorPane buttonsPane;
    
    // Main layout for the Map and StatsGUI
    BorderPane mapLayout = new BorderPane();
    
    // Base image for a full map
    private Image mapImg = new Image("images/map-pieces/full-map.png");                         
    private ImageView fullMapImgView = new ImageView(mapImg);
    
    // How to sort lists by default
    private static final String DEFAULT_SORT = "Price";
    
    // Sets display for the decimal places to show
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    
    // Initialise ImageView objects to give all map pieces unique names
    private ImageView enfieldMap = new ImageView();
    private ImageView haringeyMap = new ImageView();
    private ImageView camdenMap = new ImageView();
    private ImageView islingtonMap = new ImageView();
    private ImageView hackneyMap = new ImageView();
    private ImageView walthamMap = new ImageView();
    private ImageView redbridgeMap = new ImageView();
    private ImageView haveringMap = new ImageView();
    private ImageView barkingMap = new ImageView();
    private ImageView newhamMap = new ImageView();
    private ImageView towerHamletsMap = new ImageView();
    private ImageView coLondonMap = new ImageView();
    private ImageView westminsterMap = new ImageView();
    private ImageView brentMap = new ImageView();
    private ImageView harrowMap = new ImageView();
    private ImageView hillingdonMap = new ImageView();
    private ImageView ealingMap = new ImageView();
    private ImageView hounslowMap = new ImageView();
    private ImageView kensingtonMap = new ImageView();
    private ImageView hammersmithMap = new ImageView();
    private ImageView richmondMap = new ImageView();
    private ImageView kingstonMap = new ImageView();
    private ImageView wandsworthMap = new ImageView();
    private ImageView lambethMap = new ImageView();
    private ImageView southwarkMap = new ImageView();
    private ImageView lewishamMap = new ImageView();
    private ImageView greenwichMap = new ImageView();
    private ImageView bexleyMap = new ImageView();
    private ImageView bromleyMap = new ImageView();
    private ImageView suttonMap = new ImageView();
    private ImageView mertonMap = new ImageView();
    private ImageView barnetMap = new ImageView();
    private ImageView croydonMap = new ImageView();
    
    // Initializes Button objects to give unique names to all buttons
    private Button enfieldButton = new Button();
    private Button haringeyButton = new Button();
    private Button camdenButton = new Button();
    private Button islingtonButton = new Button();
    private Button hackneyButton = new Button();    
    private Button walthamButton = new Button();
    private Button redbridgeButton = new Button();
    private Button haveringButton = new Button();
    private Button barkingButton = new Button();
    private Button newhamButton = new Button();
    private Button towerHamletsButton = new Button();
    private Button coLondonButton = new Button();
    private Button westminsterButton = new Button();
    private Button brentButton = new Button();
    private Button harrowButton = new Button();
    private Button hillingdonButton = new Button();
    private Button ealingButton = new Button();
    private Button hounslowButton = new Button();
    private Button kensingtonButton = new Button();
    private Button hammersmithButton = new Button();
    private Button richmondButton = new Button();
    private Button kingstonButton = new Button();
    private Button wandsworthButton = new Button();
    private Button lambethButton = new Button();
    private Button southwarkButton = new Button();
    private Button lewishamButton = new Button();
    private Button greenwichButton = new Button();
    private Button bexleyButton = new Button();
    private Button bromleyButton = new Button();
    private Button suttonButton = new Button();
    private Button mertonButton = new Button();
    private Button barnetButton = new Button();
    private Button croydonButton = new Button();
    
    public MapGUI(MapEngine mapEngine) {
        try
        {
            this.mapEngine = mapEngine;
            distanceEngine = new Panel4Engine();
            textList = new ArrayList<>();
            detailedTextList = new ArrayList<>();
            sortBy = new ChoiceBox<>();
            boroughImages = new ArrayList<>();
            map = new StackPane();
            mapButtons = new ArrayList<>();
            listings = new ArrayList<>();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public BorderPane buildMapPanel() throws Exception
    {
        // Initializes stats engine
        statsEngine = new GUIBuild(mapEngine);
        statsGUI = new StatisticsGUI(statsEngine);
        
        // Generates the Map
        generateMap();
        
        // Generates Stats GUI
        HBox statsDisplay = statsGUI.buildStatsGUI();
        
        Label mapOfLondon = new Label("Map of London");
        
        // Label that stores name of currently selected borough
        boroughName = new Label();
        
        // Dropdown Menu to choose what parameters to sort by
        Text sort = new Text("Sort By: ");
        sortBy.getItems().addAll("Price", "Host Name", "Reviews");
        sortBy.setValue(DEFAULT_SORT);

        /*
         * Detects which parameter the dropdown menu is selected
         * and refreshes list accordingly
         */
        sortBy.getSelectionModel()
              .selectedItemProperty()
              .addListener((v, oldSort, newSort) -> refreshSortList(newSort));
        
        /*
         * Detects which listing is selected and creates
         * pop up that shows the details of the property
         */
        guiListings = new ListView<>();
        guiListings.getSelectionModel()
                   .selectedIndexProperty()
                   .addListener((v, oldIndex, newIndex) -> displayDetailedText(newIndex.intValue()));
        
        // Load image for toggleAsc Button
        Image toggleAscImg = new Image("images/arrow.png");
        ImageView togImgView = new ImageView(toggleAscImg);
        togImgView.setFitHeight(20);
        togImgView.setPreserveRatio(true);
                
        toggleAsc = new Button();
        toggleAsc.setGraphic(togImgView);
        toggleAsc.setPrefSize(20,20);
        toggleAsc.setOnAction(e -> 
        {
            // Rotates images to point arrow in other direction
            togImgView.setRotate(togImgView.getRotate() + 180);

            Collections.reverse(listings);
            refreshListings();
        }
        );
        
        // Layouts the display lists and other elements
        VBox listLayout = new VBox(20);
        listLayout.getChildren().addAll(boroughName, sort, sortBy,
                                         toggleAsc, guiListings);
        
        // Shows listings in new window
        listingsBox = new PopupList(listLayout);
        listLayout.setMargin(listLayout, new Insets(12,12,12,12));
        
        // Sets elements into BorderPane
        mapLayout.setTop(statsDisplay);
        mapLayout.setCenter(map);
        mapLayout.setBottom(mapOfLondon);
        mapLayout.setPadding(new Insets(10));
        mapLayout.setMinSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
        mapLayout.setMaxSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
        
        return mapLayout;    
    }
    
    /**
     * Refreshes all the GUI Listings to reflect the changes
     * made to the underlying list of Airbnb listings.
     */
    public void refreshListings()
    {
        // Clear list from previous selection
        textList.clear();
        detailedTextList.clear();
        for (AirbnbListing listing : listings) {
            // Generates the distance between a listing and its borough's high street
            double distance = 
            distanceEngine
            .getDistance(listing, mapEngine.getHighStreetListing(listing.getNeighbourhood()));
            
            String leadingText = (listing.getHost_name() + "\n\n£" 
                + listing.getPrice() + " per Night\n\n" +
                "Minimum: " + listing.getMinimumNights() + " Night(s)\n"
                + listing.getNumberOfReviews() + " Reviews");
            
            String additionalText = (leadingText + "\n" +listing.getName() + "\nRoom Type: " + listing.getRoom_type() +
               "\nDistance from " + listing.getNeighbourhood() + " High Street: "+ DF.format(distance) + " km");
            
            Text listingText = new Text();
            listingText.setText(leadingText);
            
            Text detailedText = new Text();
            detailedText.setText(additionalText);
            
            textList.add(listingText);
            detailedTextList.add(detailedText);
        }
        // Tells the ListView to update new changes
        observableTextList = FXCollections.observableList(textList);
        guiListings.setItems(observableTextList);
    }
    
    /**
     * Displays the selected listing in a new popup window
     * to give a more detailed view of the property.
     * @param index The index of the selected listing
     */
    public void displayDetailedText(int index)
    {
        singleListingBox = new PopupListing(detailedTextList.get(index));
        singleListingBox.display();
    }
    
    /**
     * Refreshes the list according to the newly set price list
     */
    public void refreshPriceRange()
    {
        // Get the string of the current neighbourhood to preserve when reordering.
        String currentNeighbourhood = boroughName.getText();
        // Refreshes listings to reflect price change
        listings = mapEngine.propertiesInBoroughList(currentNeighbourhood);
        // Resort by default parameters
        refreshSortList(DEFAULT_SORT);
        sortBy.setValue(DEFAULT_SORT);
    }
    
    /**
     * Refresh the list to sort by a different order
     * @param sortByParam the parameter to sortBy
     */
    public void refreshSortList(String sortByParam)
    {
        try {
            listings = mapEngine.mergeSortAsc(listings, sortByParam);
            refreshListings();
        }
        catch (NullPointerException e) {
            System.out.println("Failure! Something went wrong");
            e.printStackTrace();
        }
    }
    
    /**
     * Refreshes the ascending/descending order of list
     */
    public void switchAsc(ActionEvent event)
    {
        Collections.reverse(listings);
        refreshListings();
    }
    
    /**
     * Generates ImageView and Button for each unique borough
     */
    private void generateMapButtons()
    {
        for (AirbnbListing highStreet : mapEngine.getHighStreet()) {
            Button mapButton = new Button();
            // Gets the image file with corresponding borough name
            Image pieceImg = new Image("images/map-pieces/" 
                + highStreet.getNeighbourhood() + ".png");
            
            // Sets new colour for borough based on number of listings
            pieceImg = setBoroughColour(pieceImg, 
            mapEngine.boroughListCount(highStreet.getNeighbourhood()));
            ImageView pieceImgView = new ImageView(pieceImg);
            boroughImages.add(pieceImgView);
            
            // Set Button text, styling and action on press
            mapButton.setText(highStreet.getNeighbourhood());
            mapButton.setPadding(new Insets(6,6,6,6));
            mapButton.setStyle("-fx-font-size:10; -fx-wrap-text: true;");
            mapButton.setOnAction(e -> 
            {
                listings = mapEngine.propertiesInBoroughList(highStreet.getNeighbourhood());


                statsEngine.setBoroughName(highStreet.getNeighbourhood());

                statsEngine.setStatistics1();
                statsEngine.setStatistics2();
                statsEngine.setStatistics3();
                statsEngine.setStatistics4();

                statsGUI.updateAll();
                

                boroughName.setText(highStreet.getNeighbourhood());
                sortBy.setValue(DEFAULT_SORT);
                refreshSortList(DEFAULT_SORT);
                listingsBox.display();
            }
            );
            
            mapButtons.add(mapButton);
        }
    }
    
    /**
     * Generates everything needed for the Map
     */
    public void generateMap()
    {
        generateMapButtons();
        assignboroughVariables();
        layOut();
    }
    
    /**
     * Assign a unique variable to all ImageView and Buttons.
     * Needed for referencing each borough pieces and its button
     * when setting it on AnchorPane.
     */
    private void assignboroughVariables()
    {
        enfieldButton = mapButtons.get(0);
        haringeyButton = mapButtons.get(1);
        camdenButton = mapButtons.get(2);
        islingtonButton = mapButtons.get(3);
        hackneyButton = mapButtons.get(4);
        walthamButton = mapButtons.get(5);
        redbridgeButton = mapButtons.get(6);
        haveringButton = mapButtons.get(7);
        barkingButton = mapButtons.get(8);
        newhamButton = mapButtons.get(9);
        towerHamletsButton = mapButtons.get(10);
        coLondonButton = mapButtons.get(11);
        westminsterButton = mapButtons.get(12);
        brentButton = mapButtons.get(13);
        harrowButton = mapButtons.get(14);
        hillingdonButton = mapButtons.get(15);
        ealingButton = mapButtons.get(16);
        hounslowButton = mapButtons.get(17);
        kensingtonButton = mapButtons.get(18);
        hammersmithButton = mapButtons.get(19);
        richmondButton = mapButtons.get(20);
        kingstonButton = mapButtons.get(21);
        wandsworthButton = mapButtons.get(22);
        lambethButton = mapButtons.get(23);
        southwarkButton = mapButtons.get(24);
        lewishamButton = mapButtons.get(25);
        greenwichButton = mapButtons.get(26);
        bexleyButton = mapButtons.get(27);
        bromleyButton = mapButtons.get(28);
        suttonButton = mapButtons.get(29);
        mertonButton = mapButtons.get(30);
        barnetButton = mapButtons.get(31);
        croydonButton = mapButtons.get(32);
        
        enfieldMap = boroughImages.get(0);
        haringeyMap = boroughImages.get(1);
        camdenMap = boroughImages.get(2);
        islingtonMap = boroughImages.get(3);
        hackneyMap = boroughImages.get(4);
        walthamMap = boroughImages.get(5);
        redbridgeMap = boroughImages.get(6);
        haveringMap = boroughImages.get(7);
        barkingMap = boroughImages.get(8);
        newhamMap = boroughImages.get(9);
        towerHamletsMap = boroughImages.get(10);
        coLondonMap = boroughImages.get(11);
        westminsterMap = boroughImages.get(12);
        brentMap = boroughImages.get(13);
        harrowMap = boroughImages.get(14);
        hillingdonMap = boroughImages.get(15);
        ealingMap = boroughImages.get(16);
        hounslowMap = boroughImages.get(17);
        kensingtonMap = boroughImages.get(18);
        hammersmithMap = boroughImages.get(19);
        richmondMap = boroughImages.get(20);
        kingstonMap = boroughImages.get(21);
        wandsworthMap = boroughImages.get(22);
        lambethMap = boroughImages.get(23);
        southwarkMap = boroughImages.get(24);
        lewishamMap = boroughImages.get(25);
        greenwichMap = boroughImages.get(26);
        bexleyMap = boroughImages.get(27);
        bromleyMap = boroughImages.get(28);
        suttonMap = boroughImages.get(29);
        mertonMap = boroughImages.get(30);
        barnetMap = boroughImages.get(31);
        croydonMap = boroughImages.get(32);
    }
    
    /**
     * Arranges layout for all buttons and borough images.
     */
    public void layOut()
    {
        AnchorPane boroughsImg = new AnchorPane();
        AnchorPane fullMap = new AnchorPane(fullMapImgView);
        AnchorPane buttonsPane = new AnchorPane();
        
        boroughsImg.setPrefSize(600,600);
        fullMap.setPrefSize(600,600);
        buttonsPane.setPrefSize(600,600);
        
        // Adds all map pieces to AnchorPane
        boroughsImg.getChildren().addAll(enfieldMap, haringeyMap, camdenMap,islingtonMap,
        hackneyMap, walthamMap, redbridgeMap, haveringMap, barkingMap,
        newhamMap, towerHamletsMap, coLondonMap, westminsterMap, brentMap,
        harrowMap, hillingdonMap, ealingMap, hounslowMap, kensingtonMap,
        hammersmithMap, richmondMap, kingstonMap, wandsworthMap, lambethMap,
        southwarkMap, lewishamMap, greenwichMap, bexleyMap, bromleyMap, 
        suttonMap, mertonMap, barnetMap, croydonMap);
        
        // Adds all buttons to AnchorPane
        buttonsPane.getChildren().addAll(enfieldButton, haringeyButton, camdenButton,
        islingtonButton, hackneyButton, walthamButton,
        redbridgeButton, haveringButton, barkingButton, newhamButton,
        towerHamletsButton, coLondonButton, westminsterButton, brentButton, harrowButton,
        hillingdonButton, ealingButton, hounslowButton, kensingtonButton, hammersmithButton,
        richmondButton, kingstonButton, wandsworthButton, lambethButton,
        southwarkButton, lewishamButton, greenwichButton, bexleyButton,
        bromleyButton, suttonButton, mertonButton, barnetButton, croydonButton);
        
        // Set coordinate for map pieces
        AnchorPane.setTopAnchor(bexleyMap, 309.0); //Y
        AnchorPane.setLeftAnchor(bexleyMap, 484.0); //X
        
        AnchorPane.setTopAnchor(barnetMap, 116.0); //Y
        AnchorPane.setLeftAnchor(barnetMap, 178.0); //X
        
        AnchorPane.setTopAnchor(barkingMap, 182.0); //Y
        AnchorPane.setLeftAnchor(barkingMap, 465.0); //X
        
        AnchorPane.setTopAnchor(brentMap, 182.0); //Y
        AnchorPane.setLeftAnchor(brentMap, 144.0); //X
        
        AnchorPane.setTopAnchor(bromleyMap, 436.0); //Y
        AnchorPane.setLeftAnchor(bromleyMap, 399.0); //X
        
        AnchorPane.setTopAnchor(camdenMap, 207.0); //Y
        AnchorPane.setLeftAnchor(camdenMap, 228.0); //X
        
        AnchorPane.setTopAnchor(coLondonMap, 243.0); //Y
        AnchorPane.setLeftAnchor(coLondonMap, 283.0); //X
        
        AnchorPane.setTopAnchor(croydonMap, 455.0); //Y
        AnchorPane.setLeftAnchor(croydonMap, 304.0); //X
        
        AnchorPane.setTopAnchor(ealingMap, 235.0); //Y
        AnchorPane.setLeftAnchor(ealingMap, 88.0); //X
        
        AnchorPane.setTopAnchor(enfieldMap, 65.0); //Y
        AnchorPane.setLeftAnchor(enfieldMap, 277.0); //X
        
        AnchorPane.setTopAnchor(greenwichMap, 300.0); //Y
        AnchorPane.setLeftAnchor(greenwichMap, 404.0); //X
        
        AnchorPane.setTopAnchor(hackneyMap, 195.0); //Y
        AnchorPane.setLeftAnchor(hackneyMap, 310.0); //X
        
        AnchorPane.setTopAnchor(hammersmithMap, 269.0); //Y
        AnchorPane.setLeftAnchor(hammersmithMap, 184.0); //X
        
        AnchorPane.setTopAnchor(haringeyMap, 147.0); //Y
        AnchorPane.setLeftAnchor(haringeyMap, 271.0); //X
        
        AnchorPane.setTopAnchor(harrowMap, 141.0); //Y
        AnchorPane.setLeftAnchor(harrowMap, 83.0); //X
        
        AnchorPane.setTopAnchor(haveringMap, 177.0); //Y
        AnchorPane.setLeftAnchor(haveringMap, 554.0); //X
        
        AnchorPane.setTopAnchor(hillingdonMap, 215.0); //Y
        AnchorPane.setLeftAnchor(hillingdonMap, 0.0); //X
        
        AnchorPane.setTopAnchor(hounslowMap, 318.0); //Y
        AnchorPane.setLeftAnchor(hounslowMap, 58.0); //X
        
        AnchorPane.setTopAnchor(islingtonMap, 202.0); //Y
        AnchorPane.setLeftAnchor(islingtonMap, 270.0); //X
        
        AnchorPane.setTopAnchor(kensingtonMap, 263.0); //Y
        AnchorPane.setLeftAnchor(kensingtonMap, 209.0); //X
        
        AnchorPane.setTopAnchor(kingstonMap, 421.0); //Y
        AnchorPane.setLeftAnchor(kingstonMap, 134.0); //X
        
        AnchorPane.setTopAnchor(lambethMap, 318.0); //Y
        AnchorPane.setLeftAnchor(lambethMap, 270.0); //X
        
        AnchorPane.setTopAnchor(lewishamMap, 323.0); //Y
        AnchorPane.setLeftAnchor(lewishamMap, 350.0); //X
        
        AnchorPane.setTopAnchor(mertonMap, 382.0); //Y
        AnchorPane.setLeftAnchor(mertonMap, 209.0); //X
        
        AnchorPane.setTopAnchor(newhamMap, 219.0); //Y
        AnchorPane.setLeftAnchor(newhamMap, 392.0); //X
        
        AnchorPane.setTopAnchor(redbridgeMap, 147.0); //Y
        AnchorPane.setLeftAnchor(redbridgeMap, 424.0); //X
        
        AnchorPane.setTopAnchor(richmondMap, 344.0); //Y
        AnchorPane.setLeftAnchor(richmondMap, 112.0); //X
        
        AnchorPane.setTopAnchor(southwarkMap, 310.0);
        AnchorPane.setLeftAnchor(southwarkMap, 304.0);
        
        AnchorPane.setTopAnchor(suttonMap, 451.0); //Y
        AnchorPane.setLeftAnchor(suttonMap, 217.0); //X
        
        AnchorPane.setTopAnchor(towerHamletsMap, 243.0); //Y
        AnchorPane.setLeftAnchor(towerHamletsMap, 331.0); //X
        
        AnchorPane.setTopAnchor(walthamMap, 133.0); //Y
        AnchorPane.setLeftAnchor(walthamMap, 343.0); //X
        
        AnchorPane.setTopAnchor(wandsworthMap, 327.0); //Y
        AnchorPane.setLeftAnchor(wandsworthMap, 206.0); //X
        
        AnchorPane.setTopAnchor(westminsterMap, 249.0); //Y
        AnchorPane.setLeftAnchor(westminsterMap, 228.0); //X
        
        // Set coordinate for buttons
        AnchorPane.setTopAnchor(bexleyButton, 432.0); //Y
        AnchorPane.setLeftAnchor(bexleyButton, 622.0); //X
        
        AnchorPane.setTopAnchor(barnetButton, 255.0); //Y
        AnchorPane.setLeftAnchor(barnetButton, 326.0); //X
        
        AnchorPane.setTopAnchor(barkingButton, 324.0); //Y
        AnchorPane.setLeftAnchor(barkingButton, 594.0); //X
        
        AnchorPane.setTopAnchor(brentButton, 314.0); //Y
        AnchorPane.setLeftAnchor(brentButton, 269.0); //X
        
        AnchorPane.setTopAnchor(bromleyButton, 557.0); //Y
        AnchorPane.setLeftAnchor(bromleyButton, 557.0); //X
        
        AnchorPane.setTopAnchor(camdenButton, 315.0); //Y
        AnchorPane.setLeftAnchor(camdenButton, 357.0); //X
        camdenButton.setStyle("-fx-font-size:7");
        
        AnchorPane.setTopAnchor(coLondonButton, 364.0); //Y
        AnchorPane.setLeftAnchor(coLondonButton, 427.0); //X
        coLondonButton.setText("London");
        coLondonButton.setStyle("-fx-font-size:7");
        
        AnchorPane.setTopAnchor(croydonButton, 568.0); //Y
        AnchorPane.setLeftAnchor(croydonButton, 424.0); //X
        
        AnchorPane.setTopAnchor(ealingButton, 361.0); //Y
        AnchorPane.setLeftAnchor(ealingButton, 233.0); //X
        
        AnchorPane.setTopAnchor(enfieldButton, 188.0); //Y
        AnchorPane.setLeftAnchor(enfieldButton, 419.0); //X
        
        AnchorPane.setTopAnchor(greenwichButton, 421.0); //Y
        AnchorPane.setLeftAnchor(greenwichButton, 536.0); //X
        
        AnchorPane.setTopAnchor(hackneyButton, 314.0); //Y
        AnchorPane.setLeftAnchor(hackneyButton, 457.0); //X
        camdenButton.setStyle("-fx-font-size:7");
        
        AnchorPane.setTopAnchor(hammersmithButton, 389.0); //Y
        AnchorPane.setLeftAnchor(hammersmithButton, 326.0); //X
        hammersmithButton.setText("HamSmith");
        
        AnchorPane.setTopAnchor(haringeyButton, 259.0); //Y
        AnchorPane.setLeftAnchor(haringeyButton, 408.0); //X
        
        AnchorPane.setTopAnchor(harrowButton, 257.0); //Y
        AnchorPane.setLeftAnchor(harrowButton, 217.0); //X
        harrowButton.setStyle("-fx-font-size:8");
        
        AnchorPane.setTopAnchor(haveringButton, 289.0); //Y
        AnchorPane.setLeftAnchor(haveringButton, 691.0); //X
        haveringButton.setStyle("-fx-font-size:8");
        
        AnchorPane.setTopAnchor(hillingdonButton, 361.0); //Y
        AnchorPane.setLeftAnchor(hillingdonButton, 123.0); //X
        
        AnchorPane.setTopAnchor(hounslowButton, 421.0); //Y
        AnchorPane.setLeftAnchor(hounslowButton, 191.0); //X
        hounslowButton.setText("H'Slow");
        
        AnchorPane.setTopAnchor(islingtonButton, 323.0); //Y
        AnchorPane.setLeftAnchor(islingtonButton, 409.0); //X
        islingtonButton.setStyle("-fx-font-size:7");
        
        AnchorPane.setTopAnchor(kensingtonButton, 397.0); //Y
        AnchorPane.setLeftAnchor(kensingtonButton, 358.0); //X
        kensingtonButton.setStyle("-fx-font-size:8");
        
        AnchorPane.setTopAnchor(kingstonButton, 527.0); //Y
        AnchorPane.setLeftAnchor(kingstonButton, 269.0); //X
        kingstonButton.setStyle("-fx-font-size:7");
        
        AnchorPane.setTopAnchor(lambethButton, 459.0); //Y
        AnchorPane.setLeftAnchor(lambethButton, 405.0); //X
        lambethButton.setStyle("-fx-font-size:8");
        
        AnchorPane.setTopAnchor(lewishamButton, 457.0); //Y
        AnchorPane.setLeftAnchor(lewishamButton, 482.0); //X
        
        AnchorPane.setTopAnchor(mertonButton, 505.0); //Y
        AnchorPane.setLeftAnchor(mertonButton, 345.0); //X
        
        AnchorPane.setTopAnchor(newhamButton, 349.0); //Y
        AnchorPane.setLeftAnchor(newhamButton, 530.0); //X
        
        AnchorPane.setTopAnchor(redbridgeButton, 268.0); //Y
        AnchorPane.setLeftAnchor(redbridgeButton, 556.0); //X
        redbridgeButton.setStyle("-fx-font-size:8");
        
        AnchorPane.setTopAnchor(richmondButton, 459.0); //Y
        AnchorPane.setLeftAnchor(richmondButton, 237.0); //X
        richmondButton.setStyle("-fx-font-size:7");
        
        AnchorPane.setTopAnchor(southwarkButton, 433.0);
        AnchorPane.setLeftAnchor(southwarkButton, 410.0);
        southwarkButton.setText("S'wark");
        
        AnchorPane.setTopAnchor(suttonButton, 568.0); //Y
        AnchorPane.setLeftAnchor(suttonButton, 349.0); //X
        
        AnchorPane.setTopAnchor(towerHamletsButton, 356.0); //Y
        AnchorPane.setLeftAnchor(towerHamletsButton, 463.0); //X
        towerHamletsButton.setText("T'Hamlets");
        towerHamletsButton.setStyle("-fx-font-size:8");
        
        AnchorPane.setTopAnchor(walthamButton, 281.0); //Y
        AnchorPane.setLeftAnchor(walthamButton, 470.0); //X
        walthamButton.setStyle("-fx-font-size:9");
        
        AnchorPane.setTopAnchor(wandsworthButton, 444.0); //Y
        AnchorPane.setLeftAnchor(wandsworthButton, 336.0); //X
        wandsworthButton.setStyle("-fx-font-size:7");
        wandsworthButton.setText("W'worth");
        
        AnchorPane.setTopAnchor(westminsterButton, 368.0); //Y
        AnchorPane.setLeftAnchor(westminsterButton, 368.0); //X
        westminsterButton.setStyle("-fx-font-size:7");
        westminsterButton.setText("W'minster");
        
        // Stacks all 3 AnchorPane together
        map.getChildren().addAll(fullMap,boroughsImg, buttonsPane);
    }
    
    /**
     * Sets the colour of the boroughs according to the number of listings
     * available in that borough. The more listings, the darker the borough.
     * @param boroughPng Image of borough.
     * @param listingsCount the number of listings in the borough.
     */
    public WritableImage setBoroughColour(Image boroughPng, int listingsCount)
    {
        // Reads the heigh and width of the PNG file
        PixelReader pixRead = boroughPng.getPixelReader();
        int height = (int) boroughPng.getHeight();
        int width = (int) boroughPng.getWidth();
        // red 199
        WritableImage colouredBorough = new WritableImage(pixRead, width, height);
        PixelWriter pixWrite = colouredBorough.getPixelWriter();
        PixelReader pixReadWritable = colouredBorough.getPixelReader();
        
        for (int i = 0; i < listingsCount; i += 25) {
            /*
             * Loops over all pixels based on number of listings in that borough.
             * The biggestBorough() function in MapEngine class can be used to replace
             * the arbritrary value of 25 for a more dynamic shading algorithm, 
             * which is chosen to prevent an underflow in the red pixel value,
             * but implementing this function will make the load time of the application
             * significantly longer. To set an appropriate value using biggestBorough():
             * int loopNumber = (biggestBorough()/ (199*2));
             * 
             * 199 being the initial red RGB value for the main part of the map image. 
             */
            for (int x = 0; x < width; x++)  {
                for (int y = 0; y < height; y++) {
                    int pixel = pixReadWritable.getArgb(x,y);
                    // Skip through if the pixel is transparent
                    if (pixel != 0) {
                        // Splits the 32 bit int to its corresponding colours
                        int alpha = ((pixel >> 24) & 0xff);
                        int red = ((pixel >> 16) & 0xff);
                        int green = ((pixel >> 8) & 0xff);
                        int blue = (pixel & 0xff);
                        
                        // Combine ARGB values.
                        int bluer = (alpha << 24) | (red - 2 << 16) | (green - 1<< 8) | blue;
                        
                        pixWrite.setArgb(x,y,bluer);
                    }
                }
            }
        }
        return colouredBorough;
    }
}
