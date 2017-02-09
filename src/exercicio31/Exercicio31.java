package exercicio31;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import static sun.audio.AudioPlayer.player;

public class Exercicio31 {

    public static final String ODB_NAME = "BaseDatosaux";
    static ODB odb;
    static String depName = null;

    public static void main(String[] args) throws Exception {

        
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

    public static void displaySports() throws Exception {

        odb = ODBFactory.open(ODB_NAME);
        Objects<Sport> sports = odb.getObjects(Sport.class);

        Sport sport = null;
        while (sports.hasNext()) {
            sport = sports.next();
            System.out.println(sport.getName());
        }

        odb.close();

    }

    public static void consulta_xogadores() throws Exception {
        odb = ODBFactory.open(ODB_NAME);
        Objects<Player> players = odb.getObjects(Player.class);

        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName() + "," + player.getFavoriteSport().getName() + "," + player.getSalario());
        }

        odb.close();

    }

    public static void actualiza_por_nome_xogador(ODB odb, String nome_antigo, String nome_novo) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("name", nome_antigo));
        Objects<Player> players = odb.getObjects(query);
        while (players.hasNext()) {
            Player player = (Player) players.next();
            player.setName(nome_novo);
            odb.store(player);

        }
        odb.close();
    }

    public static void xogadoresDeporte(ODB odb, String deporte) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery query_aux = odb.criteriaQuery(Sport.class, Where.equal("name", deporte));
        Objects<Sport> sports = odb.getObjects(query_aux);
        Sport sport = null;
        while (sports.hasNext()) {
            sport = sports.next();
            System.out.println(sport.getName());
        }
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("favoriteSport", sport));
        Objects<Player> players = odb.getObjects(query);
        players.size();
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName());

        }
        odb.close();
    }

    public static void salario_menos(ODB odb, int cantidade) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery query = odb.criteriaQuery(Player.class, Where.lt("salario", cantidade));
        Objects<Player> players = odb.getObjects(query);
        players.size();
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName());

        }
        odb.close();
    }

    public static void nomes_por_deporte(ODB odb, String deporte, String nome_novo) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery query_aux = odb.criteriaQuery(Sport.class, Where.equal("name", deporte));
        Objects<Sport> sports = odb.getObjects(query_aux);
        Sport sport = null;
        while (sports.hasNext()) {
            sport = sports.next();

            System.out.println(sport.getName());
        }
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("favoriteSport", sport));
        Objects<Player> players = odb.getObjects(query);

        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            player.setName(nome_novo);
            odb.store(player);

        }
        odb.close();
    }

    public static void nativeQueryXogadoresDeporte(ODB odb, final String deporte) {
        odb = ODBFactory.open(ODB_NAME);
        IQuery query_aux = new SimpleNativeQuery() {
            public boolean match(Player player) {
                return player.getFavoriteSport().getName().toLowerCase().startsWith(deporte);
            }
        };
        Objects<Player> players = odb.getObjects(query_aux);
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName());

        }

        odb.close();
    }

    public static void borra_xogadores_por_nome(ODB odb, String nome) {
            odb = ODBFactory.open(ODB_NAME);
            IQuery query = odb.criteriaQuery(Player.class, Where.equal("name",nome));
            Objects<Player> players = odb.getObjects(query);
            Player player = null;
            while(players.hasNext()){
            player=players.next();
            odb.delete(player);
           
            }     
          odb.close();
            }
    public static void listar_xogadores_nome_deporte(ODB odb,String nome,String depor){
        odb = ODBFactory.open(ODB_NAME);
        IQuery sport_aux= odb.criteriaQuery(Sport.class, Where.equal("name",depor));
        Sport sport=(Sport) odb.getObjects(sport_aux).next();
        System.out.println(sport.getName());
                       
        IQuery queryaux =odb.criteriaQuery(Player.class, Where.and().add(Where.equal("favoriteSport.name","Tenis"))
                           .add(Where.equal("name", nome)));
        Objects <Player> player=odb.getObjects(queryaux);

        while(player.hasNext()){
            System.out.println(player.next().getName()+" ");
            
        }
    }
    public static void actualizar_salario(){
        IQuery query=odb.criteriaQuery(Team.class, Where.and().add(Where.equal("name","Bordeux")));
        Team play=(Team) odb.getObjects(query).getFirst();
        Iterator <Player> player= play.players.iterator();
       
        while(player.hasNext()){
            Player aux=player.next();
            System.out.println(aux.getName()+" "+aux.getSalario());
            if(aux.getName().equals("luis")){
                aux.setSalario(aux.getSalario()+100);
                 odb.store(aux);
        
            }
        }
    }
    }
    
    

