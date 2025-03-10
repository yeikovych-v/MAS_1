package v.yeikovych.extent;

import v.yeikovych.util.SerializationUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static v.yeikovych.util.ValidationUtils.*;

public class Bird implements Extent {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final List<Bird> birds = new ArrayList<>();

    private String name;
    private int age;


    public Bird(String name, int age) {
        throwIfAnyFalse(
                () -> isValidName(name),
                () -> isValidAge(age)
        );

        this.name = name;
        this.age = age;
        birds.add(this);
        SerializationUtil.registerExtent(this, Bird.class);
        SerializationUtil.writeExtent(Bird.class, birds);
    }

    public static List<Bird> getExtent() {
        return birds.stream().map(bird -> new Bird(bird.name, bird.age)).collect(Collectors.toList());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (isValidAge(age)) {
            if (this.age != age) {
                this.age = age;
                SerializationUtil.writeExtent(Bird.class, birds);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isValidName(name)) {
            if (!this.name.equals(name)) {
                this.name = name;
                SerializationUtil.writeExtent(Bird.class, birds);
            }
        }
    }

    public static void allSing() {
        birds.forEach(bird -> System.out.printf("%s: Singing...\n", bird.getName()));
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
