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

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//This Class opens message box
public class MessageBox {
	
	public static void display(String title, String message) {
		Stage MessageBoxStage = new Stage();
		
		//to freen the previous windows until message box is clicked
		MessageBoxStage.initModality(Modality.APPLICATION_MODAL);
		MessageBoxStage.setTitle(title);
		MessageBoxStage.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		
		Button closeButton = new Button("OK");
		closeButton.setOnAction(e -> MessageBoxStage.close());
		
		VBox messageVBox = new VBox(10);
		messageVBox.getChildren().addAll(label, closeButton);
		messageVBox.setAlignment(Pos.CENTER);
		
		Scene messageBoxScene = new Scene(messageVBox, 400, 100);
		MessageBoxStage.setScene(messageBoxScene);
		MessageBoxStage.showAndWait();
		
	}
}
