package jpabook.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Child> children = new ArrayList<>();

    public void addChild(Child child){
        child.setParent(this);
        children.add(child);
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
