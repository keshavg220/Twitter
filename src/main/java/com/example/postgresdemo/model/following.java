package com.example.postgresdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "following", 
uniqueConstraints= @UniqueConstraint(columnNames={"primaryUsername", "secondaryUsername"})
)

public class following extends AuditModel {
	
	@Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )

    private Long id;
	

	private String primaryUsername;
	private String secondaryUsername;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrimaryUsername() {
		return primaryUsername;
	}
	public void setPrimaryUsername(String primaryUsername) {
		this.primaryUsername = primaryUsername;
	}
	public String getSecondaryUsername() {
		return secondaryUsername;
	}
	public void setSecondaryUsername(String secondaryUsername) {
		this.secondaryUsername = secondaryUsername;
	}

}
