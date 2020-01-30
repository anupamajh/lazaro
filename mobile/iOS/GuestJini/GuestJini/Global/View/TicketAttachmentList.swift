//
//  TicketAttachmentList.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 16/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//



import SwiftUI

struct TicketAttachmentList: View {
    @EnvironmentObject var ticketUIData: TicketUIModel

    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var ticketAttachmentDrawerAction: TicketAttachmentDrawerAction
    @ObservedObject var audioRecorder: AudioRecorder = AudioRecorder()
    
    var uploadTicketAttachmentService:UploadTicketAttachmentService = UploadTicketAttachmentService(viewRouter: ViewRouter())
    
   // @State var taskAttachments:[TaskAttachment] = []
    
    @State var menuOpen: Bool = false
    @State var viewTypeIdentifier: Int = 0
    
    @State var videoView:PlayerView = PlayerView(videoURL: nil)
    @State var showVideoPicker: Bool = false
    @State var isCamera: Bool = false
    @State var videoPickerViewModel = VideoPickerViewModel()
    @State var attachedImage:Image = Image(systemName: "camera")
    @State var showImagePicker: Bool = false
    @State var imagePickerViewModel = ImagePickerViewModel()
    @State var shouldAnimate:Bool = false
    @State var showAudioRecorderView: Bool = false
    @State var audioPlayerViewModel:AudioPlayerViewModel = AudioPlayerViewModel()
    @State var audioPlayerView:AudioPlayerView = AudioPlayerView(audioURL: nil)
    
    @State var showFilePickerView: Bool = false
    @State var filePickerViewModel:FilePickerViewModel = FilePickerViewModel()
    @State var filePickerUIView:FilePickerUIView = FilePickerUIView(fileURL: nil)
    
    @State var attachmentModel: AttachmentModel = AttachmentModel()
    
    init(viewRouter:ViewRouter, ticketAttachmentDrawerAction: TicketAttachmentDrawerAction) {
        self.viewRouter = viewRouter
        self.ticketAttachmentDrawerAction = ticketAttachmentDrawerAction
       
    }
    
