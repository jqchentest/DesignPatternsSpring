:icons: font
:source-highlighter: highlight.js
//:numbered:
:toc: left
:toclevels: 2
:toc-title: 目录

= spring中的设计模式
:author: chenjunqiang <chenjunqiang@xxbmm.com>
v1.0 2018-1-20

== 什么是设计模式
根据设计模式的参考书 Design Patterns - Elements of Reusable Object-Oriented Software（中文译名：设计模式 - 可复用的面向对象软件元素） 中所提到的，总共有 23 种设计模式。 +
这些模式可以分为三大类：创建型模式（Creational Patterns）、结构型模式（Structural Patterns）、行为型模式（Behavioral Patterns）。

=== 设计模式之六大原则
====
开闭原则（Open Close Principle::
** 意思：软件模块应该对扩展开放，对修改关闭。
** 举例：在程序需要进行新增功能的时候，不能去修改原有的代码，而是新增代码，实现一个热插拔的效果（热插拔：灵活的去除或添加功能，不影响到原有的功能）。
** 目的：为了使程序的扩展性好，易于维护和升级。

里氏代换原则（Liskov Substitution Principle）::
** 意思：里氏代换原则是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。
** 举例：球类，原本是一种体育用品，它的衍生类有篮球、足球、排球、羽毛球等等，如果衍生类替换了基类的原本方法，如把体育用品改成了食用品（那么软件单位的功能受到影响），就不符合里氏代换原则。
** 目的：对实现抽象化的具体步骤的规范。

依赖倒转原则（Dependence Inversion Principle）::
** 意思：针对接口编程，而不是针对实现编程。
** 举例：以计算机系统为例,无论主板、CPU、内存、硬件都是在针对接口设计的，如果针对实现来设计，内存就要对应到针对某个品牌的主板，那么会出现换内存需要把主板也换掉的尴尬。
** 目的：降低模块间的耦合。

接口隔离原则（Interface Segregation Principle）::
** 使用多个隔离的接口，比使用单个接口要好。
** 举例：比如：登录，注册时属于用户模块的两个接口，比写成一个接口好。
** 目的：提高程序设计灵活性。

迪米特法则（最少知道原则）（Demeter Principle）::
** 意思：一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立。
** 举例：一个类公开的public属性或方法越多，修改时涉及的面也就越大，变更引起的风险扩散也就越大。
** 目的：降低类之间的耦合，减少对其他类的依赖。

单一职责原则（ Single responsibility principle ）::
** 意思：一个类只负责一个功能领域中的相应职责，或者可以定义为：就一个类而言，应该只有一个引起它变化的原因。
** 举例：该原则意思简单到不需要举例！
** 目的：类的复杂性降低，可读性提高，可维护性提高。
====

== 工厂模式
使用简单工厂模式可以将产品的“消费”和生产完全分开，客户端只需要知道自己需要什么产品，
如何来使用产品就可以了，具体的产品生产任务由具体的工厂类来实现。工厂类根据传进来的参数生产具体的产品供消费者使用。
这种模式使得更加利于扩展，当有新的产品加入时仅仅需要在工厂中加入新产品的构造就可以了。

==== 工厂模式包含如下角色
****
Factory: 工厂角色（Factory） +
AbstractProduct: 抽象产品角色（Car） +
ConcreteProduct: 具体产品角色（Audi,Benz,Bmw）
****

image::https://img-blog.csdn.net/20151123182459928[]

==== 实例
话说有一位土豪，他家有三辆汽车——Benz奔驰、Bmw宝马、Audi奥迪，还雇了司机为他开车。
不过，土豪坐车时总是怪怪的：上Benz车后跟司机说“开奔驰车！”坐上Bmw后他说“开宝马车！”，坐上Audi说“开奥迪车！”。
你一定说：这人有病！直接说开车不就行了？！ 而当把土豪的行为放到我们程序设计中来时，会发现这是一个普遍存在的现象。
幸运的是，这种有病的现象在OO（面向对象）语言中可以避免了。下面就以Java语言为例来讲解一下如何避免这种问题。

===== 抽象产品(AbstractProduct)
``` java
public abstract class Car {
  /**
   * 启动汽车
   */
  abstract void drive();
}
```

