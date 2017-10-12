# 《JAVA与模式》之访问者模式

在阎宏博士的《JAVA与模式》一书中开头是这样描述访问者(Visitor)模式的：

>  访问者模式是对象的行为模式。访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。

### 分派的概念

变量被声明时的类型叫做变量的静态类型(Static Type)，有些人又把静态类型叫做明显类型(Apparent Type)；而变量所引用的对象的真实类型又叫做变量的实际类型(Actual Type)。比如：

```java
List list = null;       //变量list静态类型(也叫明显类型)是List
list = new ArrayList(); //实际类型是ArrayList
```

根据对象的类型而对方法进行的选择，就是分派(Dispatch)，分派(Dispatch)又分为两种，即静态分派和动态分派。

* **静态分派(Static Dispatch)** 发生在编译时期，分派根据静态类型信息发生。静态分派对于我们来说并不陌生，方法重载就是静态分派。

* **动态分派(Dynamic Dispatch)** 发生在运行时期，动态分派动态地置换掉某个方法。

### 静态分派

Java通过方法重载支持静态分派。重载方法的分派是根据静态类型进行的，这个分派过程在编译时期就完成了。

```java
package com.herolei.design.visitor;

import org.junit.Test;

public class StaticDispatchTest {

    /**
     * 静态分派：重载方法的分派是根据静态类型进行的，这个分派过程在编译时期就完成了
     * 两次对ride()方法的调用传入静态类型都是Horse类型，调用的都是ride(Horse h)
     */
    @Test
    public void test1() {
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        Person person = new Person();

        person.ride(wh); //骑马
        person.ride(bh); //骑马
    }

    /**
     * 两次对ride()方法的调用传入静态类型为WhiteHorse/BlackHorse
     */
    @Test
    public void test2() {
        WhiteHorse wh = new WhiteHorse();
        BlackHorse bh = new BlackHorse();
        Person person = new Person();

        person.ride(wh); //骑白马
        person.ride(bh); //骑黑马
    }
}

class Person {
    public void ride(Horse h){
        System.out.println("骑马");
    }

    public void ride(WhiteHorse wh){
        System.out.println("骑白马");
    }

    public void ride(BlackHorse bh){
        System.out.println("骑黑马");
    }
}

interface Horse {}

class WhiteHorse implements Horse {}

class BlackHorse implements Horse {}
```

显然，Person类的ride()方法是由三个方法重载而成的。这三个方法分别接受马(Horse)、白马(WhiteHorse)、黑马(BlackHorse)等类型的参数。

那么在运行时，程序会打印出什么结果呢？结果是程序会打印出相同的两行“骑马”。

为什么呢？两次对ride()方法的调用传入的是不同的参数，也就是wh和bh。它们虽然具有不同的真实类型，但是它们的静态类型都是一样的，均是Horse类型。

### 动态分派

```java
package com.herolei.design.visitor;

import org.junit.Test;

public class DynamicDispatchTest {

    @Test
    public void test1() {
        Horse h = new BlackHorse();
        h.eat(); //黑马吃草
    }
}

abstract class Horse {
    public void eat() {
        System.out.println("马吃草");
    }
}

class WhiteHorse extends Horse {
    public void eat() {
        System.out.println("白马吃草");
    }
}

class BlackHorse extends Horse {
    public void eat() {
        System.out.println("黑马吃草");
    }
}
```

变量h的静态类型是Horse，而真实类型是BlackHorse。如果上面最后一行的eat()方法调用的是BlackHorse类的eat()方法，那么上面打印的就是“黑马吃草”；相反，如果上面的eat()方法调用的是Horse类的eat()方法，那么打印的就是“马吃草”。

所以，问题的核心就是Java编译器在编译时期并不总是知道哪些代码会被执行，因为编译器仅仅知道对象的静态类型，而不知道对象的真实类型；而方法的调用则是根据对象的真实类型，而不是静态类型。这样一来，上面最后一行的eat()方法调用的是BlackHorse类的eat()方法，打印的是“黑马吃草”。

### 分派的类型

