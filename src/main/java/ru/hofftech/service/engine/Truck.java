package ru.hofftech.service.engine;

import lombok.Getter;

import java.util.List;

@Getter
public class Truck {
    private final int[][] backTruckSlots; // координаты ячеек в кузове
    private final int width; //кол-во столбцов координата x
    private final int height; //кол-во строк координата y

    public Truck(int width, int height) {
        this.width = width;
        this.height = height;
        backTruckSlots = new int[height][width];
    }

    public void fillBackTruckSlots(List<BackTruckSlot> fillingSlots, int fillValue) {
        for (var slot : fillingSlots) {
            this.backTruckSlots[slot.getCoordinateY()][slot.getCoordinateX()] = fillValue;
        }
    }

    public boolean checkIfCurrentSlotIsFilled(int x, int y) {
        return backTruckSlots[y][x] != 0;
    }

    public boolean checkIfRangeHasFilledSlots(int startX, int startY, int endX, int endY) {
        var result = false;

        if (startX < 0 || startY < 0) {
            return result;
        }

        for(var x = startX; x <= endX; x++) {
            for(var y = startY; y <= endY; y++) {
                result = this.checkIfCurrentSlotIsFilled(x, y);
                if (result) {
                    return result;
                }
            }
        }

        return result;
    }

    public boolean checkIfHasEnoughBase(int startX, int endX, int y, int minimalBase) {
        if (y == height -1 ){
            return true;
        }

        var filledSlotCount = 0;
        for (var x = startX; x <= endX; x++) {
            if (this.checkIfCurrentSlotIsFilled(x, y + 1)) {
                filledSlotCount++;
            }
        }

        return filledSlotCount >= minimalBase;
    }
}
