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
  

- [permission](https://developer.android.com/guide/topics/security/permissions?hl=ko) 정리 그룹별 
  
- [퍼미션 상세](https://developer.android.com/reference/android/Manifest.permission?hl=ko)

- 퍼미션 선언 이유 -> 위치추적 같은 사생활 침해는 사용자가 선택할수 있도록..

- ACCESS_FINE_LOCATION : 위치정보
- ACCESS_NETWORK_STATE : 네트워크 정보
- ACCESS_WIFI_STATE : 와이파이 네트워크 정보
- BATTERY_STATES : 배터리 통계
- BLUETOOTH : 블루투스 연결 
- BLUETOOTH : 블루투스 검색하고 페어링
- CALL_PHONE : 다이얼 없이 통화
- CAMERA : 카메라 장치
- INTERNET : 네트워크 연결
- READ_CONTACTS: 사용자연락처 읽기
- READ_EXTERNAL_STORAGE : 외부 저장소에서 파일 읽기
- READ_PHONE_STATE : 장치의 전화번호, 네트워크 상태, 진행중 통화 상태등 전화 상태 에 대한 읽기
- READ_SMS : SMS 메시지 읽기
- RECEIVE_BOOT_COMPLETED : 부팅완료 시 수행
- RECEEIVE_SMS : SMS 메세지 수신
- RECORD_AUDIO : 오디오 녹음
- SEND_SMS : 메세지 발신
- VIBRATE : 진동 올리기 
- WRITE_CONTACTS : 사용자의 연락처 데이터 쓰기
- WRITE_EXTERNAL_STORAGE : 외부 저장소에 파일 쓰기 
  
### 9.1.2. 안드로이드 6.0 (API Level 23) 변경사항

- 6.0 이전 버전은 사용자 동의 없이 가능 (위치추적 기능이 아닌데 하고 있을 수있단 소리 )
- 이후 버전 부터 선택 가능으로 변경 그러므로 거부 했을 시에 대한 추가 로직이 필요 
- checkSelfPermission() 퍼미션 상태 확인 함수 
    - PERMISSION_GRANTED 퍼미션 부여
    - PERMISSION_DENTED 퍼미션 미부여
``` java
    
ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
    
```
- requestPermissions() 퍼미션 허용 여부를 묻는 대화상자는 시스템 다이얼로그
``` java
    
equestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200)
    
```
- 두번째 인자 퍼미션 이름 여래개의 경우 [0], 구분하여 명시 새번쨰 인자 퍼미션 결과 값 임의의 수 
  
``` java
     
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if(requestCode==200 && grantResults.length>0){
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            fileReadPermission=true;
        if(grantResults[1]==PackageManager.PERMISSION_GRANTED)
            fileWritePermission=true;
    }
}

```

## 9.2. 파일에 읽고 쓰기