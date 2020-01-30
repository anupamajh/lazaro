//
//  TicketUI.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 02/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketUI: View {
    @EnvironmentObject var ticketUIData: TicketUIModel
    
    @ObservedObject var viewRouter: ViewRouter
    @State var taskAttachment:[TaskAttachment] = []
    @State var ticketSubject:String = ""
    @State var ticketNarration:String = ""
    @State var hasSubject:Bool = true
    @State private var showSuccess = false
    @State private var showFailure = false
    @State var showInternetDown:Bool = false
    
    @ObservedObject var ticketSaveService:TicketSaveService
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketSaveService = TicketSaveService(viewRouter: viewRouter)
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    ZStack{
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
                                .resignKeyboardOnTapGesture()
                            HStack{
                                Text("Subject *").font(Fonts.RobotTitle)
                                Spacer()
                            }.padding()
                                .resignKeyboardOnTapGesture()
                            
                            GuestJiniRegularTextBox(placeHolderText: "Please write your subject here", text: self.$ticketSubject)
                                .padding(.horizontal)
                            if(!self.hasSubject){
                                HStack{
                                    GuestJiniFieldError()
                                    Spacer()
                                }.padding(.leading)
                                    .resignKeyboardOnTapGesture()
                            }else{
                                HStack{
                                    GuestJiniDescriptionText(description: "")
                                    Spacer()
                                }.padding(.leading).resignKeyboardOnTapGesture()
                            }
                            HStack{
                                Text("Complaint").font(Fonts.RobotTitle)
                                    .resignKeyboardOnTapGesture()
                                Spacer()
                                    .resignKeyboardOnTapGesture()
                                
                            }.padding()
                                .resignKeyboardOnTapGesture()
                            VStack{
                                MultilineTextView(text: self.$ticketNarration)
                                    .frame(width: geometry.size.width-45, height:100, alignment: .top)
                                    .padding(.all,10)
                                    .font(Fonts.RobotRegular)
                                    .background(Color.white
                                        .shadow(radius: 10))
                                    .cornerRadius(25)
                                    .overlay(
                                        RoundedRectangle(cornerRadius:5)
                                            .stroke(Color("veryLightPink"), lineWidth: 1)
                                )
                            }
                            VStack{
                                HStack{
                                    VStack{
                                        HStack{
                                            Text("Attachments(\(self.taskAttachment.count))")
                                                .font(Fonts.RobotSectionTitle)
                                                .foregroundColor(Color("brownishGrey"))
                                            Spacer()
                                        }.padding(.leading)
                                        HStack{
                                            Text("You can attach photos, video, documents or any other files here.")
                                                .font(Fonts.RobotRegularText)
                                                .foregroundColor(Color("brownishGrey"))
                                            Spacer()
                                        }.padding(.leading)
                                        
                                    }.padding()
                                    VStack{
                                        Button(action: {
                                            if(Connectivity.isConnectedToInternet()){
                                                self.ticketUIData.title = self.ticketSubject
                                                self.ticketUIData.narration = self.ticketNarration
                                                self.ticketUIData.ticketAttachments = self.taskAttachment
                                                self.viewRouter.taskAttachment = self.taskAttachment
                                                self.viewRouter.currentPage = ViewRoutes.TICKET_ATTACHMENT_LIST
                                            }else{
                                                self.showInternetDown = true
                                            }
                                        }){
                                            VStack{
                                                VStack{
                                                    Image(systemName: "chevron.right")
                                                        .resizable()
                                                        .foregroundColor(Color("greyishBrownFour"))
                                                }.frame(width: 7, height: 14, alignment: .center)
                                            }.padding()
                                        }
                                    }.padding()
                                } .cornerRadius(5)
                                    .background(Color("whiteThree")
                                        .shadow(radius: 10))
                                    .overlay(
                                        RoundedRectangle(cornerRadius:5)
                                            .stroke(Color("veryLightPink"), lineWidth: 1))
                            }.padding().resignKeyboardOnTapGesture()
                            HStack{
                                Spacer()
                                Button(action: {
                                    if(Connectivity.isConnectedToInternet()){
                                        if(self.ticketSubject.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
                                            self.hasSubject = false
                                        }else{
                                            self.hasSubject = true
                                        }
                                        if(self.hasSubject){
                                            let ticket:Ticket = Ticket();
                                            ticket.ticketTitle = self.ticketSubject
                                            ticket.ticketNarration = self.ticketNarration
                                            let ticketRequest = TaskTicketRequest()
                                            ticketRequest.taskTicket = ticket
                                            ticketRequest.taskAttachmentList = self.taskAttachment
                                            self.ticketSaveService.saveTicket(ticketData: ticketRequest) { (response) in
                                                if(response.success == true){
                                                    self.showSuccess = true
                                                    self.viewRouter.currentPage = ViewRoutes.TICKET_LIST
                                                }else{
                                                    self.showFailure = true
                                                }
                                            }
                                        }
                                    }else{
                                        self.showInternetDown = true
                                    }
                                }) {
                                    GuestJiniButtonText(buttonText: "SUBMIT")
                                    
                                }.padding(.horizontal)
                                    .actionSheet(isPresented: self.$showSuccess){
                                        ActionSheet(title: Text("Success"), message: Text("Your complaint has been submitted successfully"), buttons: [.default(Text("OK"))])
                                        
                                }.actionSheet(isPresented: self.$showFailure){
                                    ActionSheet(title: Text("Failed!"), message: Text("We could not process your complaint, Please try after sometime!"), buttons: [.default(Text("OK"))])
                                    
                                }
                            }.padding().resignKeyboardOnTapGesture()
                            
                        }
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
                    }.resignKeyboardOnTapGesture()
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                    .resignKeyboardOnTapGesture()
                    .onAppear(){
                        if(self.viewRouter.taskAttachment != nil){
                            self.taskAttachment = self.viewRouter.taskAttachment!
                        }
                        
                }.onAppear(){
                    self.ticketSubject = self.ticketUIData.title
                    self.ticketNarration = self.ticketUIData.narration
                    self.taskAttachment = self.ticketUIData.ticketAttachments
                }.resignKeyboardOnTapGesture()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
                .resignKeyboardOnTapGesture()
            
        }
    }
}

struct TicketUI_Previews: PreviewProvider {
    static var previews: some View {
        TicketUI(viewRouter: ViewRouter())
    }
}
