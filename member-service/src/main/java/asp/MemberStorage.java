package asp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class MemberStorage {
    private static final String FILE_PATH = "members.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static synchronized void saveMember(Member member) {
        List<Member> members = loadAll();
        if (members.stream().noneMatch(m -> m.getName().equalsIgnoreCase(member.getName()))) {
            members.add(member);
            writeAll(members);
        }
    }

    public static synchronized Member findByName(String name) {
        return loadAll().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static List<Member> loadAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static void writeAll(List<Member> members) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), members);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write members to file", e);
        }
    }
}
