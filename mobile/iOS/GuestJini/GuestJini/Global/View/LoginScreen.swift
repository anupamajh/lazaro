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
    
    @State private var shwLoginSuccess = false
      
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                HStack{
                    GuestJiniHeaderLarge()
                        .frame(width: geometry.size.width, height: geometry.size.height * 0.25, alignment: .center)
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
                   GuestJiniRegularTextBox(placeHolderText: "email@ddress.com", text: self.$loginId)
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
                            // What to perform
                        }) {
                            GuestJiniHyperlinkButtonText(buttonText: "Forgot Password?")
                            
                        }
                    }
                    HStack{
                        Spacer()
                        Button(action: {
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
                                //MARK - Implement Login logic here
                                self.loginService.performLogin(UserName: self.loginId, Password: self.password) { (response) in
                                    self.processLogin(authData: response)
                                }
                                
                            }
                        }) {
                            GuestJiniButtonText(buttonText: "Login")
                            
                        }
                        .alert(isPresented: self.$shwLoginSuccess) {
                            Alert(title: Text("Important message"), message: Text("Wear sunscreen"), dismissButton: .default(Text("Got it!")))
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
    
    func processLogin(authData:AuthData) -> Void {
        if(authData.access_token == ""){
            self.hasLoginError = true;
        }else{
            self.hasLoginError = false
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


