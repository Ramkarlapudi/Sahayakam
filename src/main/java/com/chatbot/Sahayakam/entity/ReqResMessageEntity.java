package com.chatbot.Sahayakam.entity;
import jakarta.persistence.*;

@Entity
public class ReqResMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 2147483647)
    private String reqMessage;

    @Column(length = 2147483647)
    private String resMessage;

    public ReqResMessageEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public Long getId() {
        return id;
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public String getResMessage() {
        return resMessage;
    }
}
