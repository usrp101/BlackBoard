package com.example.demo.Domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String description;
    private String uuid = UUID.randomUUID().toString();
    private String referenceName;
    private String referenceId;
    private String commentatorId;

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the referenceName
     */
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * @param referenceName the referenceName to set
     */
    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    /**
     * @return String return the referenceId
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * @param referenceId the referenceId to set
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * @return String return the commentatorId
     */
    public String getCommentatorId() {
        return commentatorId;
    }

    /**
     * @param commentatorId the commentatorId to set
     */
    public void setCommentatorId(String commentatorId) {
        this.commentatorId = commentatorId;
    }

}
