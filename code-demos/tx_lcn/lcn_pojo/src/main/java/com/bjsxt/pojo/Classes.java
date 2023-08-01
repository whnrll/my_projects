package com.bjsxt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 自定义班级类型对象
 */
public class Classes implements Serializable {
    private Long id;
    private String className;
    // 有默认值。如果为赋值，使用系统当前时间。
    private Date createDate = new Date();

    public Classes() {
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return Objects.equals(id, classes.id) &&
                Objects.equals(className, classes.className) &&
                Objects.equals(createDate, classes.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, createDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
