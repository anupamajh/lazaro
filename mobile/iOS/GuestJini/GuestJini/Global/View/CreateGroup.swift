//
//  CreateGroup.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 01/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct CreateGroup: View {
    @ObservedObject var viewRouter: ViewRouter
    
    @ObservedObject var createGroupService:CreateGroupService
    @State  var groupName:String = ""
    @State  var groupDescription:String = ""
    
    @State var isSubmitted:Bool = false
    @State var showResult:Bool = false
    @State var hasGroupname:Bool = true
    @State var hasGroupDescription:Bool = true
    @State var hasError:Bool = false
    
    @State var showInternetDown:Bool = false
      
    @State var alertTitle:String = ""
    @State var alertBody:String = ""
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.createGroupService = CreateGroupService(viewRouter:viewRouter)
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.groupType = GroupTypes.GROUP_TYPE_MY_GENERATED
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LANDING_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "MY GROUPS")
                        Spacer()
                        if(self.viewRouter.groupType == GroupTypes.GROUP_TYPE_MY_GENERATED){
                            Button(action:{
                                self.viewRouter.currentPage = ViewRoutes.GROUP_CREATE_PAGE
                            }){
                                GuestJiniRoundButtonSystemImage(systemImage: "plus")
                            }
                        }
                    }.padding()
                    ZStack{
                        ScrollView{
                            VStack{
                                HStack{
                                    Text("NEW GROUP")
                                        .font(Fonts.RobotButtonFont)
                                        .foregroundColor(Color("greyishBrownTwo"))
                                    Spacer()
                                }
                                HStack{
                                    VStack{
                                        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dignissim felis et magna mattis finibus.")
                                            .font(Fonts.RobotRegularSmallText)
                                        .foregroundColor(Color("brownGrey"))
                                        .lineLimit(200)
                                            .multilineTextAlignment(.leading)
                                    }
                                    Spacer()
                                }
                            }.padding()
                            
                            VStack{
                                HStack{
                                    Text("GROUP NAME")
                                    .foregroundColor(Color("aquamarine"))
                                        .font(Fonts.RobotRegularButton)
                                    .bold()
                                    Spacer();
                                }
                                GuestJiniRegularTextBox(placeHolderText: "Apache Riders", text: self.$groupName)
                            }.padding()
                            VStack{
                                HStack{
                                    Text("GROUP DESCRIPTION")
                                    .foregroundColor(Color("aquamarine"))
                                        .font(Fonts.RobotRegularButton)
                                    .bold()
                                    Spacer();
                                }
                                MultilineTextView(text: self.$groupDescription)
                                    .font(Fonts.RobotRegular)
                                    .frame(width: geometry.size.width-45, height:100, alignment: .top)
                                    .padding(.all,10)
                                    .font(Fonts.RobotRegular)
                                    .background(Color.white
                                        .shadow(radius: 10))
                                    .cornerRadius(25)
                                    .overlay(
                                        RoundedRectangle(cornerRadius:5)
                                            .stroke(Color("veryLightPink"), lineWidth: 1)
                                )
                            }
                            
                            VStack{
                                HStack{
                                    Spacer()
                                    Button(action:{
                                        if(Connectivity.isConnectedToInternet()){
                                             self.hasError = false
                                            if(self.groupName.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                                self.hasGroupname = false
                                                self.hasError = true
                                            }else{
                                                self.hasGroupname = true
                                            }
                                            if(self.groupDescription.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                                self.hasGroupDescription = false
                                                 self.hasError = true
                                            }else{
                                                self.hasGroupDescription = true
                                            }
                                            if(!self.hasError){
                                                self.isSubmitted = true
                                                let groupModel = GroupModel()
                                                groupModel.name = self.groupName
                                                groupModel.description = self.groupDescription
                                                groupModel.groupType = GroupTypes.GROUP_TYPE_USER_GENERATED
                                                self.createGroupService.createGroup(group: groupModel) { (response) in
                                                    self.isSubmitted = false
                                                    if(response.success == true){
                                                        self.alertTitle = "SUCCESS"
                                                        self.alertBody = "Your group has been created successfuly."
                                                        self.showResult = true
                                                        self.viewRouter.groupType = GroupTypes.GROUP_TYPE_MY_GENERATED
                                                        self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                                                    }else{
                                                        self.alertTitle = "FAILURE"
                                                        self.alertBody = "We had problem creating your group, Please Try after sometime."
                                                        self.showResult = true
                                                    }
                                                }
                                            }
                                        }else{
                                            self.showInternetDown = true
                                        }
                                        
                                    }){
                                        GuestJiniButtonText(buttonText: "CREATE")
                                    }
                                    Spacer()
                                }
                            }
                        }
                        if(self.isSubmitted){
                            ActivityIndicator(shouldAnimate: .constant(true))
                        }
                        
                        GeometryReader { _ in
                            EmptyView()
                        }
                        .background(Color.black.opacity(0.8))
                        .opacity(self.showResult ? 1.0 : 0.0)
                        if(self.showResult){
                            GuestJiniAlerBox(showAlert: self.$showResult, alertTitle: self.$alertTitle, alertBody: self.$alertBody)
                        }else{
                            GuestJiniAlerBox(showAlert: self.$showResult, alertTitle: self.$alertTitle, alertBody: self.$alertBody).hidden()
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
                    }
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct CreateGroup_Previews: PreviewProvider {
    static var previews: some View {
        CreateGroup(viewRouter: ViewRouter())
    }
}
