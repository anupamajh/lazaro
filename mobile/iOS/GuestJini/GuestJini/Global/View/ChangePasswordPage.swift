//
//  ChangePasswordPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct ChangePasswordPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var changePasswordService:ChangePasswordService
    
    @State var currentPassword:String = ""
    @State var newPassword:String = ""
    @State var confirmPassword:String = ""
    
    @State var hasCurrentPasswordError:Bool = false
    @State var hasNewPasswordError:Bool = false
    @State var hasConfirmPasswordError:Bool = false
    @State var hasError:Bool = false
    
    @State var showInternetDown:Bool = false
    @State private var showChangePasswordSuccess = false
    
    @State var showChangePasswordProgress:Bool = false
    
    @State var alertTitle:String = ""
    @State var alertBody:String = ""
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.changePasswordService = ChangePasswordService(viewRouter: viewRouter)
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.SETTINGS_VIEW
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "CHANGE PASSWORD")
                        Spacer()
                    }.padding()
                        .resignKeyboardOnTapGesture()
                    ZStack{
                        VStack{
                            
                            VStack{
                                GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies quis.")
                            }.padding(.vertical, 30)
                                .padding(.horizontal)
                            VStack{
                                VStack{
                                    HStack{
                                        Text("Current Password *").font(Fonts.RobotTitle)
                                            .foregroundColor(Color("greyishBrown"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    GuestJiniSecureTextBox(placeHolderText: "Current Password", text: self.$currentPassword)
                                        .padding(.horizontal)
                                    if(self.hasCurrentPasswordError){
                                        HStack{
                                            GuestJiniErrorText(message: "This field is required or Wrong Password.")
                                        }
                                    }
                                }
                                
                                VStack{
                                    HStack{
                                        Text("New Password *").font(Fonts.RobotTitle)
                                            .foregroundColor(Color("greyishBrown"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    GuestJiniSecureTextBox(placeHolderText: "New Password", text: self.$newPassword)
                                        .padding(.horizontal)
                                    if(self.hasNewPasswordError){
                                        HStack{
                                            GuestJiniErrorText(message: "This field is required. ")
                                        }
                                    }
                                    
                                }
                                VStack{
                                    HStack{
                                        Text("Confirm Password *").font(Fonts.RobotTitle)
                                            .foregroundColor(Color("greyishBrown"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    GuestJiniSecureTextBox(placeHolderText: "Confirm Password", text: self.$confirmPassword)
                                        .padding(.horizontal)
                                    if(self.hasConfirmPasswordError){
                                        HStack{
                                            GuestJiniErrorText(message: "This field is required or Passwords not matching. ")
                                        }
                                    }
                                    
                                }
                                VStack{
                                    HStack{
                                        Spacer()
                                        Button(action:{
                                            if(Connectivity.isConnectedToInternet()){
                                                self.hasError = false;
                                                self.showChangePasswordProgress = true
                                                if(self.currentPassword.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                                    self.hasCurrentPasswordError = true
                                                    self.hasError = true
                                                }else{
                                                    self.hasCurrentPasswordError = false
                                                }
                                                if(self.newPassword.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                                    self.hasNewPasswordError = true
                                                    self.hasError = true
                                                }else{
                                                    self.hasNewPasswordError = false
                                                }
                                                if(self.confirmPassword.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                                    self.hasConfirmPasswordError = true
                                                    self.hasError = true
                                                }else{
                                                    self.hasConfirmPasswordError = false
                                                }
                                                if(self.confirmPassword.trimmingCharacters(in: .whitespacesAndNewlines) != self.newPassword.trimmingCharacters(in: .whitespacesAndNewlines)){
                                                    self.hasConfirmPasswordError = true
                                                    self.hasError = true
                                                }else{
                                                    self.hasConfirmPasswordError = false
                                                }
                                                if(!self.hasError){ self.changePasswordService.changePassword(currentPassword: self.currentPassword, newPassword: self.newPassword) { (response) in
                                                        self.showChangePasswordProgress = false
                                                        if(response.success){
                                                            self.alertTitle = "SUCCESS"
                                                            self.alertBody = "Password changed successfully."
                                                            self.showChangePasswordSuccess = true;
                                                        }else{
                                                            self.alertTitle = "FAILURE"
                                                            self.alertBody = response.error
                                                            self.showChangePasswordSuccess = true;
                                                        }
                                                    }
                                                }
                                            }else{
                                                self.showInternetDown = true
                                            }
                                        }){
                                            GuestJiniButtonText(buttonText: "CHANGE PASSWORD")
                                        }
                                        Spacer()
                                    }
                                }.padding(.top, 35)
                            }.keyboardResponsive()
                        }
                        
                        GeometryReader { _ in
                            EmptyView()
                        }
                        .background(Color.black.opacity(0.8))
                        .opacity(self.showChangePasswordSuccess ? 1.0 : 0.0)
                        if(self.showChangePasswordSuccess){
                           GuestJiniAlerBox(showAlert: self.$showChangePasswordSuccess, alertTitle: self.$alertTitle, alertBody: self.$alertBody)
                        }else{
                            GuestJiniAlerBox(showAlert: self.$showChangePasswordSuccess, alertTitle: self.$alertTitle, alertBody: self.$alertBody).hidden()
                        }
                        
                        if(self.showChangePasswordProgress){
                            ActivityIndicator(shouldAnimate: .constant(true))
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
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                    .resignKeyboardOnTapGesture()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
                .resignKeyboardOnTapGesture()
        }
    }
}

struct ChangePasswordPage_Previews: PreviewProvider {
    static var previews: some View {
        ChangePasswordPage(viewRouter: ViewRouter())
    }
}
