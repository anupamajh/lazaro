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
    @State var searchText:String = ""
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketService = TicketService()
      
    var body: some View {
        GeometryReader { geometry in
            VStack{
                ScrollView{
                    HStack{
                        Spacer()
                        Text("We're here to help you")
                            .font(Fonts.RobotPageTitle)
                            .foregroundColor(Color("brownishGrey"))
                        Spacer()
                    }.padding(.bottom)
                    /*
                    VStack{
                        VStack{
                            HStack{
                                Text("FIND HELP")
                                    .font(Fonts.RobotSectionTitle)
                                    .foregroundColor(Color("greyishBrown"))
                                Spacer()
                            }.padding()
                            VStack{
                                GuestJiniSearchBox(text: self.$searchText)
                            }.padding(.horizontal)
                            HStack{
                                Spacer()
                                GuestJiniSubAction(actionText: "Popular Searches", systemImage: "chevron.down")
                                    .padding(.horizontal)
                            }.padding(.horizontal)
                            
                            VStack{
                                HStack{
                                    Text("EXPLORE")
                                        .font(Fonts.RobotSectionTitle)
                                        .foregroundColor(Color("greyishBrown"))
                                    Spacer()
                                }.padding()
                                HStack{
                                    GuestJiniDescriptionText(description: "Lorem ipsum dolor sit amet consectetur adipiscing elit sodales primis, mollis viverra.")
                                    Button(action: {
                                        // What to perform
                                    }) {
                                        GuestJiniRoundButtonSystemImage(systemImage: "chevron.right")
                                        
                                    }.padding(.horizontal)
                                }
                                
                            }
                        }
                            
                        .frame(width: geometry.size.width-20, height: 280, alignment: .top)
                        .background(Color("whiteThree"))
                        .padding()
                        .cornerRadius(15)
                        .shadow(radius: 5)
                    }
                    */
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
                                        self.viewRouter.currentPage = ViewRoutes.TICKET_UI
                                        
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
                                        // What to perform
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
                }.frame(width: geometry.size.width, height: geometry.size.height-65, alignment: .top)
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.bottom)
        }
    }
}

struct HomePage_Previews: PreviewProvider {
    static var previews: some View {
        HomePage(viewRouter: ViewRouter())
    }
}
