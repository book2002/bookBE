package com.team2002.capstone.repository;

import com.team2002.capstone.domain.MemorableSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemorableSentenceRepository extends JpaRepository<MemorableSentence, Long> {
    List<MemorableSentence> findByBookShelfItem_ItemId(Long itemId);
}