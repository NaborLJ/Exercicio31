package exercicio31;

import java.util.Date;

public class Game {
    private Date when;
    private Sport sport;
    private Team team1;
    private Team team2;
    private String result;

    public Game(Date when, Sport sport, Team team1, Team team2, String result) {
        this.when = when;
        this.sport = sport;
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
    }
}