===== 具体产品(ConcreteProduct)
``` java
/**
 * 具体产品 宝马
 * @author chenjunqiang
 **/
public static class Bmw extends Car {

  @Override
  void drive() {
    System.out.println("宝马启动~");
  }
}

/**
 * 具体产品 奔驰
 * @author chenjunqiang
 **/
public static class Benz extends Car {

  @Override
  void drive() {
    System.out.println("奔驰启动~");
  }
}

/**
 * 具体产品 奥迪
 * @author chenjunqiang
 **/
public static class Audi extends Car {

  @Override
  void drive() {
    System.out.println("奥迪启动~");
  }
}
```

===== 工厂角色(Factory)
``` java
public class Factory {

  public static Car getCar(String type) throws Exception {
    if ("Benz".equals(type)) {
      return new Benz();
    } else if ("Audi".equals(type)) {
      return new Audi();
    } else if ("Bmw".equals(type)) {
      return new Bmw();
    } else {
      throw new Exception();
    }
  }
}

//进化版
public static Car getCar(String type) throws Exception {
  return (Car) Class.forName("com.xxbmm.simple_factory.Factory$" + type).newInstance();
}
```

===== 测试类:
``` java
public static void main(String[] args) throws Exception {
   Car car = Factory.getCar("Benz");
   car.drive();
}
```

=== spring中的例子
在Spring中，我们可以通过指定的工厂方法创建bean。该方法与以前代码示例中看到的完全相同。
它是静态的，可以采取没有或多个参数。为了更好地了解案例，让我们来看一下实例。首先搞定下配置：
```xml

<bean id="welcomerBean" class="com.mysite.Welcomer" factory-method="createWelcomer">
  <constructor-arg ref="messagesLocator"></constructor-arg>
</bean>

<bean id="messagesLocator" class="com.mysite.MessageLocator">
  <property name="messages" value="messages_file.properties"></property>
</bean>
```
bean的初始化代码：
``` java
public class Welcomer {
  private String message;

  public Welcomer(String message) {
    this.message = message;
  }

  public static Welcomer createWelcomer(MessageLocator messagesLocator) {
    Calendar cal = Calendar.getInstance();
    String msgKey = "welcome.pm";
    if (cal.get(Calendar.AM_PM) == Calendar.AM) {
      msgKey = "welcome.am";
    }
    return new Welcomer(messagesLocator.getMessageByKey(msgKey));
  }
}
```

=== 优缺点
****
优点： 1、一个调用者想创建一个对象，只要知道其名称就可以了。 2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。 3、屏蔽产品的具体实现，调用者只关心产品的接口。

缺点：每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。这并不是什么好事。
****

== 适配器模式
把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。

=== 适配器模式包含如下角色：
****
Target：目标角色(Target) +
Adaptee：源角色类(PowerPort220V) +
Adapter：适配器类(Adapter220V、Adapter220V2) +
****
适配器模式有两种 类适配器和对象适配器
===== 类适配器

image::http://upload-images.jianshu.io/upload_images/944365-24c6bf44da1b79ad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240[]
===== 对象适配器

image::http://upload-images.jianshu.io/upload_images/944365-c736416f78a5b2d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240[]

=== 实例
* 背景：小成买了一个进口的电视机
* 冲突：进口电视机要求电压（110V）与国内插头标准输出电压（220V）不兼容
* 解决方案：设置一个适配器将插头输出的220V转变成110V

===== Target：目标角色(Target)
```java
public interface Target {
  /**
   * 输出110V
   */
  void Convert_110v();
}
```
===== 源角色类(Adaptee)
```java
public class PowerPort220V {
  /**
   * 原有插头只能输出220V
   */
  public void Output_220v() {
  }
}
```
==== 类适配器
===== 适配器类(Adapter)
```java
/**
 * 适配器角色 220v插头适配器
 */
class Adapter220V extends PowerPort220V implements Target {
  @Override
  public void Convert_110v() {
    System.out.println("类适配器");
    this.Output_220v();
    System.out.println("220v转换成110v电压 可以使用了");
  }

  //测试类
  public static void main(String[] args) {
    Target target = new Adapter220V();
    target.Convert_110v();
  }
}
```

