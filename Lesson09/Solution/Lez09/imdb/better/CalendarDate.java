package Lez09.imdb.better;

public class CalendarDate {
    private int day;
    private int month;
    private int year;

    public CalendarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CalendarDate(String format) throws IllegalArgumentException, NumberFormatException {
        String calendar[] = format.split("/");
        if (calendar.length != 3 ||
                calendar[0].length() != 2 ||
                calendar[1].length() != 2 ||
                calendar[2].length() != 4)
        throw new IllegalArgumentException(format+" does not respect the format dd/mm/yyyy");
        this.day = Integer.valueOf(calendar[0]);
        this.month = Integer.valueOf(calendar[1]);
        this.year = Integer.valueOf(calendar[2]);
    }

    @Override
    public String toString() {
        return String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+String.format("%04d",year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarDate that = (CalendarDate) o;
        return day == that.day &&
                month == that.month &&
                year == that.year;
    }

    /* Some java default containers (e.g. HashSet) may also require the following function:

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    */
}
