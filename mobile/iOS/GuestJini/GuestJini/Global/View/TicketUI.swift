//
//  TicketUI.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketUI: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketService:TicketService
    @State var ticketSubject:String = ""
    @State var ticketNarration:String = ""
    @State var hasSubject:Bool = true
    @State var hasNarration: Bool = true
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketService = TicketService(viewRouter: viewRouter)
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "NEW TICKET")
                        Spacer()
                    }.padding()
                    HStack{
                        Text("Subject").font(Fonts.RobotTitle)
                        Spacer()
                    }.padding()
                    
                    GuestJiniRegularTextBox(placeHolderText: "Please write your subject here", text: self.$ticketSubject)
                        .padding()
                    if(!self.hasSubject){
                        GuestJiniFieldError()
                            .padding(.leading)
                    }else{
                        GuestJiniDescriptionText(description: "")
                            .padding(.leading)
                    }
                    HStack{
                        Text("Complaint").font(Fonts.RobotTitle)
                        Spacer()
                                           
                    }.padding()
                    GuestJiniTextArea(placeHolderText: "Please write your message here", text: self.$ticketNarration).frame(width: geometry.size.width-45, height:100, alignment: .top)
                    .padding()
                    if(!self.hasNarration){
                        GuestJiniFieldError()
                            .padding(.leading)
                    }else{
                        GuestJiniDescriptionText(description: "")
                            .padding(.leading)
                    }
                    HStack{
                        Spacer()
                        Button(action: {
                            if(self.ticketSubject.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                self.hasSubject = false
                            }else{
                                self.hasSubject = true
                            }
                            if(self.ticketNarration.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                self.hasNarration = false
                            }else{
                                self.hasNarration = true
                            }
                            if(self.hasNarration && self.hasSubject){
                                
                            }
                        }) {
                           GuestJiniButtonText(buttonText: "SUBMIT")
                            
                        }.padding(.horizontal)
                    }.padding()
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct TicketUI_Previews: PreviewProvider {
    static var previews: some View {
        TicketUI(viewRouter: ViewRouter())
    }
}
