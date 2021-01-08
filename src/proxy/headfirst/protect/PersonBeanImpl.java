package proxy.headfirst.protect;

public class PersonBeanImpl implements PersonBean {
  String name;
  String gender;
  String interests;
  int rating;
  int ratingCount = 0;

  public PersonBeanImpl(String name, String gender) {
    this.name = name;
    this.gender = gender;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getGender() {
    return gender;
  }

  @Override
  public String getInterests() {
    return interests;
  }

  @Override
  public int getHotOrNotRating() {
    return ratingCount == 0 ? 0 : rating / ratingCount;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public void setInterests(String interests) {
    this.interests = interests;
  }

  @Override
  public void setHotOrNotRating(int rating) {
    this.rating += rating;
    ratingCount++;
  }
}
