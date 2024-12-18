package ru.hofftech.consolepackages.service.engine;

import java.util.List;

public interface PackagePlaceEngine {
    List<Truck> placePackages(List<Package> packages);
}
