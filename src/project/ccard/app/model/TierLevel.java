package project.ccard.app.model;

public enum TierLevel {
    GOLD(0.14, 0.12),
    SILVER(0.16, 0.18),
    BRONZE(0.20, 0.22);

    private final double monthlyInterest;
    private final double minimumPayment;

    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    public double getMinimumPayment() {
        return minimumPayment;
    }

    TierLevel(double monthlyInterest, double minimumPayment) {
        this.monthlyInterest = monthlyInterest;
        this.minimumPayment = minimumPayment;
    }
}
