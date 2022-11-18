package ru.vsu.cs.gui;

import ru.vsu.cs.Cell;
import ru.vsu.cs.cells.*;
import ru.vsu.cs.gui.gui_cells.*;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class GUICellFactory {
    static Map<Class<? extends Cell>, Function<Cell, GUICell>> registeredTypes = new HashMap<>();

    public static <T extends Cell, F extends GUICell> void registerType(Class<T> type, Function<T, F> creator) {
        //noinspection unchecked
        GUICellFactory.registeredTypes.put(type, (cell) -> creator.apply((T)cell));
    }

    public static GUICell createGuiCell(Cell cell) {
        return Optional.ofNullable(
                registeredTypes.get(cell.getClass()).apply(cell)
        ).orElseThrow(
                () -> new RuntimeException("Unknown cell type " + cell.getClass().toString())
        );
    }

    public static void getMap() {
        for (Class c : registeredTypes.keySet()) {
            System.out.println(c.toString() + "   " + registeredTypes.get(c));
        }
    }
}
