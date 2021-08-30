package utils;

import lombok.Data;
import lombok.NoArgsConstructor;

public class ResourcePoolFactory {

	private static ResourcePool resourcePool;
	
	private ResourcePoolFactory() {
		
	}
	
	public static ResourcePool getInstance(Double qa, Double dataArchitect, Double solutionArchitect, Double softwareEngineer) {
		if(resourcePool == null) {
			resourcePool = new ResourcePoolFactory().new ResourcePool(qa, dataArchitect, solutionArchitect, softwareEngineer);
		}
		
		return resourcePool;
	}

	public static ResourcePool getInstance() {
		if(resourcePool == null) {
			resourcePool = new ResourcePoolFactory().new ResourcePool();
		}
		
		return resourcePool;
	}

	
	@Data
	@NoArgsConstructor
	public class ResourcePool{

		private Double qa;
		private Double dataArchitect;
		private Double solutionArchitect;
		private Double softwareEngineer;
		
		private Double originalQa;
		private Double originalDataArchitect;
		private Double originalSolutionArchitect;
		private Double originalSoftwareEngineer;
	
	
		public ResourcePool(Double qa, Double dataArchitect, Double solutionArchitect, Double softwareEngineer) {
			this.qa = qa;
			this.originalQa = qa;
			this.dataArchitect = dataArchitect;
			this.originalDataArchitect = dataArchitect;
			this.solutionArchitect = solutionArchitect;
			this.originalSolutionArchitect = solutionArchitect;
			this.originalSoftwareEngineer = softwareEngineer;
			
		}
		
		public void reset() {
			
			this.qa = originalQa;
			this.dataArchitect = originalDataArchitect;
			this.solutionArchitect = originalSolutionArchitect;
			this.softwareEngineer = originalSoftwareEngineer;
		}
		
	}
	
	
}
