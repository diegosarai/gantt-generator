package controller;

import java.util.ArrayList;
import java.util.List;

import bean.GenerationGanttItem;
import dto.GanttItemDto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lombok.Data;
import service.GanttGenerationService;
import ui.GanttChartUI;
import utils.PageDelegatorFactory;
import utils.PageDelegatorFactory.PageDelegator;

@Data
public class GanttGenerationController {

	private GanttGenerationService service;
	
	private PageDelegator pagerDelegator;
	
	@FXML
	private TableView ganttTable;
	
	@FXML
	private TableColumn<GenerationGanttItem,String> backlogItemColumn;

	@FXML
	private TableColumn<GenerationGanttItem,String> qaColumn;

	@FXML
	private TableColumn<GenerationGanttItem,String> dataArchitectColumn;
	
	@FXML
	private TableColumn<GenerationGanttItem,String> softwareEngineerColumn;
	
	@FXML
	private TableColumn<GenerationGanttItem,String> solutionArchiectColumn;

	@FXML
	private TableColumn<GenerationGanttItem,String> effortColumn;

	@FXML
	private TableColumn<GenerationGanttItem,String> startColumn;

	@FXML
	private TableColumn<GenerationGanttItem,String> stopColumn;

	@FXML
	private TextField txtNumberSprints;
	
	@FXML
	protected void initialize() {
				
		service = new GanttGenerationService();
		pagerDelegator = PageDelegatorFactory.getInstace();
		
		txtNumberSprints.setText("10");
		
		backlogItemColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("backlogItem"));		
		
		qaColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("qa"));
		qaColumn.setCellFactory(TextFieldTableCell.<GenerationGanttItem>forTableColumn());
		
		qaColumn.setOnEditCommit(new EventHandler<CellEditEvent<GenerationGanttItem, String>>() {		       
			public void handle(CellEditEvent<GenerationGanttItem, String> t) {
				((GenerationGanttItem) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setQa(t.getNewValue());
			}
	    });
		
		
		dataArchitectColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("dataArchitect"));
		dataArchitectColumn.setCellFactory(TextFieldTableCell.<GenerationGanttItem>forTableColumn());
			
		dataArchitectColumn.setOnEditCommit(new EventHandler<CellEditEvent<GenerationGanttItem, String>>() {		       
			public void handle(CellEditEvent<GenerationGanttItem, String> t) {
				((GenerationGanttItem) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setDataArchitect(t.getNewValue());
			}
	    });
		
		
		
		softwareEngineerColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("softwareEngineer"));
		softwareEngineerColumn.setCellFactory(TextFieldTableCell.<GenerationGanttItem>forTableColumn());
			
		softwareEngineerColumn.setOnEditCommit(new EventHandler<CellEditEvent<GenerationGanttItem, String>>() {		       
			public void handle(CellEditEvent<GenerationGanttItem, String> t) {
				((GenerationGanttItem) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setSoftwareEngineer(t.getNewValue());
			}
	    });
		
		
		solutionArchiectColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("solutionArchitect"));
		solutionArchiectColumn.setCellFactory(TextFieldTableCell.<GenerationGanttItem>forTableColumn());
			
		solutionArchiectColumn.setOnEditCommit(new EventHandler<CellEditEvent<GenerationGanttItem, String>>() {		       
			public void handle(CellEditEvent<GenerationGanttItem, String> t) {
				((GenerationGanttItem) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setSolutionArchitect(t.getNewValue());
			}
	    });
		
		effortColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("effort"));
		effortColumn.setCellFactory(TextFieldTableCell.<GenerationGanttItem>forTableColumn());
		
		effortColumn.setOnEditCommit(new EventHandler<CellEditEvent<GenerationGanttItem, String>>() {		       
			public void handle(CellEditEvent<GenerationGanttItem, String> t) {
				((GenerationGanttItem) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setEffort(t.getNewValue());
			}
	    });
		
		
		startColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("start"));
		stopColumn.setCellValueFactory(new PropertyValueFactory<GenerationGanttItem, String>("stop"));
		
		

		ganttTable.getItems().setAll(generateGanntRecords());
	}
	
	public List<GenerationGanttItem> generateGanntRecords(){
		
		List<GenerationGanttItem> generationGanttItemList = new ArrayList<GenerationGanttItem>();
	
		GenerationGanttItem registro = null;
		
		
		registro = new GenerationGanttItem ("Backlog A", "1", "1", "2", "1", "2", "0", "0");
		generationGanttItemList.add(registro);

		registro = new GenerationGanttItem ("Backlog B", "0", "2", "0", "0", "1", "0", "0");
		generationGanttItemList.add(registro);

		registro = new GenerationGanttItem ("Backlog C", "1", "1", "3", "0", "2", "0", "0");
		generationGanttItemList.add(registro);

		/*
		registro = new GenerationGanttItem ("Backlog D", "1", "1", "1", "1", "1", "0", "0");
		generationGanttItemList.add(registro);

		registro = new GenerationGanttItem ("Backlog E", "2", "1", "2", "1", "2", "0", "0");
		generationGanttItemList.add(registro);

		registro = new GenerationGanttItem ("Backlog F", "3", "1", "1", "1", "2", "0", "0");
		generationGanttItemList.add(registro);
		 */
		
		return generationGanttItemList;	
	}
	
		
	public void executeGantt() {
		
		  List<GenerationGanttItem> gantItemGeneration =  (List<GenerationGanttItem>) ganttTable.getItems();
		  List<GanttItemDto> ganttItemsDto = service.calculateGanttChart(gantItemGeneration, Integer.parseInt(txtNumberSprints.getText()));
		  
		  ganttTable.getItems().setAll(convertGanttDtoToBean(ganttItemsDto));
		  
		  pagerDelegator.openPage("Gantt Chart", new GanttChartUI(), true, ganttItemsDto);				
		
	}
	
	private List<GenerationGanttItem> convertGanttDtoToBean(List<GanttItemDto> ganttItemsDto) {
		
		List<GenerationGanttItem> response = new ArrayList<>();
		
		for(GanttItemDto ganttItemDto: ganttItemsDto) {
			
			response.add(new GenerationGanttItem(ganttItemDto.getBacklogItem(), ganttItemDto.getQa().toString(), ganttItemDto.getDataArchitect().toString(), ganttItemDto.getSoftwareEngineer().toString(), 
					ganttItemDto.getSolutionArchitect().toString(), ganttItemDto.getEffort().toString(), ganttItemDto.getStart().toString(), ganttItemDto.getStop().toString()));
			
		}
		
		return response;
	}
}
