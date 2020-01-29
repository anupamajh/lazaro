//
//  GroupConversationPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GroupConversationPage: View {
    @ObservedObject var viewRouter: ViewRouter
    
    @State var message:String = ""
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.PEOPLE_LIST_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "INTEREST GROUPS")
                        Spacer()
                    }.padding()
                    VStack{
                        HStack{
                            VStack{
                                HStack{
                                    Text("Outdoor Adventure")
                                        .foregroundColor(Color("brownGrey"))
                                        .font(Fonts.RobotRegular)
                                    Spacer()
                                }
                                HStack{
                                    Text("CYCLING") .foregroundColor(Color("brownGrey"))
                                        .font(Fonts.RobotButtonFont)
                                        .bold()
                                    Spacer()
                                }
                            }
                            Spacer()
                            Button(action:{}){
                                GuestJiniRoundButtonSystemImage(systemImage: "info")
                            }
                        }
                        VStack{
                            GuestJiniInformationText(information: "Lorem ipsum dolor sit amet, consectetur adip elit.In tempor suscipit augue sit amet egestas")
                        }.padding(.top)
                            .foregroundColor(Color("brownGrey"))
                    }.padding()
                        .background(Color("whiteThree"))
                    ScrollView{
                        VStack{
                            HStack{
                                OthersTextCard(sentDate: "25-03-1982", sender: "Prasanna umar Pete", message: "Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies.")
                                Spacer()
                            }
                            HStack{
                                OthersTextCard(sentDate: "25-03-1982", sender: "Prasanna umar Pete", message: "Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies.")
                                Spacer()
                            }
                            HStack{
                                Spacer()
                                MyTextCard(sentDate: "25-03-1982", sender: "Prasanna umar Pete", message: "Lorem ipsum dolor sit amet, consectetur  adipiscing elit. Etiam erat sapien, ultricies.")
                                
                            }
                            
                        }.frame(maxHeight:.infinity)
                            .background(Color("lightBlueGrey"))
                    }.frame(maxWidth: .infinity, maxHeight:.infinity)
                        .background(Color("lightBlueGrey"))
                    HStack{
                        TextField("Write here..", text: self.$message)
                        Button(action:{}){
                            Text("SEND")
                                .font(Fonts.RobotButtonFont)
                                .padding(.top, 10)
                                .padding(.bottom, 10)
                                .padding(.leading, 20)
                                .padding(.trailing, 20)
                                .foregroundColor(Color.white)
                                .background(Color("aquaMarine"))
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

struct OthersTextCard:View {
    var sentDate:String = ""
    var sender:String = ""
    var message:String = ""
    
    var body: some View {
        VStack{
            VStack{
                HStack{
                    Text(self.sentDate)
                        .font(Fonts.RobotRegularSmallText)
                        .foregroundColor(Color("brownGrey"))
                    Spacer()
                }.padding(.horizontal)
                    .padding(.top)
                HStack{
                    Text(self.sender)
                        .font(Fonts.RobotFieldText)
                        .foregroundColor(Color("brownGrey"))
                        .bold()
                    Spacer()
                }.padding(.horizontal)
                HStack{
                    Text(self.message)
                        .font(Fonts.RobotRegular)
                        .foregroundColor(Color("brownGrey"))
                    Spacer()
                }.padding(.top)
                    .padding(.horizontal)
                    .padding(.bottom)
            }.frame(minWidth:250, maxWidth: 300)
                .background(Color.white)
            
        }.padding()
        
    }
}

struct MyTextCard:View {
    var sentDate:String = ""
    var sender:String = ""
    var message:String = ""
    
    var body: some View {
        VStack{
            VStack{
                HStack{
                    Spacer()
                    Text(self.sentDate)
                        .font(Fonts.RobotRegularSmallText)
                        .foregroundColor(Color("brownGrey"))
                    
                }.padding(.horizontal)
                    .padding(.top)
                HStack{
                    Spacer()
                    Text(self.sender)
                        .font(Fonts.RobotFieldText)
                        .foregroundColor(Color("brownGrey"))
                        .bold()
                    
                }.padding(.horizontal)
                HStack{
                    Spacer()
                    Text(self.message)
                        .font(Fonts.RobotRegular)
                        .foregroundColor(Color("brownGrey"))
                        .multilineTextAlignment(.trailing)
                    
                }.padding(.top)
                    .padding(.horizontal)
                    .padding(.bottom)
            }.frame(minWidth:250, maxWidth: 300)
                .background(Color.white)
            
        }.padding()
        
    }
}


struct GroupConversationPage_Previews: PreviewProvider {
    static var previews: some View {
        GroupConversationPage(viewRouter: ViewRouter())
    }
}
