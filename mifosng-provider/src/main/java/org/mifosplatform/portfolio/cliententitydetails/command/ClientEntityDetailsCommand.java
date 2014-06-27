package org.mifosplatform.portfolio.cliententitydetails.command;



public class ClientEntityDetailsCommand {

	    

	    
		private Long clientId;
		private String registeredName;
		private String tradingName;
		private Long dateOfIncorporation;
		private String fileLocation;
		private String fileName;
		private String fileType;
	

	    
	    
	    public ClientEntityDetailsCommand(Long clientId, String registeredName,
				String tradingName, Long dateOfIncorporation,String fileName, String fileType) {
	    	this.clientId = clientId;
	    	this.registeredName = registeredName;
	    	this.tradingName = tradingName;
	    	this.dateOfIncorporation = dateOfIncorporation;
	    	this.fileName = fileName;
	    	this.fileType = fileType;
	    	
		}

		public Long getClientId() {
			return clientId;
		}




		public void setClientId(Long clientId) {
			this.clientId = clientId;
		}




		public String getRegisteredName() {
			return registeredName;
		}




		public void setRegisteredName(String registeredName) {
			this.registeredName = registeredName;
		}




		public String getTradingName() {
			return tradingName;
		}




		public void setTradingName(String tradingName) {
			this.tradingName = tradingName;
		}




		public Long getDateOfIncorporation() {
			return dateOfIncorporation;
		}




		public void setDateOfIncorporation(Long dateOfIncorporation) {
			this.dateOfIncorporation = dateOfIncorporation;
		}




		public void setFileLocation(String fileLocation) {
			this.fileLocation = fileLocation;
			
		}




		public String getFileLocation() {
			return fileLocation;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
	    
	    
		
	    
}