一个方法所属的对象叫做方法的接收者，方法的接收者与方法的参数统称做方法的宗量。比如下面例子中的Test类:

```java
public class Test {
    
    /**
     * print()方法的宗量一：接收者也就是Test对象，因为它属于Test对象
     * print()方法的宗量二：参数是str,它的类型是String
     */
    public void print(String str){
        System.out.println(str);
    }
}
```

根据分派可以基于多少种宗量，可以将面向对象的语言划分为单分派语言(Uni-Dispatch)和多分派语言(Multi-Dispatch)。单分派语言根据一个宗量的类型进行对方法的选择，多分派语言根据多于一个的宗量的类型对方法进行选择。

C++和Java均是单分派语言，多分派语言的例子包括CLOS和Cecil。按照这样的区分，**Java就是动态的单分派语言**，因为这种语言的动态分派仅仅会考虑到方法的接收者的类型，同时又是**静态的多分派语言**，因为这种语言对重载方法的分派会考虑到方法的接收者的类型以及方法的所有参数的类型。

在一个支持动态单分派的语言里面，有两个条件决定了一个请求会调用哪一个操作：一是请求的名字，二是接收者的真实类型。单分派限制了方法的选择过程，使得只有一个宗量可以被考虑到，这个宗量通常就是方法的接收者。在Java语言里面，如果一个操作是作用于某个类型不明的对象上面，那么对这个对象的真实类型测试仅会发生一次，这就是动态的单分派的特征。

#### 双重分派

一个方法根据两个宗量的类型来决定执行不同的代码，这就是“双重分派”。Java语言不支持动态的多分派，也就意味着Java不支持动态的双分派。但是通过使用设计模式，也可以在Java语言里实现动态的双重分派。

## 访问者模式的结构

访问者模式的目的是要把处理从数据结构中分离出来，如果系统有比较稳定的数据结构，又有易于变化的算法的话，使用访问者模式是个不错的选择，因为访问者模式使的算法操作的增加变得容易。相反，如果系统的数据结构不稳定，易于变化，则此系统就不适合使用访问者模式了。

类图：

