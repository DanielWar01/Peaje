package Logic;

import Persistence.FileTicket;

import java.io.IOException;
import java.util.ArrayList;

public class ManagementTicket {
    private ArrayList<ArrayList<Ticket>> tickets;
    private ArrayList<Integer> values;
    private FileTicket file;
    private int days;

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
        days = Integer.parseInt(data.get(0));
        ArrayList<Ticket> chars = null;
        for (String line : data){
            if (line != null && line.matches("[0-9]+") && line.length()>3) {
                chars = new ArrayList<>();
                values.add(Integer.parseInt(line));
            }
            if(line.charAt(0) == 'A' || line.charAt(0) == 'B' || line.charAt(0) == 'C' ){
                chars.add(new Ticket(line.charAt(0)));
            }
            if(line.charAt(0) == 'D') {
                tickets.add(chars);
            }
        }
    }

    public int calcTotalDay(int day){
        int sum = 0;
        for (Ticket t : tickets.get(day-1)){
            sum += t.getPrice();
        }
        return sum;
    }

    public int dismatch(int day){
        return values.get(day-1)-calcTotalDay(day);
    }

    public String validate(int day){
            if (calcTotalDay(day) == values.get(day-1)){
                return "SI";
            }else{
                return "NO $"+Math.abs(dismatch(day));
            }
    }

    public String totalByTicket(){
        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (ArrayList<Ticket> t : tickets){
            for (Ticket ticket : t){
                if (ticket.getType() == 'A'){
                    sum1 += ticket.getPrice();
                }else if(ticket.getType() == 'B'){
                    sum2 += ticket.getPrice();
                }else if(ticket.getType() == 'C'){
                    sum3 += ticket.getPrice();
                }
            }
        }
        return "A $"+sum1+"\n"+
                "B $"+sum2+"\n"+
                "C $"+sum3+"\n";
    }

    public String dismatchMoreDay(){
        int[] numbers = new int[days];
        for (int i = 0; i < days; i++) {
            numbers[i] = Math.abs(dismatch(i+1));
        }
        int biggest = numbers[0], day = 0;
        for (int x = 1; x < numbers.length; x++) {
            if (numbers[x] > biggest) {
                biggest = numbers[x];
                day = x+1;
            }
        }
        return day+" $"+biggest+"\n";
    }

    public String dismatchMonth(){
        int sum = 0;
        for (int i = 0; i < days; i++) {
            sum += dismatch(i+1);
        }
        return "$"+sum;
    }

    public int totalTickets(){
        int total = 0;
        for (ArrayList<Ticket> t : tickets){
            total += t.size();
        }
        return total;
    }

    public String percentType(){
        double sum1 = 0, sum2 = 0, sum3 = 0;
        for (ArrayList<Ticket> t : tickets){
            for (Ticket ticket : t){
                if (ticket.getType() == 'A'){
                    sum1++;
                }else if(ticket.getType() == 'B'){
                    sum2++;
                }else if(ticket.getType() == 'C'){
                    sum3++;
                }
            }
        }
        return "A "+Math.round(((sum1/totalTickets())*100)*10.0)/10.0 +"%\n"+
                "B "+Math.round(((sum2/totalTickets())*100)*10.0)/10.0 +"%\n"+
                "C "+Math.round(((sum3/totalTickets())*100)*10.0)/10.0 +"%\n";
    }
}
