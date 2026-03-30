package main.java.com.library_management.service;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemberService {
    private final Repository<Member, String> memberRepository;

    public MemberService(Repository<Member, String> memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(Member item) {
        if (findByEmail(item.getEmail()).isPresent()) {
            System.out.println("A member with email " + item.getEmail() + " is already registered");
            return;
        }

        memberRepository.create(item);

        System.out.println(item);
        System.out.println("Member successfully registered!");
    }

    public void updateMember(String id, Member item) {
        if (findById(id).isEmpty()) {
            System.out.println("No member found with ID " + id);
            return;
        }

        Optional<Member> memberWithEmail = findByEmail(item.getEmail());
        if (memberWithEmail.isPresent() && !memberWithEmail.get().getId().equals(id)) {
            System.out.println("A member with email " + item.getEmail() + " is already registered");
            return;
        }

        memberRepository.update(id, item);

        System.out.println(item);
        System.out.println("Member successfully updated!");
    }

    public void removeMember(String id) {
        if (findById(id).isEmpty()) {
            System.out.println("No member found with ID " + id);
            return;
        }

        memberRepository.delete(id);
        System.out.println("Member successfully removed!");
    }

    public ArrayList<Member> listMembers() {
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
