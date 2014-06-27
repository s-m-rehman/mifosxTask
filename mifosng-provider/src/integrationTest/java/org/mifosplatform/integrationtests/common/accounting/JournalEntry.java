package org.mifosplatform.integrationtests.common.accounting;

public class JournalEntry {

    public enum TransactionType {
        CREDIT("CREDIT"), DEBIT("DEBIT");

        private TransactionType(final String type) {
            this.type = type;
        }

        private final String type;

        @Override
        public String toString() {
            return this.type;
        }
    }

    private final Float transactionAmount;
    private final TransactionType transactionType;
    private final Integer officeId;

    public JournalEntry(final float transactionAmount, final TransactionType type) {
        this.transactionAmount = transactionAmount;
        this.transactionType = type;
        this.officeId = null;
    }

    public Float getTransactionAmount() {
        return this.transactionAmount;
    }

    public String getTransactionType() {
        return this.transactionType.toString();
    }

}
