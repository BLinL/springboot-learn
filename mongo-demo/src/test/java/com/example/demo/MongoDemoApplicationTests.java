package com.example.demo;

import com.example.demo.domain.Status;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class MongoDemoApplicationTests {

    @Autowired
    UserRepository repository;

    public void init() {
        System.out.println("====================add user ======================");
        repository.deleteAll();
        repository.save(new User("张三",12));
        repository.save(new User("李四",32));
        repository.save(new User("王五",33));
        repository.save(new User("海淘",45));
        repository.save(new User("稍等",145));
        repository.save(new User("收到",66));
    }

    @Test
    public void mongodb_insert() {
        User user = new User("张伟", 12);
        user.setStatus(new Status(12 ,13));
        repository.insert(user);
    }

    @Test
    public void mongodb_findAll() {

        List<User> users = repository.findAll(Example.of(new User("abc",12)));
        System.out.println(users);
//        Assert.isTrue(users.size() == 6, "user size should be 6");
    }

    @Test
    public void mongodb_Countone(){
        Example<User> example = Example.of(new User("收到", 66));

        Assert.isTrue(repository.count(example) == 1,"must find one");
    }

    @Test
    public void mongodb_findOne(){
        Example<User> example = Example.of(new User("收到", 66));

        Assert.isTrue(repository.findOne(example).get().getName().equals("收到"),"must find 收到");
    }

    @Test
    public void mongodb_update(){

        Optional<User> usertoUpdate = repository.findOne(Example.of(new User("张三", 12)));
        User user = usertoUpdate.get();
        System.out.println(user);
        user.setAge(100);
        repository.save(user);
    }

    @Test
    public void mongodb_delete(){

        Optional<User> usertoDelete = repository.findOne(Example.of(new User("张三", 12)));
        User user = usertoDelete.get();
        repository.delete(user);
    }
}
