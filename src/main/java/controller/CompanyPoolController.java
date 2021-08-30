package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.GanttGenerationUI;
import utils.PageDelegatorFactory;
import utils.PageDelegatorFactory.PageDelegator;
import utils.ResourcePoolFactory;
import utils.ResourcePoolFactory.ResourcePool;

public class CompanyPoolController {

	private PageDelegator pageDelegator;
	private ResourcePool resourcePool;
	
	{
		pageDelegator = PageDelegatorFactory.getInstace();
		resourcePool = ResourcePoolFactory.getInstance();
	
	}
	
	@FXML
	private TextField txtQaAmount;
	
	@FXML
	private TextField txtDataArchitectAmount;
	
	@FXML
	private TextField txtSolutionArchitectAmount;

	@FXML
	private TextField txtSoftwareEngineerAmount;
	
	@FXML
	public void navigateAndConfirmPool() {
		
		resourcePool.setSolutionArchitect(Double.valueOf(txtSolutionArchitectAmount.getText()));
		resourcePool.setOriginalSolutionArchitect(Double.valueOf(txtSolutionArchitectAmount.getText()));
		
		resourcePool.setSoftwareEngineer(Double.valueOf(txtSoftwareEngineerAmount.getText()));
		resourcePool.setOriginalSoftwareEngineer(Double.valueOf(txtSoftwareEngineerAmount.getText()));
				
		resourcePool.setDataArchitect(Double.valueOf(txtDataArchitectAmount.getText()));
		resourcePool.setOriginalDataArchitect(Double.valueOf(txtDataArchitectAmount.getText()));

		resourcePool.setQa(Double.valueOf(txtQaAmount.getText()));
		resourcePool.setOriginalQa(Double.valueOf(txtQaAmount.getText()));
		
		pageDelegator.openPage("Gantt Generator", new GanttGenerationUI(), false);  
	}
	
	public TextField getTxtQaAmount() {
		return txtQaAmount;
	}
	public void setTxtQaAmount(TextField txtQaAmount) {
		this.txtQaAmount = txtQaAmount;
	}

	public PageDelegator getPageDelegator() {
		return pageDelegator;
	}

	public void setPageDelegator(PageDelegator pageDelegator) {
		this.pageDelegator = pageDelegator;
	}

	public ResourcePool getResourcePool() {
		return resourcePool;
	}

	public void setResourcePool(ResourcePool resourcePool) {
		this.resourcePool = resourcePool;
	}

	public TextField getTxtDataArchitectAmount() {
		return txtDataArchitectAmount;
	}

	public void setTxtDataArchitectAmount(TextField txtDataArchitectAmount) {
		this.txtDataArchitectAmount = txtDataArchitectAmount;
	}

	public TextField getTxtSolutionArchitectAmount() {
		return txtSolutionArchitectAmount;
	}

	public void setTxtSolutionArchitectAmount(TextField txtSolutionArchitectAmount) {
		this.txtSolutionArchitectAmount = txtSolutionArchitectAmount;
	}

	public TextField getTxtSoftwareEngineerAmount() {
		return txtSoftwareEngineerAmount;
	}

	public void setTxtSoftwareEngineerAmount(TextField txtSoftwareEngineerAmount) {
		this.txtSoftwareEngineerAmount = txtSoftwareEngineerAmount;
	}
	
}
