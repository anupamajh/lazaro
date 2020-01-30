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
    @ObservedObject var groupListService:GroupListService
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.groupListService = GroupListService(viewRouter: viewRouter,groupType: viewRouter.groupType)
        UITableView.appearance().tableFooterView = UIView()
        UITableView.appearance().separatorStyle = .none
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LANDING_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "GROUPS")
                        Spacer()
                    }.padding()
                    
                    if(self.groupListService.fetchComplete == true){
                        ScrollView{
                            ForEach(self.groupListService.groupResponse.groupList!){ group in
                                VStack{
                                    Button(action:{
                                        if(group.isSubscribed != 1){
                                            self.viewRouter.primaryKey = group.id!
                                            self.viewRouter.currentPage = ViewRoutes.GROUP_DETAIL_PAGE
                                        }else{
                                            self.viewRouter.primaryKey = group.id!
                                            self.viewRouter.currentPage = ViewRoutes.GROUP_CONVERSATION_PAGE
                                        }
                                    }){
                                        GroupListSubscribedCard(
                                            groupCategory:group.interestCategoryName!,
                                            title: group.name!,
                                            description: group.description!
                                        )
                                    }
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
        }.padding()
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
