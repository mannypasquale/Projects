package ca.camosun.realfinal.dummy;

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
public class Courses {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Courses.CourseItem> ITEMS = new ArrayList<Courses.CourseItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Courses.CourseItem> ITEM_MAP = new HashMap<String, Courses.CourseItem>();

    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
        addItem(new CourseItem(String.valueOf(i), "ADD COURSE","N/A","N/A","N/A",false));
         }
        //addItem(new CourseItem("1", "Calc","Lots","113","rob"));
        //addItem(new CourseItem("2", "COMP","Lits","233", "Steve"));

    }

    private static void addItem(CourseItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

/*    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }*/

    /**
     * A dummy item representing a piece of content.
     */
    public static class CourseItem {
        public final String id;
        public String courseName;
        public String courseDetails;
        public String courseID;
        public String courseProf;
        public Boolean hasBeenEditited;

        public CourseItem(String id, String courseName, String courseDetails, String courseID, String courseProf, Boolean hasBeenEditited) {
            this.id = id;
            this.courseName = courseName;
            this.courseDetails = courseDetails;
            this.courseID = courseID;
            this.courseProf = courseProf;
            this.hasBeenEditited = hasBeenEditited;

        }

        @Override
        public String toString() {
            return courseName;
        }
    }
}