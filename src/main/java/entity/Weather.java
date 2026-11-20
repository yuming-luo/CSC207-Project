package entity;

public class Weather {
    private String City;
    private String Condition;
    private boolean is_day;

    public Weather(String city, String condition, boolean is_day) {
        this.City = city;
        this.Condition = condition;
        this.is_day = is_day;
    }

    public String getCondition() { return Condition; }
    public boolean getIs_Day() { return is_day;}
}
