# 9장 파일및 SharedPreferences을 이용한 데이터 영속화

- 대표적으로 카메라 앱 
  - 저장시 파일 쓰기 
  - 갤러리에서 파일 읽기

## 9.1. 퍼미션

### 9.1.1. 퍼미션이란?

- permission
- AndroidManifest.xml 상의 설정
- 앱과 앱간의 연동시 권한`<permission>` 설정시 이용하는 앱은 `<use-permission>` 을 선언 

#### permission 이용