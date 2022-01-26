package com.lohika.demoGradleAppJava17.repository;

import com.lohika.demoGradleAppJava17.entity.MyUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<MyUserRole, Long> {
    MyUserRole findByName(String name);
}
