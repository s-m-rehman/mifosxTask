package org.mifosplatform.portfolio.cliententitydetails.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientEntityDetailsRepository extends JpaRepository<ClientEntityDetails, Long>,JpaSpecificationExecutor<ClientEntityDetails>{

}
