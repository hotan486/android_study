# 4장 레이아웃을 활용한 다양한 뷰 배치

## 속성 크기 기입 참고


- 대부분에 크기는 dp 권장

- 이유 : 시스템에서 스마트폰의 화면에 dpi에 따라 논리적인 크기를 계산해서 적용 -> 다양한 크기 호환

- 문자열의 경우는 sp 권장



## 3장 참고 공통 속성 기능 


- margin -> 해당 태그 객체의 외부 

<pre>
        
  <-->  │  view  | <-->
                                           
  android : margin       = "24pb"  ←↑↓→ 전방향 외부
  android : marginLeft   = "24pb"  ←  
  android : marginRight  = "24pb"  →
  android : marginTop    = "24pb"  ↑
  android : marginBottom = "24pb"  ↓

</pre>


- padding -> 해당 태그 객체의 내부 

<pre>

  <-->  |  view  | <-->

  android : paddingLeft   = "24pb"  ←
  android : paddingRight  = "24pb"  → 
  android : paddingTop    = "24pb"  ↑
  android : paddingBottom = "24pb"  ↓

</pre>

## 3장 이미지 속성 기능 

- maxWidth, maxHeight 사용시 adjustViewBounds 반드시 사용

- adjustViewBounds 원본 비 유지 

## 4.1 LinearLayout

- 방향 지정 orientation 속성 vertical, horizontal

- gravity -> 뷰 내부

- layout_gravity -> 레이아웃 내부

<pre>
  android : gravity = "bottom|right"
  android : layout_gravity = "center_vertical|center_horizontal"
  // orientation 속성 vertical이면 center_vertical만 가능
  // 그경우 레이아웃에 gravity 속성에 center를 주면 됨
</pre>

- weight 상대적인 값으로 뷰 영역이 나누어진다.

## 4.2 RelativeLayout

- layout_above : 뷰기준으로 윗부분 위치
- layout_below : 뷰기준으로 아래 부분 위치 
- layout_toLeftOf : 뷰기준으로 왼쪽부분 위치 
- layout_toRightOf : 뷰기준으로 오른쪽부분 위치 

- layout_alignTop : 기준 뷰와 윗 정렬
- layout_alignBottom : 기준 뷰와 아래 정렬
- layout_alignLeft : 기준 뷰와 왼 정렬
- layout_alignRight : 기준 뷰와 오른 정렬
- layout_alignBaseline : 기준 뷰와 텍스트 기준 정렬 -> 크기 다를시 하단 정렬 

> 상단은 기준 뷰 아이디 명시 

- layout_alignParentTop : 레이아웃 뷰와 윗 정렬
- layout_alignParentBottom : 레이아웃 뷰와 아래 정렬
- layout_alignParentLeft : 레이아웃 뷰와 왼 정렬
- layout_alignParentRight : 레이아웃 뷰와 오른 정렬
- layout_centerHorizontal : 레이아웃 뷰의 가로방향 중앙에 뷰를 위치 
- layout_centerVertical : 레이아웃 뷰의 세로방향 중앙에 뷰를 위치   
- layout_centerInParent : 레이아웃 뷰의 가로세로에 뷰를 위치  

> 상단은 true

## 4.3 FrameLayout

- 겹쳐서 사용시 사용 

## 4.3.2 TabHost

- TabHost : 전체 엑티비티
- TabWidget : 버튼 영역
- FrameLayout : 내용 영역

<pre>
  TabHost tabHost=findViewById(R.id.host);
  tabHost.setup();

  TabHost.TabSpec spec=tabHost.newTabSpec("tab1");
  spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon1, null));
  spec.setContent(R.id.tab_content1);
  tabHost.addTab(spec);

  spec=tabHost.newTabSpec("tab2");
  spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon2, null));
  spec.setContent(R.id.tab_content2);
  tabHost.addTab(spec);

  spec=tabHost.newTabSpec("tab3");
  spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon3, null));
  spec.setContent(R.id.tab_content3);
  tabHost.addTab(spec);
</pre>


## 4.4 TableLayout

-  shrinkColums = "0,1" : 화면 벗어나는 경우 인덱스를 0,1의 열 크기를 줄임
-  stretchColumns = "1" : 화면의 여백 발생시 인덱스 1의 열의 크기를 늘림
-  layout_column = "1" : 뷰의 위치지정 인덱스 1의 위치에 뷰가 위치
-  layout_span = "2" : 두개의 열을 하나의 뷰가 차지


    ``` xml
    <TableLayout
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="3">
            <TableRow>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="7"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="8"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="9"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
            </TableRow>
            <TableRow>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="4"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="5"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="6"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
            </TableRow>
            <TableRow>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="1"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="2"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="3"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
            </TableRow>
            <TableRow>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="."
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="0"
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
                <Button
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:text="="
                    android:paddingTop="32dp"
                    android:paddingBottom="32dp"/>
            </TableRow>
        </TableLayout>
    ``` 

## 4.5 GridLayout

- 방향 지정 orientation 속성 vertical, horizontal

- horizontal시 rowCount 해당 방향으로 갯수만큼 생성 

- vertical시 columnCount 해당 방향으로 갯수만큼 생성 

## 4.5.2 GridLayout 속성

- layout_column : 뷰가 위치할 열 인덱스 지정
- layout_row : 뷰가 위치할 행 인덱스 지정
- layout_columnSpan :  가로방향으로 여러 열을 하나의 뷰가 차지하고자 할떄
- layout_rowSpan : 세로방향으로 여러행을 하나의 뷰가 차지하고자 할떄
- layout_gravity : 하나의 열 내에서 뷰의 정렬위치 지정

