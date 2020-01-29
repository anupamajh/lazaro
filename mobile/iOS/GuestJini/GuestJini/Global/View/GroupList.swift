//
//  GroupList.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GroupList: View {
    @ObservedObject var viewRouter: ViewRouter
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    VStack{
                        Button(action:{
                            self.viewRouter.currentPage = ViewRoutes.GROUP_CONVERSATION_PAGE
                        }){
                            GroupListSubscribedCard(
                                groupCategory:"Outdoor Adventure",
                                title: "Cyclink",
                                description: "There is a group for every activity, hobby or topic. Connect with like minded people."
                            )
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            self.viewRouter.returnPage = ""
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                        }){
                            GroupListGroupCard(
                                groupCategory:"Tech",
                                title: "ROBOTICS",
                                description: "Explore groups created by the community members. Participate and catch all the action."
                            )
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            self.viewRouter.returnPage = ""
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                        }){
                            GroupListMatchingGroupCard(
                                groupCategory:"Outdoor Adventure",
                                title: "HIKING",
                                description: "Create and manage your own groups. Host parties, events or simply bond together."
                            )
                        }
                    }
                    
                    VStack{
                        Button(action:{
                            self.viewRouter.returnPage = ""
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                        }){
                            GroupLisGroupCardWithInformation(
                                groupCategory:"Outdoor Adventure",
                                title: "SKY DIVING",
                                description: "Create and manage your own groups. Host parties, events or simply bond together."
                            )
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

struct GroupListSubscribedCard: View {
    var groupCategory:String = ""
    var title:String = ""
    var description:String = ""
    var body: some View {
        VStack{
            VStack{
                HStack{
                    VStack{
                        Text("")
                    }.frame(minWidth: 25, maxWidth: 25, minHeight: 4, maxHeight: 4)
                        .background(Color("pastelRed"))
                        .overlay(
                            Rectangle()
                                .strokeBorder(
                                    style: StrokeStyle(
                                        lineWidth: 1,
                                        dash: [4]
                                    )
                            )
                                .foregroundColor(Color("pastelRed"))
                    ).padding(.top)
                        .padding(.leading)
                    Spacer()
                }
                HStack{
                    Text(self.groupCategory)
                        .font(Fonts.RobotRegular)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                    Spacer()
                }.padding(.leading)
                    .padding(.top, 5)
                HStack{
                    Text(self.title)
                        .font(Fonts.RobotButtonFont)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                    Spacer()
                }.padding(.leading)
                    .padding(.top, 5)
                HStack{
                    Text(self.description)
                        .font(Fonts.RobotRegularSmallText)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                        .multilineTextAlignment(.leading)
                    Spacer()
                }.padding()
            }.background(Color("whiteThree"))
                .shadow(radius: 10)
        }.padding(.all, 25)
    }
    
}

struct GroupListGroupCard: View {
    var groupCategory:String = ""
    var title:String = ""
    var description:String = ""
    var body: some View {
        VStack{
            VStack{
                HStack{
                    Text(self.groupCategory)
                        .font(Fonts.RobotRegular)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                    Spacer()
                }.padding(.leading)
                    .padding(.top, 5)
                HStack{
                    Text(self.title)
                        .font(Fonts.RobotButtonFont)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                    Spacer()
                }.padding(.leading)
                    .padding(.top, 5)
                HStack{
                    Text(self.description)
                        .font(Fonts.RobotRegularSmallText)
                        .bold()
                        .foregroundColor(Color("brownishGrey"))
                        .multilineTextAlignment(.leading)
                    Spacer()
                }.padding()
            }.background(Color("whiteThree"))
                .shadow(radius: 10)
        }.padding(.all, 25)
    }
    
}

struct GroupListMatchingGroupCard: View {
    var groupCategory:String = ""
    var title:String = ""
    var description:String = ""
    var body: some View {
        VStack{
            HStack{
                VStack{
                    HStack{
                        VStack{
                            HStack{
                                Text(self.groupCategory)
                                    .font(Fonts.RobotRegular)
                                    .bold()
                                    .foregroundColor(Color("brownishGrey"))
                                Spacer()
                            }.padding(.leading)
                                .padding(.top, 5)
                            HStack{
                                Text(self.title)
                                    .font(Fonts.RobotButtonFont)
                                    .bold()
                                    .foregroundColor(Color("brownishGrey"))
                                Spacer()
                            }.padding(.leading)
                                .padding(.top, 5)
                            HStack{
                                Text(self.description)
                                    .font(Fonts.RobotRegularSmallText)
                                    .bold()
                                    .foregroundColor(Color("brownishGrey"))
                                    .multilineTextAlignment(.leading)
                                Spacer()
                            }.padding()
                        }
                        VStack{
                            Button(action:{
                                
                            }){
                                GuestJiniButtonSystemImagePlain(imageName: "info.circle")
                            }
                        }.padding()
                    }
                    VStack{
                        HStack{
                           VStack{
                                Image(systemName: "heart")
                                   .resizable()
                                    .padding(.all, 11)
                               }.frame(width: 40, height: 40, alignment: .center)
                            .foregroundColor(Color.white)
                            .background(Color("bland"))
                            .clipped()
                            .clipShape(Circle())
                            .shadow(radius: 5)
                            Spacer()
                            
                            VStack{
                                Divider()
                                HStack{
                                Text("This group matches your interest.")
                                    .font(Fonts.RobotRegularSmallText)
                                    .foregroundColor(Color("brownGrey"))
                                    Spacer()
                                }
                            }.padding(.horizontal)
                        }.padding()
                        
                    }
                    
                }.background(Color("whiteThree"))
                    .shadow(radius: 10)
                
            }
        }.padding(.all, 25)
    }
    
}

struct GroupLisGroupCardWithInformation: View {
    var groupCategory:String = ""
    var title:String = ""
    var description:String = ""
    var body: some View {
        VStack{
            HStack{
                VStack{
                    HStack{
                        VStack{
                            HStack{
                                Text(self.groupCategory)
                                    .font(Fonts.RobotRegular)
                                    .bold()
                                    .foregroundColor(Color("brownishGrey"))
                                Spacer()
                            }.padding(.leading)
                                .padding(.top, 5)
                            HStack{
                                Text(self.title)
                                    .font(Fonts.RobotButtonFont)
                                    .bold()
                                    .foregroundColor(Color("brownishGrey"))
                                Spacer()
                            }.padding(.leading)
                                .padding(.top, 5)
                            HStack{
                                Text(self.description)
                                    .font(Fonts.RobotRegularSmallText)
                                    .bold()
                                    .foregroundColor(Color("brownishGrey"))
                                    .multilineTextAlignment(.leading)
                                Spacer()
                            }.padding()
                        }
                        VStack{
                            Button(action:{
                                
                            }){
                                GuestJiniButtonSystemImagePlain(imageName: "info.circle")
                            }
                        }.padding()
                    }
                }.background(Color("whiteThree"))
                    .shadow(radius: 10)
                
            }
        }.padding(.all, 25)
    }
    
}


struct GroupList_Previews: PreviewProvider {
    static var previews: some View {
        GroupList(viewRouter: ViewRouter())
    }
}
