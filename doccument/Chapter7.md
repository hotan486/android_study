# 7장 리소스 활용 및 스마트폰 크기 호환성

## 7.1. 안드로이드 리소스

- 안드로이드에서 다양한 리소스에 대해 알아보자 
    <pre>
    app
        src
            main    
                res -> 리소스 경로 </pre>

### 7.1.1. 리소스 종류

> res 하위로 폴더 형태

- drawable : 이미지, 이미지 관련 XML, 그림을 표현한 XML
- layout : 화면 UI를 정의한 레이아웃 XML
- values : 문자열, 색상, 크기 등 여러가지 값
- menu : 액티비티의 메뉴를 구성하기 위한 XML
- xml : 특정 폴더가 지정되어 있지 않은 기타 XML
- anim : 애니메이션을 위한 XML
- raw : 바이트 단위로 직접 이용되는 이진 파일
- mipmap : 앱 아이콘 이미지

### 7.1.2. 다양한 리소스 활용

#### 7.1.2.1 애니메이션 리소스

- 고정 애니메이션의 경우, 항상 동일하게 움직이므로 리소스 파일(.xml)을 이용
- res -> anim 폴더

    ``` xml
    <!-- 파일 작성시 -->
    <set>              <!-- anim에서 최상위 root 태그 -->
        <scale />      <!-- 크기 변경 -->
        <rotate />     <!-- 회전 조절-->
         <alpha />     <!-- 투명도 조절 -->
         <translate /> <!-- 이동 조절 -->
    </set>
    ``` 

- 태그 공통 속성
  - duration: 지속 시간 2000 -> 2초
  - startOffset: 시작한 후 얼마 후부터 적용할지 결정 0 -> 바로 , 500 -> 0.5초
  - repeatCount: 반복 횟수 infinite - 무한 반복, 2 -> 2회 
  - repeatMode: 반복 방향 restart - 그대로, reverse - 반대 방향

- 태그 주요 속성
    - scale
      - fromXScale : x축 방향 시작 배율 0 -> 안보임, 2 -> 2배 
      - toXScale : x축 방향 끝 배율
      - fromYScale : y축 방향 시작 배율
      - toYScale : y축 방향 끝 배율
      - pivotX : 확대 축소 x축 기준점, 50% 중앙
      - pivotY : 확대 축소 y축 기준점, 50% 중앙
    - rotate
      - fromDegrees :회전 시작 각도
      - toDegrees :	회전 끝 각도
      - pivotX : 확대 축소 x축 기준점, 50% 중앙
      - pivotY : 확대 축소 y축 기준점, 50% 중앙
    - alpha
      - fromAlpha : 시작 투명도
      - toAlpha : 끝 투명도
    - rotate
      - fromDegrees : 회전 시작 각도
      - toDegrees : 회전 끝 각도
      - pivotX : 확대 축소 x축 기준점
      - pivotY : 확대 축소 y축 기준점

    ``` java
    //사용 예시
    Animation anim = AnimationUtils.loadAnimation(this, R.anim.in); // 해당 .xml 파일명 
    imageView.startAnimation(anim); // startAnimation 통해 실행
    ``` 

     ``` java
     //제어
    Animation anim = AnimationUtils.loadAnimation(this, R.anim.in);
    anim.setAnimationListener(new Animation.AnimationListener() {
    // in 시작시 
    @Override
    public void onAnimationStart(Animation animation) {
    }
    // in 종료시 
    @Override
    public void onAnimationEnd(Animation animation) {
    }
    // in 반복시
    @Override
    public void onAnimationRepeat(Animation animation) {
    }
    });
    imageView.startAnimation(anim);
    ``` 
    
#### 7.1.2.2 색상, 크기 리소스

- vakues 폴더 하위 통칭 -> 파일명이 아닌 태크의 속성 값으로 자바에서 식별 
  - 문자열 : strings.xml 	
  - 색상 : colors.xml		
  - 스타일 : styles.xml		
  - 배열 : arrays.xml		

  ``` xml
  <!-- 선언시 색상 : colors.xml -->
  <resources>
      <color name = "my_background">#ffffff</dimen>
  </resources>

  <!-- 사용시 -->
  <Button
      ...
      android:padding="@color/my_background"
  />

  ```

  ``` xml
  <!-- 선언시 색상 : colors.xml -->
  <resources>
      <color name = "my_background">#ffffff</dimen>
  </resources>

  <!-- 사용시 -->
  <Button
      ...
      android:padding="@color/my_background"
  />

  ``` 

  ``` xml
  <!-- 선언시 크기 : dimens.xml -->
  <resources>
      <dimen name = "my_margin">16dp</dimen>
      <dimen name = "my_padding">16dp</dimen>
  </resources>

  <!-- 사용시 -->
  <Button
      ...
      android:padding="@dimen/my_padding"
  />
  ```

#### 7.1.2.3 스타일 리소스

  ``` xml
  <!-- 선언시 스타일 : styles.xml -->
  <style name="myStyle">
    <item name="android:textColor">#FF0000FF</item>
    <item name="android:textSize">20dp</item>
    <item name="android:textStyle">bold</item>
  </style>
  <!-- 사용시 -->
  <TextView
      ...
      style="@style/myStyle"
  />

  <!-- 상속 사용 -->
  <style name="mySubStyle">
    <item name="android:textStyle">italic</item>
  </style>
  ```

#### 7.1.2.4 테마 리소스

- 테마의 스타일은 styles 폴더에 사용은 AndroidManifest.xml
  ``` xml
  <!-- 사용시 -->
  <application
      ...
      android:theme = "@style/AppThere" 
  /> <!-- AppThere .xml 태그 이름 -->

  <!-- 툴바 제거 -->
  <style name="myTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="windowNoTitle">true</item>
    <item name="windowActionBar">false</item>
  </style>

  <!-- 상태바 제거 -->
  <style name="myTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     <item name="android:windowFullscreen">true</item>
  </style>

  <activity
    android:screenOrientation="landscape"><!-- 가로 고정 -->
  </activity>

   <activity
    android:screenOrientation="portrait"><!-- 세로 고정 -->
  </activity>
  ```


## 7.2. 스마트폰 크기 호환성

- 일관된 출력을 도와주는 기능 
  
### 7.2.1. 리소스 폴더명 조건 명시법

- 해상도에 따라 다른 이미지 파일 명시 -> mipmap 폴더

 ``` xml
    <!-- string.xml -->
    <!-- 기본 영어 -->
    <string name="hello">hello</item>
    <!-- kr 파일 작성 -->
    <string name="hello">안녕하세요</item>

```

- 레이아웃의 경우
  - 기본 layout 폴더에 activity_main.xml -> 가로 
  - layout 폴더에 activity_main.xml(land) -> 세로 
### 7.2.2. DisplayMetrics

- 스마트폰 크기 정보 

``` java
  //사용 예시
  DisplayMetrics dm = new DisplayMetrics();
  getWindowManager().getDefaultDisplay().getMetrics(dm);
``` 

- 획득 가능 정보 
  - widthPixels : 가로 화소 수
  - heightPixels : 세로 화소 수
  - densityDpi : 화면 밀도
  - density : mdpi를 기준으로 한 배율, 스케일링 시 곱해지는 값
  - scaledDensity : 문자열 스케일링 시 곱해지는 값
  - xdpi : 정확한 가로 밀도
  - ydpi : 정확한 세로 밀도
  
### 7.2.3. 논리적 단위로 스마트폰 크기 호환성 확보

  -  dp, sp, pt, px, mm, in 단위를 사용 
  -  dp 권장 문자열은 sp 권장
