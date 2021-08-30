package bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerationGanttItem {

	private String backlogItem;
	private String qa;
	private String dataArchitect;
	private String softwareEngineer;
	private String solutionArchitect;
	private String effort;
	private String start;
	private String stop;
	
	
}