    var body: some View {
        GeometryReader { geometry in
            ZStack{
                VStack{
                    VStack{
                        HStack{
                            Button(action: {
                                Connectivity.cancelAllRequests()
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
                            List {
                                ForEach(self.ticketUIData.ticketAttachments){ attachment in
                                    VStack{
                                    AttachmentRow(taskAttachment: attachment)
                                        
                                    }.padding(.vertical)
                                }.onDelete { (indexSet) in
                                    self.ticketUIData.ticketAttachments.remove(atOffsets: indexSet)
                                }
                            }
                            Divider()
                        }.background(EmptyView()
                            .sheet(isPresented: self.$videoPickerViewModel.isPresented) {
                                VideoPickerView(model: self.$videoPickerViewModel, isCamera: self.$isCamera)
                                    .onDisappear {
                                        self.ticketAttachmentDrawerAction.currentAction = 0
                                }
                        }
                        .onReceive(self.videoPickerViewModel.pickedVideoSubject) { (playerView: PlayerView) -> Void in
                            withAnimation {
                                self.videoView = playerView
                                self.ticketAttachmentDrawerAction.currentAction = 0
                                self.viewTypeIdentifier = 3
                                self.videoPickerViewModel.isPresented = false
                                self.attachmentModel.sourceURL = playerView.videoURL
                                self.attachmentModel.sourceData = self.videoPickerViewModel.sourceVideo
                                self.attachmentModel.fileName = self.videoPickerViewModel.fileName
                                self.shouldAnimate = true
                                if(self.attachmentModel.sourceData != nil){
                                    self.uploadTicketAttachmentService.uploadAttachmentURL(fileURL: self.attachmentModel.sourceData, fileName: self.attachmentModel.fileName) { (response) in
                                        if(response.success == true){
                                            self.ticketUIData.ticketAttachments.append(response.taskAttachment!)
                                        }
                                        self.shouldAnimate = false
                                    }
                                }
                            }
                            }
                        ).background(EmptyView()
                            
                            .sheet(isPresented: self.$imagePickerViewModel.isPresented) {
                                ImagePickerView(model: self.$imagePickerViewModel, isCamera: self.$isCamera)
                                    .onDisappear {
                                        self.ticketAttachmentDrawerAction.currentAction = 0
                                }
                        }
                        .onReceive(self.imagePickerViewModel.pickedImagesSubject) { (image: Image) -> Void in
                            withAnimation {
                                self.attachedImage = image
                                self.ticketAttachmentDrawerAction.currentAction = 0
                                self.viewTypeIdentifier = 1
                                self.imagePickerViewModel.isPresented = false
                                self.attachmentModel.sourceData = self.imagePickerViewModel.sourceImageData
                                self.attachmentModel.fileName = "\(Date().toString(dateFormat: "dd-MM-YY_'at'_HH:mm:ss")).jpg"
                                self.shouldAnimate = true
                                if(self.attachmentModel.sourceData != nil){
                                    self.uploadTicketAttachmentService.uploadAttachmentURL(fileURL: self.attachmentModel.sourceData, fileName: self.attachmentModel.fileName) { (response) in
                                        if(response.success == true){
                                           self.ticketUIData.ticketAttachments.append(response.taskAttachment!)
                                        }
                                        self.shouldAnimate = false
                                    }
                                }
                            }
                            }
                        ).background(
                            EmptyView()
                                .sheet(isPresented: self.$audioPlayerViewModel.isPresented){
                                    AudioRecorderView(audioRecorder: self.audioRecorder, model: self.$audioPlayerViewModel)
                                        .onDisappear {
                                            self.ticketAttachmentDrawerAction.currentAction = 0
                                            self.audioPlayerView = AudioPlayerView(audioURL: self.audioRecorder.recordingURL)
                                            let audioData = try? Data(contentsOf: self.audioRecorder.recordingURL!)
                                            self.attachmentModel.sourceURL = self.filePickerViewModel.sourceFile
                                            self.attachmentModel.sourceData = audioData
                                            self.attachmentModel.fileName = self.audioRecorder.recordingURL!.lastPathComponent
                                            self.viewTypeIdentifier = 4
                                            self.shouldAnimate = true
                                            if(self.attachmentModel.sourceData != nil){
                                                self.uploadTicketAttachmentService.uploadAttachmentURL(fileURL: self.attachmentModel.sourceData, fileName: self.attachmentModel.fileName) { (response) in
                                                    if(response.success == true){
                                                        self.ticketUIData.ticketAttachments.append(response.taskAttachment!)
                                                    }
                                                    self.shouldAnimate = false
                                                }
                                            }
                                    }
                            }
                        ).background(
                            EmptyView()
                                .sheet(isPresented: self.$filePickerViewModel.isPresented){
                                    FilePickerView(model: self.$filePickerViewModel)
                                        .onDisappear {
                                            self.ticketAttachmentDrawerAction.currentAction = 0
                                            
                                    }
                            }.onReceive(self.filePickerViewModel.pickedFileSubject) { (filePicker:FilePickerUIView) -> Void in
                                self.filePickerUIView = filePicker
                                self.ticketAttachmentDrawerAction.currentAction = 0
                                self.viewTypeIdentifier = 5
                                self.attachmentModel.sourceURL = self.filePickerViewModel.sourceFile
                                self.attachmentModel.sourceData = self.filePickerViewModel.sourceFileData
                                self.attachmentModel.fileName = self.filePickerViewModel.sourceFileName!
                                self.shouldAnimate = true
                                if(self.attachmentModel.sourceData != nil){
                                    self.uploadTicketAttachmentService.uploadAttachmentURL(fileURL: self.attachmentModel.sourceData, fileName: self.attachmentModel.fileName) { (response) in
                                        if(response.success == true){
                                            self.ticketUIData.ticketAttachments.append(response.taskAttachment!)
                                        }
                                        self.shouldAnimate = false
                                    }
                                }
                            }
                        )
                        HStack{
                            Spacer()
                            Button(action:{
                                self.viewRouter.taskAttachment = self.ticketUIData.ticketAttachments
                                self.viewRouter.currentPage = ViewRoutes.TICKET_UI
                            }){
                                GuestJiniButtonText(buttonText: "DONE")
                            }
                        }.padding()
                        
                    }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                        .padding()
                        .onAppear(){
                             
                    }
                    
                    Divider()
                    GuestJiniBottomBar(viewRouter: self.viewRouter)
                }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                    .edgesIgnoringSafeArea(.vertical)
                    .onAppear(){
                       
                }
                if(self.shouldAnimate){
                    ActivityIndicator(shouldAnimate: self.$shouldAnimate)
                }
                TicketAttachmentDrawerMenu(width: 270,
                                           isOpen: self.menuOpen,
                                           menuClose: self.openMenu,
                                           ticketAttachmentDrawerAction: self.ticketAttachmentDrawerAction
                )
            }
        }
    }
    
    func openMenu() {
        if(self.ticketAttachmentDrawerAction.currentAction == 1){
            self.isCamera = true
            self.showImagePicker = true
            self.imagePickerViewModel.isPresented = true
            self.viewTypeIdentifier = 0;
        }else if(self.ticketAttachmentDrawerAction.currentAction == 2){
            self.isCamera = false
            self.showImagePicker = true
            self.imagePickerViewModel.isPresented = true
            self.viewTypeIdentifier = 0;
        }else if(self.ticketAttachmentDrawerAction.currentAction == 3){
            self.isCamera = true
            self.showVideoPicker = true
            self.videoPickerViewModel.isPresented = true
            self.viewTypeIdentifier = 0;
        }else if(self.ticketAttachmentDrawerAction.currentAction == 4){
            self.showAudioRecorderView = true
            self.audioPlayerViewModel.isPresented = true
            self.viewTypeIdentifier = 0;
        }else if(self.ticketAttachmentDrawerAction.currentAction == 5){
            self.showFilePickerView = true
            self.filePickerViewModel.isPresented = true
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
