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
    @State var showInternetDown:Bool = false
    
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
                                GuestJiniErrorText(message:"This email or mobile number is not registered with GuestJini ")
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
                                if(Connectivity.isConnectedToInternet()){
                                    if(self.loginId.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                        self.hasEmail = false
                                    }else{
                                        self.isAnimating = true
                                        self.forgotPasswordService.resetPassword(UserName: self.loginId) { (response) in
                                            self.processResponse(userInfo: response)
                                        }
                                    }
                                }else{
                                    self.showInternetDown = true
                                }
                            }) {
                                GuestJiniButtonText(buttonText: "RESET PASSWORD")
                                
                            }.disabled(self.isAnimating)
                            
                            Spacer()
                        }
                        HStack{
                            Spacer()
                            GuestJiniDescriptionText(description: "Don't Have account yet?")
                            Button(action: {
                                self.hasLoginError = false
                                self.viewRouter.currentPage = ViewRoutes.APP_ACCESS_REQUEST_PAGE
                            }) {
                                GuestJiniHyperlinkButtonText(buttonText: "Get one now")
                            }.offset(x: -15)
                            Spacer()
                        }.padding()
                        
                    }
                }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                    .edgesIgnoringSafeArea(.all)
                    .resignKeyboardOnTapGesture()
                if(self.isAnimating){
                    HStack{
                        Spacer()
                        ActivityIndicator(shouldAnimate: self.$isAnimating)
                        Spacer()
                    }
                }
                GeometryReader { _ in
                                       EmptyView()
                                   }
                                   .background(Color.black.opacity(0.8))
                                   .opacity(self.showPopover ? 1.0 : 0.0)
                if(self.showPopover){
                   
                    GuestJiniAlerBox(showAlert: self.$showPopover, alertTitle: self.$alertTitle, alertBody: self.$alertBody)
                }else{
                    GuestJiniAlerBox(showAlert: self.$showPopover, alertTitle: self.$alertTitle, alertBody: self.$alertBody).hidden()
                }
                GeometryReader { _ in
                    EmptyView()
                }
                .background(Color.black.opacity(0.8))
                .opacity(self.showInternetDown ? 1.0 : 0.0)
                if(self.showInternetDown){
                    GuestJiniAlerBox(showAlert: self.$showInternetDown, alertTitle: .constant("Oops!"), alertBody: .constant("Looks like internet connectivity is weak or not available!"))
                }else{
                    GuestJiniAlerBox(showAlert: self.$showInternetDown, alertTitle: .constant("Oops!"), alertBody: .constant("Looks like internet connectivity is weak or not available!")).hidden()
                }
            }.resignKeyboardOnTapGesture()
        }
    }
    
    func processResponse(userInfo:ForgotPasswordResponse) -> Void {
        if(userInfo.id == ""){
            self.hasLoginError = true
            self.isAnimating = false
            self.alertTitle = "FAILED"
            self.alertBody = "We could not process your request, Kindly try after sometime."
            self.showPopover = true
        }else{
            self.success = true
            self.isAnimating = false
            self.alertTitle = "SUCCESS"
            self.alertBody = "A link has been sent to your email account to reset your password."
            self.showPopover = true
        }
    }
}

struct ForgotPassword_Previews: PreviewProvider {
    static var previews: some View {
        ForgotPassword(viewRouter: ViewRouter())
    }
}
