## 대용량 트래픽에 대비한 백엔드 서버 구축

이 프로젝트는 단순한 기능 한 가지(글 쓰기)를 구현했습니다.

### 고가용성 아키텍처로 구현한 스프링 부트 서버
스프링 부트 서버의 요청을 프록시 `ProxySQL`에서 로드 밸런싱하여 읽기 **작업(Read)과 생성/수정/삭제 작업(CUD)을 분리**했습니다. 

또한 `Orchestrator`를 사용하여 Master 서버 다운 시, Slave 서버를 Master 서버로 `Fail Over`하는 기능을 구축함으로써 HA 구성을 완성했습니다.

### 빠른 응답을 위한 비동기 처리
사용자 요청을 즉시 `Message Queue`로 비동기적으로 전송하여 빠른 응답이 가능하도록 구현했습니다. 

이를 위해 `Producer`와 `Consumer`를 분리하여 `Decoupling` 아키텍처를 구축했습니다.
## Architecture

![image](/image/architecture.png)

## Applications

- ### MySQL

  이 프로젝트에서는 MySQL 복제 마스터-슬레이브 클러스터를 설정했습니다. 이 클러스터에는 하나의 마스터와 두 개의 슬레이브 MySQL 인스턴스가 포함되어 있습니다. 복제 과정에서 데이터는 마스터에서 슬레이브로 자동으로 복사됩니다.

- ### ProxySQL

  ProxySQL은 고성능의 오픈 소스 MySQL 프록시 서버입니다. 이는 애플리케이션과 데이터베이스 서버 사이에 위치하여 MySQL 클라이언트로부터 들어오는 트래픽을 받아들이고, 이를 백엔드 MySQL 서버로 전달합니다.

- ### Orchestrator

  Orchestrator는 MySQL의 고가용성 및 복제 관리 도구로, 이 프로젝트에서는 MySQL 리플리케이션 구성의 Fail Over로 사용되었습니다.

- ### Apache Kafka

  Apache Kafka는 분산 스트리밍 플랫폼으로, 대용량의 실시간 데이터 스트림을 처리하기 위해 설계된 오픈 소스 솔루션입니다.

  이 프로젝트에서는 글 쓰기 정보를 처리하기 위해 사용되었습니다.

- ### post-api

  백엔드 서버는 `Spring Boot`로 구성했고, `MySQL`과의 직접적인 연결이 아닌 `ProxySQL`과의 연결로 애플리케이션의 설정 요소의 복잡성을 최소화 시켰습니다.
- 

  `post-api` has the following endpoints:
  ```
    POST /api/post {"userId":"...", "contents":"..."}
  DELETE /api/post [1, 2, 3, 4, 5]
  DELETE /api/post/{id}
  ```

- ### consumer

  Kafka의 특정 토픽을 구독해 메세지를 읽어와서 DB에서 작업을 처리합니다.

## Prerequisites

- `Java 17+`
- `Docker`