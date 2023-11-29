package RockPaperScissors;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RaitingSaver {
	public static final String fileName = "rating.txt";
	public static final String filePath = "D:\\JavaProjects\\JavaXAI\\src\\RockPaperScissors\\" + fileName;
	Map<String, Integer> rating = new HashMap<>();
	public void saveRatingToFile() {
        Path path = Paths.get(filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(path.toFile()))) {
            for (Map.Entry<String, Integer> entry : rating.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRatingFromFile() {
    	Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            System.out.println("Loading rating from file...");
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String lineString;
                while ((lineString = reader.readLine()) != null) {
                    String[] parts = lineString.split(",");
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    rating.put(name, score);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
