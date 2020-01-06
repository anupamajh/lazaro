//
//  ForgotPassword.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ForgotPassword: View {
  
    @ObservedObject var forgotPasswordService = ForgotPasswordService()
    
    @ObservedObject var viewRouter: ViewRouter
    @State var loginId:String = "";
    @State var hasEmail: Bool = true;
    @State var hasLoginError: Bool = false;
    @State var success: Bool = false
    
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
                            self.viewRouter.currentPage = ViewRoutes.LOGIN_PAGE
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
                        if(self.hasLoginError){
                        
                        GuestJiniErrorText(message:"This mobile number is not registered with GuestJini ")
                        }
                    }.padding()
                    GuestJiniRegularTextBox(placeHolderText: "Email or Mobile number", text: self.$loginId)
                        .padding(.horizontal)
                    if(!self.hasEmail){
                        GuestJiniFieldError()
                            .padding(.leading)
                    }else{
                        GuestJiniDescriptionText(description: "")
                            .padding(.leading)
                    }
                    
                    HStack{
                        Spacer()
                        Button(action: {
                            if(self.loginId.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                self.hasEmail = false
                            }else{ self.forgotPasswordService.resetPassword(UserName: self.loginId) { (response) in
                                self.processResponse(userInfo: response)
                                }
                            }
                        }) {
                            GuestJiniButtonText(buttonText: "RESET PASSWORD")
                            
                        }.alert(isPresented: self.$success) {
                            Alert(title: Text("Reset Password"), message: Text("An email is sent to you with the reset password link"), dismissButton: .default(Text("OK")))
                        }
                        Spacer()
                    }
                    HStack{
                        Spacer()
                        /*GuestJiniDescriptionText(description: "Don't Have account yet?")
                        Button(action: {
                            // What to perform
                        }) {
                            GuestJiniHyperlinkButtonText(buttonText: "Get one now")
                            
                        }.offset(x: -15)*/
                        Spacer()
                    }.padding()
                    
                }
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.all)
        }
    }
    
    func processResponse(userInfo:UserInfo) -> Void {
        if(userInfo.id == nil){
            self.hasLoginError = true
        }else{
            self.success = true
        }
    }
}

struct ForgotPassword_Previews: PreviewProvider {
    static var previews: some View {
        ForgotPassword(viewRouter: ViewRouter())
    }
}
