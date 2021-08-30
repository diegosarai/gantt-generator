package service;

import java.util.ArrayList;
import java.util.List;

import bean.GenerationGanttItem;
import dto.GanttItemDto;
import utils.ResourcePoolFactory;
import utils.ResourcePoolFactory.ResourcePool;

public class GanttGenerationService {

	private ResourcePool resourcePool;

	{

		resourcePool = ResourcePoolFactory.getInstance();
	}

	public List<GanttItemDto> calculateGanttChart(final List<GenerationGanttItem> ganttItems, int numberOfSprints) {

		resourcePool.reset();
		
		int currentSprint = 1;
		int maxSprintProjection = numberOfSprints;

		List<GanttItemDto> ganttItemsDto = convertGanntBeanToDto(ganttItems);

			for (currentSprint = 1; currentSprint < maxSprintProjection;) {
				
				//Iterate all backlogitems that is already address to retrieve the resources back to pool
				for (GanttItemDto ganttItemDto : ganttItemsDto) {
					
					if(ganttItemDto.isSprintAddressed() && currentSprint == ganttItemDto.getStop() + 1) {
						
						resourcePool.setSolutionArchitect(resourcePool.getSolutionArchitect() + ganttItemDto.getSolutionArchitect());
						resourcePool.setDataArchitect(resourcePool.getDataArchitect() + ganttItemDto.getDataArchitect());
						resourcePool.setSoftwareEngineer(resourcePool.getSoftwareEngineer() + ganttItemDto.getSoftwareEngineer());
						resourcePool.setQa(resourcePool.getQa() + ganttItemDto.getQa());
					}
				}
				
				// Iterate backlog items
				for (GanttItemDto ganttItemDto : ganttItemsDto) {
	
					if(ganttItemDto.isSprintAddressed()) {
						continue;
					}
					
					double differencePoolArchitect = resourcePool.getSolutionArchitect() - ganttItemDto.getSolutionArchitect();
					double differencePoolDataArchitect = resourcePool.getDataArchitect() - ganttItemDto.getDataArchitect();
					double differencePoolSoftwareEngineer = resourcePool.getSoftwareEngineer() - ganttItemDto.getSoftwareEngineer();
					double differencePoolQa = resourcePool.getQa() - ganttItemDto.getQa();

					if((ganttItemDto.getSolutionArchitect() == 0 || (ganttItemDto.getSolutionArchitect() > 0 && differencePoolArchitect >= 0)) &&
						(ganttItemDto.getDataArchitect() == 0 || (ganttItemDto.getDataArchitect() > 0 && differencePoolDataArchitect >= 0)) && 
						(ganttItemDto.getSoftwareEngineer() == 0 || (ganttItemDto.getSoftwareEngineer() > 0 && differencePoolSoftwareEngineer >= 0)) &&
						(ganttItemDto.getQa() == 0 || (ganttItemDto.getQa() > 0 && differencePoolQa >= 0))) {
						
						
						ganttItemDto.setStart(currentSprint);
						ganttItemDto.setStop((ganttItemDto.getEffort() + currentSprint) - 1);

						resourcePool.setSolutionArchitect(differencePoolArchitect);
						resourcePool.setDataArchitect(differencePoolDataArchitect);
						resourcePool.setSoftwareEngineer(differencePoolSoftwareEngineer);
						resourcePool.setQa(differencePoolQa);

						ganttItemDto.setSprintAddressed(true);


					} 
					
				}

			
				currentSprint++;
			}

		return ganttItemsDto;
	}

	private List<GanttItemDto> convertGanntBeanToDto(List<GenerationGanttItem> ganttItems) {

		List<GanttItemDto> ganttItemsDto = new ArrayList<GanttItemDto>();
		
		for (GenerationGanttItem item : ganttItems) {
			
			ganttItemsDto.add(new GanttItemDto(item.getBacklogItem(), Double.valueOf(item.getQa()),
					Double.valueOf(item.getDataArchitect()), Double.valueOf(item.getSoftwareEngineer()),
					Double.valueOf(item.getSolutionArchitect()), Integer.valueOf(item.getEffort()),
					Integer.valueOf(item.getStart()), Integer.valueOf(item.getStop()), false));
		}
		
		return ganttItemsDto;

	}

}
