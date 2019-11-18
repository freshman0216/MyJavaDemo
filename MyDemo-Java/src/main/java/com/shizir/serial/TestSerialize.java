package com.shizir.serial;

import java.io.*;

public class TestSerialize {

    public static void main(String[] args) throws Exception {
        //serializePerson();
        Person p = deserializePerson();
        System.out.println(p.getName()+";"+p.getAge());
        //Person2 p2 = deserializePerson2();
        //System.out.println(p2.getName()+";"+p2.getAge());
    }

    private static void serializePerson() throws FileNotFoundException, IOException {
        Person person = new Person();
        person.setName("测试实例");
        person.setAge(25);

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/person.txt")));
        oo.writeObject(person);
        System.out.println("序列化成功");
        oo.close();
    }

    private static Person deserializePerson() throws IOException, Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:/person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person反序列化成功");
        return person;
    }

    private static Person2 deserializePerson2() throws IOException, Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:/person.txt")));
        Person2 person = (Person2) ois.readObject();
        System.out.println("Person2反序列化成功");
        return person;
    }
}
