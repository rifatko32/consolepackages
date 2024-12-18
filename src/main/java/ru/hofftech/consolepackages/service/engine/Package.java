package ru.hofftech.consolepackages.service.engine;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Package {
    private final int descriptionNumber;
    private int width;
    private int height;

    @Setter
    @Getter
    private boolean isPlaced;

    public Package(String description) {
        descriptionNumber = Character.getNumericValue(description.charAt(0));

        switch (descriptionNumber) {
            case 1:
                this.width = 1;
                this.height = 1;
                break;
            case 2:
                this.width = 2;
                this.height = 1;
                break;
            case 3:
                this.width = 3;
                this.height = 1;
                break;
            case 4:
                this.width = 4;
                this.height = 1;
                break;
            case 5:
                this.width = 5;
                this.height = 1;
                break;
            case 6:
                this.width = 3;
                this.height = 2;
                break;
            case 7, 8:
                this.width = 4;
                this.height = 2;
                break;
            case 9:
                this.width = 3;
                this.height = 3;
                break;
        }
    }

    public boolean checkIsPackageHasEnoughSpace(int enoughWidth, int enoughHeight) {
        return enoughWidth >= this.width && enoughHeight >= this.height;
    }

    public int calcMinimalBase() {
        return (int) Math.ceil((double) this.width / 2);
    }

    public List<BackTruckSlot> mapToListOfFillingSlots(int endX, int endY) {
        var result = new ArrayList<BackTruckSlot>();

        var startX = endX - this.width + 1;
        var startY = endY - this.height + 1;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (checkIsSeventhPackageEmptySlot(endX, x, y, startY)) {
                    continue;
                }
                result.add(new BackTruckSlot(x, y));
            }
        }

        return result;
    }

    // посылка 7 особенной формы
    // 777
    // 7777
    // у ней д.б. пустой слот
    private boolean checkIsSeventhPackageEmptySlot(int endX, int x, int y, int startY) {
        return x == endX && y == startY && this.descriptionNumber == 7;
    }
}
