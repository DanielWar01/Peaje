package Logic;

import Persistence.FileTicket;

import java.io.IOException;
import java.util.ArrayList;

public class ManagementTicket {
    private ArrayList<ArrayList<Ticket>> tickets;
    private ArrayList<Integer> values;
    private FileTicket file;

    public ManagementTicket() {
        tickets = new ArrayList<>();
        values = new ArrayList<>();
        file = new FileTicket();
        file.setFilePath("Resources/Files/");
        file.setFileName("file");
        file.openFile();
    }

    public ArrayList<ArrayList<Ticket>> getTickets() {
        return (ArrayList<ArrayList<Ticket>>) tickets.clone();
    }

    public ArrayList<Integer> getValues() {
        return (ArrayList<Integer>) values.clone();
    }

    public void loadData() throws IOException{
        ArrayList<String> data = file.reader();
        int days = Integer.parseInt(data.get(0));
        ArrayList<Ticket> chars = null;
        for (String line : data){
            if (line != null && line.matches("[0-9]+") && line.length()>3) {
                chars = new ArrayList<>();
                values.add(Integer.parseInt(line));
            }
            if(line.charAt(0) == 'A' || line.charAt(0) == 'B' || line.charAt(0) == 'C' ){
                chars.add(new Ticket(line.charAt(0)));
            }
            if(line.charAt(0) == 'D'){
                tickets.add(chars);
            }

        }
    }
}
