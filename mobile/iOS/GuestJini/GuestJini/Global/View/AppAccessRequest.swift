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
                            self.appAccessRequestService.requestAppAccess(email: self.email, mobile: self.mobileNumber) { (response) in
                                debugPrint(response)
                                self.alertTitle = "Success"
                                self.alertBody = "A link has been sent to your email account to gain access to app."
                                self.showPopover = true
                            }
                        }
                        
                    }) { GuestJiniRoundButtonSystemImage(systemImage: "checkmark")
                    }
                   
                    
                }
                
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.all)
                
                if(self.showPopover){
                    GuestJiniAlerBox(showAlert: self.$showPopover, alertTitle: self.$alertTitle, alertBody: self.$alertBody)
                }else{
                    GuestJiniAlerBox(showAlert: self.$showPopover, alertTitle: self.$alertTitle, alertBody: self.$alertBody).hidden()
                }
            }
        }
    }
}

struct AppAccessRequest_Previews: PreviewProvider {
    static var previews: some View {
        AppAccessRequest(viewRouter: ViewRouter())
    }
}
