package v.yeikovych.extent;

import v.yeikovych.util.SerializationUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.*;

import static v.yeikovych.util.ValidationUtils.*;

public class Bird implements Extent {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final List<Bird> birds = new ArrayList<>();

    private String name;
    private int age;

    @SuppressWarnings("all")
    public Bird(Bird bird) {
        this(bird.getName(), bird.getAge());
    }

    public Bird(String name, int age) {
        setName(name);
        setAge(age);
        birds.add(this);
        SerializationUtil.registerExtent(birds, Bird.class);
        SerializationUtil.writeExtent();
    }

    public static List<Bird> getExtent() {
        return Collections.unmodifiableList(birds);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        throwIfFalse(() -> isValidAge(age));
        if (this.age != age) {
            this.age = age;
            SerializationUtil.writeExtent();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        throwIfFalse(() -> isValidName(name));
        if (!name.equals(this.name)) {
            this.name = name;
            SerializationUtil.writeExtent();
        }
    }

    public static List<Bird> getOldBirds() {
        return birds.stream().filter(bird -> bird.getAge() >= 8).toList();
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(age);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        age = ois.readInt();
        birds.add(this);
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
