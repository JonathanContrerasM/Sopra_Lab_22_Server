package ch.uzh.ifi.hase.soprafs22.entity;

import ch.uzh.ifi.hase.soprafs22.constant.UserStatus;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Internal User Representation
 * This class composes the internal representation of the user and defines how
 * the user is stored in the database.
 * Every variable will be mapped into a database field with the @Column
 * annotation
 * - nullable = false -> this cannot be left empty
 * - unique = true -> this value must be unqiue across the database -> composes
 * the primary key
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = true)
  private String registrationDate;

  @Column(nullable = true)
  private String birthDate;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private UserStatus status;

  //Constructor
  public User(Long id, String name, String username, String password, String registrationDate, String birthDate, String token, UserStatus status) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
        this.token = token;
        this.status = status;
    }
  //Empty Constructor
  public User() {
    }

  //Getter and Setter
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getRegistrationDate() {
      return registrationDate;
  }

  public void setRegistrationDate(String registrationDate) {
      this.registrationDate = registrationDate;
  }

  public String getBirthDate() {
      return birthDate;
  }

  public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }
}
