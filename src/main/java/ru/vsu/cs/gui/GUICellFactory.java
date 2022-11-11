package ru.vsu.cs.gui;

import ru.vsu.cs.Cell;
import ru.vsu.cs.cells.*;
import ru.vsu.cs.gui.gui_cells.*;

import java.io.IOException;

public class GUICellFactory {
    public static GUICell createGUICell(Cell cell) throws IOException {
        String className = cell.getClass().getName();
        switch (className) {
            case "ru.vsu.cs.cells.Chance" : return new GUIChance((Chance) cell);
            case "ru.vsu.cs.cells.CommunityChest" : return new GUICommunityChest((CommunityChest) cell);
            case "ru.vsu.cs.cells.FreeParking" : return new GUIFreeParking((FreeParking) cell);
            case "ru.vsu.cs.cells.GoToJail" : return new GUIGoToJail((GoToJail) cell);
            case "ru.vsu.cs.cells.Jail" : return new GUIJail((Jail) cell);
            case "ru.vsu.cs.cells.RailRoad" : return new GUIRailRoad((RailRoad) cell);
            case "ru.vsu.cs.cells.StartCell" : return new GUIStartCell((StartCell) cell);
            case "ru.vsu.cs.cells.Street" : return new GUIStreet((Street) cell);
            case "ru.vsu.cs.cells.Tax" : return new GUITax((Tax) cell);
            case "ru.vsu.cs.cells.UtilityCompany" : return new GUIUtilityCompany((UtilityCompany) cell);
            default: return null;
        }
    }
}
