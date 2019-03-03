package com.basic;

class Parent {
	
	final private String name;
	public String getName() {
		return name;
	}

/*	public void setName(String name) {
		this.name = name;
	}*/

	Parent(String s) {
		name = "Name";
		System.out.println("Parent");
	}

	Parent() {
		System.out.println("Default");
		name = "Name";
	}
	
	void method()
	{
		System.out.println("parent method");
	}
}

class Child extends Parent {
	int a[] = new int[5]; 
	Child(String s) {
		System.out.println("Child");
	}
	Child(Integer i){
		
	}
	@Override
	void method()
	{
		System.out.println("child method");
	}

}

public class TryDoubts {

	public static void main(String[] args) {
		final Parent parent = new Child("temp");
		System.out.println("1 << 30 is " + (1 << 30));
		//parent.setName("Changed");
		parent.method();
		
		long longWithL = 1000*60*60*24L*365L;
		long longWithoutL = 1000*60*60*24*365;
		System.out.println(longWithL);
		System.out.println(longWithoutL);
		double d = -10;
		System.out.println(d);
		
		
	}
}
