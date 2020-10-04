package soundweb.soundweb.Domain.Entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class DayEmbed {
    @Column(name = "day_1")
    private String day1;
    @Column(name = "day_2")
    private String day2;
    @Column(name = "day_3")
    private String day3;
    @Column(name = "day_4")
    private String day4;
    @Column(name = "day_5")
    private String day5;
    @Column(name = "day_6")
    private String day6;
    @Column(name = "day_7")
    private String day7;

    protected DayEmbed() {
    }

    public DayEmbed(String day1, String day2, String day3, String day4, String day5, String day6, String day7) {
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
    }
}
