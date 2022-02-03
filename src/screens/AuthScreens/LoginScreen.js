import React from "react";
import {  View, Button,ImageBackground,Image } from "react-native";
import MyButton from "../../components/MyButton";
import * as Google from "expo-google-app-auth";
import styled from 'styled-components/native'
import { useFonts } from 'expo-font';
import LottieView from "lottie-react-native";
import Icon from "../../images/Icon";

import Theme from "../../styles/Theme";
const Container = styled.SafeAreaView`
flex : 1;
justify-content : center;
align-items : center;
background-color:white;

`
const StyledText = styled.Text`
font-size : 60px;
font-family : NanumBarunGothicUltraLight;
text-align:center;
color:#65F6E9;

`;

const Small = styled.Text`
font-size : 20px;
font-family : NanumBarunGothicUltraLight;
text-align:center;
color : ${Theme.mintColor}
`;
const ButtonContainer = styled.View`
margin-top:auto;
width :100%;
`

const SubContainer = styled.SafeAreaView`

justify-content : center;
align-self : center;
background-color: transparent;



`

const LoginScreen = ({ navigation }) => {

    const [loaded] = useFonts({ 
        NanumBarunGothicUltraLight: require('../../../assets/font/NanumBarunGothicUltraLight.ttf'),
    });

  const signInAsync = async () => {
    console.log("LoginScreen.js 6 | loggin in");
    try {
      const { type, user,accessToken } = await Google.logInAsync({
        iosClientId: `<YOUR_IOS_CLIENT_ID>`,
        androidClientId: `237516281102-20ua63ld68kq02ohkha3654pgrl53id0.apps.googleusercontent.com`,
      });

      if (type === "success") {
        // Then you can use the Google REST API
        console.log("LoginScreen.js 17 | success, navigating to profile");
        navigation.navigate("Signin", { user });
        console.log(accessToken);
      }
    } catch (error) {
      console.log("LoginScreen.js 19 | error with login", error);
    }
  };

  if(!loaded)
  return null;

  return (
    <Container>
         <Image source={Icon.Shoe} style={{width:100,height:100}}/>
     <StyledText>JUPGGING</StyledText>
     <Small>Run for Earth</Small>
   
     <Image source={Icon.  HandsOnEarth} style={{width:100,height:100,margin:30}}/>
   <SubContainer>
     <ButtonContainer>
      <MyButton title="구글계정으로 로그인하기" onPress={signInAsync} bgColor={'white'}  txtColor={'#5f5f5f'} type='google'/>
      <MyButton title="카카오계정으로 로그인하기" onPress={()=>alert('구현 준비중')} bgColor={'#f9e000'}  txtColor={'#3c1e1e'} type='kakao'/>
      </ButtonContainer>
      </SubContainer>
   
    </Container>
  );
};

export default LoginScreen;