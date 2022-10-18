package ua.com.company.utils;

import ua.com.company.view.dto.TopicDTO;

import java.util.Collection;

public class ContainsTag {
    public static boolean contains(Collection<TopicDTO> collection, Object o) {
        return collection.contains(o);
    }
}
