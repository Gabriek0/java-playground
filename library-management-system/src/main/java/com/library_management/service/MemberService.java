package main.java.com.library_management.service;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Member;
import main.java.com.library_management.repository.MemberRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class MemberService {
    private final Repository<Member, String> memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(Member item) {
        Optional<Member> memberExists = getByEmail(item.getEmail());

        if (memberExists.isPresent()) {
            System.out.println("This email address has already been registered");
            return;
        }

        memberRepository.create(item);
    }

    public void updateMember(String id, Member item) {
        memberRepository.update(id, item);
    }

    public void removeMember(String id) {
        memberRepository.delete(id);
    }

    public ArrayList<Member> listMembers () {
        return memberRepository.list();
    }

    public Optional<Member> getByEmail(String email) {
        Optional<Member> member = Optional.empty();
        ArrayList<Member> members = listMembers();

        for (Member value : members) {
            if (value.getEmail().equals(email)) {
                member = Optional.of(value);
            }
        }

        return member;
    }

    public Optional<Member> getByName(String name) {
        Optional<Member> member = Optional.empty();
        ArrayList<Member> members = listMembers();

        for (Member value : members) {
            if (value.getName().equals(name)) {
                member = Optional.of(value);
            }
        }

        return member;
    }
}
