package com.example.jakubkalinowski.contractfoxandroid.dummy;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    private FirebaseAuth mAuth;
  //  private FirebaseAuth.AuthStateListener mAuthListener; //signed_in state listener object

    /**
     * An array of sample (dummy) items.
     * this list should be populated with all the contractors.
     * note: this means not only the type of list will change from string to member, or contracotr,
     * but more change is needed in the adaptor class for the recycler view. the type of adaptor in its
     * constructor should match the type of data in this list.
     */
    public static String[] data = {"company 1", "company 2","Ladimer"};

    public static List<String> ITEMSs = Arrays.asList(data);
    public static List<String> ITEMS = ITEMSs;

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 2;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    private static void addItem(String item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
