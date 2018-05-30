package com.project.todo.tasks.repository;

import com.project.todo.tasks.document.UserAuth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends MongoRepository<UserAuth,String> {
}
