package org.mifosplatform.infrastructure.survey.domain;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.survey.api.LikelihoodApiConstants;
import org.mifosplatform.infrastructure.survey.data.LikelihoodStatus;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Cieyou on 3/12/14.
 */
@Entity
@Table(name="ppi_likelihoods_ppi")
public final class Likelihood extends AbstractPersistable<Long> {

    @Column(name = "ppi_name", nullable = false)
    private String ppiName;

    @Column(name = "likelihood_id", nullable = false)
    private Long likelihoodId;

    @Column(name = "enabled", nullable = false)
    private Long enabled;

    public Map<String, Object> update(final JsonCommand command)
    {
        final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(7);

        final boolean enabled = command.booleanPrimitiveValueOfParameterNamed(LikelihoodApiConstants.ACTIVE);

        Long changeToValue = null;

        if(enabled){
            changeToValue =  LikelihoodStatus.ENABLED;
        }else{
             changeToValue =  LikelihoodStatus.DISABLED;
        }


        if(!changeToValue.equals(this.enabled))
        {
            actualChanges.put(LikelihoodApiConstants.ACTIVE,enabled);
            this.enabled = changeToValue;
        }

        return actualChanges;
    }

    public boolean isActivateCommand(final JsonCommand command)
    {
        return command.booleanPrimitiveValueOfParameterNamed(LikelihoodApiConstants.ACTIVE);
    }

    public String getPpiName() {
        return ppiName;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public void disable()
    {
        this.enabled = LikelihoodStatus.DISABLED;
    }
}
