package org.example.kitchensink.controller;

import jakarta.validation.Valid;
import org.example.kitchensink.model.Member;
import org.example.kitchensink.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    @PostMapping
    public ResponseEntity<Member> registerMember(@Valid @RequestBody Member member) {
       Member savedMember  = memberService.register(member);
       return ResponseEntity.ok(savedMember);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();

    }
}
