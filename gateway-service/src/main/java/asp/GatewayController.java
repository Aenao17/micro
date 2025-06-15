package asp;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final RestTemplate rest = new RestTemplate();
    private static final String MEMBER_SERVICE_URL = "http://localhost:8081/members/";
    private static final String BOOK_SERVICE_URL = "http://localhost:8082/books";

    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody Map<String, String> bookData) {
        String memberName = bookData.get("memberName");

        try {
            // Validare membru
            ResponseEntity<String> memberResponse = rest.getForEntity(MEMBER_SERVICE_URL + memberName, String.class);
            if (memberResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member does not exist.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member does not exist.");
        }

        // Transmitere carte către book-service
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(bookData, headers);

        try {
            rest.postForEntity(BOOK_SERVICE_URL, request, String.class);
            return ResponseEntity.ok("Book added for member: " + memberName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add book.");
        }
    }
}
