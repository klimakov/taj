## Демонстрация работы транзакций с использованием компилятора AspectJ

Этот проект служит для демонстрации подхода с использованием компилятора AspectJ для
борьбы с известной проблемой Spring.

Известно, что такие аннотации как @Transactional или @Cachable будут проигнорированы фреймворком,
если методы отмеченные ими вызываются из другого метода того же класса.

Наиболее популярным решением является избегание таких ситуаций. Но во первых всегда может найтись джун, который этого не знает. 
Во вторых иногда бывает полезно использовать из одного сервисного метода другой, а выделение его в отдельный класс, либо вызов через заинъекченый прокси будет вредить простоте и очевидности кода.

В этом примере показывается подход с использованием компилятора AspectJ, который делает более честное обертывание указанных методов аспектным поведением.

Запускаем приложение

```./mwnw clean install spring-boot:run```

Проверяем

```curl --location 'http://localhost:8080/fake-person-and-return-all' --header 'Accept: */*'```

В результате должны вернуться только залитые в базу flyway миграцией люди: John и Lisa

Если же в pom.xml удалить плагин aspectj-maven-plugin, то среди результатов вернется Fake Person,
вставка которого должна была быть отменена откатившейся транзакцией, но аннотация @Transactional над методом saveFakePersonAndRollback
в классе PersonService не будет обработана как ожидается из-за ограничений cglib proxy, который обычно используется по дефолту.

P.S.: В репозитории есть ветка spring-data-jdbc демонстирующая то же самое на Spring Data Jdbc вместо JPA.

