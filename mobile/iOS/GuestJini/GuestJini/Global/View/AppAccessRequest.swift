//
//  AppAccessRequest.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct AppAccessRequest: View {
    @ObservedObject var viewRouter: ViewRouter
    @State var email:String = "";
    @State var mobileNumber:String = "";
    @State var hasEmail: Bool = true;
    @State var hasMobile: Bool = true;
    @State var hasError: Bool = false;
    
    @State var showPopover: Bool = false
    @State var alertTitle:String = ""
    @State var alertBody:String = ""
    
    @State var isAnimating: Bool = false
    @State var showInternetDown:Bool = false
    
    var appAccessRequestService:AppAccessRequestService = AppAccessRequestService()
    
    var body: some View {
        GeometryReader { geometry in
            
            ZStack{
                VStack{
                    HStack{
                        GuestJiniHeaderSmall()
                            .frame(width: geometry.size.width, height: geometry.size.height * 0.1, alignment: .center)
                            .background(Color("veryLightPink"))
                    }
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.LOGIN_PAGE
                        }) { GuestJiniButtonSystemImagePlain(imageName: "arrow.left").padding(.leading,2)
                            
                        }
                        GuestJiniTitleText(title: "CUSTOMER CARE")
                        Spacer()
                    }.padding()
                    VStack{
                        HStack{
                            
                            GuestJiniTitleText(title: "APP ACCESS REQUEST")
                            
                            Spacer()
                        }.padding()
                        HStack{
                            GuestJiniInformationText(information: "Experience like-minded co-living while you also perceive your passion and interests. ")
                        }.padding()
                        HStack{
                            if(self.hasError){
                                GuestJiniErrorText(message: "Invalid Login Credentials")
                            }
                            
                        }.frame(width: geometry.size.width, height: 30, alignment: .center)
                        HStack{
                            GuestJiniFieldLabel(labelText: "REGISTERED EMAIL")
                            Spacer()
                        }
                        GuestJiniRegularTextBox(placeHolderText:  "email@ddress.com", text: self.$email)
                            .padding(.horizontal)
                        if(!self.hasEmail){
                            GuestJiniFieldError()
                                .padding(.leading)
                        }else{
                            GuestJiniDescriptionText(description: "")
                                .padding(.leading)
                        }
                        HStack{
                            GuestJiniFieldLabel(labelText: "REGISTERED MOBILE NUMBER")
                            Spacer()
                        }
                        GuestJiniRegularTextBox(placeHolderText: "Mobile number", text: self.$mobileNumber)
                            .padding(.horizontal)
                        if(!self.hasMobile){
                            GuestJiniFieldError()
                                .padding(.leading)
                        }else{
                            GuestJiniDescriptionText(description: "")
                                .padding(.leading)
                        }
                        Button(action: {
                            if(Connectivity.isConnectedToInternet()){
                                self.hasError = false
                                if(self.email.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                    self.hasEmail = false
                                }else{
                                    self.hasEmail = true
                                }
                                
                                if(self.mobileNumber.trimmingCharacters(in: .whitespacesAndNewlines) == "" ){
                                    self.hasMobile = false;
                                }else{
                                    self.hasMobile = true
                                }
                                
                                if(self.hasEmail && self.hasMobile){
                                    self.isAnimating = true
                                    self.appAccessRequestService.requestAppAccess(email: self.email, mobile: self.mobileNumber) { (response) in
                                        self.isAnimating = false
                                        if(response.id != ""){
                                            self.alertTitle = "Success"
                                            self.alertBody = "Request has been sent to administrator, You will receive an activation email once details are verified."
                                            self.showPopover = true
                                        }else{
                                            self.hasError = true
                                        }
                                    }
                                }
                            }else{
                                self.showInternetDown = true
                            }
                            
                        }) { GuestJiniRoundButtonSystemImage(systemImage: "checkmark")
                        }
                        
                        
                    }
                    
                }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                    .edgesIgnoringSafeArea(.all)
                    .keyboardResponsive()
                
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
}

struct AppAccessRequest_Previews: PreviewProvider {
    static var previews: some View {
        AppAccessRequest(viewRouter: ViewRouter())
    }
}
