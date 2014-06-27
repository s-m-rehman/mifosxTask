package org.mifosplatform.portfolio.savings.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.mifosplatform.portfolio.interestratechart.domain.InterestIncentivesFields;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_savings_interest_incentives")
public class DepositAccountInterestIncentives extends AbstractPersistable<Long> {

    @SuppressWarnings("unused")
    @ManyToOne
    @JoinColumn(name = "deposit_account_interest_rate_slab_id", nullable = false)
    private DepositAccountInterestRateChartSlabs depositAccountInterestRateChartSlabs;

    @Embedded
    private InterestIncentivesFields interestIncentivesFields;

    protected DepositAccountInterestIncentives() {

    }

    private DepositAccountInterestIncentives(final DepositAccountInterestRateChartSlabs depositAccountInterestRateChartSlabs,
            final InterestIncentivesFields interestIncentivesFields) {
        this.depositAccountInterestRateChartSlabs = depositAccountInterestRateChartSlabs;
        this.interestIncentivesFields = interestIncentivesFields;
    }

    public static DepositAccountInterestIncentives from(final DepositAccountInterestRateChartSlabs depositAccountInterestRateChartSlabs,
            final InterestIncentivesFields interestIncentivesFields) {
        return new DepositAccountInterestIncentives(depositAccountInterestRateChartSlabs, interestIncentivesFields);
    }

    public void updateDepositAccountInterestRateChartSlabs(DepositAccountInterestRateChartSlabs depositAccountInterestRateChartSlabs) {
        this.depositAccountInterestRateChartSlabs = depositAccountInterestRateChartSlabs;
    }

    public InterestIncentivesFields interestIncentivesFields() {
        return this.interestIncentivesFields;
    }

}
