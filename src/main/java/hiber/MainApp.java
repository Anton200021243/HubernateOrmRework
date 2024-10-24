package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("lada", 2107);
      Car car2 = new Car("toyota", 200);
      Car car3 = new Car("bmv", 3);
      Car car4 = new Car("mers", 60);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().toString());
         System.out.println();
      }

      User userByCar = userService.getUserByCar(car3);
      System.out.println("Id = "+userByCar.getId());
      System.out.println("First Name = "+userByCar.getFirstName());
      System.out.println("Last Name = "+userByCar.getLastName());
      System.out.println("Email = "+userByCar.getEmail());
      System.out.println("Car = " + userByCar.getCar().toString());
      System.out.println();

      context.close();
   }
}
