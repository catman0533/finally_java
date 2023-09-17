package myproject.example.com.service;

import myproject.example.com.model.Toy;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ToyShop {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyShop() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void changeWeight(int toyId, int newWeight) {
        Toy toy = toys.get(toyId);
        toy.setWeight(newWeight);
    }

    public void startLottery() {
        // Очищаем список призовых игрушек перед каждым розыгрышем
        prizeToys.clear();
        
        for (Toy toy : toys) {
            for (int i = 0; i < toy.getWeight(); i++) {
                prizeToys.add(toy);
            }
        }
    }

    public Toy chooseToy() {
        int randomIndex = (int) (Math.random() * prizeToys.size());
        return prizeToys.get(randomIndex);
    }

    public void getPrizeToy(Toy toy) {
        // Записываем призовую игрушку в текстовый файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prizeToys.txt", true))) {
            writer.write(toy.getId() + "," + toy.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Уменьшаем количество игрушек после розыгрыша
        toy.setQuantity(toy.getQuantity() - 1);
        
        // Если количество игрушек становится равным нулю, удаляем игрушку из списка
        if (toy.getQuantity() == 0) {
            toys.remove(toy);
        }
        
        // Удаляем призовую игрушку из списка призовых игрушек
        prizeToys.remove(toy);
    }

    public void readFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("prizeToys.txt"));
            for (String line : lines) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
