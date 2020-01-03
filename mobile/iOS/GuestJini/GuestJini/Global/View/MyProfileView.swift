//
//  MyProfileView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct MyProfileView: View {
    @ObservedObject var viewRouter: ViewRouter
    @State private var showProfilePic = true
    @State private var showMobileNumber = false
    
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
                                    Toggle("",isOn: self.$showProfilePic) .padding()
                                        .frame(width: 75, height: 50, alignment: .center)
                                    
                                    Spacer()
                                }.padding(.bottom)
                                HStack{
                                    Text("NAME")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                HStack{
                                    Text("John Doe") .font(Fonts.RobotFieldText)
                                        .foregroundColor(Color("greyishBrownThree"))
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
                                    Toggle("Male",isOn: self.$showProfilePic) .font(Fonts.RobotFieldText)
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
                                    Toggle("33 Years",isOn: self.$showProfilePic) .font(Fonts.RobotFieldText)
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
                                    Toggle("9886393685",isOn: self.$showMobileNumber) .font(Fonts.RobotFieldText)
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
                                    Toggle("email@email.com",isOn: self.$showProfilePic) .font(Fonts.RobotFieldText)
                                        .foregroundColor(Color("greyishBrownThree"))
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
                                    Toggle("",isOn: self.$showMobileNumber) .font(Fonts.RobotFieldText)
                                        .foregroundColor(Color("greyishBrownThree"))
                                    Spacer()
                                }.padding(.horizontal)
                                Divider()
                            }
                            
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

struct MyProfileView_Previews: PreviewProvider {
    static var previews: some View {
        MyProfileView(viewRouter: ViewRouter())
    }
}
