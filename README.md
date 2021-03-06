# thymeleafexamples-stsm

## 配置

## Creating a Form

1. comment 对象：Command对象是Spring MVC为表单支持bean提供的名称，这是对表单的字段进行建模并提供getter和setter方法的对象，框架将使用这些方法来建立和获取用户在浏览器中输入的值。

   我们把comment对象放在form表单中的th:object中

   ```
   <form action="#" th:action="@{/seedstartermng}" th:object="${seedStarter}" method="post"> 
   ...
   </form>
   ```

   :yellow_heart:在th:object中，必须使用`${}`，并且只能放model attribute的名字，不能放model attribute的属性

2. inputs:我们使用th:field存储object对象中的属性，并且进行封装

   ```html
   <input type="text" th:field="*{datePlanted}" />
   ```

   属性必须卸载`*{}`中

3. Checkbox & radio:一般在定义一个checkbox或radio的时候，我们都会定义一个label标签

   ```html
   <div>
   	<label th:for="${#ids.next('covered')}" th:text="#{seedstarter.covered}">Covered</label> 	<input type="checkbox" th:field="*{covered}" />
   </div>
   
   <ul>
   	<li th:each="feat : ${allFeatures}">
   		<input type="checkbox" th:field="*{features}" th:value="${feat}" /> 
   		<label th:for="${#ids.prev('features')}"
   	</li> 
   </ul>
   
   <ul> 
       <li>
   	<input id="features1" name="features" type="checkbox" value="SEEDSTARTER_SPECIFIC_SUBSTRATE" /> 
           <input name="_features" type="hidden" value="on" />
   		<label for="features1">Seed starter-specific substrate</label>
   	</li> 
       <li>
   		<input id="features2" name="features" type="checkbox" value="FERTILIZER" /> 
           <input name="_features" type="hidden" value="on" />
   		<label for="features2">Fertilizer used</label>
   	</li>
       <li>
   		<input id="features3" name="features" type="checkbox" value="PH_CORRECTOR" />
           <input name="_features" type="hidden" value="on" />
   		<label for="features3">PH Corrector used</label>
   	</li>
   </ul>
   
   
   
   <ul>
   <li th:each="ty : ${allTypes}">
   <input type="radio" th:field="*{type}" th:value="${ty}" />
   <label th:for="${#ids.prev('type')}" th:text="#{${'seedstarter.type.' + ty}}">Wireframe</label> </li>
   </ul>
   ```

   在lable标签中使用th:for属性来定义id，如果我们有一系列的checkbox，通常这些个的id都是有相同的前缀，所以在定义的时候，可以通过#ids属性来产生有同样前缀的id值

   \#ids是thymeleaf提供的一个内置的属性，utility methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).通过迭代的方法自动生成有同样前缀的属性值

   ```html
   /*
    * Normally used in th:id attributes, for appending a counter to the id attribute value
    * so that it remains unique even when involved in an iteration process.
    */
   ${#ids.seq('someId')}
   /*
    * Normally used in th:for attributes in <label> tags, so that these labels can refer to Ids
    * generated by means if the #ids.seq(...) function.
    *
    * Depending on whether the <label> goes before or after the element with the #ids.seq(...)
    * function, the "next" (label goes before "seq") or the "prev" function (label goes after
    * "seq") function should be called.
    */
   ${#ids.next('someId')}
   ${#ids.prev('someId')}
   ```

   checkbox能使用多个值，radio只能使用一个值

4. Dropdown/List selectors：有两部分，一个是<select>,一个是<option>,在select中引入th:filed,option引入th:value,通过这个属性可以确定我们选择了那一个option

   ```html
   <select th:field="*{type}">
   	<option th:each="type : ${allTypes}"
   			th:value="${type}"
   			th:text="#{${'seedstarter.type.' + type}}">Wireframe</option> 
   </select>
   ```

5. Dynamic fields:⚠️没有看明白

   **:yellow_heart:如果我们想要使用数组下标进行数组内成员的选择的时候，`*{rows[rowStat.index].variety}`如果单纯的使用这个是不会得到值的，因为不会验证数组下标的值，如果我们想要得到下标的值，我们需要使用**

   **`*{rows[__${rowStat.index}__].variety}`写法**

## Validation and Error Messages

我们的表单需要展示有效的信息为了通知用户产生的错误

提供了一个`#object`对象，以及`th:errors` and the `th:errorclass` attributes 

## The Conversion Service

转换层，能把一个对象转换成另一个对象

### 配置

```java
@Override
public void addFormatters(final FormatterRegistry registry) {
    super.addFormatters(registry); 
    registry.addFormatter(varietyFormatter());
    registry.addFormatter(dateFormatter());
}
@Bean
public VarietyFormatter varietyFormatter() {
	return new VarietyFormatter(); 
}
@Bean
public DateFormatter dateFormatter() {
	return new DateFormatter(); 
}
```

我们需要在我们的配置类中继承WebMvcConfigurerAdapter进行配置

如果我们想要自己定一个，需要我们实现ConversionService

```java
@Component
public final class UserConversionService implements Converter<String, User> {

    private UserService userService = new UserService();

    @Override
    public User convert(String s) {
        return userService.find(s);
    }
}
```

之后在addFormatter中添加registry.addConverter(new UserConversionService());

⚠️：测试没有通过

\#conversions能够用来进行转化

```html
<p th:text="${'Val: ' + #conversions.convert(val,'String')}">...</p>
```

\#conversions.convert(Object,Class) : converts the object to the specified class.

\#conversions.convert(Object,String) : same as above, but specifying the target class as a String (note the

java.lang. package can be ommitted).

## Rendering Template Fragments

我们使用th:fragment写了一个片段，如果我们想要使用直接调用，可以使用下面的这个写法

```java
@Bean(name="content-part")
@Scope("prototype")
public ThymeleafView someViewBean() {
    // templateName = 'index'
    ThymeleafView view = new ThymeleafView("index");
    view.setMarkupSelector("content");
    return view;
}
```

和之前的一样我们定义了templateName::selector的形式引用了代码段

