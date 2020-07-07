package com.pluralsight.conference.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name="sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {
//    Use _ in Java names so they exactly match the names in the DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;

    @ManyToMany
    @JoinTable(
            name="session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name="speaker_id")
    )
    private List<Speaker> speakers;

    public Session() {
    }

    public Long getSession_id() {
        return session_id;
    }

    public Session setSession_id(Long session_id) {
        this.session_id = session_id;
        return this;
    }

    public String getSession_name() {
        return session_name;
    }

    public Session setSession_name(String session_name) {
        this.session_name = session_name;
        return this;
    }

    public String getSession_description() {
        return session_description;
    }

    public Session setSession_description(String session_description) {
        this.session_description = session_description;
        return this;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public Session setSession_length(Integer session_length) {
        this.session_length = session_length;
        return this;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public Session setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
        return this;
    }
}
