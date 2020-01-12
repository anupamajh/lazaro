//
//  MyProfileView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct MyProfileView: View {
    @ObservedObject var userInfoService:UserInfoService = UserInfoService()
    @ObservedObject var userInfo:UserInfoService = UserInfoService()
    @ObservedObject var viewRouter: ViewRouter
    @State private var showProfilePic = false
    @State private var showGender = false
    @State private var showAge = false
    @State private var showEmail = false
       @State private var showPlaceofOrigin = false
    @State private var showMobileNumber = false
    @State private var myName = ""
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        UISwitch.appearance().onTintColor = UIColor(named: "aquaMarine")
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
                        
                        GuestJiniTitleText(title: "MY PROFILE")
                        Spacer()
                    }.padding()
                    if(self.userInfoService.userInfo.id != nil){
                        Group{
                            VStack{
                                Group{
                                    HStack{
                                        Spacer()
                                        Image(systemName: "person.crop.circle")
                                            .resizable()
                                            .frame(width: 60, height: 60, alignment: .center)
                                        Spacer()
                                    }.padding()
                                    HStack{
                                        Spacer()
                                        /*Toggle("",isOn: self.$showProfilePic) .padding()
                                            .frame(width: 75, height: 50, alignment: .center)*/
                                        
                                        Spacer()
                                    }.padding(.bottom)
                                    HStack{
                                        Text("NAME")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    HStack{
                                        if(self.userInfoService.userInfo.fullName == nil){
                                            Text("") .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }else{
                                            Text(self.userInfoService.userInfo.fullName!) .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                        Spacer()
                                    }.padding(.horizontal)
                                    Divider()
                                    HStack{
                                        Text("GENDER")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    HStack{
                                        Toggle("Male",isOn: self.$showGender) .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    Divider()
                                }
                                Group{
                                    HStack{
                                        Text("AGE")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    HStack{
                                        Toggle("",isOn: self.$showAge) .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    Divider()
                                    HStack{
                                        Text("MOBILE NUMBER")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    HStack{
                                        Toggle("",isOn: self.$showMobileNumber) .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    Divider()
                                    HStack{
                                        Text("EMAIL")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    HStack{
                                        if(self.userInfoService.userInfo.userName == nil){
                                            Toggle("",isOn: self.$showEmail) .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }else{
                                            Toggle(self.userInfoService.userInfo.userName!,isOn: self.$showEmail) .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                        Spacer()
                                    }.padding(.horizontal)
                                    Divider()
                                    
                                }
                                Group{
                                    HStack{
                                        Text("PLACE OF ORIGIN")
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    HStack{
                                        Toggle("",isOn: self.$showPlaceofOrigin).font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                        Spacer()
                                    }.padding(.horizontal)
                                    Divider()
                                }
                                
                            }
                        }
                    }else{
                        ActivityIndicator(shouldAnimate: .constant(true))
                    }
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
    
    func processUserInfo(userInfo:UserInfo) -> Void{
        self.myName = userInfo.fullName!
    }
}

struct MyProfileView_Previews: PreviewProvider {
    static var previews: some View {
        MyProfileView(viewRouter: ViewRouter())
    }
}
