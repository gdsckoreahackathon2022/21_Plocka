### 목차
1. [🗓️ 서비스 소개](#%EF%B8%8F-서비스-소개)
2. [🌞 Flow](#flow)
3. [⏰ 개발 기간](#-개발-기간-20220121--20220205)
4. [✨ 주요기능 소개](#-주요기능-소개)
5. [🛠️ 기술스택](#%EF%B8%8F-기술스택)
6. [✏️ 기획 및 설계](#%EF%B8%8F-기획-및-설계)
7. [👨‍👩‍👦‍👦 팀원 소개](#-팀-소개-Plocka)
---
# 2022 GDSC KR Winter Hack
[💻 해커톤 설명페이지](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/bcd83556-b931-41e1-8497-ec08c1bfda82/Info_Brochure.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220204%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220204T204721Z&X-Amz-Expires=86400&X-Amz-Signature=be1011cba05c0cfe25461ef1428b506b5e2719966b01cdfbb4cdf32884eae9b4&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Info%2520Brochure.pdf%22&x-id=GetObject)
본 프로젝트는 GDSC KR 2022 Winter Hack에 참여하는 프로젝트임을 밝힙니다.

# 🏃 서비스 소개

![](https://user-images.githubusercontent.com/70425378/152608852-448ccabd-9135-4ccc-96df-862bd6796ee8.JPG)

# 	🌍 지구도 지키고 건강도 지키자! 줍깅 🌍

##  🌼 우리 모두의 지구를 위해 함께 플로깅 해요 🌼

- 플로깅이란?  
    ➡  `plocka upp(줍다)` + `jogging(조깅)`의 합성어로 *조깅을 하며 쓰레기를 줍는* 환경 보호 활동입니다!

- 레벨과 캠페인 기능이 있어요!  
	➡ 사용자의 `적극적인 참여`를 유도합니다

- 월별 랭크와 플로깅 통계페이지가 있어요!  
	➡ 사용자에게 꾸준히 플로깅할 수 있도록 `의지`를 줍니다!

- 플로깅 뉴스피드와 플로깅 키트 판매페이지가 있어요!  
	➡ 플로깅 `진입장벽은 낮춰줍니다!`
    
    
## 🌞Flow
![](https://user-images.githubusercontent.com/70425378/152623737-0087956d-8058-4a5b-9309-3ba08d7acf32.PNG)

## ⏰ 개발 기간 (2022.01.21 ~ 2022.02.05)      
기획 및 디자인 : 2022.01.21 ~ 2022.01.28    
개발 : 2022.01.28 ~ 2022.02.05

## ✨ 주요기능 소개


### 1️⃣ 로그인, 회원가입, 플로깅 소개
`구글 소셜로그인`을 이용하여 사용자의 편리함을 제공하였고 로그인 후에 JWT를 반환하였습니다.   
첫 로그인인 경우 회원가입페이지로 리다이렉트했고 플로깅을 처음 접하는 사용자를 위해 간단한 플로깅 소개 페이지를 넣었습니다.
   
<img src="https://user-images.githubusercontent.com/70425378/152621723-60a8bdbf-8cfe-4a7f-a549-a384e1086bfb.PNG"  width="750" height="420"/>

### 2️⃣ 마이페이지, 레벨, 뱃지
마이페이지로 들어오면 정보를 수정할 수 있고 몸무게, 키, 성별등 민감한 정보도 설정할 수 있습니다.   
이러한 정보는 추후 더욱 정확한 칼로리 계산을 위해 받았습니다. 또한 플로깅 횟수에 따라 `레벨`을 나누어 사용자가 플로깅에 흥미를 느끼도록 했습니다.    
플로깅을 꾸준히 하도록 하기 위해 플로깅 미션을 횟수, 거리, 좋아요, 쓰레기통 위치 제보 등으로 세분화하여 `뱃지(챌린지)`를 만들었습니다.  
 <img src="https://user-images.githubusercontent.com/70425378/152622159-e1d52ed8-7a82-4997-b391-8812ff78b010.PNG"  width="1100" height="420"/>
 
 
 ### 3️⃣ 플로깅, 월별 랭킹, 나의 플로깅 통계 보기
플로깅 화면에선 근처에 있는 `쓰레기통이 화면에 노출`되어 주운 쓰레기를 손쉽게 처리할 수 있도록 도와주고   
 플로깅 종료버튼을 누르면 `시간, 속력, 거리가 저장`되고 마지막엔 `쓰레기 사진을 첨부하여 플로깅 인증`을 합니다.    
 또한 플로깅 도중 쓰레기통을 발견한다면 이를 등록하여 다른 사람들에게 편의를 제공할 수 있습니다.    
 월별로  거리순, 횟수순으로 `랭킹`이 매겨지며 이를 통해 뿌듯함을 느끼고 동기부여를 받도록 했습니다. 랭킹리스트에 들어간 사용자의 프로필을 누르면 사용자 페이지에 들어가 `좋아요`를 누를 수 있습니다. 마지막으로 플로깅 통계 조회 페이지에는 자신의 성장과 꾸준함을 볼 수 있도록 `그래프`로 나타냈습니다.  
<img src="https://user-images.githubusercontent.com/70425378/152623687-4b8a8139-98f2-437a-b13b-2137dcf75d2a.PNG"  width="750" height="420"/>
 
 ### 4️⃣ 뉴스피드, 플로깅 키트 판매 정보 제공
 `환경, 플로깅 관련 뉴스피드를 제공`하여 사용자에게 플로깅 캠페인이나 봉사에 참여할 기회를 제공하고 더욱 더 환경에 신경쓰는 시간을 갖도록 했습니다. 또한 외부에서 `판매하는 플로깅 키트의 정보들을 제공`하여 쉽게 가격비교를 할 수 있고 링크로 연결하여 손쉽게 구매하도록 했습니다. 추후 "줍깅"을 런칭한다면 자체 플로깅 키트를 판매할 생각입니다.   
<img src="https://user-images.githubusercontent.com/70425378/152623592-7a799a34-b3b1-4ac8-bca4-77cb92e2b81b.PNG"  width="450" height="420"/>


## 🛠️ 기술스택
- ForntEnd   
   - ReactNative
    - Google map
    - expo-location
    - react-native-chart-kit

- BackEnd   
   - spring
    - Mysql
    - JPA
    - vm instance (GCP)
    - SQL (GCP)
    - Google Cloud Storage(GCP)
    - JWT



## ✏️ 기획 및 설계

[📌 API 명세서](https://military-wildcat-495.notion.site/api-0784e44443fa4ef38d7201cd6aebcadb)

[📌 노션](https://military-wildcat-495.notion.site/02ed143562ca41de867f906e265c5f59)

## 👨‍👩‍👦‍👦 팀 소개 (Plocka)
|이름|포지션|깃허브 주소|
|:---:|:---:|:---:|
|홍민주|Spring|https://github.com/HONGMINJU|
|이하늘|Spring|https://github.com/twoosky|
|김서연|ReactNative|https://github.com/flowersayo|
|박철현|ReactNative|https://github.com/CreamMeatball|

