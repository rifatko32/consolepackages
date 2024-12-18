package ru.hofftech.consolepackages.util;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;

public class ReportToConsoleWriter {

    public void writeReportToConsole(PackagePlaceStringReport report) {
        for (var reportString : report.getReportStrings()) {
            System.out.println(reportString);
        }
    }
}
