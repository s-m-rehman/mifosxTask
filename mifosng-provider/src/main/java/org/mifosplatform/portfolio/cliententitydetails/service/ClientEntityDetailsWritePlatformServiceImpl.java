package org.mifosplatform.portfolio.cliententitydetails.service;

import java.io.InputStream;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.documentmanagement.command.DocumentCommand;
import org.mifosplatform.infrastructure.documentmanagement.contentrepository.ContentRepository;
import org.mifosplatform.infrastructure.documentmanagement.contentrepository.ContentRepositoryFactory;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.cliententitydetails.command.ClientEntityDetailsCommand;
import org.mifosplatform.portfolio.cliententitydetails.domain.ClientEntityDetails;
import org.mifosplatform.portfolio.cliententitydetails.domain.ClientEntityDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class ClientEntityDetailsWritePlatformServiceImpl implements
		ClientEntityDetailsWritePlatformService {

	private final PlatformSecurityContext context;
	private final ContentRepositoryFactory contentRepositoryFactory;
    private final ClientEntityDetailsRepository clientEntityDetailsRepository;
    
    private final static Logger logger = LoggerFactory.getLogger(ClientEntityDetailsWritePlatformService.class);
	
	@Autowired
	public ClientEntityDetailsWritePlatformServiceImpl(final PlatformSecurityContext context,
			final ContentRepositoryFactory contentRepositoryFactory,
			final ClientEntityDetailsRepository clientEntityDetailsRepository) {
		this.context = context;
		this.contentRepositoryFactory = contentRepositoryFactory;
		this.clientEntityDetailsRepository = clientEntityDetailsRepository;
	}
	
	@Override
	public CommandProcessingResult createClientEntityDetails(JsonCommand command) {
		
		return null;
	}
	
	@Override
	public Long createDocument(ClientEntityDetailsCommand entityDetailCommand,
			DocumentCommand documentCommand, InputStream inputStream) {

        try {
            this.context.authenticatedUser();

            
            final ContentRepository contentRepository = this.contentRepositoryFactory.getRepository();
            final String fileLocation = contentRepository.saveFile(inputStream, documentCommand);
            
            entityDetailCommand.setFileLocation(fileLocation);
            ClientEntityDetails clientEntityDetails = ClientEntityDetails.fromJson(entityDetailCommand);
            
            this.clientEntityDetailsRepository.save(clientEntityDetails);

            return clientEntityDetails.getId();
        } catch (final DataIntegrityViolationException dve) {
            logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("error.msg.document.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource.");
        }
	}
	

}
