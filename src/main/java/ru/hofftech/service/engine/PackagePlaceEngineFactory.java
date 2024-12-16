package ru.hofftech.service.engine;

import ru.hofftech.service.engine.defaultengine.DefaultPackagePlaceEngine;
import ru.hofftech.service.engine.singlepackagepertruckengine.SinglePackagePerTruckPlaceEngine;

public class PackagePlaceEngineFactory {
    public PackagePlaceEngine getPackagePlaceEngine(PackagePlaceEngineType engineType) {
        switch (engineType){
            case DEFAULT_PACKAGE_PLACE_ENGINE -> {
                return new DefaultPackagePlaceEngine();
            }
            case SINGLE_PACKAGE_PER_TRUCK_PLACE_ENGINE -> {
                return new SinglePackagePerTruckPlaceEngine();
            }
            default -> throw new IllegalArgumentException("Unknown engine type");
        }
    }
}