==== 对象适配器
===== 适配器类(Adapter)
```java
/**
 * 适配器角色 220v插头适配器
 */
class Adapter220V extends PowerPort220V implements Target {
  private PowerPort220V powerPort220V = new PowerPort220V();

  @Override
  public void Convert_110v() {
    System.out.println("对象适配器");
    powerPort220V.Output_220v();
    System.out.println("220v转换成110v电压 可以使用了");
  }

  //测试类
  public static void main(String[] args) {
    Target target = new Adapter220V2();
    target.Convert_110v();
  }
}
```

=== spring中的例子
在Spring的Aop中，使用的Advice（通知）来增强被代理类的功能。Spring实现这一AOP功能的原理就使用代理模式（1、JDK动态代理。2、CGLib字节码生成技术代理。）对类进行方法级别的切面增强，即，生成被代理类的代理类， 并在代理类的方法前，设置拦截器，通过执行拦截器重的内容增强了代理方法的功能，实现的面向切面编程。

Advice（通知）的类型有：BeforeAdvice、AfterReturningAdvice、ThreowSadvice的。

在每个类型Advice（通知）都有对应的拦截器，MethodBeforeAdviceInterceptor、AfterReturningAdviceInterceptor、ThrowsAdviceInterceptor。

Spring需要将每个Advice（通知）都封装成对应的拦截器类型，返回给容器，所以需要使用适配器模式对Advice进行转换。下面看看具体的代码。

*MethodBeforeAdvice类：Adaptee*
``` java
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, Object target) throws Throwable;
}
```
Adapter类接口：Target
``` java
public interface AdvisorAdapter {

  boolean supportsAdvice(Advice advice);

  MethodInterceptor getInterceptor(Advisor advisor);
}
```
MethodBeforeAdviceAdapter类，Adapter
``` java
class MethodBeforeAdviceAdapter implements AdvisorAdapter, Serializable {

  public boolean supportsAdvice(Advice advice) {
    return (advice instanceof MethodBeforeAdvice);
  }

  public MethodInterceptor getInterceptor(Advisor advisor) {
    MethodBeforeAdvice advice = (MethodBeforeAdvice) advisor.getAdvice();
    return new MethodBeforeAdviceInterceptor(advice);
  }
}
```
DefaultAdvisorAdapterRegistry类，Client
``` java
public class DefaultAdvisorAdapterRegistry implements AdvisorAdapterRegistry, Serializable {

  private final List<AdvisorAdapter> adapters = new ArrayList<AdvisorAdapter>(3);


  /**
  * Create a new DefaultAdvisorAdapterRegistry, registering well-known adapters.
  */
  public DefaultAdvisorAdapterRegistry() {//这里注册了适配器
    registerAdvisorAdapter(new MethodBeforeAdviceAdapter());
    registerAdvisorAdapter(new AfterReturningAdviceAdapter());
    registerAdvisorAdapter(new ThrowsAdviceAdapter());
  }


  public Advisor wrap(Object adviceObject) throws UnknownAdviceTypeException {
    if (adviceObject instanceof Advisor) {
      return (Advisor) adviceObject;
    }
    if (!(adviceObject instanceof Advice)) {
      throw new UnknownAdviceTypeException(adviceObject);
    }
    Advice advice = (Advice) adviceObject;
    if (advice instanceof MethodInterceptor) {
      // So well-known it doesn't even need an adapter.
      return new DefaultPointcutAdvisor(advice);
    }
    for (AdvisorAdapter adapter : this.adapters) {
      // Check that it is supported.
      if (adapter.supportsAdvice(advice)) {//这里调用了适配器的方法
        return new DefaultPointcutAdvisor(advice);
      }
    }
    throw new UnknownAdviceTypeException(advice);
  }

  public MethodInterceptor[] getInterceptors(Advisor advisor) throws UnknownAdviceTypeException {
    List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>(3);
    Advice advice = advisor.getAdvice();
    if (advice instanceof MethodInterceptor) {
      interceptors.add((MethodInterceptor) advice);
    }
    for (AdvisorAdapter adapter : this.adapters) {
      if (adapter.supportsAdvice(advice)) {//这里调用了适配器的方法
        interceptors.add(adapter.getInterceptor(advisor));
      }
    }
    if (interceptors.isEmpty()) {
      throw new UnknownAdviceTypeException(advisor.getAdvice());
    }
    return interceptors.toArray(new MethodInterceptor[interceptors.size()]);
  }

  public void registerAdvisorAdapter(AdvisorAdapter adapter) {
    this.adapters.add(adapter);
  }

}
```


