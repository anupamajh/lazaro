//
//  TicketAttachmentList.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

/*
 .sheet(isPresented: self.$videoPickerViewModel.isPresented) {
     VideoPickerView(model: self.$videoPickerViewModel, isCamera: self.$isCamera)
 }
 .onReceive(self.videoPickerViewModel.pickedVideoSubject) { (playerView: PlayerView) -> Void in
     withAnimation {
         self.videoView = playerView
     }
 }
 
 if(self.ticketAttachmentDrawerAction.currentAction == 3){
     self.showVideoPicker = true
     self.isCamera = true
     self.videoPickerViewModel.isPresented = true
 }
 */

import SwiftUI

struct TicketAttachmentList: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketAttachmentDrawerAction: TicketAttachmentDrawerAction
    
    @State var menuOpen: Bool = false
    @State var viewTypeIdentifier: Int = 0
    
    @State var videoView:PlayerView = PlayerView(videoURL: nil)
    @State var showVideoPicker: Bool = false
    @State var isCamera: Bool = false
    @State var videoPickerViewModel = VideoPickerViewModel()
    
    var body: some View {
        GeometryReader { geometry in
            ZStack{
                VStack{
                    VStack{
                        HStack{
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.TICKET_UI
                            }) {
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                
                            }.padding(.horizontal)
                            
                            GuestJiniTitleText(title: "NEW TICKET")
                            Spacer()
                        }.padding()
                        HStack{
                            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. .")
                                .font(Fonts.RobotRegularText)
                                .foregroundColor(Color("brownishGrey"))
                            Spacer()
                        }.padding(.leading)
                        HStack{
                            Button(action:{
                                self.openMenu()
                            }){
                                GuestJiniButtonText(buttonText: "ATTACH FILES")
                            }
                            Spacer()
                        }.padding()
                        VStack{
                            Divider()
                        }.padding(.leading,35)
                            .padding(.top, 50)
                        VStack{
                            if(self.viewTypeIdentifier == 0){
                                EmptyView()
                            }else if(self.viewTypeIdentifier == 1){
                                EmptyView()
                            }else if(self.viewTypeIdentifier == 2){
                                EmptyView()
                            }else if(self.viewTypeIdentifier == 3){
                                self.videoView
                            }else if(self.viewTypeIdentifier == 4){
                                EmptyView()
                            }
                            Divider()
                        }.sheet(isPresented: self.$videoPickerViewModel.isPresented) {
                            VideoPickerView(model: self.$videoPickerViewModel, isCamera: self.$isCamera)
                        }
                        .onReceive(self.videoPickerViewModel.pickedVideoSubject) { (playerView: PlayerView) -> Void in
                            withAnimation {
                                self.videoView = playerView
                                self.ticketAttachmentDrawerAction.currentAction = 0
                                self.viewTypeIdentifier = 3
                                self.videoPickerViewModel.isPresented = false
                            }
                        }
                        HStack{
                            Spacer()
                            Button(action:{}){
                                GuestJiniButtonText(buttonText: "DONE")
                            }
                        }.padding()
                        
                    }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                        .padding()
                    
                    Divider()
                    GuestJiniBottomBar(viewRouter: self.viewRouter)
                }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                    .edgesIgnoringSafeArea(.vertical)
                
                
                TicketAttachmentDrawerMenu(width: 270,
                                           isOpen: self.menuOpen,
                                           menuClose: self.openMenu,
                                           ticketAttachmentDrawerAction: self.ticketAttachmentDrawerAction
                )
            }
        }
    }
    
    func openMenu() {
        if(self.ticketAttachmentDrawerAction.currentAction == 3){
            self.isCamera = true
            self.showVideoPicker = true
            self.videoPickerViewModel.isPresented = true
            self.viewTypeIdentifier = 0;
        }
        self.menuOpen.toggle()
    }
}

struct TicketAttachmentList_Previews: PreviewProvider {
    static var previews: some View {
        TicketAttachmentList(viewRouter: ViewRouter(),ticketAttachmentDrawerAction: TicketAttachmentDrawerAction())
    }
}
