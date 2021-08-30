package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GanttGenerationUI extends GenericUI{

	@Override
	public Parent initPage(Object parameter) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/gantt_generation_ui.fxml"));
		Parent parent = (Parent) loader.load();
		
		return parent;
	}
}
