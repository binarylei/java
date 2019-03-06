package com.github.binarylei.collection.sort;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TreeSet 在添加时排序，添加后修改元素内容可能导致元素重复，排序也不是我们想要的
 * @author: leigang
 * @version: 2018-05-05
 */
public class TreeSetTest {

    @Test
    public void test1() {
        TreeSet<Person> persons = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.sortValue - o2.sortValue;
            }
        });
        Person person1 = new Person("person1", 5);
        Person person2 = new Person("person2", 4);
        Person person3 = new Person("person3", 6);

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(new Person("person3", 6));
        System.out.println(persons);

        // 添加后修改数据可能导致数据重复
        person1.setName("person2");
        person1.setSortValue(4);
        System.out.println(persons);
    }

    @Test
    public void test2() {
        TreeSet<UserBean> users = new TreeSet<>();
        users.add(new UserBean(5));
        users.add(new UserBean(4));

        UserBean user = new UserBean(1);
        users.add(user);

        System.out.println(users);
        user.num = 7;
        System.out.println(users);
    }
    
    public static class Person {
        private String name;
        private int sortValue;

        public Person(String name, int sortValue) {
            this.name = name;
            this.sortValue = sortValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSortValue() {
            return sortValue;
        }

        public void setSortValue(int sortValue) {
            this.sortValue = sortValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (sortValue != person.sortValue) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + sortValue;
            return result;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", sortValue=" + sortValue +
                    '}';
        }
    }
}
