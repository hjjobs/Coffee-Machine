import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dispenser.enums.Beverages;
import dispenser.enums.Ingredients;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BeverageMachine {

    private Integer NUMBER_OF_OUTLETS;
    private Integer NUMBER_OF_BEVERAGES;
    private Map<Ingredients, Integer> totalItemsQuantity;
    private Machine machine;
    private ThreadPoolExecutor executor;
    private String filePath;


    public BeverageMachine(String filePath) throws IOException {
        this.filePath = filePath;
        initialize();
    }

    private void initialize() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        ObjectMapper objectMapper = new ObjectMapper();
        machine = objectMapper.readValue(jsonData, Machine.class);
        totalItemsQuantity = machine.totalItemsQuantity;
        NUMBER_OF_BEVERAGES = machine.beverages.size();
        NUMBER_OF_OUTLETS = machine.outlets.get("count_n").asInt();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMBER_OF_OUTLETS);
    }

    public void prepareBeverage() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<Ingredients, Integer>> beveragesMap = objectMapper.convertValue(machine.beverages,
                new TypeReference<Map<String, Map<Ingredients, Integer>>>() {
                });
        beveragesMap.entrySet().stream().forEach(m -> {
            executor.execute(() -> {
                Beverages.getByValue(m.getKey()).dispenseDrink(totalItemsQuantity, m.getValue());
            });
        });
    }

    public void switchOffMachine() throws IOException {
        executor.shutdown();
        PrintWriter writer = new PrintWriter(Paths.get("../testproject/src/main/resources/output.txt").toFile());
        writer.print("");
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        String filePath = "../testproject/src/main/resources/input.json";
        BeverageMachine beverageMachine = new BeverageMachine(filePath);
        beverageMachine.prepareBeverage();
        beverageMachine.switchOffMachine();
    }
}
