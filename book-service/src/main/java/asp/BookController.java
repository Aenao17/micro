package asp;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @PostMapping
    public String add(@RequestBody Book book) {
        BookStorage.save(book);
        return "Book added for member: " + book.getMemberName();
    }

    @GetMapping("/by-member/{name}")
    public List<Book> getByMember(@PathVariable String name) {
        return BookStorage.findByMember(name);
    }
}
