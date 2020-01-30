//
//  GroupDetailPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 24/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GroupDetailPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var groupDetailService:GroupDetailService
    @ObservedObject var groupSubscribeService:GroupSubscribeService
    
    @State var message:String = ""
    @State var shouldAnimate:Bool = false
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.groupDetailService = GroupDetailService(viewRouter: viewRouter, groupId: viewRouter.primaryKey)
        self.groupSubscribeService = GroupSubscribeService(viewRouter: viewRouter)
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.GROUP_CONVERSATION_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "INTEREST GROUPS")
                        Spacer()
                    }.padding()
                    ScrollView{
                        ZStack{
                            VStack{
                                VStack{
                                    HStack{
                                        VStack{
                                            HStack{
                                                if(self.groupDetailService.fetchComplete == true && self.groupDetailService.group.interestCategoryName != nil){
                                                    Text(self.groupDetailService.group.interestCategoryName!)
                                                        .foregroundColor(Color("brownGrey"))
                                                        .font(Fonts.RobotRegular)
                                                }else{
                                                    Text("")
                                                        .foregroundColor(Color("brownGrey"))
                                                        .font(Fonts.RobotRegular)
                                                }
                                                Spacer()
                                            }
                                            HStack{
                                                if(self.groupDetailService.fetchComplete == true && self.groupDetailService.group.name != nil){
                                                    Text(self.groupDetailService.group.name!)
                                                        .foregroundColor(Color("greyishBrownFour"))
                                                        .font(Fonts.RobotButtonFont)
                                                        .bold()
                                                }else{
                                                    Text("")
                                                        .foregroundColor(Color("greyishBrownFour"))
                                                        .font(Fonts.RobotButtonFont)
                                                        .bold()
                                                }
                                                
                                                
                                                Spacer()
                                            }
                                        }
                                        Spacer()
                                        if(self.groupDetailService.fetchComplete && self.groupDetailService.group.isSubscribed != 1){
                                            Button(action:{
                                                self.shouldAnimate = true
                                                self.groupSubscribeService.subscribe(groupId: self.viewRouter.primaryKey) { (response) in
                                                    self.shouldAnimate = false;
                                                    self.groupDetailService.fetchComplete = false
                                                    self.groupDetailService.reload(groupId: self.viewRouter.primaryKey)
                                                    
                                                }
                                            }){
                                                GuestJiniButtonText(buttonText: "SUBSCRIBE")
                                            }
                                        }
                                    }
                                    HStack{
                                        VStack{
                                            
                                            if(self.groupDetailService.fetchComplete == true && self.groupDetailService.group.description != nil){
                                                GuestJiniInformationText(information:  self.groupDetailService.group.description!)
                                            }else{
                                                GuestJiniInformationText(information:  "")
                                                
                                            }
                                            
                                        }
                                        Spacer()
                                    }.padding(.top)
                                        .foregroundColor(Color("brownGrey"))
                                }.padding()
                                    .background(Color("whiteThree"))
                                VStack{
                                    VStack{
                                        HStack{
                                            VStack{
                                                Image(systemName: "desktopcomputer")
                                                    .resizable()
                                                    .padding(.all, 11)
                                            }.frame(width: 40, height: 40, alignment: .center)
                                                .foregroundColor(Color.white)
                                                .background(Color("brownGrey"))
                                                .clipped()
                                                .clipShape(Circle())
                                                .shadow(radius: 5)
                                                .padding()
                                            VStack{
                                                HStack{
                                                    Text("Created By")
                                                        .foregroundColor(Color("brownGrey"))
                                                        .font(Fonts.RobotRegularSmallText)
                                                    Spacer()
                                                }
                                                HStack{
                                                    Text("SYSTEM")
                                                        .font(Fonts.RobotRegular)
                                                        .bold()
                                                        .foregroundColor(Color("greyishBrownFour"))
                                                    Spacer()
                                                }
                                                HStack{
                                                    Text("25-03-1981").foregroundColor(Color("brownGrey"))
                                                        .font(Fonts.RobotRegularSmallText)
                                                    Spacer()
                                                }
                                            }
                                        }.padding()
                                    }.background(Color("whiteThree"))
                                    VStack{
                                        HStack{
                                            if(self.groupDetailService.fetchComplete == true && self.groupDetailService.groupResponse.groupPeople != nil){
                                                if(self.groupDetailService.group.isSubscribed == 1){
                                                Text("\(self.groupDetailService.groupResponse.groupPeople!.count + 1) members")
                                                    .foregroundColor(Color("brownGrey"))
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .bold()
                                                }else{
                                                    Text("\(self.groupDetailService.groupResponse.groupPeople!.count) members")
                                                    .foregroundColor(Color("brownGrey"))
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .bold()
                                                }
                                            }else{
                                                Text("0 members")
                                                    .foregroundColor(Color("brownGrey"))
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .bold()
                                            }
                                            VStack{
                                                Divider()
                                            }
                                        }
                                        if(self.groupDetailService.fetchComplete && self.groupDetailService.group.isSubscribed == 1){
                                            GroupDetailPeopleCard(
                                                name: "YOU",
                                                joinedDate: self.groupDetailService.group.subscribedDate!.convetToDateFromMySQLUTC(),
                                                imageURL: ""
                                            )
                                        }
                                        if(self.groupDetailService.fetchComplete == true){
                                            ForEach(self.groupDetailService.groupResponse.groupPeople!){ people in
                                                GroupDetailPeopleCard(
                                                    name: people.displayName!,
                                                    joinedDate: people.creationTime!.convetToDateFromMySQLUTC(),
                                                    imageURL: (people.logoPath == nil) ? "" : people.logoPath!
                                                )
                                            }
                                        }
                                        
                                    }
                                }.padding()
                            }
                            if(self.groupDetailService.fetchComplete != true){
                                ActivityIndicator(shouldAnimate: .constant(true))
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

struct GroupDetailPeopleCard:View{
    var name:String = ""
    var joinedDate:String  = ""
    var imageURL:String  = ""
    var body: some View {
        VStack{
            HStack{
                VStack{
                    VStack{
                        Image(systemName: "person")
                            .resizable()
                            .padding(.all, 11)
                    }.frame(width: 40, height: 40, alignment: .center)
                        .foregroundColor(Color.white)
                        .background(Color("bland"))
                        .clipped()
                        .clipShape(Circle())
                        .shadow(radius: 5)
                        .padding()
                }
                VStack{
                    HStack{
                        Text(self.name)
                            .font(Fonts.RobotRegular)
                            .bold()
                            .foregroundColor(Color("brownishGrey"))
                        Spacer()
                    }
                    HStack{
                        Text("Since \(self.joinedDate)")
                            .font(Fonts.RobotRegularSmallText)
                            .foregroundColor(Color("brownGrey"))
                        Spacer()
                    }
                    
                }
            }
            VStack{
                Divider()
                    .padding(.leading)
            }
        }
    }
}

struct GroupDetail_page_Previews: PreviewProvider {
    static var previews: some View {
        GroupDetailPage(viewRouter:ViewRouter())
    }
}
