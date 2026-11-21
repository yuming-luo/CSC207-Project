package entity;

public class Weather {
    private final int Code;
    private final String Condition;
    private final int is_day;
    private final String image;

    public Weather(int code, String condition, int is_day, String image) {
        this.Code = code;
        this.Condition = condition;
        this.is_day = is_day;
        this.image = image;
    }
    public int getCode() { return this.Code; }
    public String getCondition() { return this.Condition; }
    public int getIs_Day() { return this.is_day;}
    public String getImage() {return this.image; }
}
