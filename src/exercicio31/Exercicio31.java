package exercicio31;

import java.util.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Exercicio31 {

    public static final String ODB_NAME = "BaseDatos";

    public static void main(String[] args) throws Exception {
        step1();
        displaySports();
    }

    public static void step1() throws Exception {
        ODB odb = null;
        try {

            Sport sport = new Sport("volley-ball");

            odb = ODBFactory.open(ODB_NAME);

            odb.store(sport);
        } finally {
            if (odb != null) {

                odb.close();
            }
        }

    }

    public static void step2() throws Exception {
        // Create instance
        Sport volleyball = new Sport("volley-ball");
        Player xogador_1 = new Player("olivier", new Date(), volleyball,1000);
        Player xogador_2 = new Player("pierre", new Date(), volleyball,1500);   
        Player xogador_3 = new Player("elohim", new Date(), volleyball,2000);
        Player xogador_4 = new Player("minh", new Date(), volleyball,1300);





// Create two teams
        Team team1 = new Team("Paris",(lista de xogadores) xogador_1 xogador_2);
        Team team2 = new Team("Montpellier");
// Set players for team1
        team1.addPlayer(player1);
        team1.addPlayer(player2);
// Set players for team2
        team2.addPlayer(player3);
        team2.addPlayer(player4);
// Then create a volley ball game for the two teams
        Game game = new Game(new Date(), volleyball, team1, team2);
        ODB odb = null;
        try {
// Open the database
            odb = ODBFactory.open(ODB_NAME);
// Store the object
            odb.store(game);
        } finally {
            if (odb != null) {
// Close the database
                odb.close();
            }
        }
    }

    public static void displaySports() throws Exception {

        ODB odb = ODBFactory.open(ODB_NAME);
        Objects<Sport> sports = odb.getObjects(Sport.class);

        Sport sport = null;
        while (sports.hasNext()) {
            sport = sports.next();
            System.out.println(sport.getName());
        }

        odb.close();

    }

}
