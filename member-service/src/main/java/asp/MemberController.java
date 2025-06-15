package asp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @PostMapping
    public String add(@RequestBody Member member) {
        MemberStorage.saveMember(member);
        return "Member added: " + member.getName();
    }

    @GetMapping("/{name}")
    public Member get(@PathVariable String name) {
        Member found = MemberStorage.findByName(name);
        if (found == null)
            throw new RuntimeException("Member not found");
        return found;
    }
}
