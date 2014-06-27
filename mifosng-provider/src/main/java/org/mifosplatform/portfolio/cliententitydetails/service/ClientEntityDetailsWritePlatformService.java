/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.cliententitydetails.service;

import java.io.InputStream;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.documentmanagement.command.DocumentCommand;
import org.mifosplatform.portfolio.cliententitydetails.command.ClientEntityDetailsCommand;

public interface ClientEntityDetailsWritePlatformService {

    CommandProcessingResult createClientEntityDetails(JsonCommand command);

	Long createDocument(ClientEntityDetailsCommand entityDetailCommand,
			DocumentCommand documentCommand, InputStream inputStream);


}