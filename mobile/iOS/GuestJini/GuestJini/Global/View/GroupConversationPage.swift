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
    @ObservedObject var groupDetailService:GroupDetailService
    @ObservedObject var groupSaveConversationService:GroupSaveConversationService
    @ObservedObject var groupConversationListService:GroupConversationListService
    
    
    @State var message:String = ""
    @State var isConversationSaving: Bool = false
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.groupDetailService = GroupDetailService(viewRouter: viewRouter, groupId: viewRouter.primaryKey)
        self.groupSaveConversationService = GroupSaveConversationService(viewRouter: viewRouter)
        self.groupConversationListService = GroupConversationListService(viewRouter: viewRouter, groupId: viewRouter.primaryKey)
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.GROUP_LIST_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "INTEREST GROUPS")
                        Spacer()
                    }.padding()
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
                                                    .foregroundColor(Color("brownGrey"))
                                                    .font(Fonts.RobotButtonFont)
                                                    .bold()
                                            }else{
                                                Text("")
                                                    .foregroundColor(Color("brownGrey"))
                                                    .font(Fonts.RobotButtonFont)
                                                    .bold()
                                            }
                                            
                                            
                                            Spacer()
                                        }
                                    }
                                    Spacer()
                                    Button(action:{
                                        self.viewRouter.primaryKey = self.viewRouter.primaryKey
                                        self.viewRouter.currentPage = ViewRoutes.GROUP_DETAIL_PAGE
                                        self.viewRouter.returnPage = ViewRoutes.GROUP_CONVERSATION_PAGE
                                    }){
                                        GuestJiniRoundButtonSystemImage(systemImage: "info")
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
                            ScrollView{
                                if(self.groupConversationListService.fetchComplete){
                                    ForEach(self.groupConversationListService.groupConversationResponse.groupConversationList!){
                                        conversation in
                                        
                                        if(conversation.isItMe == 1){
                                            HStack{
                                                Spacer()
                                                MyTextCard(
                                                    sentDate: conversation.creationTime!.convetToDateFromMySQLUTC(),
                                                    sender: conversation.displayName!,
                                                    message: conversation.message!
                                                )
                                                
                                            }
                                        }else{
                                            HStack{
                                                OthersTextCard(
                                                    sentDate: conversation.creationTime!.convetToDateFromMySQLUTC(),
                                                    sender: conversation.displayName!,
                                                    message: conversation.message!
                                                )
                                                Spacer()
                                            }
                                        }
                                    }
                                }
                                
                            }.frame(maxWidth: .infinity, maxHeight:.infinity)
                                .background(Color("lightBlueGrey"))
                            HStack{
                                TextField("Write here..", text: self.$message)
                                Button(action:{
                                    self.isConversationSaving = true
                                    self.groupSaveConversationService.saveConversation(groupId: self.viewRouter.primaryKey, message: self.message) { (response) in
                                        self.isConversationSaving = false
                                        self.message = ""
                                        self.groupConversationListService.reload(groupId: self.viewRouter.primaryKey)
                                    }
                                }){
                                    Text("SEND")
                                        .font(Fonts.RobotButtonFont)
                                        .padding(.top, 10)
                                        .padding(.bottom, 10)
                                        .padding(.leading, 20)
                                        .padding(.trailing, 20)
                                        .foregroundColor(Color.white)
                                        .background(Color("aquaMarine"))
                                }
                            }.keyboardResponsive()
                        }
                        if(self.groupDetailService.fetchComplete != true){
                            ActivityIndicator(shouldAnimate: .constant(true))
                        }
                        if(self.isConversationSaving){
                            ActivityIndicator(shouldAnimate: .constant(true))
                        }
                    }.resignKeyboardOnTapGesture()
                    
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
