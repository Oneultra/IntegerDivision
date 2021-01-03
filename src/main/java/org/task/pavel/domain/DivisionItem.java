package org.task.pavel.domain;

public class DivisionItem {
    private final int divisor;
    private final int dividend;

    public DivisionItem(int dividend, int divisor) {
        this.divisor = divisor;
        this.dividend = dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getDividend() {
        return dividend;
    }
}
