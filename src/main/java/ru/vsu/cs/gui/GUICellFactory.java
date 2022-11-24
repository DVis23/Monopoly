package ru.vsu.cs.gui;

import org.reflections.Reflections;
import ru.vsu.cs.Cell;
import ru.vsu.cs.PlayingField;
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

    public static GUICell [] guiCells(PlayingField playingField) throws IOException {
        Cell [] cells = playingField.getCells();
        GUICell [] guiCells = new GUICell[cells.length];

        var r = new Reflections("ru.vsu.cs.gui.gui_cells");
        var l = r.getSubTypesOf(GUICell.class);
        Arrays.stream(l.toArray(Class[]::new)).map(x -> x.getName()).forEach(x -> {
            try {
                Class.forName(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < cells.length; i++) {
            GUICell guiCell = GUICellFactory.createGuiCell(cells[i]);
            guiCells[i] = guiCell;
        }

        return guiCells;
    }
}
