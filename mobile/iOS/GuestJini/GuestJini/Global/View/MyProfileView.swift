//
//  MyProfileView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI



struct MyProfileView: View {
    @ObservedObject var viewRouter: ViewRouter
    @State var isInfoLoaded = true
    @State var imagePickerViewModel = ImagePickerViewModel()
    @State var isAnimating:Bool = false;
    
    
    var saveProfilePicService: SaveProfilePicService
    @ObservedObject var userInfoService:UserInfoService;
    @ObservedObject var getProfilePicService:GetProfilePicService;
    
    
    @State var profilePic:Image = Image(systemName: "camera")
    
    @State var showAction: Bool = false
    @State var showImagePicker: Bool = false
    @State var isCamera: Bool = false
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter
        UISwitch.appearance().onTintColor = UIColor(named: "aquaMarine")
        saveProfilePicService = SaveProfilePicService(viewRouter: viewRouter)
        userInfoService = UserInfoService(viewRouter: viewRouter)
        getProfilePicService = GetProfilePicService(viewRouter: viewRouter)
    }
    
    var sheet: ActionSheet {
        ActionSheet(
            title: Text("Profile Picture"),
            message: Text("Change Profile Picture"),
            buttons: [
                .default(Text("Camera"), action: {
                    self.showAction = false
                    self.showImagePicker = true
                    self.isCamera = true
                    self.imagePickerViewModel.isPresented = true
                    
                }),
                .default(Text("Gallery"), action: {
                    self.showAction = false
                    self.showImagePicker = true
                    self.isCamera = false
                    self.imagePickerViewModel.isPresented = true
                }),
                .cancel(Text("Close"), action: {
                    self.showAction = false
                }),
                
        ])
        
    }
    
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                ZStack{
                    VStack{
                        HStack{
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.SETTINGS_VIEW
                            }) {
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                
                            }.padding(.horizontal)
                            
                            GuestJiniTitleText(title: "MY PROFILE")
                            Spacer()
                        }.padding()
                        VStack{
                            HStack{
                                Spacer()
                                self.profilePic
                                    .resizable()
                                    .frame(width: 60, height: 60, alignment: .center)
                                    .clipShape(Circle())
                                    .shadow(radius: 10)
                                    .overlay(Circle().stroke(Color.white, lineWidth: 1))
                                    .padding()
                                Button(action: {
                                    self.showAction = true
                                }) {
                                    GuestJiniEditIconButton(systemImage: "pencil")
                                }
                                .offset(x:-18, y: -25)
                                .sheet(isPresented: self.$imagePickerViewModel.isPresented) {
                                    ImagePickerView(model: self.$imagePickerViewModel, isCamera: self.$isCamera)
                                }
                                .onReceive(self.imagePickerViewModel.pickedImagesSubject) { (image: Image) -> Void in
                                    withAnimation {
                                        self.profilePic = image
                                        self.isAnimating = true
                                        self.saveProfilePic(image: self.imagePickerViewModel.sourceImage!)
                                        debugPrint("Hello")
                                    }
                                }
                                .actionSheet(isPresented: self.$showAction) {
                                    self.sheet
                                }
                                Spacer()
                                
                            }.padding()
                            VStack{
                                HStack{
                                    Text("NAME")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                HStack{
                                    if(self.userInfoService.userInfo.fullName == ""){
                                        Text("") .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                    }else{
                                        Text(self.userInfoService.userInfo.fullName) .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                    }
                                    Spacer()
                                }.padding()
                                
                                Divider()
                            }
                            VStack{
                                HStack{
                                    Text("GENDER")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                VStack{
                                    Toggle(isOn:self.$userInfoService.userPreferenceUIModel.showGender)
                                    {
                                        if(self.userInfoService.userInfo.gender != 0){
                                            if(self.userInfoService.userInfo.gender == 1){
                                                Text("MALE")
                                                    .font(Fonts.RobotFieldText)
                                                    .foregroundColor(Color("greyishBrownThree"))
                                            }else if(self.userInfoService.userInfo.gender == 2){
                                                Text("FEMALE")
                                                    .font(Fonts.RobotFieldText)
                                                    .foregroundColor(Color("greyishBrownThree"))
                                            }else{
                                                Text("NOT SPECIFIED")
                                                    .font(Fonts.RobotFieldText)
                                                    .foregroundColor(Color("greyishBrownThree"))
                                            }
                                            
                                        }else{
                                            Text("NOT SPECIFIED")
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                    }
                                    
                                }.padding(.horizontal)
                                Divider()
                            }
                            VStack{
                                HStack{
                                    Text("AGE")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                VStack{
                                    Toggle(isOn:self.$userInfoService.userPreferenceUIModel.showAge)
                                    {
                                        if(self.userInfoService.userInfo.addressBook.dateOfBirth != nil){
                                            Text(String(format: "%d Years", (self.userInfoService.userInfo.addressBook.dateOfBirth?.convetToAgeFromMySQLDate().year)!))
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }else{
                                            Text("")
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                    }
                                    
                                }.padding(.horizontal)
                                Divider()
                            }
                            VStack{
                                HStack{
                                    Text("MOBILE NUMBER")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                VStack{
                                    Toggle(isOn:self.$userInfoService.userPreferenceUIModel.showMobileNumber)
                                    {
                                        if(self.userInfoService.userInfo.addressBook.phone1 != nil){
                                            Text(self.userInfoService.userInfo.addressBook.phone1!)
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }else{
                                            
                                            Text("")
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                    }
                                    
                                }.padding(.horizontal)
                                Divider()
                            }
                            VStack{
                                HStack{
                                    Text("EMAIL")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                VStack{
                                    Toggle(isOn:self.$userInfoService.userPreferenceUIModel.showEmail)
                                    {
                                        if(self.userInfoService.userInfo.addressBook.email1 != nil){
                                            Text(self.userInfoService.userInfo.addressBook.email1!)
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }else{
                                            Text("")
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                    }
                                    
                                }.padding(.horizontal)
                                Divider()
                            }
                            VStack{
                                HStack{
                                    Text("PLACE OF ORIGIN")
                                        .font(Fonts.RobotRegular)
                                        .foregroundColor(Color("brownishGrey"))
                                    Spacer()
                                }.padding(.horizontal)
                                VStack{
                                    Toggle(isOn:self.$userInfoService.userPreferenceUIModel.showPlaceofOrigin)
                                    {
                                        if(self.userInfoService.userInfo.addressBook.place != nil){
                                            Text(self.userInfoService.userInfo.addressBook.place!)
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }else{
                                            
                                            Text("")
                                                .font(Fonts.RobotFieldText)
                                                .foregroundColor(Color("greyishBrownThree"))
                                        }
                                    }
                                    
                                }.padding(.horizontal)
                                Divider()
                            }
                            
                        }
                        
                    }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                        .padding()
                    HStack{
                        Spacer()
                        
                        if(self.userInfoService.userInfo.id == ""){
                            ActivityIndicator(shouldAnimate: .constant(true))
                        }
                        if(self.isAnimating){
                            ActivityIndicator(shouldAnimate: .constant(true))
                        }
                        Spacer()
                    }
                }
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
                
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
            
        }
    }
    
    func saveProfilePic(image:UIImage){
        saveProfilePicService.saveProfilePic(imageData: ImageConverter.imageToBase64(image)!) { (response) in
            self.isAnimating = false
        }
        
    }
    
}

struct MyProfileView_Previews: PreviewProvider {
    static var previews: some View {
        MyProfileView(viewRouter: ViewRouter())
    }
}
