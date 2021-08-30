package mappers;

import org.mapstruct.Mapper;

import bean.GenerationGanttItem;
import dto.GanttItemDto;

@Mapper
public interface GanttItemMapper {

	GanttItemDto generationGanttItemToGantItemDto(GenerationGanttItem generationGanttItem);
}
