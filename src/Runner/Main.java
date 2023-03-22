package Runner;

import Logic.ManagementTicket;

public class Main {
    public static void main(String[] args) {
        ManagementTicket mt = new ManagementTicket();
        try {
            mt.loadData();
        }catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mt.getTickets().size(); i++) {
            System.out.println(mt.validate(i+1));
        }

        System.out.println("Total vendido por tipo de tiquete");
        System.out.println(mt.totalByTicket());

        System.out.println("Día de mayor descuadre y valor");
        System.out.println(mt.dismatchMoreDay());

        System.out.println("Total de descuadre en el mes");
        System.out.println(mt.dismatchMonth());

        System.out.println("Porcentaje por tipo de tiquete");
        System.out.println(mt.percentType());
    }
}
