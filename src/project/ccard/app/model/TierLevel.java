package project.ccard.app.model;

public enum TierLevel {
    GOLD(14, 12),
    SILVER(16, 18),
    BRONZE(20, 22);

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
