package ru.hofftech.service.engine.defaultengine;

import ru.hofftech.service.engine.PackagePlaceEngine;
import ru.hofftech.service.engine.Truck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultPackagePlaceEngine implements PackagePlaceEngine {
    private final int TRUCK_BACK_WEIGHT = 6;
    private final int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<String> packages) {
        // сортируем по убыванию ширины посылок
        var packageRecords = this.initPackageRecords(packages)
                .stream()
                .sorted(Comparator.comparing(PackageRecord::getWidth).reversed())
                .toList();

        if (packageRecords.isEmpty()) {
            return new ArrayList<>();
        }

        return this.placePackageRecords(packageRecords);
    }

    private List<PackageRecord> initPackageRecords(List<String> packages) {
        var result = new ArrayList<PackageRecord>();

        for(String curPackage : packages){
            result.add(new PackageRecord(curPackage));
        }

        return result;
    }

    private List<Truck> placePackageRecords(List<PackageRecord> packages) {
        var trucks = new ArrayList<Truck>();

        do{
            var truck = new Truck(TRUCK_BACK_WEIGHT,TRUCK_BACK_HEIGHT);

            for(PackageRecord record : packages){
                this.tryPlacePackage(record, truck);
            }

            trucks.add(truck);
        }
        while (packages.stream().anyMatch(p -> !p.isPlaced()));

        return trucks;
    }

    public void tryPlacePackage(PackageRecord packageRecord, Truck truck) {
        if (packageRecord.isPlaced()) {
            return;
        }

        // пытаемся разместить посылку с правого нижнего угла кузова
        for (var y = truck.getHeight() - 1; y >= 0; y--) {
            for (var x = truck.getWidth() - 1; x >= 0; x--) {
                // ячейка занята ИЛИ ширина посылки больше оставшегося свободного места в кузова
                if (truck.checkIfRangeHasFilledSlots(x - packageRecord.getWidth() + 1, y - packageRecord.getHeight() + 1, x ,y)
                        || !packageRecord.checkIfPackageHasEnoughSpace(x + 1,y + 1)
                )
                {
                    continue;
                }

                packageRecord.setPlaced(true);
                var fillingSlots = packageRecord.getListOfFillingSlots(x, y);
                truck.fillBackTruckSlots(fillingSlots, packageRecord.getDescriptionNumber());

                return;
            }
        }
    }
}
