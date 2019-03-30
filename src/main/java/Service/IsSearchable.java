package Service;


import java.util.ArrayList;
import java.util.List;

import Domain.Entity;

public class IsSearchable<T extends Entity> {

    public List<Integer> fullTextSearch(String[] searchedWords, List<T> entities) {
        List<Integer> searchResults = new ArrayList<>();

        for (String word : searchedWords) {
            for (T entity : entities) {
                List<String> fields = entity.getAllFields();
                for (String field : fields) {
                    if (word.toLowerCase().equals(field.toLowerCase())) {

                        if (!searchResults.contains(entity.getId())) {
                            searchResults.add(entity.getId());
                        }
                    }
                }
            }
        }

        return searchResults;
    }
}