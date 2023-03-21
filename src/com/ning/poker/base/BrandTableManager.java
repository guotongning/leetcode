package com.ning.poker.base;

import java.util.concurrent.ConcurrentHashMap;

public class BrandTableManager {
    public static final ConcurrentHashMap<String, BrandTable> gameTables = new ConcurrentHashMap<>();

    public static void register(BrandTable brandTable) {
        gameTables.put(brandTable.tableId(), brandTable);
    }

    public static BrandTable getByTableID(String ID) {
        return gameTables.get(ID);
    }
}
