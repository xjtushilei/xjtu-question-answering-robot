package com.xjtushilei.knowledge.repository;

import com.xjtushilei.knowledge.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shilei
 * @Date 2017/6/26.
 */
public interface QnaRepository extends JpaRepository<Qna,Long> {

    Qna findByQuestion(String question);

}
