package org.cx.model;

import java.io.Serializable;

public class Test implements Serializable {

    private static final long serialVersionUID = -7617215978965450075L;

    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String nums;

    /**
     *
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums == null ? null : nums.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", nums='" + nums + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}