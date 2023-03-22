package Logic;

public class Ticket {
    private int price;
    private char type;

    public Ticket(char type) {
        this.type = type;
        switch(type){
            case 'A':
                this.price = 5000;
                break;
            case 'B':
                this.price = 7500;
                break;
            case 'C':
                this.price = 18000;
                break;
            default:
                this.price = 1;
                break;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", type=" + type +
                '}';
    }
}
