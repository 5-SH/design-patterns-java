package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
	void walk();
	void talk();
}

class People implements Human {
	@Override
	public void walk() {
		System.out.println("I am walking");
	}
	@Override
	public void talk() {
		System.out.println("I am talking");
	}
}

class LoggingHandler implements InvocationHandler {
	private final Object target;
	private Map<String, Integer> calls = new HashMap<>();
	
	public LoggingHandler(Object target) {
		this.target = target;
	}
	
	// proxy instance �� method �� ȣ���ϰ� ����� �����Ѵ�.
	// proxy parameter : method �� ȣ��� proxy instance
	// method parameter : proxy instance �� ���� ȣ��� method �� ��ġ�ϴ� method instance
	// args parameter : method parameter �� arguments ��
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();
		if (name.contains("toString")) {
			return calls.toString();
		}
		calls.merge(name, 1, Integer::sum);
		return method.invoke(target, args);
	}
	
}

// ������ proxy �� �ʿ��� class ���� ���ο� proxy class �� ���� �ؾ���.
// proxy ����� �ʿ� ������ target class �� interface �� �����ϰų� �����ؾ���.
// -> Dynamic proxy �� �ذ�, dynamic proxy ��
// ��Ÿ�� �� �������� ��������� proxy
// java refletion ����� �̿��� ���Ͻ� ����,
// target interface �� ������ ���·� ����,
// ���丮 ������ ���� ����
public class DynamicProxy {
	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> itf) {
		// dynamic proxy instance �� ����� ���� static method
		// target class �� ������� �� itf interface ���� �����Ѵ�. 
		// �� proxy ��ü�� InvocationHandler ����ü�� ����ȴ�. 
		return (T) Proxy.newProxyInstance(itf.getClassLoader(), new Class<?>[] { itf }, new LoggingHandler(target));
	}
	public static void main(String[] args) {
		People p = new People();
		Human logged = withLogging(p, Human.class);
		// proxy instance �� ���� ȣ��� method �� invocation handler �� invoke method �� �ٴ´�.
		logged.talk();
		logged.walk();
		logged.walk();
		
		System.out.println(logged);
	}

}
