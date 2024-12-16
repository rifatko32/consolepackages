package ru.hofftech.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.service.engine.PackagePlaceEngineFactory;
import ru.hofftech.service.engine.PackagePlaceEngineType;
import ru.hofftech.util.PackageFileReader;
import ru.hofftech.util.TrucksToConsoleWriter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PackageFromFilePlaceService {
    private final PackageFileReader fileReader;
    private final PackagePlaceEngineFactory placeEngineFactory;
    private final TrucksToConsoleWriter trucksToConsoleWriter;

    public void placePackages(String filePath, PackagePlaceEngineType engineType) {
        try {
            List<String> packages = fileReader.readAllLines(filePath);
            if (packages.isEmpty()){
                log.info("No packages found");
                return;
            }

            log.info("Found {} packages, start of placing...", packages.size());

            var packagePlaceEngine = placeEngineFactory.getPackagePlaceEngine(engineType);
            var result = packagePlaceEngine.placePackages(packages);

            trucksToConsoleWriter.writeTrucksToConsole(result);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
