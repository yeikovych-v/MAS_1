package v.yeikovych.util;

import v.yeikovych.extent.Extent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SerializationUtil {

    private static final String DIRECTORY_PATH = "src\\main\\resources\\extents";
    private static final Map<Class<? extends Extent>, List<Extent>> extents = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void readExtent() {
        File dir = new File(DIRECTORY_PATH);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory: " + DIRECTORY_PATH);
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".ser"));
        if (files == null || files.length == 0) {
            System.out.println("No serialized files found in: " + DIRECTORY_PATH);
            return;
        }

        for (File file : files) {
            String fileName = file.getName();
            try {
                String className = fileName.split("_")[0];

                Class<?> clazz = Class.forName("v.yeikovych.extent." + className);
                if (!Extent.class.isAssignableFrom(clazz)) {
                    System.out.println("Skipping non-extent file: " + fileName);
                    continue;
                }

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Extent obj = (Extent) ois.readObject();

                    var extentClass = (Class<? extends Extent>) clazz;
                    extents.computeIfAbsent(extentClass, k -> new ArrayList<>()).add(obj);
                }

                System.out.println("Successfully deserialized: " + fileName);
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found for file: " + fileName);
            } catch (IOException | ClassCastException e) {
                System.err.println("Failed to deserialize: " + fileName);
                e.printStackTrace();
            }
        }
    }

    public static <T extends Extent> void writeExtent(Class<T> extentClass, List<T> extents) {
        try {
            Files.createDirectories(Paths.get(DIRECTORY_PATH));

            for (int i = 0; i < extents.size(); i++) {
                String filePath = String.format("%s\\%s_%d.ser", DIRECTORY_PATH, extentClass.getSimpleName(), i);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                    oos.writeObject(extents.get(i));
                }
            }
            System.out.printf("%s serialized successfully.\n", extentClass.getSimpleName());
        } catch (IOException e) {
            System.out.println("Serialization failed for " + extentClass.getSimpleName() +
                    " with stacktrace: ");
            e.printStackTrace();
        }
    }

    public static <T extends Extent> void registerExtent(T extent, Class<T> extentClass) {
        extents.computeIfAbsent(extentClass, k -> new ArrayList<>()).add(extent);
    }
}
