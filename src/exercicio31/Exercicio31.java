package exercicio31;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Exercicio31 {

    public static final String ODB_NAME ="BaseDatosax";

    public static void main(String[] args) throws Exception {
        
        consulta_xogadores();
        
        
        

    }

    public static void step1() throws Exception {
        ODB odb = null;
        try {

            Sport sport = new Sport("tenis");

            odb = ODBFactory.open(ODB_NAME);

            odb.store(sport);
        } finally {
            if (odb != null) {

                odb.close();
            }
        }

    }

    public static void step2() throws Exception {

        Sport volleyball = new Sport("volley-ball");
        Sport tenis = new Sport("Tenis");
        Player xogador_1 = new Player("olivier", new Date(), volleyball, 1000);
        Player xogador_2 = new Player("pierre", new Date(), volleyball, 1500);
        Player xogador_3 = new Player("elohim", new Date(), volleyball, 2000);
        Player xogador_4 = new Player("minh", new Date(), volleyball, 1300);
        Player xogador_5 = new Player("luis", new Date(), tenis, 1600);
        Player xogador_6 = new Player("carlos", new Date(), tenis, 2000);
        Player xogador_7 = new Player("luis", new Date(), tenis, 1500);
        Player xogador_8 = new Player("jose", new Date(), tenis, 3000);

        List list1 = new ArrayList();
        list1.add(xogador_1);
        list1.add(xogador_2);
        List list2 = new ArrayList();
        list2.add(xogador_3);
        list2.add(xogador_4);
        List list3 = new ArrayList();
        list3.add(xogador_5);
        list3.add(xogador_6);
        List list4 = new ArrayList();
        list4.add(xogador_7);
        list4.add(xogador_8);

        Team team1 = new Team("Paris", (list1));
        Team team2 = new Team("Montpellier", (list2));
        Team team3 = new Team("Bordeux", (list3));
        Team team4 = new Team("Lion", (list4));

        Game game1 = new Game(new Date(), volleyball, team1, team2);
        Game game2 = new Game(new Date(), tenis, team3, team4);
        ODB odb = null;
        
        try {
            odb = ODBFactory.open(ODB_NAME);
            
            odb.store(game1);
            odb.store(game2);
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
    
    public static void step3() throws Exception {
        ODB odb = null;
        try {

            odb = ODBFactory.open(ODB_NAME);
            IQuery query = odb.criteriaQuery(Player.class, Where.equal("name", "pierre"));
            Objects<Player> players = odb.getObjects(query);
            System.out.println("\nStep 3 : Players with name olivier");
            while (players.hasNext()) {
                int i = 0;
                System.out.println((i + 1) + "\t: " + players.next());
            }
        } finally {
            if (odb != null) {

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
    public static void consulta_xogadores() throws Exception{
        ODB odb = ODBFactory.open(ODB_NAME);
        Objects<Player> players = odb.getObjects(Player.class);

        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName()+","+player.getFavoriteSport().getName()+","+player.getSalario());
        }

        odb.close();

    }

}
