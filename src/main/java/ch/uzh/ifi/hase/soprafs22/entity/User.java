package ch.uzh.ifi.hase.soprafs22.entity;

import ch.uzh.ifi.hase.soprafs22.constant.UserStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
  private Date creation_date;

  @Column(nullable = true)
  private Date birthday;     //Change later to Date Datatype

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private UserStatus logged_in;

  //Constructor
  public User(Long id, String name, String username, String password, Date creation_date, Date birthday, String token, UserStatus logged_in) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.creation_date = creation_date;
        this.birthday = birthday;
        this.token = token;
        this.logged_in = logged_in;
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

  public Date getCreation_date() {
      return creation_date;
  }

  public void setCreation_date(Date creation_date) {
      this.creation_date = creation_date;
  }

  public Date getBirthday() {
      return birthday;
  }

  public void setBirthday(Date birthday) {
      this.birthday = birthday;
  }

  public UserStatus getLogged_in() {
    return logged_in;
  }

  public void setLogged_in(UserStatus logged_in) {
    this.logged_in = logged_in;
  }
}
