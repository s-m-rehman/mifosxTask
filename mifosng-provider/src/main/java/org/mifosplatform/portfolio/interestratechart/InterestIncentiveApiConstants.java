package org.mifosplatform.portfolio.interestratechart;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface InterestIncentiveApiConstants {

    public static final String idParamName = "id";
    public static final String entityTypeParamName = "entityType";
    public static final String attributeNameParamName = "attributeName";
    public static final String conditionTypeParamName = "conditionType";
    public static final String attributeValueParamName = "attributeValue";
    public static final String incentiveTypeparamName = "incentiveType";
    public static final String amountParamName = "amount";
    public static final String deleteParamName = "delete";
    
    public static final String INCENTIVE_RESOURCE_NAME = "interest.rate.incentives";

    public static final Set<String> INTERESTRATE_INCENTIVE_CREATE_REQUEST_DATA_PARAMETERS = new HashSet<String>(Arrays.asList(idParamName,
            entityTypeParamName, attributeNameParamName, conditionTypeParamName, attributeValueParamName, incentiveTypeparamName,
            amountParamName));

    public static final Set<String> INTERESTRATE_INCENTIVE_UPDATE_REQUEST_DATA_PARAMETERS = new HashSet<String>(Arrays.asList(idParamName,
            entityTypeParamName, attributeNameParamName, conditionTypeParamName, attributeValueParamName, incentiveTypeparamName,
            amountParamName));

    public static final Set<String> INTERESTRATE_INCENTIVE_RESPONSE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList(idParamName,
            entityTypeParamName, attributeNameParamName, conditionTypeParamName, attributeValueParamName, incentiveTypeparamName,
            amountParamName));

}
