import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Петр", 27));
        list.add(new Person("Екатерина", 24));
        list.add(new Person("Анатолий", 36));
        list.add(new Person("Аркадий", 19));

        Map map = MyStream.of(list)
                .filter(p -> p.getAge() > 20)
                .transform( p -> new Person(p.getName(), p.getAge() + 30))
                .toMap(p -> p.getName(), p -> p);

        System.out.println(map);
    }
}
