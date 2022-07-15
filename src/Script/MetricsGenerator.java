package Script;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class MetricsGenerator {
    private static String fileName = "metrics.csv";
    private static boolean createNewFile = true;

    private static BufferedWriter writer;

    public MetricsGenerator() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
        createNewFile = true;

        // framework
        final String frameworkPathToSearch = "./src/project/framework";
        final String frameworkPathToRemoveForPrint = ".\\src\\project\\framework\\";
        writeDataToCSVFile("Summary of code sizes from Project");
        writeDataToCSVFile(",,,");
        writeDataToCSVFile(", Package, Class, size (LOC)");
        writeDataToCSVFile("Framework,,,");
        generateForFilePath(frameworkPathToSearch, frameworkPathToRemoveForPrint);
        writeDataToCSVFile(",,,");

        // bank
        writeDataToCSVFile("Bank,,,");
        final String bankPathToSearch = "./src/project/bank";
        final String bankPathToRemoveForPrint = ".\\src\\project\\bank\\";
        generateForFilePath(bankPathToSearch, bankPathToRemoveForPrint);
//        writeDataToCSVFile("Total,0,0,0");
        writeDataToCSVFile(",,,");


        // ccard
        writeDataToCSVFile("CCard,,,");
        final String creditPathToSearch = "./src/project/ccard";
        final String creditPathToRemoveForPrint = ".\\src\\project\\ccard\\";
        generateForFilePath(creditPathToSearch, creditPathToRemoveForPrint);
//        writeDataToCSVFile("Total,0,0,0");
        writeDataToCSVFile(",,,");

        writer.close();
    }

    public static void generateForFilePath(String pathToSearch, String pathToRemoveForPrint) throws IOException {
        BiPredicate<Path, BasicFileAttributes> FileMatcher = (path, basicFileAttributes) -> {
            // only need regular file
            boolean regularFile = basicFileAttributes.isRegularFile();
            boolean isJavaExtension = path.getFileName().toString().matches(".*\\.java");
            return regularFile && isJavaExtension;
        };

        Stream<Path> pathStream = Files.find(Paths.get(pathToSearch), 99999, FileMatcher);
        final String[] previousPkgName = {""};
        final Long[] allStats = {-1L, 0L, 0L}; // packagesTotal (-1 ignore root), classesTotal, lineOfCodeTotal
        pathStream.forEach((Path x) -> {
            Stream<String> stream = null;
            try {
                stream = Files.lines(x, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long lineCount = stream.count();
            String packageName = x.toString();

            packageName = packageName.replace(pathToRemoveForPrint, "");
            packageName = packageName.replace("\\", ".");
            String currentPackageName = packageName.replace("." + x.getFileName().toString(), "");

            String lineToWrite = ", " + currentPackageName + ", " + x.getFileName() + ", " + lineCount;
            if(previousPkgName[0].equals(currentPackageName)) {
                lineToWrite = ", " + "~" + ", " + x.getFileName() + ", " + lineCount;
            } else {
                previousPkgName[0] = currentPackageName;
                allStats[0]++; // packages total
            }
            allStats[1]++; // classes total
            allStats[2] = allStats[2] + lineCount; // lineCountTotal
            System.out.println(lineToWrite);
            try {
                writeDataToCSVFile(lineToWrite);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        String totalToWrite = "Total" + "," + allStats[0] + "," + allStats[1] + "," + allStats[2];
        writeDataToCSVFile(totalToWrite);
    }

    public static void writeDataToCSVFile(String data) throws IOException {
        if (createNewFile) {
            writer.write(data);
            createNewFile = true;
        } else {
            writer.append(data);
        }
        writer.append("\n");
    }
}
