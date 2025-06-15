package asp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookStorage {
    private static final String FILE_PATH = "books.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static synchronized void save(Book book) {
        List<Book> books = loadAll();
        books.add(book);
        writeAll(books);
    }

    public static synchronized List<Book> findByMember(String memberName) {
        return loadAll().stream()
                .filter(b -> b.getMemberName().equalsIgnoreCase(memberName))
                .collect(Collectors.toList());
    }

    private static List<Book> loadAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static void writeAll(List<Book> books) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), books);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write books to file", e);
        }
    }
}
