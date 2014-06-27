package org.mifosplatform.infrastructure.survey.handler;

import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.survey.service.WriteLikelihoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Cieyou on 3/12/14.
 */

@Service
public class UpdateLikelihoodCommandHandler implements NewCommandSourceHandler {


    private final WriteLikelihoodService writePlatformService;

    @Autowired
    public UpdateLikelihoodCommandHandler(final WriteLikelihoodService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.writePlatformService.update(command.entityId(), command);
    }
}
