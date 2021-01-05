package proxy.udemy;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class DomainObject {
  protected LoadStatus Status;
  private Long key;

  public DomainObject(long key) {
    this.key = key;
    this.Status = Status.GHOST;
  }

  protected void Load() {
    if (isGhost()) {
      System.out.println("DomainObject is ghost status");
      DataSource.Load(this);
    }
  }

  public boolean isGhost() {
    return Status == LoadStatus.GHOST;
  }

  public boolean isLoaded() {
    return Status == LoadStatus.LOADED;
  }

  public void MarkLoading() {
    Status = LoadStatus.LOADING;
  }

  public void MarkLoaded() {
    Status = LoadStatus.LOADED;
  }

  enum LoadStatus {
    GHOST, LOADING, LOADED
  };
}

interface IDataSource {
  void Load(DomainObject obj);
}

class DataSource {
  private static IDataSource instance;

  public static void Load(DomainObject obj) {
    instance.Load(obj);
  }

  public static void init(IDataSource ids) {
    instance = ids;
  }
}

class Employee extends DomainObject {
  private String name;
  private Department department;

  public Employee(Long key) {
    super(key);
  }

  public String getName() {
    Load();
    return name;
  }

  public void setName(String name) {
    Load();
    this.name = name;
  }

  public Department getDepartment() {
    Load();
    return department;
  }

  public void setDepartment(Department department) {
    Load();
    this.department = department;
  }
}

class Department extends DomainObject {
  private String name;

  public Department(long key) {
    super(key);
  }

  public String getName() {
    Load();
    return name;
  }

  public void setName(String name) {
    Load();
    this.name = name;
  }
}

class MapperRegistry implements IDataSource {
  private static Map mappers = new Hashtable<Class<? extends DomainObject>, Mapper>();

  @Override
  public void Load(DomainObject obj) {
    Mapper(obj.getClass()).Load(obj);
  }

  public static Mapper Mapper(Class<? extends DomainObject> type) {
    return (Mapper) mappers.get(type);
  }

  public static void Set(Class<? extends DomainObject> type, Mapper mapper) {
    mappers.put(type, mapper);
  }
}

abstract class Mapper {
  private Map loadedMap = new Hashtable<Long, DomainObject>();

  public void Load(DomainObject obj) {
    if (!obj.isGhost())
      return;
    // key 로 DB 조회
    Map reader = new HashMap<String, String>();
    reader.put("ename", "test employee name");
    reader.put("dname", "test department name");

    LoadLine(reader, obj);
  }

  public void LoadLine(Map reader, DomainObject obj) {
    if (obj.isGhost()) {
      System.out.println("data source start loading");
      obj.MarkLoading();

      doLoadLine(reader, obj);

      System.out.println("data source loaded");
      obj.MarkLoaded();
    }
  }

  public DomainObject AbstractFind(long key) {
    DomainObject result;
    result = (DomainObject) loadedMap.get(key);
    if (result == null) {
      result = createGhost(key);
      loadedMap.put(key, result);
    }
    return result;
  }

  public abstract DomainObject createGhost(long key);

  protected abstract void doLoadLine(Map reader, DomainObject obj);
}

class EmployeeMapper extends Mapper {
  public Employee Fine(long key) {
    return (Employee) AbstractFind(key);
  }

  @Override
  public DomainObject createGhost(long key) {
    return new Employee(key);
  }

  @Deprecated
  protected void doLoadLine(Map reader, DomainObject obj) {
    Employee employee = (Employee) obj;
    employee.setName((String) reader.get("ename"));
    DepartmentMapper depMapper = (DepartmentMapper) MapperRegistry.Mapper(Department.class);
    employee.setDepartment(depMapper.Find(1L));
  }
}

class DepartmentMapper extends Mapper {
  public Department Find(long key) {
    return (Department) AbstractFind(key);
  }

  @Override
  public DomainObject createGhost(long key) {
    return new Department(key);
  }

  @Deprecated
  protected void doLoadLine(Map reader, DomainObject obj) {
    Department department = (Department) obj;
    department.setName((String) reader.get("dname"));
  }
}

public class LazyLoadGhost {
  public static void main(String[] args) {
    MapperRegistry mr = new MapperRegistry();
    EmployeeMapper em = new EmployeeMapper();
    DepartmentMapper dm = new DepartmentMapper();

    mr.Set(Employee.class, em);
    mr.Set(Department.class, dm);
    DataSource.init(mr);

    Employee e = new Employee(1L);
    System.out.println("employee name : " + e.getName());
    System.out.println("employee department name : " + e.getDepartment().getName());
    
    System.out.println("employee name : " + e.getName());
    System.out.println("employee department name : " + e.getDepartment().getName());
  }
}