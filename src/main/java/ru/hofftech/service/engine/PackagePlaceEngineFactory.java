package ru.hofftech.service.engine;

import ru.hofftech.service.engine.defaultengine.DefaultPackagePlaceEngine;

public class PackagePlaceEngineFactory {
    public PackagePlaceEngine getPackagePlaceEngine(PackagePlaceEngineType engineType) {
        switch (engineType){
            case DEFAULT_PACKAGE_PLACE_ENGINE -> {
                return new DefaultPackagePlaceEngine();
            }
            default -> throw new IllegalArgumentException("Unknown engine type");
        }
    }
}
