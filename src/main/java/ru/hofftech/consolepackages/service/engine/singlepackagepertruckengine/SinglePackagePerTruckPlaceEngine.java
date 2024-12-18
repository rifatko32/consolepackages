package ru.hofftech.consolepackages.service.engine.singlepackagepertruckengine;

import ru.hofftech.consolepackages.service.engine.Package;
import ru.hofftech.consolepackages.service.engine.PackagePlaceEngine;
import ru.hofftech.consolepackages.service.engine.Truck;

import java.util.ArrayList;
import java.util.List;

public class SinglePackagePerTruckPlaceEngine implements PackagePlaceEngine {
    private final int TRUCK_BACK_WIDTH = 6;
    private final int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<Package> packages) {

        if (packages.isEmpty()) {
            return new ArrayList<>();
        }

        return this.placePackageRecords(packages);
    }

    private List<Truck> placePackageRecords(List<Package> packages) {
        var trucks = new ArrayList<ru.hofftech.consolepackages.service.engine.Truck>();

        for (var packageRecord : packages) {
            var truck = new ru.hofftech.consolepackages.service.engine.Truck(TRUCK_BACK_WIDTH, TRUCK_BACK_HEIGHT);
            this.tryPlacePackage(packageRecord, truck);
            trucks.add(truck);
        }

        return trucks;
    }

    private void tryPlacePackage(Package packageRecord, Truck truck) {
        if (packageRecord.isPlaced()) {
            return;
        }

        packageRecord.setPlaced(true);
        var fillingSlots = packageRecord.mapToListOfFillingSlots(TRUCK_BACK_WIDTH - 1, TRUCK_BACK_HEIGHT - 1);

        truck.fillBackTruckSlots(fillingSlots, packageRecord.getDescriptionNumber());
        truck.getPackages().add(packageRecord);
    }
}
