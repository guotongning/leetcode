package com.ning.stream;

/**
 * TODO
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since
 */
public class Person {
    private String name;
    private Integer age;
    private Gender gender;
    private double salary;
    private Area area;

    public Person() {
    }

    public Person(String name, Integer age, Gender gender, double salary, Area area) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                ", area='" + area + '\'' +
                '}';
    }
}
