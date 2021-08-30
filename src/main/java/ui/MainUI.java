package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.PageDelegatorFactory;
import utils.PageDelegatorFactory.PageDelegator;

public class MainUI extends Application{

	private PageDelegator pageDelegator;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		pageDelegator = PageDelegatorFactory.getInstace(primaryStage);
			
		pageDelegator.openPage("Company Pool", new CompanyPoolUI(), false); 
	}
	
}
