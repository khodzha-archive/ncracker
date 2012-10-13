//package ru.ncedu.java.tasks;

import ru.ncedu.java.tasks.Reflections;
import java.lang.reflect.*;
import java.util.*;

public class ReflectionsImpl implements Reflections {
	
	public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
		if (object == null) {
			throw new NullPointerException();
		}
		Class cl = object.getClass();
		Field f = cl.getDeclaredField(fieldName);
		f.setAccessible(true);
		try {
			return f.get(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public Set<String> getProtectedMethodNames(Class clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		Set<String> result = new HashSet<String>();
		for (Method m : methods) {
			if (Modifier.isProtected(m.getModifiers())) {
				result.add(m.getName());
			}
		}
		return result;
	}
	
	public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		HashSet<Method> methods = new HashSet<Method>();
		Class t = clazz;
		methods.addAll(Arrays.asList(t.getDeclaredMethods()));
		try {
			do {
				t = t.getSuperclass();
				methods.addAll(Arrays.asList(t.getDeclaredMethods()));
			} while (t != Class.forName("java.lang.Object"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return methods;
	}

	public List<Class> getExtendsHierarchy(Class clazz) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		ArrayList<Class> supers = new ArrayList<Class>();
		Class t = clazz;
		try {
			do {
				t = t.getSuperclass();
				supers.add(t);
			} while (t != Class.forName("java.lang.Object"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supers;
	}
	
	public Set<Class> getImplementedInterfaces(Class clazz) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		return new HashSet<Class>(Arrays.asList(clazz.getInterfaces()));
	}

	public List<Class> getThrownExceptions(Method method) {
		if (method == null) {
			throw new NullPointerException();
		}
		return new ArrayList<Class>(Arrays.asList(method.getExceptionTypes()));
	}

	// FAILED
	public String getFooFunctionResultForDefaultConstructedClass() {
		SecretClass sc = new SecretClass();
		try {
			Method[] methods = SecretClass.class.getDeclaredMethods();
			Method foo = null;
			for (Method f : methods) {
				if (f.getName() == "foo" && f.getParameterTypes().length == 0) {
					foo = f;
				}
			}
			foo.setAccessible(true);
			return (String)foo.invoke(sc, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// FAILED
	public String getFooFunctionResultForClass(String constructorParameter, String string, Integer ... integers) {
		SecretClass sc = new SecretClass(constructorParameter);
		try {
			Method[] methods = SecretClass.class.getDeclaredMethods();
			Method foo = null;
			for (Method f : methods) {
				if (f.getName() == "foo" && f.getParameterTypes().length != 0) {
					foo = f;
				}
			}
			foo.setAccessible(true);
			return (String)foo.invoke(sc, new Object[] { string, integers});
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		ReflectionsImpl q = new ReflectionsImpl();
		System.out.println(q.getFooFunctionResultForClass("constructor_parameter", "_sEcRReT", 1, 5, 10));
	}

	
	@SuppressWarnings("unused")
	public class SecretClass {

		private String text = null;
		
		private String secret = "secret";
		
		private SecretClass() {
			;
		}
		
		public SecretClass(String text) {
			this.text = text;
		}
		
		public void setSecret (String secret) {
			this.secret = secret;
		}
		
		private String foo(String string, Integer ... integers){
			String s = "";
			/* Some text hidden : start */
			int in = 0;
			if(integers != null)
				for(Integer i : integers)
					in += i;
			s += string + " - > " + in;
			/* Some text hidden : end */
			return s;
		}
		
		private String foo(){
			String s = "";
			/* Some text hidden : start */
			s += "abraKadabra";
			/* Some text hidden : end */
			return s;
		}
		
	}
}