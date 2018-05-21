package com.miketa.locationtracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Routes {


    /*
    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }*/

    /**
     * A dummy item representing a piece of content.
     */

    public final String id;
    public final String content;

    public Routes(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
            return content;
        }

}
