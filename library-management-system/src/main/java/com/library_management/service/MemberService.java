package main.java.com.library_management.service;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Member;

import java.util.*;
import java.util.stream.Collectors;

public class MemberService {
    private final Repository<Member, String> memberRepository;

    public MemberService(Repository<Member, String> memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(Member item) {
        Optional<Member> member = findByEmail(item.getEmail());

        if (member.isPresent()) {
            throw new IllegalArgumentException("This email address has already been registered");
        }

        memberRepository.create(item);
    }

    public void updateMember(String id, Member item) {
        Optional<Member> member = findByEmail(item.getEmail());

        if (member.isPresent() && !member.get().getId().equals(id)) {
            throw new IllegalArgumentException("This email address has already been registered");
        }

        memberRepository.update(id, item);
    }

    public void removeMember(String id) {
        Optional<Member> member = findById(id);

        if (member.isEmpty()) {
            throw new IllegalArgumentException("The member with that ID does not exist");
        }

        memberRepository.delete(id);
    }

    public ArrayList<Member> listMembers () {
        return memberRepository.list();
    }

    public Optional<Member> findById(String id) {
        return listMembers().stream().filter(member -> member.getId().equals(id)).findFirst();
    }

    public Optional<Member> findByEmail(String email) {
        return listMembers().stream().filter(member -> member.getEmail().equals(email)).findFirst();
    }

    public List<Member> findByName(String name) {
        return listMembers().stream().filter(member -> member.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
}
