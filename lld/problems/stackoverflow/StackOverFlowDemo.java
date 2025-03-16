package stackoverflow;

import stackoverflow.models.Role;
import stackoverflow.models.Tag;
import stackoverflow.models.User;
import stackoverflow.services.StackOverFlowService;
import stackoverflow.services.UserService;

import java.util.List;
import java.util.UUID;

public class StackOverFlowDemo {

    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        StackOverFlowService stackOverFlowService = StackOverFlowService.getInstance(userService);

        User user = new User("2", "Ganapathy", "gomma", Role.USER);
        User user2 = new User("1", "Tamil", "gomma", Role.USER);
        userService.addUser(user);
        userService.addUser(user2);
        Tag tag = Tag.builder().name("Java").id("1").build();
        stackOverFlowService.postQuestion("2","What is Java", "Normal Question", List.of(tag));
        stackOverFlowService.postAnswer("1","gomma","1");
        System.out.println(user.getQuestions());
        System.out.println(user2.getAnswers());

    }




}
