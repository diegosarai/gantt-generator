package ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dto.GanttItemDto;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class GanttChartUI extends GenericUI{

	
	public Parent initPage(Object parameter) {
		
		List<GanttItemDto> ganttItemsDto = (List<GanttItemDto>) parameter;
		
		String[] resources = new String[ganttItemsDto.size()];
		
		ganttItemsDto.stream().map(GanttItemDto::getBacklogItem).collect(Collectors.toList()).toArray(resources);
		
		
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("Sprint Number");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(10);
        
        
        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(resources)));

        chart.setLegendVisible(false);
        chart.setBlockHeight(50);
        
        
        for (GanttItemDto item : ganttItemsDto) {
			
        	  System.out.println(item.getBacklogItem() + " - start: " + item.getStart() + " - stop: " + item.getStop() + " - address: " + item.isSprintAddressed());
        	
        	  if(item.isSprintAddressed()) {

    	    	  String backlogItem = item.getBacklogItem();
    	          XYChart.Series series = new XYChart.Series();
    	          series.getData().add(new XYChart.Data(item.getStart(), backlogItem, new GanttChart.ExtraData(item.getEffort(), "status-green")));
    	      
    	          chart.getData().add(series);

        	  }
		}
        
        
        chart.getStylesheets().add(getClass().getResource("/css/ganttchart.css").toExternalForm());
        
        return chart;
		
	}

	
	
}
