//
//  HomePage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

//9845792195

struct HomePage: View {
    @State var helpSearchText:String = ""
    @State var helpSearchCancel:Bool = false
    
    @ObservedObject var viewRouter: ViewRouter
    @EnvironmentObject var ticketUIData: TicketUIModel
    @State var showInternetDown:Bool = false
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                ZStack{
                    ScrollView{
                        HStack{
                            Spacer()
                            Text("We're here to help you")
                                .font(Fonts.RobotPageTitle)
                                .foregroundColor(Color("brownishGrey"))
                            Spacer()
                        }.padding(.bottom)
                        VStack{
                            VStack{
                                HStack{
                                    Text("FIND HELP")
                                        .font(Fonts.RobotSectionTitle)
                                        .foregroundColor(Color("greyishBrown"))
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
                                    }
                                    .padding(.horizontal)
                                    .navigationBarHidden(self.helpSearchCancel)
                                    //GuestJiniSearchView(searchText: self.$helpSearchText, showCancelButton: self.$helpSearchCancel)
                                }
                                HStack{
                                    Spacer()
                                    /* GuestJiniSubAction(actionText: "Popular Searches", systemImage: "chevron.down")
                                     .padding(.horizontal)*/
                                }.padding(.horizontal)
                                
                                VStack{
                                    HStack{
                                        Text("EXPLORE")
                                            .font(Fonts.RobotSectionTitle)
                                            .foregroundColor(Color("greyishBrown"))
                                        Spacer()
                                    }.padding()
                                    HStack{
                                        VStack{
                                            GuestJiniInformationText(information: "Explore our knowldgebase to find help for your problems")
                                        }
                                        VStack{
                                            Button(action: {
                                                if(Connectivity.isConnectedToInternet()){
                                                    self.viewRouter.searchText = self.helpSearchText
                                                    self.viewRouter.currentPage = ViewRoutes.FIND_HELP_PAGE
                                                }else{
                                                    self.showInternetDown = true
                                                }
                                            }) {
                                                GuestJiniRoundButtonSystemImage(systemImage: "chevron.right")
                                                
                                            }.padding(.horizontal)
                                        }
                                    }
                                    
                                }
                            }
                                
                            .frame(width: geometry.size.width-20, height: 280, alignment: .top)
                            .background(Color("whiteThree"))
                            .padding()
                            .cornerRadius(15)
                            .shadow(radius: 5)
                        }
                        VStack{
                            VStack{
                                HStack{
                                    Text("TICKETS")
                                        .font(Fonts.RobotSectionTitle)
                                        .foregroundColor(Color("greyishBrown"))
                                    Spacer()
                                }.padding()
                                VStack{
                                    GuestJiniInformationText(information: "You can submit your complaint, track progress and communicate with our team")
                                    HStack{
                                        Button(action: {
                                            if(Connectivity.isConnectedToInternet()){
                                                self.viewRouter.currentPage = ViewRoutes.TICKET_UI
                                                self.ticketUIData.narration = ""
                                                self.ticketUIData.title = ""
                                                self.ticketUIData.ticketAttachments = []
                                            }else{
                                                self.showInternetDown = true
                                            }
                                            
                                        }) {
                                            GuestJiniButtonText(buttonText: "CREATE TICKET")
                                            
                                        }.padding(.top)
                                        Spacer()
                                    }
                                    Divider()
                                }.padding(.horizontal)
                                
                                
                                
                                VStack{
                                    HStack{
                                        GuestJiniInformationText(information: "Go to your ticket(s)").padding(.horizontal)
                                        Spacer()
                                    }
                                    HStack{
                                        Button(action: {
                                            if(Connectivity.isConnectedToInternet()){
                                                self.viewRouter.currentPage = ViewRoutes.TICKET_LIST
                                            }else{
                                                self.showInternetDown = true
                                            }
                                        }) {
                                            GuestJiniRoundButtonSystemImage(systemImage: "chevron.right")
                                            
                                        }.padding(.horizontal)
                                        Spacer()
                                    }
                                    
                                }
                            }
                            .frame(width: geometry.size.width-20, height: 250, alignment: .top)
                            .background(Color("whiteThree"))
                            .padding()
                            .cornerRadius(15)
                            .shadow(radius: 5)
                        }
                    }
                    
                    /*
                     VStack{
                     VStack{
                     
                     VStack{
                     GuestJiniInformationText(information: "Lorem ipsum dolor sit amet consectetur adipiscing elit sodales primis, mollis viverra.")
                     .padding(.top)
                     HStack{
                     Image(systemName: "phone.fill")
                     .resizable()
                     .frame(width: 10, height: 10, alignment: .center)
                     Text("1800 0156 4237")
                     .font(Fonts.RobotButtonFont)
                     Spacer()
                     }.padding()
                     HStack{
                     GuestJiniInformationText(information: "(Monday - Friday, 09:00am - 06:00pm)")
                     Spacer()
                     }.padding()
                     }.padding(.horizontal)
                     
                     
                     }
                     .frame(width: geometry.size.width-20, height: 170, alignment: .top)
                     .background(Color("whiteThree"))
                     .padding()
                     .cornerRadius(15)
                     .shadow(radius: 5)
                     }
                     */
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
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
                .resignKeyboardOnTapGesture()
        }
    }
}

struct HomePage_Previews: PreviewProvider {
    static var previews: some View {
        HomePage(viewRouter: ViewRouter())
    }
}
