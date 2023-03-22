package Persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileTicket extends FilePlain {

    public ArrayList<String> reader() throws IOException {
        ArrayList<String> output = new ArrayList<>();
        String data = readFile();
        StringTokenizer tokens = new StringTokenizer(data, "\n");
        while (tokens.hasMoreElements()) {
            output.add(tokens.nextToken());
        }

        return output;
    }
}