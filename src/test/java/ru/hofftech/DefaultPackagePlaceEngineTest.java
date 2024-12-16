package ru.hofftech;

import org.junit.jupiter.api.Test;
import ru.hofftech.service.engine.defaultengine.DefaultPackagePlaceEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPackagePlaceEngineTest {
    @Test
    public void when_has_packages_should_return_filled_truck() {
        // Arrange
        var packages = List.of("1", "22");
        var engine = new DefaultPackagePlaceEngine();
        int[] [] expectedBackTruck = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 2}};

        // Act
        var result = engine.placePackages(packages);
        var truck = result.stream().findFirst().orElse(null);

        // Assert
        assertThat(truck).isNotNull();
        assertThat(truck.getBackTruckSlots()).isEqualTo(expectedBackTruck);
    }

    @Test
    public void when_does_not_have_packages_should_return_empty_truck_list() {
        // Arrange
        var packages = new ArrayList<String>();
        var engine = new DefaultPackagePlaceEngine();

        // Act
        var result = engine.placePackages(packages);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isZero();
    }
}
