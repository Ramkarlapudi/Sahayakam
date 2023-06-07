package com.chatbot.Sahayakam.repository;

import com.chatbot.Sahayakam.entity.ReqResMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ReqResMessageRepository extends JpaRepository<ReqResMessageEntity,Long> {

    Boolean existsBy();

    public List<ReqResMessageEntity> findAllByOrderByIdAsc();
}
