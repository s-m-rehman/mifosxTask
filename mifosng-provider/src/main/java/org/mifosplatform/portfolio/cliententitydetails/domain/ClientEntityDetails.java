package org.mifosplatform.portfolio.cliententitydetails.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.portfolio.cliententitydetails.command.ClientEntityDetailsCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name="client_entity_details")
public class ClientEntityDetails extends AbstractPersistable<Long>{
	
	 	@Column(name = "registered_name", length = 100)
	    private String registeredName;

	    @Column(name = "trading_name", length = 200)
	    private String tradingName;

	    @Column(name = "date_of_incorporation")
	    @Temporal(TemporalType.DATE)
	    private Date dateOfIncorporation;

	    @Column(name = "file_location", length = 300)
	    private String fileLocation;
	    
	    @Column(name="client_id",length = 20)
	    private Long clientId;
	    
	    @Column(name="file_name",length=250)
	    private String fileName;
	    
	    @Column(name="file_type",length=250)
	    private String fileType;
	    
	    
	    public ClientEntityDetails() {
			
		}
	    
		public ClientEntityDetails(Long clientId, String registeredName, String tradingName,
				Date dateOfIncorporation, String fileLocation,String fileName, String fileType) {
			 this.clientId = clientId;
			 this.registeredName = registeredName;
			 this.tradingName = tradingName;
			 this.dateOfIncorporation = dateOfIncorporation;
			 this.fileLocation = fileLocation;
			 this.fileName = fileName;
			 this.fileType = fileType;
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

		public Date getDateOfIncorporation() {
			return dateOfIncorporation;
		}

		public void setDateOfIncorporation(Date dateOfIncorporation) {
			this.dateOfIncorporation = dateOfIncorporation;
		}

		public String getFileLocation() {
			return fileLocation;
		}

		public void setFileLocation(String fileLocation) {
			this.fileLocation = fileLocation;
		}
		

		public Long getClientId() {
			return clientId;
		}

		public void setClientId(Long clientId) {
			this.clientId = clientId;
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

		public static ClientEntityDetails fromJson(
				ClientEntityDetailsCommand entityDetailCommand) {
			final String registeredName = entityDetailCommand.getRegisteredName();
			final String tradingName = entityDetailCommand.getTradingName();
			final Date dateOfIncorporation = new Date(entityDetailCommand.getDateOfIncorporation()); 
			final String fileLocation = entityDetailCommand.getFileLocation();
			final String fileName = entityDetailCommand.getFileName();
			final String fileType = entityDetailCommand.getFileType();
			return new ClientEntityDetails(entityDetailCommand.getClientId(), registeredName, tradingName, dateOfIncorporation, fileLocation,fileName,fileType);
		}
	    
}
