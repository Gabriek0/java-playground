package main.java.com.library_management.repository;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Member;

import java.util.ArrayList;
import java.util.Optional;

public class MemberRepository implements Repository<Member, String> {
    private ArrayList<Member> members = new ArrayList<>();

    public void create(Member item) {
        members.add(item);
    }

    public void update(String id, Member item) {
        members.removeIf(m -> m.getId().equals(id));
        members.add(item);
    }

    public void delete(String id) {
        members.removeIf(m -> m.getId().equals(id));
    }

    public Optional<Member> findById(String id) {
        return members.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public ArrayList<Member> list() {
        return members;
    }
}
