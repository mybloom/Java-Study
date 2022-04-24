# 생성자

> 목차
- 기본 생성자
- this()
- this
- 트러블슈팅


## 기본 생성자
- 생성자가 명시적으로 없을 경우, 컴파일러가 자동적으로 기본 생성자를 추가해준다.
- 단, 클래스 내에 생성자가 하나도 없을 때만 자동으로 기본 생성자를 추가한다.

## this()
- 생성자 사이에서 서로 호출
- 생성자의 이름으로 클래스이름 대신 this를 사용
- 한 생성자에서 다른 생성자를 호출할 때 반드시 첫 줄에서만 호출 가능

- 아래 코드처럼 생성자들은 일반적으로 서로 관계가 깊은 경우가 많아서 서로 호출하도록 연결해주면 더 좋은 코드를 얻을 수 있다. 
```java
class Car {

    private String color;
    private String gearType;
    private int numberOfDoor;

    Car(String color) {
        this(color, "auto", 4);
    }

    Car(String color, String gearType, int numberOfDoor) {
        this.color = color;
        this.gearType = gearType;
        this.numberOfDoor = numberOfDoor;
    }

}
```

## this
- 참조변수 
- 인스턴스 자신을 가리킨다.
- 생성자와 인스턴스 메서드에는 자신을 가리키는 참조변수 `this`가 `지역변수`로 숨겨져 있다.


## 트러블슈팅

> 문제 : Spring Data JDBC에서 엔티티를 instance화 하지 못한 에러 

- 에러 메세지  
```
org.springframework.data.mapping.model.MappingInstantiationException: 
Failed to instantiate 클래스명 using constructor NO_CONSTRUCTOR with arguments 
```

- 문제 원인
  - Spring Data JDBC에서 엔티티를 인스턴스화 하기 위해서는 생성자를 사용한다.
    - 단일 생성자가 있는 경우 사용
    - 생성자 여러 개 있는 경우 @PersistenceConstructor 애노테이션이 있는 생성자 사용
    - 기본 생성자가 있는 경우, 다른 생성자는 무시되고 사용
  - Spring Data JDBC가 오브젝트를 생성하는 방법은 위와 같은데 나는 argument가 있는 생성자만 2개를 만들어 놓았었다. 
  그랬기 때문에 Data JDBC는 어떤 생성자를 사용해야 하는지 몰랐을 것이다.

- 해결 방법
  - 기본생성자(@NoArgsConstructor)를 롬복을 통해 생성했다. 
  - @AllArgsConstructor를 사용해도 되는데 기본생성자를 사용한 이유는 `JavaBean Convention`을 지키기 위해서 사용했다.
  - [Todo] `JavaBean Convention` 관련 내용은 정리하기!
  
- 해결 과정
  - 먼저, Spring Data JDBC가 오브젝트를 생성하는 방법에 대해 알아보았다.
  - 공식 문서에서 `Object creation` 파트를 찾아보았다.
    - 찾은 방법 : 구글에 검색해도 별 내용이 없어서 공식문서 활용
    - 적용 전 공식문서를 쭉 읽어서 해당 내용이 있었다는 것을 기억했고,       
    `Object creation` 이라는 키워드는 생각하지 못했지만 `property population`이라는 키워드는 
    immutable한 객체를 변경하는 방법이 나와 있었고 단어 자체가 한국어랑 매칭이 안되 특이해서 기억하고 있었다.    
    해당 카테고리를 검색으로 찾은 후 바로 위에 Object creation이 있어서 금방 찾을 수 있었다.     
  - 공식문서 해당링크 : https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#mapping.object-creation

- 깨닫게 된 점
  - 처음 공식문서를 읽을 때 해당 기술에서 필요한 개념은 먼저 익히고 읽어야 이해 된다는 것을 알게 되어서 공식문서 읽는 것이 재밌었는데 얼만큼 상세히 읽어야 하는지는 감이 오지 않았다.
  - 이번 트러블슈팅을 통해서 `얼마나 상세히` 부분에서 감을 잡은 것 같아 재미있었다.
  - Spring Data JDBC가 하는 일이 무엇인지를 알고, 큰 그림으로 파악하고 있는 것이 중요하다.
  - 그것을 안다면 공식문서에서 그 세부 내용을 다룰 것이라는 것을 짐작할 수 있고
  - 그것들을 목차를 통해서 확인할 수 있을 것이다.
  - 그 목차를 먼저 파악하고, 목차를 클릭해서 보면서 해당 내용을 대강 파악하고
  - 실제 구현 시, 공식문서의 해당 파트를 참고해 구현하면 된다는 것을 알게 되었다.
