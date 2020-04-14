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

 ``` xml

<activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
    
```

> 액티비티를 외부 이용시 `intent` 인센트 발생

 ``` xml
    
<permission android:name="퍼미션이름"
    android:label="퍼미션에 대한 설명"
    android:description="퍼미션에 대한 설명"
    android:protectionLevel="보호수준"/>
    
```

> normal : 낮은 수준, 권한 요청 없음
> 
> dangerous : 높은 수준, 권한 요청 있음
> 
> signature : 동일키만 가능
> 
> signatureOrSystem : 안드로이드거나 동일키

- ㅡ 특정 컴토넌트에 퍼미션 적용

 ``` xml
    
<activity android:name=".MainActivity"
    android:permission="퍼미션이름">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
    
```
- 퍼미션 사용 등록
 ``` xml
    
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    
```

- 퍼미션 선언시 사용자에게 권한여부 동의를 구하는 창 열림

#### 시스템의 permission

- 시스템 상에서 요구하는 경우 
- uses-permission 선언해야 함
- 구룹에 따라 나옴
  

- permission 정리 그룹별 