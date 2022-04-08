package Model;

public class Range
{
    private int low;
    private int high;

    public Range(int low, int high)
    {
        this.low = low;
        this.high = high;
    }

    public Range(int exact)
    {
        this.low = exact;
        this.high = exact;
    }

    public boolean contains(int number)
    {
        return (number >= low && number <= high);
    }
}
