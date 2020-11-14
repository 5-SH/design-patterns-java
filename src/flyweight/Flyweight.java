package flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

// as-is
class User {
  private String fullName;

  public User(String fullName) {
    this.fullName = fullName;
  }
}

// to-be flyweight
class User2 {
  static List<String> strings = new ArrayList<>();
  private int[] names;

  public User2(String fullName) {
    Function<String, Integer> getOrAdd = (String s) -> {
      int idx = strings.indexOf(s);
      if (idx != -1)
        return idx;
      else {
        strings.add(s);
        return strings.size() - 1;
      }
    };

    // Stream은 자바 8부터 추가된 기능으로 컬렉션, 배열등의 저장 요소를 하나씩 참조하며
    // 함수형 인터페이스(람다식)를 적용하며 반복적으로 처리할 수 있도록 해주는 기능
    // mapXXX 함수들은 해당 타입의 스트림으로 바꿔준다.
    // 예를들어 "1","2","3" 을 가진 스트림이 있었으면 mapToInt를 적용하면 1,2,3 을 가진 스트림으로 변환 해준다.
    names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
  }
}

// memory space optimization
// avoid redundancy
// use less memory by storing externally the data associated with similar
// objects
public class Flyweight {
  public static void main(String[] args) {
    User2 user1 = new User2("John Smith");
    User2 user2 = new User2("Jane Smith");
  }
}
