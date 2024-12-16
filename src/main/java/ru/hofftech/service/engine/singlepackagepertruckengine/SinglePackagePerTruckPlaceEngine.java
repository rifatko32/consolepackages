package ru.hofftech.service.engine.singlepackagepertruckengine;

import ru.hofftech.service.engine.PackagePlaceEngine;
import ru.hofftech.service.engine.PackageRecord;
import ru.hofftech.service.engine.Truck;

import java.util.ArrayList;
import java.util.List;

public class SinglePackagePerTruckPlaceEngine implements PackagePlaceEngine {
    private final int TRUCK_BACK_WIDTH = 6;
    private final int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<String> packages) {
        var packageRecords = this.initPackageRecords(packages);

        if (packageRecords.isEmpty()) {
            return new ArrayList<>();
        }

        return this.placePackageRecords(packageRecords);
    }

    private List<Truck> placePackageRecords(List<PackageRecord> packages) {
        var trucks = new ArrayList<ru.hofftech.service.engine.Truck>();

        for (var packageRecord : packages) {
            var truck = new ru.hofftech.service.engine.Truck(TRUCK_BACK_WIDTH,TRUCK_BACK_HEIGHT);
            this.tryPlacePackage(packageRecord, truck);
            trucks.add(truck);
        }

        return trucks;
    }

    private List<PackageRecord> initPackageRecords(List<String> packages) {
        var result = new ArrayList<PackageRecord>();

        for(String curPackage : packages){
            result.add(new PackageRecord(curPackage));
        }

        return result;
    }

    public void tryPlacePackage(PackageRecord packageRecord, Truck truck) {
        if (packageRecord.isPlaced()) {
            return;
        }

        packageRecord.setPlaced(true);
        var fillingSlots = packageRecord.getListOfFillingSlots(TRUCK_BACK_WIDTH - 1, TRUCK_BACK_HEIGHT - 1);
        truck.fillBackTruckSlots(fillingSlots, packageRecord.getDescriptionNumber());
    }
}
