package com.minod.jpa.domain.cascade;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id @GeneratedValue
    private Long id;
    private String name;

//    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
    @OneToMany(mappedBy="parent", cascade=CascadeType.PERSIST, orphanRemoval=true)
//    @OneToMany(mappedBy="parent")
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) { // 커스텀메서드, 연관관계 양쪽객체에 갱신
        childList.add(child);
        child.setParent(this); // 자식에도 부모객체 정보 넣어준다.
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
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
