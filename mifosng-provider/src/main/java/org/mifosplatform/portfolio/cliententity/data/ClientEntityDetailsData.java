package org.mifosplatform.portfolio.cliententity.data;

import org.joda.time.LocalDate;

public class ClientEntityDetailsData {

	private Long id;
	private String registeredName;
	private LocalDate dateOfIncorporation;
	private String tradingName;
	private String fileName;

	public ClientEntityDetailsData(Long id, String registeredName,
			LocalDate dateOfIncorporation, String tradingName, String fileName) {
		this.id = id;
		this.registeredName = registeredName;
		this.dateOfIncorporation = dateOfIncorporation;
		this.tradingName = tradingName;
		this.fileName = fileName;
	}	
}
