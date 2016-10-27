package com.tangwan.guava.collection;

import com.google.common.collect.*;

import java.util.*;

public class GuavaCollections {

	public void testMap() {
		// jdk写法
		Map<String, Map<String, String>> map1 = new HashMap<>();

		// guava写法
		Map<String, Map<String, String>> map2 = Maps.newHashMap();
		Map<String, Person> personMap1 = Maps.newHashMap();
		// 不可变map
		Map<String, String> map = ImmutableMap.of("ON", "TRUE", "OFF", "FALSE");
		Map<String, Person> personMap2 = ImmutableMap.of("hello", new Person("tw", "132654"), "fuck", new Person("ww", "321645"));
		ImmutableMap<String, Person> personImmutableMap = ImmutableMap.<String, Person> builder().put("hell", new Person("tw", "1223")).putAll(personMap2).build();
	}

	public void testList() {
		// jdk写法
		List<List<Map<String, String>>> list1 = new ArrayList<List<Map<String, String>>>();

		// guava写法
		List<List<Map<String, String>>> list2 = Lists.newArrayList();
		List<Person> personList1 = Lists.newLinkedList();
		List<Person> personList2 = Lists.newArrayList(new Person("唐婉", "132645"), new Person("tangwan", "123456"));
		List<String> list = Lists.newArrayList("one", "two", "three");

		// 不可变
		ImmutableList<Person> personImmutableList = ImmutableList.of(new Person("1", "321456"), new Person("2", "132465"));
	}

	public void testSet() {
		// jdk写法
		Set<Person> personSet1 = new HashSet<Person>();
		Set<String> set1 = new HashSet<String>();
		set1.add("one");
		set1.add("two");
		set1.add("three");

		Set<Integer> data = new HashSet<Integer>();
		data.addAll(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80));
		// fixedData - [50,70, 80,20, 40,10, 60,30]
		Set<Integer> fixedData = Collections.unmodifiableSet(data);
		data.add(90); // fixedData - [50, 70, 80, 20, 40, 10, 90, 60, 30]

		// guava写法
		Set<Person> personSet2 = Sets.newHashSet();
		Set<Person> personSet3 = Sets.newHashSet(new Person("1123", "213465"));
		Set<String> set2 = Sets.newHashSet("one", "two", "three");

		// 不可变集合
		ImmutableSet<Integer> numbers = ImmutableSet.of(10, 20, 30, 40, 50);
		// 使用copyOf方法
		ImmutableSet<Integer> another = ImmutableSet.copyOf(numbers);
		// 使用Builder方法
		ImmutableSet<Integer> numbers2 = ImmutableSet.<Integer> builder().addAll(numbers).add(60).add(70).add(80).build();

		ImmutableSet<Person> personImmutableSet = ImmutableSet.copyOf(personSet2);
	}

	public void testArrays() {
		// jdk写法
		Integer[] intArrays1 = new Integer[10];

		// guava写法
		Integer[] intArrays2 = ObjectArrays.newArray(Integer.class, 10);
	}

	public void testMultiMap() {
		// 一种key可以重复的map，子类有ListMultimap和SetMultimap，对应的通过key分别得到list和set
		Multimap<String, Person> customersByType = ArrayListMultimap.create();
		customersByType.put("abc", new Person("12345", "12546"));
		customersByType.put("abc", new Person("12345", "12546"));
		customersByType.put("abc", new Person("12345", "12546"));
		customersByType.put("abc", new Person("12345", "12546"));
		customersByType.put("abcd", new Person("12345", "12546"));
		customersByType.put("abcde", new Person("12345", "12546"));
		for (Person person : customersByType.get("abc")) {
			System.out.println(person.getUsername());
		}
	}

	public void testMultiSet() {
		// 不是集合，可以增加重复的元素，并且可以统计出重复元素的个数，例子如下：
		Multiset<Integer> multiSet = HashMultiset.create();
		multiSet.add(10);
		multiSet.add(30);
		multiSet.add(30);
		multiSet.add(40);
		System.out.println(multiSet.count(30)); // 2
		System.out.println(multiSet.size()); // 4
	}

	public void testTable() {
		// 相当于有两个key的map，不多解释
		Table<Integer, Integer, Person> personTable = HashBasedTable.create();
		personTable.put(1, 20, new Person("12345", "12546"));
		personTable.put(0, 30, new Person("12345", "12546"));
		personTable.put(0, 25, new Person("12345", "12546"));
		personTable.put(1, 50, new Person("12345", "12546"));
		personTable.put(0, 27, new Person("12345", "12546"));
		personTable.put(1, 29, new Person("12345", "12546"));
		personTable.put(0, 33, new Person("12345", "12546"));
		personTable.put(1, 66, new Person("12345", "12546"));
		// 1,得到行集合
		Map<Integer, Person> rowMap = personTable.row(0);
		int maxAge = Collections.max(rowMap.keySet());
	}

	public void testBiMap() {
		// 是一个一一映射，可以通过key得到value，也可以通过value得到key；
		// 双向map
		BiMap<Integer, String> biMap = HashBiMap.create();
		biMap.put(1, "hello");
		biMap.put(2, "helloa");
		biMap.put(3, "world");
		biMap.put(4, "worldb");
		biMap.put(5, "my");
		biMap.put(6, "myc");
		int value = biMap.inverse().get("my");
		System.out.println("my --" + value);
	}

	public void testClassToInstanceMap() {
		// 有的时候，你的map的key并不是一种类型，他们是很多类型，你想通过映射他们得到这种类型，guava提供了ClassToInstanceMap满足了这个目的。
		// 除了继承自Map接口，ClassToInstaceMap提供了方法 T getInstance(Class<T>) 和 T
		// putInstance(Class<T>, T),消除了强制类型转换。
		ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
		numberDefaults.putInstance(Integer.class, Integer.valueOf(0));

		// 从技术上来说，ClassToInstanceMap<B> 实现了Map<Class<? extends B>,
		// B>，或者说，这是一个从B的子类到B对象的映射，这可能使得ClassToInstanceMap的泛型轻度混乱，但是只要记住B总是Map的上层绑定类型，通常来说B只是一个对象。
		// guava提供了有用的实现， MutableClassToInstanceMap 和
		// ImmutableClassToInstanceMap.重点：像其他的Map<Class,Object>,ClassToInstanceMap
		// 含有的原生类型的项目，一个原生类型和他的相应的包装类可以映射到不同的值；
		ClassToInstanceMap<Person> classToInstanceMap = MutableClassToInstanceMap.create();
		Person person = new Person("abc", "46464");
		classToInstanceMap.putInstance(Person.class, person);
		// System.out.println("string:"+classToInstanceMap.getInstance(String.class));
		// System.out.println("integer:" +classToInstanceMap.getInstance(Integer.class));
		Person person1 = classToInstanceMap.getInstance(Person.class);
	}
}
