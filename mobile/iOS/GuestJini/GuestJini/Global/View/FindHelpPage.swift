//
//  FindHelpPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct FindHelpPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var kbListService:KBListService
    @State var helpSearchText:String = ""
    @State private var shouldAnimate = true
    @State var helpSearchCancel:Bool = false
    @State var showInternetDown:Bool = false
    
    init(viewRouter:ViewRouter) {
        self.viewRouter = viewRouter
        self.kbListService = KBListService(viewRouter: viewRouter)
        self.helpSearchText = viewRouter.searchText;
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            ZStack{
                VStack{
                    VStack{
                        HStack{
                            Button(action: {
                                Connectivity.cancelAllRequests()
                                self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                            }) {
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                
                            }.padding(.horizontal)
                            
                            GuestJiniTitleText(title: "FIND HELP")
                            Spacer()
                        }.padding()
                        VStack{
                            HStack {
                                HStack {
                                    TextField("search", text: self.$helpSearchText, onEditingChanged: { isEditing in
                                        self.helpSearchCancel = true
                                    }, onCommit: {
                                        print("onCommit")
                                    }).foregroundColor(.primary)
                                    
                                    Button(action: {
                                        self.helpSearchText = ""
                                    }) {
                                        Image(systemName: "xmark.circle.fill").opacity(self.helpSearchText == "" ? 0 : 1)
                                    }
                                    Button(action: {
                                        
                                    }) {
                                        Image(systemName: "magnifyingglass")
                                    }.padding(.leading)
                                    
                                    
                                }
                                .padding(EdgeInsets(top: 8, leading: 6, bottom: 8, trailing: 15))
                                .foregroundColor(.secondary)
                                .background(Color.white)
                                .cornerRadius(20.0)
                                .overlay(
                                    RoundedRectangle(cornerRadius:20)
                                        .stroke(Color("veryLightPink"), lineWidth: 1)
                                )
                            }
                            .padding(.horizontal)
                            .navigationBarHidden(self.helpSearchCancel)
                            HStack{
                                Spacer()
                                /*GuestJiniSubAction(actionText: "Popular Searches", systemImage: "chevron.down")
                                 .padding(.horizontal)*/
                            }
                        }
                        VStack{
                            if(self.kbListService.fetchComplete != true){
                                ActivityIndicator(shouldAnimate: self.$shouldAnimate)
                            }
                            if(self.kbListService.kbList.filter{
                                ($0.authorName?.lowercased().contains(self.helpSearchText.lowercased()))! ||
                                    ($0.topicNarration?.lowercased().contains(self.helpSearchText.lowercased()))! ||
                                    self.helpSearchText.trimmingCharacters(in: .whitespacesAndNewlines) == ""
                            }.count == 0){
                                if(self.kbListService.fetchComplete == true){
                                    VStack{
                                        HStack{
                                            Spacer()
                                            Image("magnifying_glass_sorry")
                                                .resizable()
                                                .frame(width: 56, height: 56, alignment: .center)
                                            Spacer()
                                        }.padding()
                                        HStack{
                                            Spacer()
                                            GuestJiniTitleText(title: "No Results Found")
                                            Spacer()
                                        }
                                        HStack{
                                            Spacer()
                                            GuestJiniInformationText(information: "Sorry, we couldn’t find any content for “\(self.helpSearchText)”")
                                            Spacer()
                                        }
                                    }.padding()
                                }
                            }
                            List {
                                ForEach(
                                    
                                    self.kbListService.kbList.filter{
                                        ($0.authorName?.lowercased().contains(self.helpSearchText.lowercased()))! ||
                                            ($0.topicNarration?.lowercased().contains(self.helpSearchText.lowercased()))! ||
                                            self.helpSearchText.trimmingCharacters(in: .whitespacesAndNewlines) == ""
                                    }
                                    
                                ) { kb in
                                    Button(action: {
                                        if(Connectivity.isConnectedToInternet()){
                                            self.viewRouter.primaryKey = kb.id!
                                            self.viewRouter.currentPage = ViewRoutes.HELP_ARTICLE_PAGE
                                        }else{
                                            self.showInternetDown = true
                                        }
                                    }) {
                                        KBRow(
                                            kb: kb,
                                            getKbAuthorPicService: GetKBAuthorPicService(
                                                viewRouter: self.viewRouter,
                                                kbId: kb.id!
                                            ),
                                            viewRouter:self.viewRouter
                                        )
                                    }
                                }
                            }
                            
                        }
                    }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                        .padding()
                    Divider()
                    GuestJiniBottomBar(viewRouter: self.viewRouter)
                }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                    .edgesIgnoringSafeArea(.vertical)
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
            }.onAppear(){
                self.helpSearchText = self.viewRouter.searchText
                if(!Connectivity.isConnectedToInternet()){
                    self.kbListService.fetchComplete = true
                    Connectivity.cancelAllRequests()
                    self.showInternetDown = true
                }
            }
            
        }
    }
}

struct FindHelpPage_Previews: PreviewProvider {
    static var previews: some View {
        FindHelpPage(viewRouter: ViewRouter())
    }
}
