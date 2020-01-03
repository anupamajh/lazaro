//
//  OTPVerification.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct OTPVerification: View {
    @State var OTPDigit1:String = "";
    @State var OTPDigit2:String = "";
    @State var OTPDigit3:String = "";
    @State var OTPDigit4:String = "";
    var body: some View {
        GeometryReader { geometry in
            VStack{
                HStack{
                    GuestJiniHeaderLarge()
                        .frame(width: geometry.size.width, height: geometry.size.height * 0.25, alignment: .center)
                        .background(Color("veryLightPink"))
                }
                VStack{
                    HStack{
                        Button(action: {
                            // What to perform
                        }) { GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }
                        Spacer()
                    }.padding()
                    HStack{
                        GuestJiniTitleText(title: "Forgot Password?")
                        Spacer()
                    }.padding(.horizontal)
                    HStack{
                        GuestJiniInformationText(information: "Please type the verification code sent to your mobile number ")
                    }.padding()
                    HStack{
                        GuestJiniErrorText(message:"You have Entered Wrong OTP ")
                    }.padding()
                    HStack{
                        Spacer()
                        GuestJiniInformationText(information: "00:59")
                    }.padding(.horizontal,35)
                    HStack{
                        Spacer()
                        GuestJiniRegularTextBox(placeHolderText: "", text: self.$OTPDigit1)
                            .padding(.horizontal, 20)
                        GuestJiniRegularTextBox(placeHolderText: "", text: self.$OTPDigit2)
                            .padding(.horizontal, 20)
                        GuestJiniRegularTextBox(placeHolderText: "", text: self.$OTPDigit3)
                            .padding(.horizontal, 20)
                        GuestJiniRegularTextBox(placeHolderText: "", text: self.$OTPDigit3)
                            .padding(.horizontal, 20)
                        Spacer()
                    }
                    HStack{
                        Spacer()
                        Button(action: {
                            // What to perform
                        }) {
                            GuestJiniHyperlinkButtonText(buttonText: "Resend OTP")
                            
                        }
                        Spacer()
                        
                    }
                    HStack{
                        Spacer()
                        Button(action: {
                            // What to perform
                        }) {
                            GuestJiniButtonText(buttonText: "SUBMIT")
                            
                        }
                        Spacer()
                    }
                    HStack{
                        Spacer()
                        GuestJiniDescriptionText(description: "Don't Have account yet?")
                        Button(action: {
                            // What to perform
                        }) {
                            GuestJiniHyperlinkButtonText(buttonText: "Get one now")
                            
                        }.offset(x: -15)
                        Spacer()
                    }.padding()
                    
                }
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.all)
        }
    }
}

struct OTPVerification_Previews: PreviewProvider {
    static var previews: some View {
        OTPVerification()
    }
}
