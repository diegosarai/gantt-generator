package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GanttItemDto {

	private String backlogItem;
	private Double qa;
	private Double dataArchitect;
	private Double softwareEngineer;
	private Double solutionArchitect;
	private Integer effort;
	private Integer start;
	private Integer stop;	
	private boolean sprintAddressed;
}
