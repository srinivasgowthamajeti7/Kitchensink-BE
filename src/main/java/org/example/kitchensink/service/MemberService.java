package org.example.kitchensink.service;

import org.example.kitchensink.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {


    Member register(Member member);

    List<Member> getAllMembers();


}
