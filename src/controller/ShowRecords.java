/*
 * --------------------------------------------------------------------
 * Developer Name 	: Udeshika Dissanayake
 * Subject			: COSC1295 Advanced Programming
 * Assignment		: Assignment 2 - Semester 1, 2019
 * Student Number	: s3400652
 * Date				: 01/06/2019 * 
 *--------------------------------------------------------------------
 */

package controller;

import java.util.Arrays;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

//This Class shows all the records in the database in GUI table view
public class ShowRecords {
	
		
	public static void display(String[][] recordData) {
		
	    String[] columnNames = {"Record ID (VehicleID_CusID_RentDate)", "Rend Date", "Estimated Return Date", "Actual Return Date",
	    		"Rent Fee", "Late Fee"};
	    int[] columnWidth = {300, 200, 200, 200, 100, 100};
	    
	    ObservableList<String[]> ObserveRecords = FXCollections.observableArrayList();
	    ObserveRecords.addAll(Arrays.asList(recordData));
	    	    
	    TableView<String[]> tabelView = new TableView<>();
	    
	    for(int i=0; i < recordData[0].length; i++) {
	    	TableColumn tc = new TableColumn(recordData[0][i]);
	    	int colNo = i;
	    	
	    }
	    
	    for (int i = 0; i < recordData[0].length; i++) {
            TableColumn tc = new TableColumn(columnNames[i]);
            tc.setMinWidth(columnWidth[i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            tabelView.getColumns().add(tc);
        }
	    	    
	    TextField searchField = new TextField();
	    searchField.setFont(Font.font("Cambria", 16));
	    searchField.setPromptText("Search");
	    searchField.setMaxWidth(200);
	    	    
	    tabelView.setItems(ObserveRecords);
	     
		Stage ShowRecordStage = new Stage();
		
		//to freen the previous windows until message box is clicked
		ShowRecordStage.initModality(Modality.APPLICATION_MODAL);
		ShowRecordStage.setTitle("Show Vehicle Records");
		ShowRecordStage.setMinWidth(1000);
		
		Label label = new Label();
		label.setText("Seach For Record");
		
		HBox topHbox = new HBox(10);
		topHbox.setPadding(new Insets(20,200,1,20));
		topHbox.getChildren().addAll(label, searchField);
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> ShowRecordStage.close());
		
		VBox showRecordsVBox = new VBox(10);
		showRecordsVBox.getChildren().addAll(topHbox, tabelView, closeButton);
		showRecordsVBox.setAlignment(Pos.CENTER);
		
		Scene showRecordScene = new Scene(showRecordsVBox, 1200, 500);
		ShowRecordStage.setScene(showRecordScene);
		ShowRecordStage.showAndWait();
	
	}

}
