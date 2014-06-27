package org.mifosplatform.portfolio.search.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.portfolio.loanaccount.domain.LoanStatus;

public class AdHocQuerySearchConditions {

    private final List<String> loanStatus;
    private final List<Long> loanProducts;
    private final List<Long> offices;
    private final String loanDateOption;
    private final LocalDate loanFromDate;
    private final LocalDate loanToDate;
    private final Boolean includeOutStandingAmountPercentage;
    private final String outStandingAmountPercentageCondition;
    private final BigDecimal minOutStandingAmountPercentage;
    private final BigDecimal maxOutStandingAmountPercentage;
    private final BigDecimal outStandingAmountPercentage;
    private final Boolean includeOutstandingAmount;
    private final String outstandingAmountCondition;
    private final BigDecimal minOutstandingAmount;
    private final BigDecimal maxOutstandingAmount;
    private final BigDecimal outstandingAmount;

    public static AdHocQuerySearchConditions instance(final List<String> loanStatus, final List<Long> loanProducts,
            final List<Long> offices, final String loanDateOption, final LocalDate loanFromDate, final LocalDate loanToDate,
            final Boolean includeOutStandingAmountPercentage, final String outStandingAmountPercentageCondition,
            final BigDecimal minOutStandingAmountPercentage, final BigDecimal maxOutStandingAmountPercentage,
            final BigDecimal outStandingAmountPercentage, final Boolean includeOutstandingAmountParamName,
            final String outstandingAmountCondition, final BigDecimal minOutstandingAmount, final BigDecimal maxOutstandingAmount,
            final BigDecimal outstandingAmount) {

        return new AdHocQuerySearchConditions(loanStatus, loanProducts, offices, loanDateOption, loanFromDate, loanToDate,
                includeOutStandingAmountPercentage, outStandingAmountPercentageCondition, minOutStandingAmountPercentage,
                maxOutStandingAmountPercentage, outStandingAmountPercentage, includeOutstandingAmountParamName, outstandingAmountCondition,
                minOutstandingAmount, maxOutstandingAmount, outstandingAmount);

    }

    public AdHocQuerySearchConditions(final List<String> loanStatus, final List<Long> loanProducts, final List<Long> offices,
            final String loanDateOption, final LocalDate loanFromDate, final LocalDate loanToDate,
            final Boolean includeOutStandingAmountPercentage, final String outStandingAmountPercentageCondition,
            final BigDecimal minOutStandingAmountPercentage, final BigDecimal maxOutStandingAmountPercentage,
            final BigDecimal outStandingAmountPercentage, final Boolean includeOutstandingAmount, final String outstandingAmountCondition,
            final BigDecimal minOutstandingAmount, final BigDecimal maxOutstandingAmount, final BigDecimal outstandingAmount) {

        this.loanStatus = loanStatus;
        this.loanProducts = loanProducts;
        this.offices = offices;
        this.loanDateOption = loanDateOption;
        this.loanFromDate = loanFromDate;
        this.loanToDate = loanToDate;
        this.includeOutStandingAmountPercentage = includeOutStandingAmountPercentage;
        this.outStandingAmountPercentageCondition = outStandingAmountPercentageCondition;
        this.minOutStandingAmountPercentage = minOutStandingAmountPercentage;
        this.maxOutStandingAmountPercentage = maxOutStandingAmountPercentage;
        this.outStandingAmountPercentage = outStandingAmountPercentage;
        this.includeOutstandingAmount = includeOutstandingAmount;
        this.outstandingAmountCondition = outstandingAmountCondition;
        this.minOutstandingAmount = minOutstandingAmount;
        this.maxOutstandingAmount = maxOutstandingAmount;
        this.outstandingAmount = outstandingAmount;

    }

    public List<String> getLoanStatus() {
        return getStatusVluesFromStatusCodes();
    }

    private List<String> getStatusVluesFromStatusCodes() {
        List<String> loanStatusValues = new ArrayList<String>();
        if (this.loanStatus != null) {
            for (String statusCode : this.loanStatus) {

                if (statusCode.equalsIgnoreCase("active")) {
                    loanStatusValues.add(LoanStatus.ACTIVE.getValue().toString());
                }

                if (statusCode.equalsIgnoreCase("overpaid")) {
                    loanStatusValues.add(LoanStatus.OVERPAID.getValue().toString());
                }

                if (statusCode.equalsIgnoreCase("closed")) {
                    loanStatusValues.add(LoanStatus.CLOSED_OBLIGATIONS_MET.getValue().toString());
                }

                if (statusCode.equalsIgnoreCase("writeoff")) {
                    loanStatusValues.add(LoanStatus.CLOSED_WRITTEN_OFF.getValue().toString());
                }

                if (statusCode.equalsIgnoreCase("arrears")) {
                    // TODO - No status code is there for loans which are in
                    // active bad standing
                }

                if (statusCode.equalsIgnoreCase("all")) {
                    loanStatusValues.add("all");
                }
            }
        }

        return loanStatusValues;
    }

    public List<Long> getLoanProducts() {
        return this.loanProducts;
    }

    public List<Long> getOffices() {
        return this.offices;
    }

    public String getLoanDateOption() {
        return this.loanDateOption;
    }

    public LocalDate getLoanFromDate() {
        return this.loanFromDate;
    }

    public LocalDate getLoanToDate() {
        return this.loanToDate;
    }

    public Boolean getIncludeOutStandingAmountPercentage() {
        return this.includeOutStandingAmountPercentage;
    }

    public String getOutStandingAmountPercentageCondition() {
        return this.outStandingAmountPercentageCondition;
    }

    public BigDecimal getMinOutStandingAmountPercentage() {
        return this.minOutStandingAmountPercentage;
    }

    public BigDecimal getMaxOutStandingAmountPercentage() {
        return this.maxOutStandingAmountPercentage;
    }

    public BigDecimal getOutStandingAmountPercentage() {
        return this.outStandingAmountPercentage;
    }

    public Boolean getIncludeOutstandingAmount() {
        return this.includeOutstandingAmount;
    }

    public String getOutstandingAmountCondition() {
        return this.outstandingAmountCondition;
    }

    public BigDecimal getMinOutstandingAmount() {
        return this.minOutstandingAmount;
    }

    public BigDecimal getMaxOutstandingAmount() {
        return this.maxOutstandingAmount;
    }

    public BigDecimal getOutstandingAmount() {
        return this.outstandingAmount;
    }

}
