import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {

    private final List<T> list;

    private MyStream(Collection<? extends T> collection) {
        this.list = new ArrayList<>(collection);
    }

    /**
     * статический метод, который принимает коллекцию и создает новый объект Streams
     */
    public static <T> MyStream<T> of(Collection<? extends T> collection) {
        return new MyStream(collection);
    }

    /**
     * оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде.
     */
    public MyStream<T> filter(Predicate<? super T> predicate) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T object = iterator.next();
            if (!predicate.test(object)) iterator.remove();
        }
        return this;
    }

    /**
     * преобразует элемент в другой
     */
    public MyStream<T> transform(Function<? super T, ? extends T> function) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T object = iterator.next();
            T newObject = function.apply(object);
            iterator.set(newObject);
        }
        return this;
    }

    /**
     * принимает 2 лямбды для создания мапы, в одной указывается, что использовать в качестве ключа, в другой, что в качестве значения.
     */
    public <K,V> Map<K,V> toMap(Function<? super T, ? extends K> keyMap, Function<? super T, ? extends V> valueMap ) {
        Map<K,V> map = new HashMap<>();
        for(T x : list) {
            K key = keyMap.apply(x);
            V value = valueMap.apply(x);
            map.put(key, value);
        }
        return map;
    }

}
