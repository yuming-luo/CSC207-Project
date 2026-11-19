package entity;

public class Weather {
    private final int Code;
    private final String Condition;
    private final boolean is_day;
    private final String image;

    public Weather(int code, String condition, boolean is_day, String image) {
        this.Code = code;
        this.Condition = condition;
        this.is_day = is_day;
        this.image = image;
    }
    public int getCode() { return this.Code; }
    public String getCondition() { return this.Condition; }
    public boolean getIs_Day() { return this.is_day;}
    public String getImage() {return this.image; }
}
