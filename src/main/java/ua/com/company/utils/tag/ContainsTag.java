package ua.com.company.utils.tag;


import java.util.Collection;

public class ContainsTag {
    public static boolean contains(Collection<?> collection, Object o) {
        return collection.contains(o);
    }
}
