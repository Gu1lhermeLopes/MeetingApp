package api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "contacts")
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;


  @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
  @JoinTable(name = "contacts_meetings",joinColumns = {@JoinColumn(name = "contact_id")},inverseJoinColumns = {@JoinColumn(name = "meeting_id")})
  @JsonIgnoreProperties("contacts")
  Set <Meeting> meetings = new HashSet <Meeting>();



  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public Set<Meeting> getMeetings() {
    return meetings;
  }


  public void setMeetings(Set<Meeting> meetings) {
    this.meetings = meetings;
  }


  @Override
  public String toString() {
    return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", meetings=" + meetings + "]";
  }


}
