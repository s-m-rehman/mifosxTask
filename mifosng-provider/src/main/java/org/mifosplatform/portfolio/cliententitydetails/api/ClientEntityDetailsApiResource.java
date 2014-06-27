
package org.mifosplatform.portfolio.cliententitydetails.api;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.commands.domain.CommandWrapper;
import org.mifosplatform.commands.service.CommandWrapperBuilder;
import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.documentmanagement.command.DocumentCommand;
import org.mifosplatform.infrastructure.documentmanagement.data.FileData;
import org.mifosplatform.infrastructure.documentmanagement.service.DocumentWritePlatformService;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.cliententity.data.ClientEntityDetailsData;
import org.mifosplatform.portfolio.cliententitydetails.command.ClientEntityDetailsCommand;
import org.mifosplatform.portfolio.cliententitydetails.service.ClientEntityDetailsReadPlatformService;
import org.mifosplatform.portfolio.cliententitydetails.service.ClientEntityDetailsWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("cliententitydetails")
@Component
@Scope("singleton")
public class ClientEntityDetailsApiResource {

    private final PlatformSecurityContext context;
    
    private final ToApiJsonSerializer<ClientEntityDetailsData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final DocumentWritePlatformService documentWritePlatformService;
    private final ClientEntityDetailsWritePlatformService clientEntityDetailsWritePlatformService;
    private final ClientEntityDetailsReadPlatformService clientEntityDetailsReadPlatformService;
    
    
    private static final String CLIENTENTITY_RESOURCE_NAME = "CLIENTENTITYDETAILS";
    private static final Set<String> CLIENTENTITY_RESPONSE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("registeredName","tradingName","dateOfIncorporation","scanOfRegistrationDocument"));

    @Autowired
    public ClientEntityDetailsApiResource(final PlatformSecurityContext context,
            final ToApiJsonSerializer<ClientEntityDetailsData> toApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final DocumentWritePlatformService documentWritePlatformService,
            final ClientEntityDetailsWritePlatformService clientEntityDetailsWritePlatformService,
    		final ClientEntityDetailsReadPlatformService clientEntityDetailsReadPlatformService) {
        this.context = context;
        this.clientEntityDetailsWritePlatformService = clientEntityDetailsWritePlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.documentWritePlatformService = documentWritePlatformService;
        this.clientEntityDetailsReadPlatformService = clientEntityDetailsReadPlatformService;
    }

    @GET
    @Path("{clientId}/template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveTemplate(@Context final UriInfo uriInfo, @PathParam("clientId") final Long clientId) {
    	
        this.context.authenticatedUser().validateHasReadPermission(CLIENTENTITY_RESOURCE_NAME);
        List<ClientEntityDetailsData> clientEntityData = clientEntityDetailsReadPlatformService.retriveEntityDetails(clientId);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, clientEntityData, CLIENTENTITY_RESPONSE_DATA_PARAMETERS);
    }

    @GET
    @Path("{fileId}/attachment")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_OCTET_STREAM })
    public Response downloadFile(@PathParam("fileId") final Long fileId) {
        this.context.authenticatedUser().validateHasReadPermission(CLIENTENTITY_RESOURCE_NAME);
        final FileData fileData = this.clientEntityDetailsReadPlatformService.downloadFile(fileId);
        final ResponseBuilder response = Response.ok(fileData.file());
        response.header("Content-Disposition", "attachment; filename=\"" + fileData.name() + "\"");
        response.header("Content-Type", fileData.contentType());
        return response.build();
    }


    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String create(final String apiRequestBodyAsJson) {
        final CommandWrapper commandRequest = new CommandWrapperBuilder().createClientEntityDetails().withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
        return this.toApiJsonSerializer.serialize(result);
    }
    
    @POST
    @Consumes({ MediaType.MULTIPART_FORM_DATA })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createDocument(@FormDataParam("clientId") Long clientId,
            @HeaderParam("Content-Length") final Long fileSize, @FormDataParam("file") final InputStream inputStream,
            @FormDataParam("file") final FormDataContentDisposition fileDetails, @FormDataParam("file") final FormDataBodyPart bodyPart,
            @FormDataParam("registeredName") final String registeredName,
            @FormDataParam("tradingName") final String tradingName, @FormDataParam("dateOfIncorporation") final Long dateOfIncorporation) {
    	
    	
    	ClientEntityDetailsCommand entityDetailCommand = new ClientEntityDetailsCommand(clientId,registeredName,tradingName,dateOfIncorporation,fileDetails.getFileName(),bodyPart.getMediaType().toString());
    	DocumentCommand documentCommand = new DocumentCommand(null,null,"cliententitydetails",clientId,null,fileDetails.getFileName(),
                fileSize,bodyPart.getMediaType().toString(),null,null);
    	Long documentId = clientEntityDetailsWritePlatformService.createDocument(entityDetailCommand,documentCommand,inputStream);
    	return this.toApiJsonSerializer.serialize(CommandProcessingResult.resourceResult(documentId, null));
    }
    
}