package ru.hofftech;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.controller.ConsoleController;
import ru.hofftech.service.PackageFromFilePlaceService;
import ru.hofftech.service.engine.PackagePlaceEngineFactory;
import ru.hofftech.util.PackageFileReader;
import ru.hofftech.util.TrucksToConsoleWriter;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Стартуем приложение...");
        Main.start();
    }

    private static void start() {
        ConsoleController consoleController = new ConsoleController(
                new PackageFromFilePlaceService(
                        new PackageFileReader(),
                        new PackagePlaceEngineFactory(),
                        new TrucksToConsoleWriter()));
        consoleController.listen();
    }
}