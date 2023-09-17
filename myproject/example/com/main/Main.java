package myproject.example.com.main;


import myproject.example.com.model.Toy;
import myproject.example.com.service.ToyShop;

public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        Toy toy1 = new Toy(1, "Toy 1", 5, 20);
        Toy toy2 = new Toy(2, "Toy 2", 3, 30);
        Toy toy3 = new Toy(3, "Toy 3", 8, 50);

        toyShop.addToy(toy1);
        toyShop.addToy(toy2);
        toyShop.addToy(toy3);

        toyShop.changeWeight(0, 30);

        toyShop.startLottery();

        Toy prizeToy = toyShop.chooseToy();

        toyShop.getPrizeToy(prizeToy);

        toyShop.readFromFile();
    }
}