=== 优缺点
****
优点: 灵活性高、低耦合  采用 “对象组合”的方式，是动态组合方式 +
缺点: 使用复杂  需要引入对象实例
****

== 构建器模式
建造者模式（英：Builder Pattern）是一种创建型设计模式，又名：生成器模式。
GOF 给建造者模式的定义为：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
这句话说的比较抽象，其实解释一下就是：将建造复杂对象的过程和组成对象的部件解耦。

==== 建造者模式包含如下角色：
****
Builder：抽象建造者(Builder) +
ConcreteBuilder：具体建造者(CommonBuilder、SuperManBuilder) +
Director：指挥者(Director) +
Product：产品角色(Product) +
****

image::http://www.hollischuang.com/wp-content/uploads/2016/05/Builder.jpg[]
==== 用途
假设现在我们是一家网游设计公司，现在我们要”抄袭”梦幻西游这款游戏，你是该公司的游戏角色设计人员。你怎么设计出该游戏中的各种角色呢？ 在梦幻西游来中包括人、仙、魔等种族的角色，而每种不同的种族的角色中又包含龙太子、逍遥生等具体的角色。

作为一个出色的开发人员，我们设计的角色生成系统应该包含以下功能和特性:

****
为了保证游戏平衡，所有角色的基本属性应该一致 +
因为角色的创建过程可能很复杂，所以角色的生成细节不应该对外暴露 +
随时可以新增角色 +
对某个具体角色的修改应该不影响其他角色 +
****

其实，对于角色的设计，我们可以使用抽象工厂模式，将同一种族的角色看成是一个产品族。
但是，这样做可能存在一个问题，那就是我们可能要在每个角色的创建过程中都要从头到尾的构建一遍该角色。
比如一个角色包含头部、身体。其中头部又包括脸部、和其他部位。其中脸部又包含眉毛、嘴巴、鼻子等部位。
整个角色的创建过程是极其复杂的。很容易遗漏其中的某个步骤。

那么，我们可以将这些具体部位的创建工作和对象的创建进行解耦。这就是建造者模式。

===== 产品角色(Product)
``` java
public class Product {
  /**
  * 名字
  */
  private String name;
  /**
  * 颜值
  */
  private String face;
  /**
  * 力量
  */
  private Integer power;

  /**
  * 女朋友
  */
  private String girlFriend;
}
```
===== 抽象建造者(Builder)
``` java
public interface Builder {
  void name();

  void face();

  void power();

  void girlFriend();

  Product getProduct();
}
```

===== 具体建造者(ConcreteBuilder)
``` java
public class CommonBuilder implements Builder {

  private Product product = new Product();

  @Override
  public void name() {
    product.setName("宋洋");
  }

  @Override
  public void face() {
    product.setFace("丑陋的");
  }

  @Override
  public void power() {
    product.setPower(2);
  }

  @Override
  public void girlFriend() {
  }

  @Override
  public Product getProduct() {
    return product;
  }
}

public class SuperManBuilder implements Builder {

  private Product product = new Product();

  @Override
  public void name() {
    product.setName("宋青龙");
  }

  @Override
  public void face() {
    product.setFace("帅气的");
  }

  @Override
  public void power() {
    product.setPower(9999);
  }

  @Override
  public void girlFriend() {
    product.setGirlFriend("有三个");
  }

  @Override
  public Product getProduct() {
    return product;
  }
}
```

===== 具体建造者(Director)
``` java

public class Director {
  private Builder builder;

  Director(Builder builder) {
    this.builder = builder;
  }

  /**
  * 产品构造方法，在该方法内，调用人类建造方法。
  */
  public void construct() {
    builder.name();
    builder.face();
    builder.power();
    builder.girlFriend();
  }
}
```
===== 测试类:
``` java
public static void main(String[] args) {
  Builder common = new CommonBuilder();
  Director director = new Director(common);
  director.construct();
  Product product = common.getProduct();
  System.out.println(product);
}
```

