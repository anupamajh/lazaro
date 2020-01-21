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
    @ObservedObject var ticketNotesSaveService:TicketNotesSaveService
    @ObservedObject var ticketNotesListService:TicketNotesListService
    @State private var shouldAnimate = true
    @State var showAttachmentList = false
    @State var ticketNotes:String = ""
    @State var savingTaskNote:Bool = false
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.ticketGetService = TicketGetService(viewRouter: viewRouter, ticketId: viewRouter.primaryKey)
        self.ticketNotesSaveService = TicketNotesSaveService(viewRouter: viewRouter)
        self.ticketNotesListService = TicketNotesListService(viewRouter: viewRouter, ticketId: viewRouter.primaryKey)
        UITableView.appearance().separatorStyle = .none
        
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
                        ScrollView{
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
                                        }.padding(.bottom, 3)
                                        HStack{
                                            Text(self.ticketGetService.ticket.creationTime!.convetToDateFromMySQLUTC())
                                                .font(Fonts.RobotSectionTitle)
                                                .foregroundColor(Color("brownishGrey"))
                                            Spacer()
                                        }.padding(.bottom, 15)
                                        
                                        HStack{
                                            Text("Ticket #")
                                                .font(Fonts.RobotRegularSmallText)
                                                .foregroundColor(Color("brownishGrey"))
                                            Spacer()
                                        }.padding(.bottom, 3)
                                        HStack{
                                            Text(self.ticketGetService.ticket.ticketNo!)
                                                .font(Fonts.RobotSectionTitle)
                                                .foregroundColor(Color("greyishBrown"))
                                            Spacer()
                                        }.padding(.bottom, 15)
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
                                }.padding(.bottom)
                                HStack{
                                    GuestJiniInformationText(information: self.ticketGetService.ticket.ticketNarration)
                                    Spacer()
                                }.padding(.bottom)
                                Divider()
                                HStack{
                                    VStack{
                                        HStack{
                                            Spacer()
                                            
                                            Text("ATTACHMENTS")
                                                .font(Fonts.RobotSectionTitle)
                                                .foregroundColor(Color("greyishBrownTwo"))
                                        }
                                        HStack{
                                            Spacer()
                                            if(self.ticketGetService.ticketResponse.taskAttachments != nil){
                                                Text("\( self.ticketGetService.ticketResponse.taskAttachments!.count ) Attachments")
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .foregroundColor(Color("brownGrey"))
                                            } else {
                                                Text("0 Attachments")
                                                    .font(Fonts.RobotRegularSmallText)
                                                    .foregroundColor(Color("brownGrey"))
                                            }
                                        }
                                    }.padding()
                                    Button(action:{
                                        self.showAttachmentList.toggle()
                                        /*
                                         .popover(isPresented:self.$showAttachmentList){
                                         HStack{
                                         Spacer()
                                         VStack{
                                         VStack{
                                         Text("Hello Attachment List")
                                         Divider()
                                         Text("Hello Attachment List")
                                         Divider()
                                         Text("Hello Attachment List")
                                         Divider()
                                         }
                                         
                                         }.background(Color("whiteTwo"))
                                         .shadow(radius: 10)
                                         .cornerRadius(5)
                                         .overlay(
                                         Rectangle()
                                         .stroke(Color("whiteTwo"), lineWidth: 1)
                                         )
                                         
                                         }
                                         }
                                         */
                                        
                                    }){
                                        GuestJiniRoundButtonSystemImage(systemImage: "paperclip")
                                    }
                                    
                                }
                                VStack{
                                    HStack{
                                        Text("ACTIVITY")
                                            .foregroundColor(Color("greyishBrownFour"))
                                            .font(Fonts.RobotSectionTitle)
                                        Spacer()
                                    }
                                    
                                }
                                VStack{
                                    ZStack{
                                        VStack{
                                            HStack{
                                                Text("You")
                                                    .foregroundColor(Color("warmGrey"))
                                                    .font(Fonts.RobotSectionTitle)
                                                Spacer()
                                            }
                                            VStack{
                                                MultilineTextView(text: self.$ticketNotes)
                                                    .frame(height:100, alignment: .top)
                                                    .padding(.all,10)
                                                    .font(Fonts.RobotRegular)
                                                    .background(Color.white)
                                                    .overlay(
                                                        RoundedRectangle(cornerRadius:5)
                                                            .stroke(Color("veryLightPink"), lineWidth: 1)
                                                )
                                            }
                                            HStack{
                                                Button(action: {
                                                    if(self.ticketNotes.trimmingCharacters(in: .whitespacesAndNewlines) != ""){
                                                        let taskNote:TaskNote = TaskNote()
                                                        taskNote.ticketId = self.viewRouter.primaryKey
                                                        taskNote.notes = self.ticketNotes
                                                        self.savingTaskNote = true
                                                        self.ticketNotesSaveService.saveTicket(taskNote: taskNote) { (response) in
                                                            self.savingTaskNote = false
                                                            self.ticketNotes = ""
                                                            self.ticketNotesListService.fetchComplete = false
                                                            self.ticketNotesListService.getNotesList(ticketId: self.viewRouter.primaryKey) { (response) in
                                                                self.ticketNotesListService.taskNotesList = response.taskNoteList!
                                                            }
                                                        }
                                                    }
                                                }){
                                                    GuestJiniButtonText(buttonText: "SUBMIT")
                                                }
                                                Spacer()
                                            }
                                        }.padding()
                                            .background(Color("paleGrey"))
                                        if(self.savingTaskNote){
                                            ActivityIndicator(shouldAnimate: self.$savingTaskNote)
                                        }
                                    }
                                    
                                }.padding(.top)
                                    .padding(.trailing)
                                    .padding(.bottom)
                                    .padding(.leading, 40)
                                    .cornerRadius(5)
                            }.padding()
                            
                            VStack{
                                List { ForEach(self.ticketNotesListService.taskNotesList){ taskNote in
                                        VStack{
                                            TaskNoteRow(ticketNote: taskNote)
                                            .listRowInsets(.init(top: 0, leading: 0, bottom: 0, trailing: 0))
                                                .edgesIgnoringSafeArea(.all)

                                            Divider()
                                                .padding(.bottom, 25)
                                            .padding(.leading, 40)
                                        }
                                    }
                                }.frame(minHeight: 200, maxHeight: .infinity)
                            }
                        }
                        
                    }
                    
                }.frame(width: geometry.size.width-30, height: geometry.size.height-85, alignment: .top)
                    .padding()
                    .resignKeyboardOnTapGesture()
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
