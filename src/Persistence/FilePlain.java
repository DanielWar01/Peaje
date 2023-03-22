package Persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FilePlain {
    private Path path;
    private String filePath;
    private String fileName;
    private String separator;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void openFile() {
        path = Paths.get(filePath + fileName);
    }

    public String readFile() throws IOException {
        BufferedReader input = Files.newBufferedReader(path, Charset.defaultCharset());
        StringBuilder result = new StringBuilder();
        String line = null;
        while ((line = input.readLine()) != null) {
            result.append(line + "\n");
        }
        return result.toString();
    }

    public void writeFile(String object) throws IOException {
        BufferedWriter output = Files.newBufferedWriter(path, Charset.defaultCharset(),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        output.write(object);

        output.close();
    }

}