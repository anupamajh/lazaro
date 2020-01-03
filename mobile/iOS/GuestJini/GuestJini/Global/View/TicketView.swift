//
//  TicketView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketView: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketGetService:TicketGetService
    @State private var shouldAnimate = true
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketGetService = TicketGetService(viewRouter: viewRouter, ticketId: viewRouter.primaryKey)
        
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.TICKET_LIST
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "MY TICKETS")
                        Spacer()
                    }.padding()
                    if(self.ticketGetService.ticket.ticketNo?.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                        ActivityIndicator(shouldAnimate: .constant(true))
                    }else{
                        VStack{
                            
                            HStack{
                                VStack{
                                    
                                    if(self.ticketGetService.ticket.ticketStatus == 3){
                                        Text("OPEN")
                                            .padding(.all,5)
                                            .background(Color("coral"))
                                            .foregroundColor(Color.white)
                                            .font(Fonts.RobotRegularText)
                                            .cornerRadius(5)
                                    }else if(self.ticketGetService.ticket.ticketStatus == 2){
                                        Text("STARTED")
                                            .padding(.all,5)
                                            .background(Color("squash"))
                                            .foregroundColor(Color.white)
                                            .font(Fonts.RobotRegularText)
                                            .cornerRadius(5)
                                    }else if(self.ticketGetService.ticket.ticketStatus == 1){
                                        Text("CLOSED")
                                            .padding(.all,5)
                                            .background(Color("blueyGrey"))
                                            .foregroundColor(Color.white)
                                            .font(Fonts.RobotRegularText)
                                            .cornerRadius(5)
                                    }else{
                                        Text("NEW")
                                            .padding(.all,5)
                                            .background(Color("coral"))
                                            .foregroundColor(Color.white)
                                            .font(Fonts.RobotRegularText)
                                            .cornerRadius(5)
                                    }
                                    
                                }.padding(.trailing)
                                Spacer()
                                VStack{
                                    HStack{
                                        Text("Submitted On")
                                            .font(Fonts.RobotRegularSmallText)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.bottom, 10)
                                    HStack{
                                        Text(self.ticketGetService.ticket.creationTime!)
                                            .font(Fonts.RobotRegularSmallText)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.top, 15)
                                    
                                    HStack{
                                        Text("Ticket #")
                                            .font(Fonts.RobotRegularSmallText)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }.padding(.bottom, 10)
                                    HStack{
                                        Text(self.ticketGetService.ticket.ticketNo!)
                                            .font(Fonts.RobotRegular)
                                            .foregroundColor(Color("greyishBrown"))
                                        Spacer()
                                    }.padding(.bottom, 10)
                                }.padding()
                            }
                        }
                        .padding()
                        .background(Color("whiteThree"))
                        .shadow(radius: 10)
                        .cornerRadius(5)
                        .overlay(
                            RoundedRectangle(cornerRadius:5)
                                .stroke(Color("veryLightPink"), lineWidth: 1)
                        )
                        VStack{
                            HStack{
                                Text(self.ticketGetService.ticket.ticketTitle!)
                                    .font(Fonts.RobotSectionTitle)
                                Spacer()
                            }
                            HStack{
                                GuestJiniInformationText(information: self.ticketGetService.ticket.ticketNarration)
                                Spacer()
                            }
                            
                        }.padding()
                    }
                }.frame(width: geometry.size.width-30, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
            
        }
    }
}

struct TicketView_Previews: PreviewProvider {
    static var previews: some View {
        TicketView(viewRouter: ViewRouter())
    }
}
