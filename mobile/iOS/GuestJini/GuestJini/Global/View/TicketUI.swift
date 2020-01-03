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
    @State var ticketSubject:String = ""
    @State var ticketNarration:String = ""
    @State var hasSubject:Bool = true
    @State var hasNarration: Bool = true
    @State private var showSuccess = false
    @State private var showFailure = false

    @ObservedObject var ticketSaveService:TicketSaveService
      
    
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketSaveService = TicketSaveService(viewRouter: viewRouter)
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
                                let ticket:Ticket = Ticket();
                                ticket.ticketTitle = self.ticketSubject
                                ticket.ticketNarration = self.ticketNarration
                                self.ticketSaveService.saveTicket(ticketData: ticket) { (response) in
                                    if(response.success == true){
                                        self.showSuccess = true
                                        self.viewRouter.currentPage = ViewRoutes.TICKET_LIST
                                    }else{
                                        self.showFailure = true
                                    }
                                }
                            }
                        }) {
                           GuestJiniButtonText(buttonText: "SUBMIT")
                            
                        }.padding(.horizontal)
                            .actionSheet(isPresented: self.$showSuccess){
                                      ActionSheet(title: Text("Success"), message: Text("Your complaint has been submitted successfully"), buttons: [.default(Text("OK"))])
                                
                        }.actionSheet(isPresented: self.$showFailure){
                                      ActionSheet(title: Text("Failed!"), message: Text("We could not process your complaint, Please try after sometime!"), buttons: [.default(Text("OK"))])
                                
                        }
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
