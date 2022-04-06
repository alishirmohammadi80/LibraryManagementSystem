package com.alishirmohammadi.librarymanagementsystem.service.impl;

import java.util.List;

import com.alishirmohammadi.librarymanagementsystem.entity.Member;
import com.alishirmohammadi.librarymanagementsystem.exception.NotFoundException;
import com.alishirmohammadi.librarymanagementsystem.repository.MemberRepository;
import com.alishirmohammadi.librarymanagementsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
@Autowired
MemberRepository memberRepository;

	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}

	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Member findMemberById(Long id) {
		return memberRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Member not found with ID %d", id)));
	}

	@Override
	public void createMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void updateMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void deleteMember(Long id) {
		final Member member = memberRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Member not found with ID %d", id)));

		memberRepository.deleteById(member.getId());
	}

}