![访问者模式的类图](http://pic002.cnblogs.com/images/2012/73404/2012121117525725.gif)

访问者模式涉及到的角色如下：

* 抽象访问者(Visitor)角色：声明了一个或者多个方法操作，形成所有的具体访问者角色必须实现的接口。

* 具体访问者(ConcreteVisitor)角色：实现抽象访问者所声明的接口，也就是抽象访问者所声明的各个访问操作。

* 抽象节点(Node)角色：声明一个接受操作，接受一个访问者对象作为一个参数。

* 具体节点(ConcreteNode)角色：实现了抽象节点所规定的接受操作。

* 结构对象(ObjectStructure)角色：有如下的责任，可以遍历结构中的所有元素；如果需要，提供一个高层次的接口让访问者对象可以访问每一个元素；如果需要，可以设计成一个复合对象或者一个聚集，如List或Set。

**源代码：**

抽象节点元素角色是接受操作的数据结构，接受一个访问者对象作为一个参数。

```java
package com.herolei.design.visitor.demo4;

public interface Service {
    public void accept(Visitor visitor);
}

class Saving implements Service {
    public void accept(Visitor visitor) {
        visitor.process(this);
    }
}

class Draw implements Service {
    public void accept(Visitor visitor) {
        visitor.process(this);
    }
}

class Fund implements Service {
    public void accept(Visitor visitor) {
        visitor.process(this);
    }
}
```

可以看到，抽象访问者角色为每一个具体节点都准备了一个访问操作。一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。

```java
package com.herolei.design.visitor.demo4;

public interface Visitor {
    public void process(Service service);

    public void process(Saving service);

    public void process(Draw service);

    public void process(Fund service);
}


class VisitorA implements Visitor {
    public void process(Service service) {
        // 基本业务
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：基本业务");
    }

    public void process(Saving service) {
        // 存款
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：存款");
    }

    public void process(Draw service) {
        // 提款
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：提款");
    }

    public void process(Fund service) {
        // 基金
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：基金");
    }
}

class VisitorB implements Visitor {
    public void process(Service service) {
        // 基本业务
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：基本业务");
    }

    public void process(Saving service) {
        // 存款
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：存款");
    }

    public void process(Draw service) {
        // 提款
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：提款");
    }

    public void process(Fund service) {
        // 基金
        System.out.println(getClass().getSimpleName() + " + " + service.getClass().getSimpleName() + "：基金");
    }
}
```

结构对象角色类，这个结构对象角色持有一个聚集，并向外界提供add()方法作为对聚集的管理操作。通过调用这个方法，可以动态地增加一个新的节点。

```java
package com.herolei.design.visitor.demo4;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {

    private List<Service> services = new ArrayList<Service>();

    //添加业务
    public void add(Service Service){
        services.add(Service);
    }

    //删除业务
    public void remove(Service Service){
        services.remove(Service);
    }

    //执行方法操作
    public void accept(Visitor visitor) {
        for(Service service : services) {
            service.accept(visitor);
        }
    }
}
```

客户端

```java
package com.herolei.design.visitor.demo4;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class client {

    @Test
    //常规业务逻辑 随着业务量增大 代码维护量会非常的大 需要不断的去判断
    public void test1(){
        Service service1 = new Saving();
        Service service2 = new Fund();
        Service service3 = new Draw();
        List<Service> ls = new ArrayList<Service>();
        ls.add(service1);
        ls.add(service2);
        ls.add(service3);
        for (Service service : ls) {
            if (service instanceof Saving) {
                System.out.println("存款");
            } else if (service instanceof Fund) {
                System.out.println("基金");
            } else if (service instanceof Draw) {
                System.out.println("提款");
            }
        }
    }

    @Test
    //采用访问者模式解决
    public void test2(){
        Service saving = new Saving();
        Service fund = new Fund();
        Service draw = new Draw();
        Visitor visitor = new VisitorA();

        saving.accept(visitor); //VisitorA + Saving：存款
        fund.accept(visitor);   //VisitorA + Fund：基金
        draw.accept(visitor);   //VisitorA + Draw：提款
        //上述中accept中实际上有观察者的影子 实际上 访问者我们也可以理解成一个对业务熟悉的观察者
        //他不断观察者是否有新的业务需求 有的话 进行相应的业务处理
    }

    @Test
    //访问者模式添加观察者
    public void test3(){
        ObjectStructure os = new ObjectStructure();
        os.add(new Saving());
        os.add(new Fund());
        os.add(new Draw());

        //执行业务
        os.accept(new VisitorA());
    }
}
```

**访问者模式的优点：**

* 好的扩展性

　　能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。

* 好的复用性

　　可以通过访问者来定义整个对象结构通用的功能，从而提高复用程度。

* 分离无关行为

　　可以通过访问者来分离无关的行为，把相关的行为封装在一起，构成一个访问者，这样每一个访问者的功能都比较单一。

**访问者模式的缺点：**

* 对象结构变化很困难

　　不适用于对象结构中的类经常变化的情况，因为对象结构发生了改变，访问者的接口和访问者的实现都要发生相应的改变，代价太高。

* 破坏封装

　　访问者模式通常需要对象结构开放内部数据给访问者和ObjectStructrue，这破坏了对象的封装性。

#### 访问者模式实现了双重分派 

```java
@Test
//双重分派：一个方法根据两个宗量(接收者和参数)的类型来决定执行不同的代码，这就是“双重分派”
public void test4(){
    Service saving = new Saving();
    Service fund = new Fund();
    Service draw = new Draw();
    Visitor visitorA = new VisitorA();

    saving.accept(visitorA);//VisitorA + Saving：存款
    fund.accept(visitorA);  //VisitorA + Fund：基金
    draw.accept(visitorA);  //VisitorA + Draw：提款

    Visitor visitorB = new VisitorB();
    saving.accept(visitorB);//VisitorB + Saving：存款
    fund.accept(visitorB);  //VisitorB + Fund：基金
    draw.accept(visitorB);  //VisitorB + Draw：提款
}
```
