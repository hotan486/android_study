# 3장  사용자 인터페이스

## 3.1. UI의 기본 구조

- 기본 아키텍처 (architecture)는 컴포넌트(Component) 기반의 개발 
- 런타임(runtime) 실행에 제약이 없어야 함 -> 상시 구동

### 3.1.1 액티비티-뷰 구조

- 엑티비티 (빈화면) 에 다양한 컴포넌트 (뷰)를 추가하는 구조 

    - setContentView 함수를 이용 하여 출력 

        ``` java
        public void setContentView(View view);
        ```

### 3.1.2 UI 프르그램 작성 방법 : 자바 코드 vs 레이아웃 XML

- 자바 코드로 화면 구성 : 경로 src > main > java > 패키지 
  

    ``` java
    // MainActivity.java
    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            LinearLayout linearLayout = new LinearLayout(this);

            Button bt = new Button(this);
            bt.setText("Button 1");
            linearLayout.addView(bt);

            Button bt1 = new Button(this);
            bt1.setText("Button 2");
            linearLayout.addView(bt1);
            
            setContentView(linearLayout);
        }
    }
    ```
- 레이아웃 XML로 화면 구성 : 경로 res > layout

    ``` xml
    <!-- activity_main.xml -->
    <LinearLayout>
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 1"/>
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 2"/>
    </LinearLayout>
    
    ```

### 3.1.3 뷰 기초 속성

- id 속성 
    
    - 아이디를 등록하면 자바코드에서 식별하여 객체에 접근가능
    

    ``` xml
    
    <TextView
        android:id="@+id/fontView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
    ``` 
    - 식별시 `findViewById` 함수를 통해 가능 
    - 등록된 아이디는 `R.java`에 명시

    ``` java
    checkBox = findViewById(R.id.checkbox);
    ```
- layout_width, layout_height 속성

    - match_parent : 전체 크기
  
    - wrap_content : 객체의 크기 

- margin, padding 속성
  
    - margin : 뷰와 뷰 사이 간격 지정
    - padding : 내용과 테두리간 간격 지정
    - 양방향시 해당 태그 명시
    - 단방향시 
        
        - margin : layout_margin방향
        - padding : padding방향
  
- clickable 속성
    
    - Botton에 명시된 속성
    - TextView, ImageView -> 지정을 통해 사용 가능
  
- visibility 속성
  
  - 기본 값 true , 크기 없이 안보임 gone, 크기명시 안보임 invisible


## 3.2. 뷰 아키텍처
- 뷰 클래스 라이브러리 ex) Button, TextView, EditText, ImageView, Spinner, ListView ...등
- 뷰 클레스의 구조

### 3.2.1 뷰의 계층구조

### 3.2.1.1 구조
- 소프트웨어 모델 : `DOM(Doccument Object Model)`
- 패턴(Pattern): `Gof 다자인패턴의 Composite 패턴`

### 3.2.1.2 예시

- 계층 구조 트리(tree)의 맨위에 위치한 루트(root)뷰와 그 밑의 가지에 위치한 자식뷰

    > View
    >> ViewGroup
    >>
    >> TextView

- View : 최상위 클래스 
- ViewGroup : 
    - 뷰의 서브클래스
    - 서브 뷰 여러개를 포함하여 그룹화 제어 용이
    - 컨테이너(Container) 기능 담당
- TextView : 문자열 출력 뷰    
                   
### 3.2.2 뷰 계층구조 구현

- 레이아웃 XML로 계층구조 구현
    
    ``` xml
    <LinearLayout>
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 1"/>
        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 2"/>
    </LinearLayout>
    
    ```

    - 태크의 상하 관계를 이용
    - 서브 객체 뷰는 상위 객체 뷰에 포함


  
        
- 자바로 계층구조 구현
    
    - `addView` 함수 이용
        ``` java
        public void addView(View child)
        ```
    - 버튼 포함 레이아웃
        ``` java
        LinearLayout linearLayout = new LinearLayout(this);

        Button bt = new Button(this);
        bt.setText("Button 1");
        linearLayout.addView(bt);

        Button bt1 = new Button(this);
        bt1.setText("Button 2");
        linearLayout.addView(bt1);
        ```


## 3.3. 기초 뷰 활용
---

### 3.3.1 TextView 

  - text : 문자열 명시 
  - typeface : 폰트 명시
  - normal, sans, serif, monospace 
  - textStyle : 문자열 효과 
  - normal, bold, italic
  - textColor : 문자열 색상
  - textSize : 폰트의 크기
  - autoLink : 문자열에 url 포함되면 자동 링크
  - maxLine : 특정줄 만큼만 출력 
  - ellipsize : end, start, middle 옵션중 안보이는 부분 ... 처리
### 3.3.2 ImageView 

- src : 출력 이미지 지정
- maxWith, maxHeight : 최대 크기 지정
- adjustViewBounds : 이미지 크기 변경시 가로세로 비율을 유지(true,false) 지정 속성
- tint : 다른 색상을 입힘

### 3.3.3 EditText

- lines : 처음부터 특정줄만큼 보이게 사용
- maxLines : 최대 줄 지정
- inputType : 
    
    - none : 지정없음
    - text : none 동일, 줄바꿈만 불가능
    - textCapCharacter : 대문자 모드
    - textCapWords : 단어 첫 글자 입력시 대문자
    - textCapSentences : 문자 첫 글자 입력시 대문자 
    - textMultiLine : 여러줄 입력
    - textNoSuggestions : 단어 입력시 추천 단어 비활성화
    - textUri : url 입력
    - textEmailAddress : 이메일 입력
    - textPassword : 비번 입력 * 표시
    - textVisiblePassword : 비번입력 표시
    - number : 숫자 입력
    - numberSigned : - 입력 가능
    - numberDecimal : 소수점 입력 가능
    - numberPassword : 숫자만 입력
    - phone : 전화번호 입력
    - datetime : 날짜와 시간
    - date : 날짜 입력
    - time : 시간 입력  
- gravity : top, left, center, right 글의 위치 지정

### 3.3.4 Button

- 클릭 이벤트 처리 
- CheckBox, RadioButton, ToggleButton
  
### 3.3.5 Checkbox, RadioButton

 - Checkbox : 두가지 상태를 제어하는 뷰
    
    - isChecked : 체크 상태 반환
    - setChecked : 채크 상태 변경
    - toogle : 상태가 반대로 변경
- RadioButton : 여러 개중 하나만 선택가능

    - check : 하나의 id 값으로 체크
    - clearCheck : 체크 해제 
    - getCheckRadioButtonId : 채크 id 확인

