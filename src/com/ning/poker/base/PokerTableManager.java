package com.ning.poker.base;

import com.ning.poker.base.api.PokerTable;

import java.util.concurrent.ConcurrentHashMap;

public class PokerTableManager {
    public static final ConcurrentHashMap<String, PokerTable> gameTables = new ConcurrentHashMap<>();

    public static void register(PokerTable pokerTable) {
        gameTables.put(pokerTable.tableId(), pokerTable);
    }

    public static PokerTable getByTableID(String ID) {
        return gameTables.get(ID);
    }
}
