package org.example.kitchensink.service;

import org.example.kitchensink.exception.DuplicateEmailException;
import org.example.kitchensink.model.Member;
import org.example.kitchensink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl {

    @Autowired
    private MemberRepository memberRepository;

    // Method to register a new member
    public Member register(Member member) {
        // Check if email already exists
        Optional<Member> existingMember = memberRepository.findByEmail(member.getEmail());
        if (existingMember.isPresent()) {
            throw new DuplicateEmailException("Email already exists ");
        }

        // If email is unique, save the member
        return memberRepository.save(member);
    }

    // Method to get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
