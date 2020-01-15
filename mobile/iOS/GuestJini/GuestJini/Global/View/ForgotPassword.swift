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
    @State var isAnimating: Bool = false
    
    @State var showPopover: Bool = false
    @State var alertTitle:String = ""
    @State var alertBody:String = ""
      
    var body: some View {
        GeometryReader { geometry in
            ZStack{
                VStack{
                    HStack{
                        GuestJiniHeaderLarge()
                            .frame(width: geometry.size.width, height: geometry.size.height * 0.30, alignment: .center)
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
                            GuestJiniInformationText(information: "Experience like-minded co-living while you also perceive your passion and interests.")
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
                                }else{
                                    self.isAnimating = true
                                    self.forgotPasswordService.resetPassword(UserName: self.loginId) { (response) in
                                        self.processResponse(userInfo: response)
                                    }
                                }
                            }) {
                                GuestJiniButtonText(buttonText: "RESET PASSWORD")
                                
                            }.disabled(self.isAnimating)
                            .alert(isPresented: self.$success) {
                                Alert(title: Text("Reset Password"), message: Text("An email is sent to you with the reset password link"), dismissButton: .default(Text("OK")))
                            }
                            Spacer()
                        }
                        HStack{
                            Spacer()
                            GuestJiniDescriptionText(description: "Don't Have account yet?")
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.APP_ACCESS_REQUEST_PAGE
                            }) {
                                GuestJiniHyperlinkButtonText(buttonText: "Get one now")
                            }.offset(x: -15)
                            Spacer()
                        }.padding()
                        
                    }
                }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                    .edgesIgnoringSafeArea(.all)
                if(self.isAnimating){
                    HStack{
                        Spacer()
                        ActivityIndicator(shouldAnimate: self.$isAnimating)
                        Spacer()
                    }
                }
                
                if(self.showPopover){
                    GuestJiniAlerBox(showAlert: self.$showPopover, alertTitle: self.$alertTitle, alertBody: self.$alertBody)
                }else{
                    GuestJiniAlerBox(showAlert: self.$showPopover, alertTitle: self.$alertTitle, alertBody: self.$alertBody).hidden()
                }
            }
        }
    }
    
    func processResponse(userInfo:UserInfo) -> Void {
        if(userInfo.id == ""){
            self.hasLoginError = true
            self.isAnimating = false
            self.alertTitle = "SUCCESS"
            self.alertBody = "A link has been sent to your email account to reset your password."
            self.showPopover = true
            
        }else{
            self.success = true
            self.isAnimating = true
            self.alertTitle = "FAILED"
            self.alertBody = "Please try again or contact administrtaor."
            self.showPopover = true
        }
    }
}

struct ForgotPassword_Previews: PreviewProvider {
    static var previews: some View {
        ForgotPassword(viewRouter: ViewRouter())
    }
}
