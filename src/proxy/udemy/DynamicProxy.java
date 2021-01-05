package proxy.udemy;

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
	
	// proxy instance 의 method 를 호출하고 결과를 리턴한다.
	// proxy parameter : method 가 호출된 proxy instance
	// method parameter : proxy instance 를 통해 호출된 method 와 일치하는 method instance
	// args parameter : method parameter 의 arguments 들
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

// 기존에 proxy 가 필요한 class 마다 새로운 proxy class 를 정의 해야함.
// proxy 기능이 필요 없더라도 target class 의 interface 를 구현하거나 위임해야함.
// -> Dynamic proxy 로 해결, dynamic proxy 는
// 런타임 시 동적으로 만들어지는 proxy
// java refletion 기능을 이용해 프록시 생성,
// target interface 와 동일한 형태로 생성,
// 팩토리 빈으로 생성 가능
public class DynamicProxy {
	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> itf) {
		// dynamic proxy instance 를 만들기 위한 static method
		// target class 가 만들어질 때 itf interface 들을 구현한다. 
		// 각 proxy 객체는 InvocationHandler 구현체와 연결된다. 
		return (T) Proxy.newProxyInstance(itf.getClassLoader(), new Class<?>[] { itf }, new LoggingHandler(target));
	}
	public static void main(String[] args) {
		People p = new People();
		Human logged = withLogging(p, Human.class);
		// proxy instance 를 통해 호출된 method 는 invocation handler 의 invoke method 에 붙는다.
		logged.talk();
		logged.walk();
		logged.walk();
		
		System.out.println(logged);
	}

}
