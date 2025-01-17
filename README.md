# 고객 지원 시스템 프로젝트 과제
직접 과제를 만들고 직접 풀어보는 과제

## 목표
- 기능 구현
- 다양한 spring 및 java 기술 사용
- 디자인 패턴 적극 사용
- 대규모 요청 대응 가능한 서비스 구축

## 과제 개요
고객 지원 시스템 일부를 구현하는 과제입니다.
이 과제는 고객 지원 로직과 다양한 디자인 패턴을 적용하여 설계 및 구현 과정을 익히는 것을 목표로 합니다.

## 요구사항

1. **티켓 관리**
    - 지원 티켓 생성, 수정, 삭제, 조회 API 구현

2. **티켓 할당 및 우선 순위 관리**
    - 티켓을 적절한 지원 담당자에게 할당
    - 티켓 우선 순위 설정

3. **알림 시스템**
    - 티켓 상태 변경 시 고객에게 알림 발송 (이메일, SMS, 메신저 중 중복 선택 가능)

4. **성능 및 확장성 고려**
    - 이벤트 등으로 특정 시간에 티켓 생성 요청이 몰릴 수 있음
    - 이러한 요소를 고려하여 시스템의 성능과 확장성을 보장해야 함

## 세부 요구사항

### 1. 티켓 관리

- **티켓 생성**
    - **티켓 유형**: 고객 지원 시스템은 다양한 유형의 티켓을 지원하며, 현재 3가지 유형의 티켓이 있습니다. 추후 필요에 따라 새로운 티켓 유형이 추가될 수 있습니다.
        1. **문제 신고 티켓**: 제품이나 서비스와 관련된 문제를 신고 (우선 순위: 1)
        2. **일반 문의 티켓**: 일반적인 문의 사항 (우선 순위: 2)
        3. **피드백 티켓**: 고객의 피드백이나 제안 사항 (우선 순위: 3)
    - 티켓 유형에 따라 생성 제약 조건이 다릅니다,
        1. **문제 신고 티켓**: 제목, 설명, 고객ID, 휴대전화번호 필수
        2. **일반 문의 티켓**: 제목, 설명, 고객ID 필수
        3. **피드백 티켓**: 제목, 설명 필수
        4. **공통**: 티켓 유형, 상태(새 티켓, 진행중, 완료됨), 이메일 필수
    - 우선 순위의 숫자가 작을수록 우선 순위가 높습니다.
    - **성능 고려사항**: 특정 이벤트 (예: 타임 세일) 동안 티켓 생성 요청이 급증할 수 있으므로, 시스템은 높은 부하를 처리할 수 있도록 설계되어야 합니다.

- **티켓 수정**
    - 티켓의 제목, 설명을 수정할 수 있습니다.
    - 티켓의 유형은 수정할 수 없습니다.

- **티켓 삭제**
    - 티켓을 삭제할 수 있습니다.

- **티켓 조회**
    - 특정 티켓의 현재 상태와 관련 정보를 조회할 수 있습니다.

### 2. 티켓 할당 및 우선 순위 관리

- **티켓 할당**
    - **티켓 할당 방식**
        - **Round Robin 방식**: 새로운 티켓은 지원 담당자에게 순차적으로 할당됩니다.
        - **우선 순위 기반 할당**: 우선 순위가 가장 높은 티켓은 즉시 가장 적은 티켓을 보유한 지원 담당자에게 할당됩니다.

- **티켓 우선 순위 설정**
    - **우선 순위 레벨**
        - 숫자로 표시되며, 숫자가 낮을수록 우선 순위가 높습니다.
        - 예를 들어, 우선 순위 1은 우선 순위 3보다 더 높은 우선 순위입니다.

### 3. 알림 시스템

- **알림 유형**
    - **이메일**: 이메일 주소로 알림 발송
    - **SMS**: 전화번호로 SMS 발송
    - **메신저**: 메신저 앱(예: WhatsApp, Slack)으로 알림 발송
    - 알림 발송은 로그를 남기는 방식으로 대체하며 각 유형별 로그 내용은 달라야 합니다.
- **티켓 상태 변경 시 알림**
    - 티켓 상태 변경 후 5분 이내 고객에게 선택한 방법(이메일, SMS, 메신저)으로 알림을 발송합니다.

### 4. 성능 및 확장성 고려

- **티켓 생성 요청의 급증 처리**
    - 특정 이벤트 기간 동안 티켓 생성 요청이 급증할 수 있음
    - 시스템은 높은 부하를 처리할 수 있도록 설계되어야 함
    - 높은 부하를 처리할 때 데이터베이스의 부하를 최소화 하는 것을 최우선 시 합니다.
    - 내부 메시지 큐를 사용하여 티켓 생성 요청을 비동기로 처리하고, 데이터베이스와의 부하를 최소화

## 디자인 패턴 적용

- **Factory Pattern**: 티켓 생성 시 다양한 티켓 유형을 생성하는 팩토리
- **Strategy Pattern**: 티켓 생성 시 다양한 티켓 유형에 따라 전략을 선택하여 로직 구현
- **Observer Pattern**: 티켓 상태 변경 시 알림 시스템에 적용
