import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Service implements ServiceImpl{
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);




    void tranferPlayerProcess(int playerId){

    }


    void showTransferMarket(Club club, List<Player> players){
        for (Player player : players) {
            if (player.isForSale() == true && club.getName() != player.getOwnedClub()) {
                System.out.println(player.toString());
            }
        }
    }

    void showClubPlayers(Club club, List<Player> players){
        for (Player player : players) {
            if (player.getOwnedClub().equals(club.getName())) {
                System.out.println(player.toString());
            }
        }
    }

    void showClubs(List<Club> clubs){
        for (Club club : clubs) {
            System.out.println(club.toString());
        }
    }

    @Override
    public void addPlayer(List<Player> players, int playerId) {
        System.out.println("Enter fullName of the player: ");
        String fullName = scannerStr.nextLine();

        System.out.println("Enter age: ");
        int age = scannerInt.nextInt();

        System.out.println("Enter market value of the Player: ");
        int price = scannerInt.nextInt();

        System.out.println("Enter position of the player: ");
        String position = scannerStr.nextLine();

        System.out.println("Enter club of the Player: ");
        String ownedClub = scannerStr.nextLine();

        Player player = new Player(playerId, fullName, age, price, false, position, ownedClub);

        players.add(player);
    }
}
