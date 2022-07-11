package project.framework.core.accountdetails.model.account;

public enum TransactionType {
    INCOMING,
    OUTGOING;

    @Override
    public String toString() {
        return switch (this) {
            case INCOMING -> "INCOMING";
            case OUTGOING -> "OUTGOING";
        };
    }
}
