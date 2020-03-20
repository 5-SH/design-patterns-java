package proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

interface VirtualListLoader {
  List load();
}

class SupplierVL {
  private Long id;
  private String name;
  private List products;

  public SupplierVL(Long in, String name) {
    this.id = id;
    this.name = name;
  }

  public void setProducts(VirtualList list) {
    this.products = list;
  }

  public List getProducts() {
    return this.products;
  }
}

class SupplierMapper {
  public static class ProductLoader implements VirtualListLoader {
    private Long id;

    public ProductLoader(long id) {
      this.id = id;
    }

    @Override
    public List load() {
      return ProductMapper.create().findForSupplier(id);
    }
  }

  // ResultSet rs -> List rs
  protected Object doLoad(long id, List rs) throws SQLException {
    // String nameArg = rs.getString(2);
    String nameArg = rs.get(0).toString();
    SupplierVL result = new SupplierVL(id, nameArg);
    result.setProducts(new VirtualList(new ProductLoader(id)));
  }
}

class ProductMapper {
  private static ProductMapper instance;

  public static ProductMapper create() {
    if (instance == null)
      return new ProductMapper();
    return instance;
  }

  // test stub
  public List findForSupplier(long id) {
    List<String> product = new ArrayList<String>();
    product.add("test1");
    product.add("test2");
    product.add("test3");

    return product;
  }
}

class VirtualList implements List {
  private List source;
  private VirtualListLoader loader;

  public VirtualList(VirtualListLoader loader) {
    this.loader = loader;
  }

  private List getSource() {
    if (source == null)
      source = loader.load();
    return source;
  }

  @Override
  public int size() {
    return getSource().size();
  }

  @Override
  public boolean isEmpty() {
    return getSource().isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    return null;
  }

  @Override
  public Object[] toArray(Object[] a) {
    return null;
  }

  @Override
  public boolean add(Object e) {
    return false;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection c) {
    return false;
  }

  @Override
  public boolean addAll(Collection c) {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public Object get(int index) {
    return getSource().get(index);
  }

  @Override
  public Object set(int index, Object element) {
    return null;
  }

  @Override
  public void add(int index, Object element) {

  }

  @Override
  public Object remove(int index) {
    return null;
  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator listIterator() {
    return null;
  }

  @Override
  public ListIterator listIterator(int index) {
    return null;
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    return null;
  }
}

public class LazyLoadProxy {
  public static void main(String[] argv) throws SQLException {
    // test ResultSet
    List supplier = new ArrayList();
    supplier.add("test name");

    SupplierMapper sm = new SupplierMapper();
    SupplierVL svl = (SupplierVL) sm.doLoad(0L, supplier);

    for (int i = 0; i < svl.getProducts().size(); i++) {
      System.out.println("product " + i + " : " + svl.getProducts().get(i));
    }
  }
}