import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

class BeverageMachineTest {

    @Test
    void beverageMachineEndToEnd() throws IOException {
        String filePath = "../testproject/src/main/resources/input.json";
        BeverageMachine beverageMachine = new BeverageMachine(filePath);
        beverageMachine.prepareBeverage();
        beverageMachine.switchOffMachine();
        Assert.assertEquals(true, checkOutput());
    }

    private Boolean checkOutput() throws IOException {
        String filePath = "../testproject/src/main/resources/output.txt";
        byte[] outputData = Files.readAllBytes(Paths.get(filePath));
        byte[] validOutput1 = "hot_tea is prepared\nblack_tea is prepared\nhot_coffee cannot be prepared because item hot_water is not sufficient\ngreen_tea cannot be prepared because green_mixture is not available".getBytes();
        byte[] validOutput2 = "hot_tea is prepared\nblack_tea is prepared\nhot_coffee cannot be prepared because item hot_water is not sufficient\ngreen_tea cannot be prepared because green_mixture is not available".getBytes();
        byte[] validOutput3 = "black_tea is prepared\nhot_tea is prepared\nhot_coffee cannot be prepared because item hot_water is not sufficient\ngreen_tea cannot be prepared because green_mixture is not available".getBytes();
        byte[] validOutput4 = "hot_tea is prepared\nblack_tea cannot be prepared because item hot_water is not sufficient\ngreen_tea cannot be prepared because green_mixture is not available\nhot_coffee is prepared".getBytes();
        if (Arrays.equals(outputData, validOutput1) || Arrays.equals(outputData, validOutput2) || Arrays.equals(outputData, validOutput3))
            return true;
        return false;
    }
}
