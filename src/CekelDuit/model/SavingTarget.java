package CekelDuit.model;

import java.time.LocalDate;

public class SavingTarget {

    private String name;
    private double targetAmount;
    private double currentAmount;
    private LocalDate targetDate;
    private String icon;

    public SavingTarget(String name, double targetAmount, LocalDate targetDate, String icon) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.targetDate = targetDate;
        this.icon = icon;
        this.currentAmount = 0;
    }

    public void addSaving(double amount) {
        currentAmount += amount;
    }

    public boolean isCompleted() {
        return currentAmount >= targetAmount;
    }

    // ===== GETTER =====
    public String getName() {
        return name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public String getIcon() {
        return icon;
    }
}
