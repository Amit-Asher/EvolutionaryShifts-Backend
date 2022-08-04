package Model;

public class Range
{
    private int min;
    private int max;

    public Range(int low, int high)
    {
        this.min = low;
        this.max = high;
    }

    public Range(int exact)
    {
        this.min = exact;
        this.max = exact;
    }

    public String toString() {
        if (this.min == this.max) {
            return Integer.toString(min);
        }
        return String.format("%s-%s", min, max);
    }

    public boolean contains(int number)
    {
        return (number >= min && number <= max);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean equals(Range range) {
        return this.min == range.min &&
                this.max == range.max;
    }
}