=== spring中的例子
``` java
private static RedisCacheConfiguration defaultRedisCacheConfiguration() {
  return RedisCacheConfiguration.defaultCacheConfig()
      .serializeKeysWith(STRING_PAIR)
      .serializeValuesWith(JACKSON_PAIR)
      .computePrefixWith(RedisCacheConstant::getPrefixKeysWith);
}
```

=== 优缺点
****
优点： 1、建造者独立，易扩展。 2、便于控制细节风险。 +
缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
****

== 观察者模式
观察者模式（又被称为发布-订阅（Publish/Subscribe）模式，属于行为型模式的一种，它定义了一种一对多的依赖关系，
让多个观察者对象同时监听某一个主题对象。这个主题对象在状态变化时，会通知所有的观察者对象，使他们能够自动更新自己。

=== 观察者模式包含如下角色
****
Subject：抽象主题（抽象被观察者），抽象主题角色把所有观察者对象保存在一个集合里，每个主题都可以有任意数量的观察者，抽象主题提供一个接口，可以增加和删除观察者对象。 +
ConcreteSubject：具体主题（具体被观察者），该角色将有关状态存入具体观察者对象，在具体主题的内部状态发生改变时，给所有注册过的观察者发送通知。 +
Observer：抽象观察者，是观察者者的抽象类，它定义了一个更新接口，使得在得到主题更改通知时更新自己。 +
ConcrereObserver：具体观察者，实现抽象观察者定义的更新接口，以便在得到主题更改通知时更新自身的状态。 +
****
image::https://img-blog.csdn.net/20161111191040882[]

=== 实例

观察者模式这种发布-订阅的形式我们可以拿微信公众号来举例，假设微信用户就是观察者，微信公众号是被观察者，有多个的微信用户关注了程序猿这个公众号，当这个公众号更新时就会通知这些订阅的微信用户。好了我们来看看用代码如何实现：

===== 抽象观察者（Observer）
``` java
public interface Observer {

  void update(String message);
}
```

===== 具体观察者（ConcrereObserver）
``` java
public class WeixinUser implements Observer {
  private String name;

  public WeixinUser(String name) {
    this.name = name;
  }

  @Override
  public void update(String message) {
    System.out.println(String.format("%s，%s", name, message));
  }
}
```

===== 抽象被观察者（Subject）
``` java
public interface Subject {
  /**
   * 增加订阅者
   */
  void attach(Observer observer);

  /**
   * 删除订阅者
   */
  void detach(Observer observer);

  /**
   * 通知订阅者更新消息
   */
  void notify(String message);
}
```

===== 具体被观察者（ConcreteSubject）
``` java
/**
 * 具体被观察者 公众号
 * @author chenjunqiang 2019/1/22
 **/
public class GongzhonghaoSubject implements Subject {
  /**
   * 储存订阅公众号的微信用户
   */
  private List<Observer> userList = new ArrayList<>();

  @Override
  public void attach(Observer observer) {
      userList.add(observer);
  }

  @Override
  public void detach(Observer observer) {
    userList.remove(observer);
  }

  @Override
  public void notify(String message) {
    for (Observer observer : userList) {
      observer.update(message);
    }
  }
}

/**
 * 具体被观察者 小程序
 * @author chenjunqiang 2019/1/22
 **/
public class XiaochengxuSubject implements Subject {
  /**
   * 储存订阅小程序的微信用户
   */
  private List<Observer> userList = new ArrayList<>();

  @Override
  public void attach(Observer observer) {
    userList.add(observer);
  }

  @Override
  public void detach(Observer observer) {
    userList.remove(observer);
  }

  @Override
  public void notify(String message) {
    for (Observer observer : userList) {
      observer.update(message);
    }
  }
}
```

===== 客户端调用
``` java
public class Client {
  private static GongzhonghaoSubject gongzhonghao = new GongzhonghaoSubject();
  private static XiaochengxuSubject xiaochengxu = new XiaochengxuSubject();

  static {
    //创建微信用户
    WeixinUser user1 = new WeixinUser("宋洋(๑ŐдŐ)b");
    WeixinUser user2 = new WeixinUser("宋青龙");
    WeixinUser user3 = new WeixinUser("李泽坤 皮卡皮卡001号");
    //订阅公众号
    gongzhonghao.attach(user1);
    gongzhonghao.attach(user2);
    gongzhonghao.attach(user3);
    //订阅小程序
    xiaochengxu.attach(user1);
    xiaochengxu.attach(user3);
  }

  public static void main(String[] args) {
    //公众号更新发出消息给订阅的微信用户
    gongzhonghao.notify("小小包麻麻文章更新了");
    xiaochengxu.notify("育儿锦囊有新视频啦");
  }
}
```

