
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import javafx.scene.layout.HBox;
import java.awt.event.MouseEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;

/**
 * This is the class that creates the GUI layout for the Statistics panel
 *
 * @author Shun Sheng Lee, K21081997
 * @version 30.2.2022
 */
public class StatisticsGUI 
{
    private GUIBuild a;
    private Label label1;
    private Label label2;
    private Label label4;
    private Label label5;
    private Label label6;
    private Label label7;
    private Label label8;
    private Label label9;

    public StatisticsGUI(GUIBuild statsEngine)
    {
        try
        {
            a = statsEngine;
            label1 = new Label();
            label2 = new Label();
            label4 = new Label();
            label5 = new Label();
            label6 = new Label();
            label7 = new Label();
            label8 = new Label();
            label9 = new Label();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    //@Override
    public HBox buildStatsGUI() throws Exception
    {

        // Create a Button or any control item
        Button myButton1 = new Button("<");
        myButton1.setMaxWidth (Double.MIN_VALUE);
        myButton1.setMaxHeight (Double.MAX_VALUE);
        myButton1.setOnAction(this::prevButton1);

        // Create a Button or any control item
        Button myButton2 = new Button (">");
        myButton2.setMaxWidth (Double.MIN_VALUE);
        myButton2.setMaxHeight (Double.MAX_VALUE);
        myButton2.setOnAction(this::nextButton1);

        label1.setText(a.getHeader1());
        label1.setMaxWidth(Double.MAX_VALUE);
        label1.setMaxHeight(Double.MAX_VALUE);
        label1.setAlignment(Pos.CENTER);

        label2.setText(a.getStatistics1());
        label2.setMaxWidth (Double.MAX_VALUE);
        label2.setMaxHeight (Double.MAX_VALUE);
        label2.setAlignment (Pos.CENTER);

        Label label3 = new Label ();

        // Create a new border pane
        BorderPane root1 = new BorderPane();
        root1.setTop (label1);
        root1.setLeft(myButton1);
        root1.setRight (myButton2);
        root1.setCenter (label2);
        root1.setBottom (label3);

        // Create a Button or any control item
        Button myButton3 = new Button("<");
        myButton3.setMaxWidth (Double.MIN_VALUE);
        myButton3.setMaxHeight (Double.MAX_VALUE);
        myButton3.setOnAction(this::prevButton2);

        //Create a Button or any control item
        Button myButton4 = new Button(">");
        myButton4.setMaxWidth (Double.MIN_VALUE);
        myButton4.setMaxHeight (Double.MAX_VALUE);
        myButton4.setOnAction(this::nextButton2);

        label4.setText(a.getHeader2());
        label4.setMaxWidth(Double.MAX_VALUE);
        label4.setMaxHeight (Double.MAX_VALUE);
        label4.setAlignment(Pos.CENTER);

        label5.setText(a.getStatistics2());
        label5.setMaxWidth(Double.MAX_VALUE);
        label5.setMaxHeight (Double.MAX_VALUE);
        label5.setAlignment(Pos.CENTER);

        //Create a new border pane 
        BorderPane root2 = new BorderPane();
        root2.setTop (label4);
        root2.setLeft (myButton3);
        root2.setRight (myButton4);
        root2.setCenter (label5);
        root2.setBottom (label3);

        //Create a Button or any control item
        Button myButton5 = new Button("<");
        myButton5.setMaxWidth (Double.MIN_VALUE);
        myButton5.setMaxHeight (Double.MAX_VALUE);
        myButton5.setOnAction(this::prevButton3);

        //Create a Button or any control item
        Button myButton6 = new Button(">");
        myButton6.setMaxWidth (Double.MIN_VALUE);
        myButton6.setMaxHeight (Double.MAX_VALUE);
        myButton6.setOnAction(this::nextButton3);

        label6.setText(a.getHeader3());
        label6.setMaxWidth(Double.MAX_VALUE);
        label6.setMaxHeight(Double.MAX_VALUE);
        label6.setAlignment(Pos.CENTER);

        label7.setText(a.getStatistics3());
        label7.setMaxWidth(Double.MAX_VALUE);
        label7.setMaxHeight(Double.MAX_VALUE);
        label7.setAlignment(Pos.CENTER);

        //Create a new border pane 
        BorderPane root3 = new BorderPane();
        root3.setTop (label6);
        root3.setLeft (myButton5);
        root3.setRight (myButton6);
        root3.setCenter (label7);
        root3.setBottom (label3);

        //Create a Button or any control item
        Button myButton7 = new Button("<");
        myButton7.setMaxWidth (Double.MIN_VALUE);
        myButton7.setMaxHeight (Double.MAX_VALUE);
        myButton7.setOnAction(this::prevButton4);

        //Create a Button or any control item
        Button myButton8 = new Button(">");
        myButton8.setMaxWidth (Double.MIN_VALUE);
        myButton8.setMaxHeight (Double.MAX_VALUE);
        myButton8.setOnAction(this::nextButton4);

        label8.setText(a.getHeader4());
        label8.setMaxWidth(Double.MAX_VALUE);
        label8.setMaxHeight(Double.MAX_VALUE);
        label8.setAlignment(Pos.CENTER);

        label9.setText(a.getStatistics4());
        label9.setMaxWidth(Double.MAX_VALUE);
        label9.setMaxHeight(Double.MAX_VALUE);
        label9.setAlignment(Pos.CENTER);

        //Create a new border pane 
        BorderPane root4 = new BorderPane();
        root4.setTop (label8);
        root4.setLeft (myButton7);
        root4.setRight (myButton8);
        root4.setCenter (label9);
        root4.setBottom (label3);

        //Create a VBox
        VBox box1 = new VBox();
        box1.getChildren().add (root1);
        box1.getChildren().
        add(root2);
        box1.setMaxWidth(Double.MAX_VALUE);
        box1.setMaxHeight(Double.MAX_VALUE);

        VBox box2 = new VBox();
        box2.getChildren().add (root3);
        box2.getChildren().
        add (root4);
        box2.setMaxWidth(Double.MAX_VALUE);
        box2.setMaxHeight(Double.MAX_VALUE);

        //Create a new HBox
        HBox box = new HBox();
        box.setHgrow(box1, Priority.ALWAYS);
        box.setHgrow(box2, Priority.ALWAYS);
        box.setMaxWidth(Double.MAX_VALUE);
        box.setMaxHeight(Double.MAX_VALUE);
        box.getChildren().addAll (box1, box2);

        box.setAlignment(Pos.CENTER);

        myButton1.getStyleClass().add("myButton");
        myButton2.getStyleClass().
        add("myButton");
        myButton3.getStyleClass().
        add("myButton");
        myButton4.getStyleClass().
        add("myButton");
        myButton5.getStyleClass().
        add("myButton");
        myButton6.getStyleClass().
        add("myButton");
        myButton7.getStyleClass().
        add("myButton");
        myButton8.getStyleClass().
        add("myButton");

        return box;
    }

    /**
     * actions to be executed when button1 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void prevButton1 (ActionEvent event)
    {
        a.setHeader1();
        label1.setText(a.getHeader1());
        a.setStatistics1();
        label2.setText(a.getStatistics1());
    }

    /**
     * actions to be executed when button2 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void nextButton1 (ActionEvent event)
    {
        a.setHeader1();
        label1.setText(a.getHeader1());
        a.setStatistics1();
        label2.setText(a.getStatistics1());
    }

    /**
     * actions to be executed when button3 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void prevButton2 (ActionEvent event)
    {
        a.setHeader2();
        label4.setText(a.getHeader2());
        a.setStatistics2();
        label5.setText(a.getStatistics2());
    }

    /**
     * actions to be executed when button4 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void nextButton2 (ActionEvent event)
    {
        a.setHeader2();
        label4.setText(a.getHeader2());
        a.setStatistics2();
        label5.setText(a.getStatistics2());
    }

    /**
     * actions to be executed when button5 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void prevButton3 (ActionEvent event)
    {
        a.setHeader3();
        label6.setText(a.getHeader3());
        a.setStatistics3();
        label7.setText(a.getStatistics3());
    }

    /**
     * actions to be executed when button6 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void nextButton3 (ActionEvent event)
    {
        a.setHeader3();
        label6.setText(a.getHeader3());
        a.setStatistics3();
        label7.setText(a.getStatistics3());
    }

    /**
     * actions to be executed when button7 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void prevButton4 (ActionEvent event)
    {
        a.setHeader4();
        label8.setText(a.getHeader4());
        a.setStatistics4();
        label9.setText(a.getStatistics4());
    }

    /**
     * actions to be executed when button8 is clicked
     * sets header of stats box, then displays it
     * sets stastistics, then displays it
     */
    private void nextButton4 (ActionEvent event)
    {
        a.setHeader4();
        label8.setText(a.getHeader4());
        a.setStatistics4();
        label9.setText(a.getStatistics4());
    }


    /**
     * updates the statistics display of the stats box
     */
    public void updateAll()
    {
        label2.setText(a.getStatistics1());
        label5.setText(a.getStatistics2());
        label7.setText(a.getStatistics3());
        label9.setText(a.getStatistics4());

    }

}
