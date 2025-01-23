# 1. 리팩터링: 첫 번째 예시

# 첫 번째 발자국(2025-01-19)

- Kotest
- 직렬화(Serialization) vs. 역직렬화(Deserialization)
- String templates
- IntelliJ IDEA 자주 사용하는 리팩터링 단축키

### volumeCredits 변수 제거하기

1. 반복문 쪼개기(8.7), 316
2. 문장 슬라이드하기(8.6), 310
3. 함수 추출하기(6.1), 158
4. 변수 인라인하기(6.4), 178

**<참고 자료>**

- [Kotest 'Quick Start'](https://kotest.io/docs/quickstart)
- [Kotlin Docs 'Serialization'](https://kotlinlang.org/docs/serialization.html)
- [Kotlin Docs 'Strings - String templates'](https://kotlinlang.org/docs/serialization.html)
- [IntelliJ IDEA Documentation 'Code refactoring'](https://www.jetbrains.com/help/idea/refactoring-source-code.html)

# 두 번째 발자국(2025-01-20)

## 1.6 계산 단계와 포맷팅 단계 분리하기

- **목표**: 계산 관련 코드와 포맷팅 코드를 분리한다.

**중간 데이터 구조 생성하기**

> 중간 데이터 구조란 `DTO(Data Transfer Object)`를 의미한다.

- `invoice`, `plays`를 통해 전달되는 `JSON` 데이터를 중간 데이터 구조를 사용해서 전달한다.
  - 보강한 `data class`를 선언한다.
- `statement`: 계산 관련 로직
- `rederPlainText`: 데이터 포맷팅

### Todo

> 8.1 Move Function(함수 옮기기)

1. 고객 정보(customer) 옮기기
2. 공연 정보(performances) 옮기기
3. 연극 정보 옮기기
4. `amountFor` 옮기기

**amountFor 옮기기**

> 간결한 Kotlin 문법, 어떻게 활용할 것인가?

5. 적립 포인트 옮기기
6. 총합 계산 옮기기

**<참고 자료>**

- [martinFowler 'Data Transfer Object'](https://martinfowler.com/eaaCatalog/dataTransferObject.html)

7. 반복문을 파이프라인으로 바꾸기

> 8.8 Replace Loop with Pipeline(반복문을 파이프라인으로 바꾸기)

**fold vs. reduce**

`JavaScript` 버릇이 여든까지 간다. 습관적으로 `reudce`를 사용했다. (심지어 책에서도 `reudce`를 사용한다) 하지만 `fold`를 사용해서 문제를 해결할 수 있었고
비슷해 보이지만 달랐다. 어떤 상황에 사용하면 좋을까?

- 타입 변환, 초깃값

# 세 번째 발자국(2025-01-23)

## 1.7 중간 점검: 두 파일(과 단계)로 분리됨

- 63, 이렇게 모듈화하면 각 부분이 하는 일과 그부분들이 맞물려 돌아가는 과정을 파악하기 쉬워진다. 간결함이 지혜의 정수일지 몰라도, 프로그래밍에서만큼은 명료함이 진화할 수 있는 소프트웨어 정수다. 
  모듈화한 덕분에 계산 코드를 중복하지 않고도 `HTML`버전을 만들 수 있었다.

소프트웨어의 궁극적인 가치 '변화'에 초점을 둔 최선의 선택이 '모듈화'인 것 같다. 사람마다 읽는 속도, 환경이 달라 '최소한'의 단위도 모두 다르겠지만, 개인이 허용할 수 있는 최소한으로 나눠야
'이해'에 사용되는 비용이 줄어들지 않을까? 고민해 볼 문제다. 기준과 균형의 줄다기는 이미 시작됐다.

**<참고 자료>**

- [Kotlin Docs 'Aggregate operations'](https://kotlinlang.org/docs/collection-aggregate.html)
- [Baeldung 'Difference Between fold and reduce in Kotlin'](https://www.baeldung.com/kotlin/fold-vs-reduce)
