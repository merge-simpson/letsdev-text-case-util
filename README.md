# Download

Gradle(Kotlin):  
`build.gradle.kts`에서 다음 두 라인을 추가합니다.

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") } // added
}

dependencies {
    implementation("com.github.merge-simpson:letsdev-text-case-util:0.1.0") // added
}
```

# Features (Prerelease)

대문자로 된 `UPPER_SNAKE_CASE`를 입력하면, 대문자로 시작하는 문자열을 반환하는 두 메서드를 제공합니다.

- `capitalizeAndSaveUpperSnakeCase(upperSnakeCase: String)`: `String`
  이 메서드는 입력받은 문자열을 기억하여, 이후 동일 문자열의 처리 결과를 더 빠르게 반환합니다.  
- `capitalizeUpperSnakeCase(upperSnakeCase: String)`: `String`
  이 메서드만 사용하면 캐시 맵을 생성하지 않아 메모리를 아낍니다. (항상 연산을 수행합니다.)

캐시 맵이 필요할 때만 캐시맵을 생성하기 위해 내부 클래스를 사용하며, 클래스로드타임에 의해 동시성을 보장합니다.

다른 기능은 아직 제공하지 않으며, 다른 기능을 개발할 필요가 아직 없기 때문에 이 클래스의 발전은 더딜 수 있습니다.

# Example Code

```java
String error = TextUtil.capitalizeAndSaveUpperSnakeCase(
        HttpStatus.NOT_FOUND.name()
);
```