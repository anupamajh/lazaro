//
//  LoginScreen.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct LoginScreen: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var loginService = LoginService()
    
    @State var loginId:String = "";
    @State var password:String = "";
    @State var hasLoginError: Bool = false;
    @State var hasEmail: Bool = true;
    @State var hasPassword: Bool = true;
    @State var isAnimating:Bool = false;
    @State var showInternetDown:Bool = false
    @State private var shwLoginSuccess = false
    
    
    var body: some View {
        GeometryReader { geometry in
            ZStack(alignment: .center){
                VStack{
                    HStack{
                        GuestJiniHeaderLarge()
                            .frame(width: geometry.size.width, height: geometry.size.height * 0.30, alignment: .center)
                            .background(Color("veryLightPink"))
                    }
                    VStack(alignment:.leading){
                        HStack{
                            GuestJiniTitleText(title: "Login")
                            Spacer()
                        }.padding()
                        HStack{
                            if(self.hasLoginError){
                                
                                GuestJiniErrorText(message: "Invalid Login Credentials").padding()
                            }
                            
                        }.frame(width: geometry.size.width, height: 30, alignment: .center)
                        
                        GuestJiniRegularTextBox(placeHolderText: "email@address.com", text: self.$loginId)
                            .padding(.horizontal)
                        if(!self.hasEmail){
                            GuestJiniFieldError()
                                .padding(.leading)
                        }else{
                            GuestJiniDescriptionText(description: "")
                                .padding(.leading)
                        }
                        
                        GuestJiniSecureTextBox(placeHolderText: "Password", text: self.$password)
                            .padding(.horizontal)
                        if(!self.hasPassword){
                            GuestJiniFieldError()
                                .padding(.leading)
                        }else{
                            GuestJiniDescriptionText(description: "")
                                .padding(.leading)
                        }
                        
                        HStack{
                            Spacer()
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.FORGOT_PASSWORD_PAGE
                            }) {
                                GuestJiniHyperlinkButtonText(buttonText: "Forgot Password?").padding(.trailing, 25)
                            }
                        }
                        HStack{
                            Spacer()
                            Button(action: {
                                if(Connectivity.isConnectedToInternet()){
                                    if(self.loginId.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                        self.hasEmail = false
                                    }else{
                                        self.hasEmail = true
                                    }
                                    if(self.password.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                        self.hasPassword = false
                                    }else{
                                        self.hasPassword = true
                                    }
                                    if(self.hasPassword && self.hasEmail){
                                        self.isAnimating = true
                                        
                                        self.loginService.performLogin(UserName: self.loginId, Password: self.password) { (response) in
                                            self.processLogin(authData: response)
                                        }
                                        
                                    }
                                }else{
                                    self.showInternetDown = true
                                }
                            }) {
                                GuestJiniButtonText(buttonText: "Login")
                                
                            }.disabled(self.isAnimating)
                                .alert(isPresented: self.$shwLoginSuccess) {
                                    Alert(title: Text("Important message"), message: Text("Wear sunscreen"), dismissButton: .default(Text("Got it!")))
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
                            }.offset(x: -20)
                            Spacer()
                        }.padding()
                    }.resignKeyboardOnTapGesture()
                        .keyboardResponsive()
                    
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
                .opacity(self.showInternetDown ? 1.0 : 0.0)
                if(self.showInternetDown){
                    GuestJiniAlerBox(showAlert: self.$showInternetDown, alertTitle: .constant("Oops!"), alertBody: .constant("Looks like internet connectivity is weak or not available!"))
                }else{
                    GuestJiniAlerBox(showAlert: self.$showInternetDown, alertTitle: .constant("Oops!"), alertBody: .constant("Looks like internet connectivity is weak or not available!")).hidden()
                }
                
            }.resignKeyboardOnTapGesture()
            
        }
        
    }
    
    func processLogin(authData:AuthData) -> Void {
        if(authData.access_token == ""){
            self.hasLoginError = true
            self.isAnimating = false
        }else{
            self.hasLoginError = false
            self.isAnimating = false
            UserDefaults.standard.set(true, forKey: "isLoggedIn")
            UserDefaults.standard.set(authData.access_token, forKey: "access_token")
            UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
            UserDefaults.standard.set(authData.token_type, forKey: "token_type")
            UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
            
            self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
        }
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen(viewRouter: ViewRouter())
    }
}


