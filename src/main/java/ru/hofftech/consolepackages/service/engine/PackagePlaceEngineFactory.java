package ru.hofftech.consolepackages.service.engine;

import ru.hofftech.consolepackages.service.engine.defaultengine.DefaultPackagePlaceEngine;
import ru.hofftech.consolepackages.service.engine.singlepackagepertruckengine.SinglePackagePerTruckPlaceEngine;

public class PackagePlaceEngineFactory {
    public PackagePlaceEngine createPackagePlaceEngine(PackagePlaceEngineType engineType) {
        switch (engineType) {
            case DEFAULT_ALGORITHM -> {
                return new DefaultPackagePlaceEngine();
            }
            case SINGLE_PACKAGE_PER_TRUCK_ALGORITHM -> {
                return new SinglePackagePerTruckPlaceEngine();
            }
            default -> throw new IllegalArgumentException("Unknown engine type");
        }
    }
}
