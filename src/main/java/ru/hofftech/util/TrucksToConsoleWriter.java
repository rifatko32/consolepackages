package ru.hofftech.util;

import ru.hofftech.service.engine.Truck;

import java.util.List;

public class TrucksToConsoleWriter {
    public void writeTrucksToConsole(List<Truck> trucks) {
        for (Truck truck : trucks) {
            System.out.println("-------------------------------------------");
            var backTruckSlots = truck.getBackTruckSlots();
            var stringBuilder = new StringBuilder();

            for (var x = 0; x <= truck.getWidth() - 1; x++) {
                stringBuilder.setLength(0);
                stringBuilder.append("+");
                for (var y = 0; y <= truck.getHeight() - 1; y++) {
                    stringBuilder.append(backTruckSlots[x][y] == 0 ? " " : backTruckSlots[x][y]);
                }
                stringBuilder.append("+");
                System.out.println(stringBuilder);
            }
            System.out.println("++++++++");
        }
    }

}
