package denpear.javatrain.learn.threading;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализовать контейнер типа «Object Pool» для хранения и выдачи переиспользуемых объектов:
 * - Требования:
 * -- Generic
 * -- Методы:
 * 1. T get() – вернуть незанятый объект из пула во временное пользование
 * 2. void release(T object) – вернуть объект в пул
 * -- Объект, возвращенный из get(), больше не должен возвращаться из get(), пока его не вернут через release(..)
 * -- Все незанятые объекты в пуле равнозначны, из get() можно возвращать любой незанятый
 * -- !!!!!!!==============> Первичную инициализацию, поддержку многопоточности не реализовывать
 * -- (В последнюю очередь) Если при вызове get() незанятых объектов не нашлось, создавать новый
 * <p>
 * public class Pool<T> {
 * <p>
 * // ...
 * <p>
 * }
 * <p>
 * interface ObjectPoolInterface<T> {
 * public T get();
 * <p>
 * public void release(T object);
 * <p>
 * }
 * <p>
 * public class ObjectPool implements ObjectPoolInterface<Object> {
 * <p>
 * <p>
 * public static void main(String[] args) {
 * <p>
 * ConcurrentHashMap<String, Integer> pool = new ConcurrentHashMap<>();
 * <p>
 * pool.put("First", 10);
 * pool.put("Second", 20);
 * pool.put("Third", 30);
 * pool.put("Forth", 40);
 * <p>
 * <p>
 * }
 *
 * @param <T>
 * @Override public Object get() {
 * return null;
 * }
 * @Override public void release(Object object) {
 * <p>
 * }
 */


interface ObjectPoolInterface<T> {
    public T get();

    public void release(T object);
}


public class ObjectPool<T> implements ObjectPoolInterface<T> {
    private Map<T, Boolean> pooledObjects = new ConcurrentHashMap<>(); // false - занято, true - свободно

    public static void main(String[] args) {
        System.out.println(new ObjectPool<>().get());
        System.out.println(new ObjectPool<>().get());
    }

    @Override
    public T get() {
        pooledObjects.put((T) "First", false);
        pooledObjects.put((T) "Second", false);
        pooledObjects.put((T) "Third", false);
        pooledObjects.put((T) "Forth", false);

        Optional<Map.Entry<T, Boolean>> firstFreeObject = pooledObjects.entrySet().stream().filter(e -> e.getValue().equals(true)).findFirst();
        if (firstFreeObject.isPresent()) {
            pooledObjects.put((T) firstFreeObject.get().getKey(), false); // мы его забрали в пользование
            return (T) firstFreeObject.get().getKey();
        } else {
            //создать новый объект, добавить его в пул
            String potentialKey = UUID.randomUUID().toString();
            pooledObjects.put((T) potentialKey, false);
            return (T) potentialKey;
        }
    }

    /**
     * Вывод: сходу при написании учитываются два пути развития ситуации.
     * Инженерная дихотомия вариантов в реальной жизни.
     * ToDo: сделать пул разделяемой среди нескольких потоков коллекцией,
     * потому что сейчас состав начальной коллекции всегда одинаков:
     * используется ананимный внутренний класс для вызова, где к тому же
     * вышиты исходные данные в коллекции
     *
     * @param object
     */
    @Override
    public void release(T object) {
        if (!pooledObjects.containsKey(object) || pooledObjects.get(object)) {
            throw new IllegalArgumentException();
        } else {
            pooledObjects.put(object, true);
        }
    }

}


