package com.pluralsight.conference.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity(name="speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    //Means that we've bound it to com.pluralsight.conference.models.Session.speakers
    @ManyToMany(mappedBy = "speakers")
    //So that we don't loop between the many to many sessions and speakers
    @JsonIgnore
    private List<Session> sessions;

    //Lob = Large object
    @Lob
    //This annotation is to help hibernate deal with binary data
    //Hibernate is the JPA application that we're using under the covers
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] speaker_photo;

    public Speaker() {
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public Speaker setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public Speaker setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public Speaker setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Speaker setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Speaker setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public Speaker setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
        return this;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Speaker setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public Speaker setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
        return this;
    }
}