=== spring中的例子
在Spring中，观察者设计模式用于将与应用程序上下文相关的事件传输到org.springframework.context.ApplicationListener的实现。
要了解它们的实现方法，我们来看一下AbstractApplicationContext类
``` java
public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext {

  /** Statically specified listeners */
  private final Set<ApplicationListener<?>> applicationListeners = new LinkedHashSet<>();

	@Override
	public void addApplicationListener(ApplicationListener<?> listener) {
		Assert.notNull(listener, "ApplicationListener must not be null");
		if (this.applicationEventMulticaster != null) {
			this.applicationEventMulticaster.addApplicationListener(listener);
		}
		else {
			this.applicationListeners.add(listener);
		}
	}

	/**
	 * Return the list of statically specified ApplicationListeners.
	 */
	public Collection<ApplicationListener<?>> getApplicationListeners() {
		return this.applicationListeners;
	}

	@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
		// Prepare this context for refreshing.
		prepareRefresh();

		// Tell the subclass to refresh the internal bean factory.
		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
    ...
		try {

			// Check for listener beans and register them.
			registerListeners();
      ...
	}

  /**
   * Add beans that implement ApplicationListener as listeners.
   * Doesn't affect other listeners, which can be added without being beans.
   */
  protected void registerListeners() {
    // Register statically specified listeners first.
    for (ApplicationListener<?> listener : getApplicationListeners()) {
      getApplicationEventMulticaster().addApplicationListener(listener);
    }

    // Do not initialize FactoryBeans here: We need to leave all regular beans
    // uninitialized to let post-processors apply to them!
    String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
    for (String listenerBeanName : listenerBeanNames) {
      getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
    }

    // Publish early application events now that we finally have a multicaster...
    Set<ApplicationEvent> earlyEventsToProcess = this.earlyApplicationEvents;
    this.earlyApplicationEvents = null;
    if (earlyEventsToProcess != null) {
      for (ApplicationEvent earlyEvent : earlyEventsToProcess) {
        getApplicationEventMulticaster().multicastEvent(earlyEvent);
      }
    }
  }
}
```

在提供的代码中，监听器在内部添加到应用程序上下文类中，并且在registerListeners()方法之后，
它们被注册到由接口org.springframework.context.event.ApplicationEventMulticaster表示的适当的事件多路广播器
(因为有很多listeners)。EventMulticaster负责管理不同的listener和向他们发布事件。

``` java
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
  private Executor taskExecutor;
  private ErrorHandler errorHandler;

  public SimpleApplicationEventMulticaster() {
  }

  public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
    this.setBeanFactory(beanFactory);
  }

  public void setTaskExecutor(Executor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  protected Executor getTaskExecutor() {
    return this.taskExecutor;
  }

  public void setErrorHandler(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  protected ErrorHandler getErrorHandler() {
    return this.errorHandler;
  }

  public void multicastEvent(ApplicationEvent event) {
    this.multicastEvent(event, this.resolveDefaultEventType(event));
  }
  //发布事件:通过池执行任务的方式来做并发处理，这样就把之前的对象池模式给利用上了
  public void multicastEvent(final ApplicationEvent event, ResolvableType eventType) {
    ResolvableType type = eventType != null?eventType:this.resolveDefaultEventType(event);
    Iterator var4 = this.getApplicationListeners(event, type).iterator();

    while(var4.hasNext()) {
      final ApplicationListener<?> listener = (ApplicationListener)var4.next();
      Executor executor = this.getTaskExecutor();
      if(executor != null) {
        executor.execute(new Runnable() {
          public void run() {
            SimpleApplicationEventMulticaster.this.invokeListener(listener, event);
          }
        });
      } else {
        this.invokeListener(listener, event);
      }
    }

  }
...
}
```

=== 优缺点
****
优点： 1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。

缺点： 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 +
2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 +
3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
****
