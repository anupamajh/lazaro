//
//  TicketList.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketList: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketListService:TicketListService
    @State private var shouldAnimate = true
    @State var ticketSearchText:String = ""
    @State var ticketSearchCancel:Bool = false
    @State var showInternetDown:Bool = false
    
    @ObservedObject var audioPlayer = AudioPlayer()
    
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketListService = TicketListService(viewRouter: viewRouter)
        UITableView.appearance().tableFooterView = UIView()
        
        // To remove all separators including the actual ones:
        UITableView.appearance().separatorStyle = .none
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            Connectivity.cancelAllRequests()
                            self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "MY TICKETS")
                        Spacer()
                    }.padding()
                    VStack{
                        HStack {
                            HStack {
                                TextField("search", text: self.$ticketSearchText, onEditingChanged: { isEditing in
                                    self.ticketSearchCancel = true
                                }, onCommit: {
                                    print("onCommit")
                                }).foregroundColor(.primary)
                                
                                Button(action: {
                                    self.ticketSearchText = ""
                                }) {
                                    Image(systemName: "xmark.circle.fill").opacity(self.ticketSearchText == "" ? 0 : 1)
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
                        .navigationBarHidden(self.ticketSearchCancel)
                        
                        /*HStack{
                         Spacer()
                         GuestJiniSubAction(actionText: "Popular Searches", systemImage: "chevron.down")
                         .padding(.horizontal)
                         }*/
                    }
                    VStack{
                        if(self.ticketListService.fetchComplete != true){
                            ActivityIndicator(shouldAnimate: self.$shouldAnimate)
                        }
                        if(self.ticketListService.ticketList.filter{($0.ticketTitle?.lowercased().contains(self.ticketSearchText.lowercased()))! || $0.ticketNarration.lowercased().contains(self.ticketSearchText.lowercased()) || self.ticketSearchText == ""}.count == 0){
                            if(self.ticketListService.fetchComplete == true){
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
                                        GuestJiniInformationText(information: "Sorry, we couldn’t find any content for “<search_keyword>”")
                                        Spacer()
                                    }
                                }.padding()
                            }
                            
                        }else{
                            List {
                                ForEach(self.ticketListService.ticketList.filter{
                                    ($0.ticketTitle?.lowercased().contains(self.ticketSearchText.lowercased()))! ||
                                        $0.ticketNarration.lowercased().contains(self.ticketSearchText.lowercased()) ||
                                        self.ticketSearchText == ""}) { ticket in
                                            Button(action: {
                                                self.viewRouter.primaryKey = ticket.id!
                                                self.viewRouter.currentPage = ViewRoutes.TICKET_VIEW
                                            }) {
                                                TicketRow(ticket: ticket)
                                            }
                                }
                            }
                        }
                    }.onAppear(){
                        if(!Connectivity.isConnectedToInternet()){
                            self.showInternetDown = true
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

struct TicketList_Previews: PreviewProvider {
    static var previews: some View {
        TicketList(viewRouter: ViewRouter())
    }
}
