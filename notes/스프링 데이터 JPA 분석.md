# 목차
🎀 [스프링 데이터 JPA 구현제 분석](#스프링-데이터-jpa-구현제-분석)  
🎀 [새로운 엔티티를 구별하는 방법](#새로운-엔티티를-구별하는-방법)

## 스프링 데이터 JPA 구현제 분석
- 스프링 데이터 JPA가 제공하는 공통 인터페이스의 구현체
- `org.springframework.data.jpa.repository.support.SimpleJpaRepository`

### 리스트 12.31 SimpleJpaRepository
```java
@Repository
@Transactional(
    readOnly = true
)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    @Transactional
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Entity must not be null.");
        if (this.entityInformation.isNew(entity)) {
            this.em.persist(entity);
            return entity;
        } else {
            return this.em.merge(entity);
        }
    }
    
    ...
}
```

- `@Repository` 적용: JPA 예외를 스프링이 추상화한 예외로 반환한다.
- `@Transactional` 트랜잭션 적용
  - JPA의 모든 변경은 트랜잭션 안에서 동작한다.
  - 스프링 데이터 JPA는 변경(등록, 수정, 삭제) 메서드를 트랜잭션 처리한다.
  - 서비스 계층에서 트랜잭션을 시작하지 않으면 리포지토리에서 트랜잭션을 시작한다.
  - 서비스 계층에서 트랜잭션을 시작하면 리포지토리는 해당 트랜잭션을 전파받아서 사용한다.
  - 스프링 데이터 JPA를 사용할 때 `@Transactional`을 작성하지 않아도 데이터 등록과 변경이 가능했다.
  ➡️ 트랜잭션이 리포지토리 계층이 걸려있기 때문이다.
- `@Transactional(readOnly = true)`
  - 데이터를 단순히 조회만 하고 변경하지 않는 트랜잭션에서 `readOnly = true` 옵션을 사용하면 플로시를 생략해서 약간의 성능 향상을 얻을 수 있다.

