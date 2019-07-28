package org.sid;

import org.sid.dao.TaskRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.entities.Task;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AccountService accountService;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder();
}
    @Override
    public void run(String... args) throws Exception {

        accountService.save(new AppUser("admin","admin"));
        accountService.save(new AppUser("user","user"));
        accountService.save(new AppRole("ADMIN"));
        accountService.save(new AppRole("USER"));
        accountService.addRoleToUser("ADMIN","admin");
        accountService.addRoleToUser("USER","admin");
        accountService.addRoleToUser("USER","user");

        Stream.of("T1", "T2", "T3").forEach(task -> {
            taskRepository.save(new Task(task));
        });
        taskRepository.findAll().forEach(System.out::println);
    }
}
