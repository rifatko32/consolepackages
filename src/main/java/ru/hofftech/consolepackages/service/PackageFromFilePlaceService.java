package ru.hofftech.consolepackages.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.engine.Package;
import ru.hofftech.consolepackages.service.engine.PackagePlaceEngineFactory;
import ru.hofftech.consolepackages.service.engine.PackagePlaceEngineType;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReportEngine;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.ReportToConsoleWriter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PackageFromFilePlaceService {
    private final PackageFileReader fileReader;
    private final PackagePlaceEngineFactory placeEngineFactory;
    private final ReportToConsoleWriter reportToConsoleWriter;
    private final PackagePlaceStringReportEngine reportEngine;

    public void placePackages(String filePath, PackagePlaceEngineType engineType) {
        try {
            List<String> packages = fileReader.readPackages(filePath);
            if (packages.isEmpty()) {
                log.info("No packages found");
                return;
            }

            log.info("Found {} packages, start of placing...", packages.size());

            var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(engineType);
            var packageRecords = this.initPackages(packages);
            var trucks = packagePlaceEngine.placePackages(packageRecords);

            var packagePlaceReport = this.reportEngine.generateReport(trucks);

            reportToConsoleWriter.writeReportToConsole(packagePlaceReport);
        } catch (Exception e) {
            log.error("Error while try to place packages", e);
        }
    }

    private List<Package> initPackages(List<String> packages) {
        var result = new ArrayList<Package>();

        for (String curPackage : packages) {
            result.add(new Package(curPackage));
        }

        return result;
    }
}
