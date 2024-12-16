package ru.hofftech.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import ru.hofftech.service.PackageFromFilePlaceService;
import ru.hofftech.service.engine.PackagePlaceEngineType;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final PackageFromFilePlaceService packagePlaceService;

    private final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\.txt)");

    public void listen() {
        var scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                System.exit(0);
            }

            Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(command);
            if (matcher.matches()) {
                String filePath = matcher.group(1);
                log.info("Start of handling file: {}", filePath);
                packagePlaceService.placePackages(filePath, PackagePlaceEngineType.DEFAULT_PACKAGE_PLACE_ENGINE);
                log.info("End of handling file: {}", filePath);
            }
            else {
                log.error("Invalid command: {}", command);
            }
        }
    }
}
