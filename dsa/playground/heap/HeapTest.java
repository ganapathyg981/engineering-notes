package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapTest {

    public static void main(String[] args) {
        PriorityQueue<Person> lolai = new PriorityQueue<>(Comparator.comparingInt(a->a.age));
        PriorityQueue<Person> malai = new PriorityQueue<>((a,b)->a.name.compareTo(a.name));

    }
}


class Person {
    String name;
    int age;
}
