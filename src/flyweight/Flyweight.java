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

    // Stream�� �ڹ� 8���� �߰��� ������� �÷���, �迭���� ���� ��Ҹ� �ϳ��� �����ϸ�
    // �Լ��� �������̽�(���ٽ�)�� �����ϸ� �ݺ������� ó���� �� �ֵ��� ���ִ� ���
    // mapXXX �Լ����� �ش� Ÿ���� ��Ʈ������ �ٲ��ش�.
    // ������� "1","2","3" �� ���� ��Ʈ���� �־����� mapToInt�� �����ϸ� 1,2,3 �� ����
    // ��Ʈ������ ��ȯ ���ش�.
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
