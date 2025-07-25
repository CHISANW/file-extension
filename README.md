# 파일 확장자 관리 시스템

파일 확장자 관리 시스템은 특정 파일 확장자를 차단하여 시스템 보안을 강화하는 웹 애플리케이션입니다. 사용자는 기본 제공되는 고정 확장자(exe, bat 등)를 활성화하거나 비활성화할 수 있으며, 커스텀 확장자를 추가하여 차단 목록을 관리할 수 있습니다.

## 주요 기능

### 고정 확장자 관리
- 시스템에서 기본 제공하는 위험한 확장자 목록 제공 (bat, cmd, com, cpl, exe, scr, js)
- 각 고정 확장자의 활성화/비활성화 상태 토글
- 모든 고정 확장자 한 번에 활성화 기능

### 커스텀 확장자 관리
- 사용자 정의 확장자 추가 (최대 200개)
- 여러 확장자 한 번에 추가 가능 (쉼표로 구분)
- 각 확장자 개별 삭제 및 전체 삭제 기능
- 확장자 길이 제한 (최대 20자)
- 중복 확장자 및 특수문자 포함 확장자 검증

### 파일 확장자 테스트
- 파일 업로드 또는 파일명 입력을 통한 확장자 차단 여부 테스트
- 테스트 결과 상세 표시 (파일명, 확장자, 차단 상태, 설명)

## 기술 스택

- **Backend**: Java 17, Spring Boot 3.4.7
- **Frontend**: HTML, CSS, JavaScript, Thymeleaf
- **Database**: MySQL
- **Build Tool**: Gradle

## 프로젝트 구조

```
src/
├── main/
│   ├── java/project/
│   │   ├── constants/       # 상수 정의
│   │   ├── controller/      # API 및 뷰 컨트롤러
│   │   ├── dto/             # 데이터 전송 객체
│   │   ├── entity/          # 데이터베이스 엔티티
│   │   ├── exception/       # 예외 처리
│   │   ├── repository/      # 데이터 액세스 계층
│   │   └── service/         # 비즈니스 로직
│   └── resources/
│       └── templates/       # Thymeleaf 템플릿


```

## API 문서

### 파일 확장자 API

| 메소드 | 엔드포인트 | 설명 |
|--------|------------|------|
| POST | `/api/extensions` | 새 파일 확장자 생성 |
| DELETE | `/api/extensions/{id}` | 파일 확장자 삭제 |
| PUT | `/api/extensions/default/{extension}` | 파일 확장자 상태 업데이트 |

### 요청/응답 예시

#### 확장자 생성
```
POST /api/extensions
Content-Type: application/json

{
  "extension": "pdf",
  "isDefault": false
}
```

응답:
```json
{
  "id": 1,
  "extension": "pdf",
  "default": false,
  "active": true
}
```

#### 확장자 삭제
```
DELETE /api/extensions/1
```

응답:
```json
{
  "success": true
}
```

#### 확장자 상태 업데이트
```
PUT /api/extensions/default/pdf
Content-Type: application/json

{
  "isActive": true
}
```

응답:
```json
{
  "id": 1,
  "extension": "pdf",
  "default": false,
  "active": true
}
```

## 설치 및 실행 방법

### 요구사항
- Java 17 이상
- MySQL 8.0 이상

### 설치 단계

1. 저장소 클론
   ```
   git clone https://github.com/yourusername/file-extension.git
   cd file-extension
   ```

2. 데이터베이스 설정
   - MySQL에서 데이터베이스 생성
   - `application.yml` 파일에서 데이터베이스 연결 정보 설정

3. 애플리케이션 빌드 및 실행
   ```
   ./gradlew build
   ./gradlew bootRun
   ```

4. 웹 브라우저에서 접속
   ```
   http://localhost:8080
   ```

## 사용 예시

1. **고정 확장자 관리**
   - 기본 제공되는 고정 확장자(exe, bat 등) 목록에서 차단하고자 하는 확장자의 체크박스를 선택합니다.
   - "전체 선택" 버튼을 클릭하여 모든 고정 확장자를 한 번에 활성화할 수 있습니다.

2. **커스텀 확장자 추가**
   - 입력 필드에 차단하고자 하는 확장자를 입력하고 "추가" 버튼을 클릭합니다.
   - 여러 확장자를 한 번에 추가하려면 쉼표(,)로 구분하여 입력합니다. (예: jpg,png,gif)
   - 추가된 확장자는 태그 형태로 표시되며, 'x' 버튼을 클릭하여 개별 삭제할 수 있습니다.
   - "전체 삭제" 버튼을 클릭하여 모든 커스텀 확장자를 한 번에 삭제할 수 있습니다.

3. **파일 확장자 테스트**
   - "파일 업로드" 탭에서 파일을 선택하거나 "파일명 입력" 탭에서 파일명을 입력합니다.
   - "테스트" 버튼을 클릭하여 해당 파일의 확장자가 차단되는지 확인합니다.
   - 테스트 결과에서 파일명, 확장자, 차단 상태, 설명을 확인할 수 있습니다.
