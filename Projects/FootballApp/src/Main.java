import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Club> clubs = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        List<Player> transferMarket = new ArrayList<>();
        Service service = new Service();

        //Clubs
        int clubId = 1;
        Club club1 = new Club(clubId, "Manchester United", 1000000, 1902);
        clubId++;
        clubs.add(club1);
        Club club2 = new Club(clubId,"Barcelona", 1000000, 1918);
        clubId++;
        clubs.add(club2);
        Club club3 = new Club(clubId,"Real Madrid", 1000000, 1902);
        clubId++;
        clubs.add(club3);


        //Players
        int playerId = 1;
        players.add(new Player(playerId, "David de Gea", 31, 200000, true, "Goalkeeper", "Manchester United")); playerId++;
        players.add(new Player(playerId, "Victor Lindelof", 27, 200000,true, "Defender", "Manchester United")); playerId++;
        players.add(new Player(playerId, "Paul Pogba", 20, 200000,false, "MidFielder", "Manchester United")); playerId++;
        players.add(new Player(playerId,"Bruno Fernandes", 24, 200000,false, "MidFielder", "Manchester United")); playerId++;
        players.add(new Player(playerId, "Anthony Martial", 28, 200000,false, "Forward", "Manchester United")); playerId++;

        players.add(new Player(playerId, "Neto", 32, 200000,true, "Goalkeeper", "Barcelona")); playerId++;
        players.add(new Player(playerId,"Eric Garcia", 20, 200000,true, "Mid centre", "Barcelona")); playerId++;
        players.add(new Player(playerId,"Dani Alves", 38, 200000,false, "Right back", "Barcelona")); playerId++;
        players.add(new Player(playerId,"Gavi", 17, 200000,false, "Mid fielder", "Barcelona"));playerId++;
        players.add(new Player(playerId,"Yusuf Demir", 18, 200000,false, "Right Winger", "Barcelona")); playerId++;
        players.add(new Player(playerId,"Sergio Aguero", 33, 200000,false, "Centre forward", "Barcelona")); playerId++;

        players.add(new Player(playerId,"Andrey Lunin", 22, 200000,true, "Goalkeeper", "Real Madrid")); playerId++;
        players.add(new Player(playerId,"Marcelo", 33, 200000,true, "Left-Back", "Real Madrid")); playerId++;
        players.add(new Player(playerId,"Toni Kroos", 31, 200000,false, "Central-Midfield", "Real Madrid")); playerId++;
        players.add(new Player(playerId,"Isco", 29, 200000,false, "Central-Attacker", "Real Madrid")); playerId++;
        players.add(new Player(playerId,"Karim Benzema", 33, 200000,false, "Central-Forward", "Real Madrid")); playerId++;
        players.add(new Player(playerId,"Eden Hazard", 30, 200000,false, "Left-Winger", "Real Madrid")); playerId++;






        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);


            System.out.println("Welcome to Football App");
            boolean active = true;
            while (active) {
                System.out.println("1.Club Menu\n2.Player Menu\n3.Exit\nChoose one option: ");
                int option = scannerInt.nextInt();
                switch (option){
                    case 1: {
                        boolean case1Menu = true;
                        while (case1Menu) {
                            System.out.println("1.Show Clubs\n2.Select a club by ID\n3.Back\nChoose one option: ");
                            int case1option = scannerInt.nextInt();
                            switch (case1option){
                                case 1:{
                                   service.showClubs(clubs);
                                }break;

                                case 2:{
                                    service.showClubs(clubs);
                                    System.out.print("Input club id: ");
                                    int selectedClubID = scannerInt.nextInt();
                                    for (Club club : clubs) {
                                        if(selectedClubID == club.getClubId()){
                                            System.out.println("Selected club : " + club.getName());
                                            int flag = 0;
                                            while (flag == 0){
                                                System.out.println("1.Show players\n2.Select a player id and put on the transfer market\n3.Buy a new player from the transfer market\n4.Back\nChoose one option: ");
                                                int selectedClubMenuOption = scannerInt.nextInt();
                                                switch (selectedClubMenuOption){
                                                    case 1: {
                                                        service.showClubPlayers(club, players);
                                                    }break;
                                                    case 2: {
                                                        service.showClubPlayers(club, players);
                                                        System.out.println("Input Player id: ");
                                                        int selectedPlayerID = scannerInt.nextInt();
                                                        for (Player player : players) {
                                                            if(player.getPlayerId() == selectedPlayerID){
                                                                player.setForSale(true);
                                                            }
                                                        }

                                                    }break;
                                                    case 3: {
                                                        service.showTransferMarket(club, players);
                                                        System.out.print("Input player ID in order to buy: ");
                                                        int buyingPlayerID = scannerInt.nextInt();
                                                        for (Player player : players) {
                                                            if(player.getPlayerId() == buyingPlayerID){
                                                                for (Club club4 : clubs) {
                                                                    if(player.getOwnedClub() == club4.getName()){
                                                                        club4.setBudget(club4.getBudget() + player.getPrice());
                                                                    }
                                                                }
                                                                club.setBudget(club.getBudget() - player.getPrice());
                                                                player.setOwnedClub(club.getName());
                                                            }
                                                        }

                                                    }break;

                                                    case 4: {
                                                        flag = 1;
                                                    }break;

                                                    default:{
                                                        System.out.println("Wrong option.");
                                                    }break;
                                                }


                                            }
                                        }
                                    }
                                    System.out.println("Club or id not found");

                                }break;

                                case 3:{
                                    case1Menu = false;
                                }break;

                                default:{
                                    System.out.println("Wrong option");
                                }break;
                            }


                        }

                    }break;

                    case 2: {

                        System.out.println("1.Show all players\n2.Add Player\n3.Add Club\n4.Back\nChoose one");
                        int flag = 0;
                        while (flag == 0){
                            int case2Option = scannerInt.nextInt();
                            switch (case2Option){
                                case 1:{
                                    players.forEach(player -> System.out.println(player.toString()));
                                }break;
                                case 2:{
                                    service.addPlayer(players, playerId);
                                    playerId++;
                                }break;
                                case 3:{
                                    System.out.println("Enter name of the club: ");
                                    String name = scannerStr.nextLine();

                                    System.out.println("Enter budget of the club : ");
                                    double budget = scannerInt.nextInt();

                                    System.out.println("");




                                    Club club = new Club();
                                }break;
                                case 4:{
                                    flag = 1;
                                }break;
                                default:{
                                    System.out.println("Wrong option");
                                }break;
                            }
                        }

                    }break;

                    case 3: {
                        active = false;
                    }break;
                    default:{
                        System.out.println("Wrong Option");
                    }break;
                }


            }







        }
}

