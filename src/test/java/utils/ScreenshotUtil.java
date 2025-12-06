package utils;

import factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String takeScreenshot(String testName) {
    	System.out.println("📸 Taking screenshot now...");

        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) return null;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String folderPath = "target/screenshots";
            Files.createDirectories(Path.of(folderPath));

            String filePath = folderPath + "/" + testName + "_" + timestamp + ".png";
            Files.copy(src.toPath(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
