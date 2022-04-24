package Controller;

import Model.Game;
import Model.Users.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

public class MainController extends Controller{

    public void logoutUser() {
        Game.setCurrentUser(null);
        changeMenu("LoginMenu");
    }

    public void playGame(Matcher matcher) {

    }

    public void loadGame(Matcher matcher){

    }

    public String menuChange(String menuName){
        if (!menuName.equals("login menu") && !menuName.equals("profile menu")){
            return ("invalid menu name\navailable menu names : \"login menu\" \"profile menu\"");
        }

        return ("menu changed successfully");
    }

    public ArrayList<User> getSortedList(){
        Comparator<User> comparator = Comparator.comparingInt(User::getScore).reversed().thenComparing(User::getUsername);
        ArrayList<User> sortedUsers = new ArrayList<>(Game.getUsers());
        sortedUsers.sort(comparator);

        return sortedUsers;
    }
}
