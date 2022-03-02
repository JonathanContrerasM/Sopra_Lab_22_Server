package ch.uzh.ifi.hase.soprafs22.rest.dto;

public class UserPostDTO {

  private String name;

  private String username;

  private String password;

  private String registrationDate;

  private String birthDate;



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
}
