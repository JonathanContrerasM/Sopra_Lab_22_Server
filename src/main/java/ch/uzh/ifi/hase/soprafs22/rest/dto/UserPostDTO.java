package ch.uzh.ifi.hase.soprafs22.rest.dto;

import ch.uzh.ifi.hase.soprafs22.constant.UserStatus;

import java.util.Date;

public class UserPostDTO {

  private String name;

  private String username;

  private String password;

  private Date creation_date;

  private Date birthday;

  private String token;

  private Long id;

  private UserStatus logged_in;



  public String getPassword() {
        return password;
    }

  public void setPassword(String password) {
        this.password = password;
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

  public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserStatus getLogged_in() {
        return logged_in;
    }

    public void setLogged_in(UserStatus logged_in) {
        this.logged_in = logged_in;
    }
}
