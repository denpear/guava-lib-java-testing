package denpear.javatrain.learn.comparator;

import java.math.BigDecimal;

public class Developer {
    private String name;

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }

    private BigDecimal salary;
    private Integer age;

    public Developer(String name, BigDecimal salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
