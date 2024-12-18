package ru.hofftech.consolepackages.service.engine.defaultengine;

import ru.hofftech.consolepackages.service.engine.Package;
import ru.hofftech.consolepackages.service.engine.PackagePlaceEngine;
import ru.hofftech.consolepackages.service.engine.Truck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultPackagePlaceEngine implements PackagePlaceEngine {
    private final static int TRUCK_BACK_WIDTH = 6;
    private final static int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<Package> packages) {

        if (packages.isEmpty()) {
            return new ArrayList<>();
        }

        // сортируем по убыванию ширины посылок
        var sortedPackages = packages
                .stream()
                .sorted(Comparator.comparing(Package::getWidth).reversed())
                .toList();

        return this.placeSortedPackages(sortedPackages);
    }

    private List<Truck> placeSortedPackages(List<Package> packages) {
        var trucks = new ArrayList<Truck>();

        do {
            var truck = new Truck(TRUCK_BACK_WIDTH, TRUCK_BACK_HEIGHT);

            for (Package record : packages) {
                this.tryPlacePackage(record, truck);
            }

            trucks.add(truck);
        }
        while (packages.stream().anyMatch(p -> !p.isPlaced()));

        return trucks;
    }

    private void tryPlacePackage(Package packageItem, Truck truck) {
        if (packageItem.isPlaced()) {
            return;
        }

        // пытаемся разместить посылку с правого нижнего угла кузова
        for (var y = truck.getHeight() - 1; y >= 0; y--) {
            for (var x = truck.getWidth() - 1; x >= 0; x--) {
                if (!this.checkIsPackageCouldBePlaced(x, y, packageItem, truck)) {
                    continue;
                }

                packageItem.setPlaced(true);
                var fillingSlots = packageItem.mapToListOfFillingSlots(x, y);

                truck.fillBackTruckSlots(fillingSlots, packageItem.getDescriptionNumber());
                truck.getPackages().add(packageItem);

                return;
            }
        }
    }

    private boolean checkIsPackageCouldBePlaced(
            int xCoordinate,
            int yCoordinate,
            Package packageItem,
            Truck truck) {
        return !truck.checkIsRangeHasFilledSlots(
                    xCoordinate - packageItem.getWidth() + 1,
                    yCoordinate - packageItem.getHeight() + 1,
                    xCoordinate,
                    yCoordinate)
                && packageItem.checkIsPackageHasEnoughSpace(
                    xCoordinate + 1,
                    yCoordinate + 1)
                && truck.checkIsHasEnoughBase(
                    xCoordinate - packageItem.getWidth() + 1,
                    xCoordinate,
                    yCoordinate,
                    packageItem.calcMinimalBase());
    }
}
