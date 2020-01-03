//
//  ForgotPassword.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ForgotPassword: View {
    @State var loginId:String = "";
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
                        GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies. ")
                    }.padding()
                    HStack{
                        GuestJiniErrorText(message:"This mobile number is not registered with GuestJini ")
                    }.padding()
                    GuestJiniRegularTextBox(placeHolderText: "Email or Mobile number", text: self.$loginId)
                        .padding(.horizontal)
                    
                    HStack{
                        Spacer()
                        Button(action: {
                            // What to perform
                        }) {
                            GuestJiniButtonText(buttonText: "RESET PASSWORD")
                            
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

struct ForgotPassword_Previews: PreviewProvider {
    static var previews: some View {
        ForgotPassword()
    }
}
