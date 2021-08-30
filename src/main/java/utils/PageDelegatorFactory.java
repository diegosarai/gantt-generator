package utils;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.GenericUI;

public class PageDelegatorFactory {

	private static PageDelegator pageDelegatorInstace;
	
	private PageDelegatorFactory() {
	}
	
	public static PageDelegator getInstace(Stage primaryStage) {
		if(pageDelegatorInstace == null) {			
			pageDelegatorInstace = new PageDelegatorFactory().new PageDelegator(primaryStage);
		}
		
		return pageDelegatorInstace;
	}
	
	public static PageDelegator getInstace() {		
		return pageDelegatorInstace;
	}
	
	public class PageDelegator{
	
		private Stage primaryStage;
		
		private PageDelegator(Stage primaryStage) {
			this.primaryStage = primaryStage;
		}
		
		public void openPage(String pageTitle, GenericUI pane, int width, int height) {
			
			primaryStage.setTitle(pageTitle);
			primaryStage.setResizable(false);
			
			try {
		
				primaryStage.setScene(new Scene(pane.initPage(null), width, height));
				
			}catch(IOException a) {
				
				a.printStackTrace();
			}
			primaryStage.show();
		}

		
		public void openPage(String pageTitle, GenericUI pane, int width, int height, Object parameter) {
			
			primaryStage.setTitle(pageTitle);
			primaryStage.setResizable(false);
			
			try {
		
				primaryStage.setScene(new Scene(pane.initPage(parameter), width, height));
				
			}catch(IOException a) {
				
				a.printStackTrace();
			}
			primaryStage.show();
		}

		public void openPage(String pageTitle, GenericUI pane, boolean flagNewStage, Object parameter) {
			
			Stage currentStage = flagNewStage ? new Stage() : primaryStage;

			currentStage.setTitle(pageTitle);
			currentStage.setResizable(false);
			
			try {
		
				currentStage.setScene(new Scene(pane.initPage(parameter)));
				
			}catch(IOException a) {
				
				a.printStackTrace();
			}
			currentStage.show();
		}
		
		public void openPage(String pageTitle, GenericUI pane, boolean flagNewStage) {
			
			Stage currentStage = flagNewStage ? new Stage() : primaryStage;

			currentStage.setTitle(pageTitle);
			currentStage.setResizable(false);
			
			try {
		
				currentStage.setScene(new Scene(pane.initPage(null)));
				
			}catch(IOException a) {
				
				a.printStackTrace();
			}
			currentStage.show();
		}
		
	}
	
}
