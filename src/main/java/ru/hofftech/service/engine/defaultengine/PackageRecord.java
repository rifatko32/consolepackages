package ru.hofftech.service.engine.defaultengine;

import lombok.Getter;
import lombok.Setter;
import ru.hofftech.service.engine.BackTruckSlot;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PackageRecord {
    private final int descriptionNumber;
    private int width;
    private int height;

    @Setter
    @Getter
    private boolean isPlaced;

    public PackageRecord(String description) {
        descriptionNumber = Character.getNumericValue(description.charAt(0));

        switch(descriptionNumber) {
            case 1:
                width = 1;
                height = 1;
                break;
            case 2:
                width = 2;
                height = 1;
                break;
            case 3:
                width = 3;
                height = 1;
                break;
            case 4:
                width = 4;
                height = 1;
                break;
            case 5:
                width = 5;
                height = 1;
                break;
            case 6:
                width = 3;
                height = 2;
                break;
            case 7, 8:
                width = 4;
                height = 2;
                break;
            case 9:
                width = 3;
                height = 3;
                break;
        }
    }

    public boolean checkIfPackageHasEnoughSpace(int enoughWidth, int enoughHeight) {
        return enoughWidth >= this.width && enoughHeight >= this.height;
    }

    public List<BackTruckSlot> getListOfFillingSlots(int endX, int endY){
        var result = new ArrayList<BackTruckSlot>();

        var startX = endX - width + 1;
        var startY = endY - height + 1;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (isSeventhPackageEmptySlot(endX, x, y, startY)) {
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
    private boolean isSeventhPackageEmptySlot(int endX, int x, int y, int startY) {
        return x == endX && y == startY && descriptionNumber == 7;
    }
}
