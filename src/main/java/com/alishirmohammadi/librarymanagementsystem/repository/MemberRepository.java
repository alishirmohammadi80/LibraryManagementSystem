package com.alishirmohammadi.librarymanagementsystem.repository;

import com.alishirmohammadi.librarymanagementsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
