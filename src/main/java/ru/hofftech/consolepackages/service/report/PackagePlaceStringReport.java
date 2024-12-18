package ru.hofftech.consolepackages.service.report;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PackagePlaceStringReport {
    private final ArrayList<String> reportStrings;

    public PackagePlaceStringReport() {
        this.reportStrings = new ArrayList<>();
    }
}
