package org.mifosplatform.portfolio.cliententitydetails.service;

import java.util.List;

import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.portfolio.cliententity.data.ClientEntityDetailsData;

public interface ClientEntityDetailsReadPlatformService {

	FileData downloadFile(Long fileId);

	List<ClientEntityDetailsData> retriveEntityDetails(Long clientId);

}
