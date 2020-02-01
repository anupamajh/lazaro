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
    
    @State var isSubmitClicked:Bool = false
    @State var isTicketCreationSuccess:Bool = false
    @State var showTicketCreationSuccess:Bool = false
    
    @State var alertTitle:String = ""
    @State var alertBody:String = ""
    
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
                            if(self.viewRouter.returnPage == ""){
                                self.viewRouter.currentPage = ViewRoutes.TICKET_LIST
                            }else{
                                self.viewRouter.currentPage = self.viewRouter.returnPage
                            }
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        
                        GuestJiniTitleText(title: "NEW TICKET")
                        Spacer()
                    }.padding()
                    ZStack{
                        VStack{
                            ScrollView{
                                VStack{
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
                                    
                                }
                                VStack{
                                    HStack{
                                        Text("Complaint").font(Fonts.RobotTitle)
                                            .resignKeyboardOnTapGesture()
                                        Spacer()
                                            .resignKeyboardOnTapGesture()
                                        
                                    }.padding()
                                        .resignKeyboardOnTapGesture()
                                    VStack{
                                        MultilineTextView(text: self.$ticketNarration)
                                            .font(Fonts.RobotRegular)
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
                                        }
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
                                    }
                                    .cornerRadius(5)
                                    .background(Color("whiteThree")
                                    .shadow(radius: 10))
                                    .overlay(
                                        RoundedRectangle(cornerRadius:5)
                                            .stroke(Color("veryLightPink"), lineWidth: 1))
                                }.padding().resignKeyboardOnTapGesture()
                                
                                VStack{
                                    HStack{
                                        Spacer()
                                        Button(action: {
                                            if(!self.isSubmitClicked){
                                                self.isSubmitClicked = true
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
                                                            self.isSubmitClicked = false
                                                            if(response.success == true){
                                                                self.showSuccess = true
                                                                self.alertTitle = "SUCCESS"
                                                                self.alertBody = "Your complaint has been registered successfuly."
                                                                self.isTicketCreationSuccess = true
                                                                self.viewRouter.currentPage = ViewRoutes.TICKET_LIST
                                                            }else{
                                                                self.alertTitle = "FAILURE"
                                                                self.alertBody = "We had problem registering your complaint, Please Try after sometime."
                                                                self.isTicketCreationSuccess = true
                                                                self.showFailure = true
                                                            }
                                                        }
                                                    }else{
                                                        self.isSubmitClicked = false
                                                    }
                                                }else{
                                                    self.isSubmitClicked = false
                                                    self.showInternetDown = true
                                                }
                                            }
                                        }) {
                                            GuestJiniButtonText(buttonText: "SUBMIT")
                                        }.padding(.horizontal)
                                        
                                    }.padding().resignKeyboardOnTapGesture()
                                }
                            }
                        }.onAppear(){
                            self.ticketSubject = self.ticketUIData.title
                            self.ticketNarration = self.ticketUIData.narration
                            self.taskAttachment = self.ticketUIData.ticketAttachments
                        }
                        
                        
                        GeometryReader { _ in
                            EmptyView()
                        }
                        .background(Color.black.opacity(0.8))
                        .opacity(self.isTicketCreationSuccess ? 1.0 : 0.0)
                        if(self.isTicketCreationSuccess){
                            GuestJiniAlerBox(showAlert: self.$isTicketCreationSuccess, alertTitle: self.$alertTitle, alertBody: self.$alertBody)
                        }else{
                            GuestJiniAlerBox(showAlert: self.$isTicketCreationSuccess, alertTitle: self.$alertTitle, alertBody: self.$alertBody).hidden()
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
                        
                        if(self.isSubmitClicked){
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

struct TicketUI_Previews: PreviewProvider {
    static var previews: some View {
        TicketUI(viewRouter: ViewRouter())
    }
}
