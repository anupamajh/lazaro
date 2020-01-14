//
//  MyProfileView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct UserPreferenceUIModel {
    @ObservedObject var viewRouter: ViewRouter
    var saveUserPreferenceService:SaveUserPreferenceService
    init(){
        let viewRouter = ViewRouter()
        self.viewRouter = viewRouter
        saveUserPreferenceService = SaveUserPreferenceService(viewRouter: viewRouter)
    }
    
    var hasFinishedLoading = false
    var showGender: Bool = false {
        willSet {
            if(hasFinishedLoading){
                if(newValue){
                    saveUserPreference(hide: 0, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_GENDER)
                }else{
                    saveUserPreference(hide: 1, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_GENDER)
                }
            }
        }
    }
    
    var showProfilePic = false {
        willSet {
            if(hasFinishedLoading){
                if(newValue){
                    saveUserPreference(hide: 0, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_PROFILE_PIC)
                }else{
                    saveUserPreference(hide: 1, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_PROFILE_PIC)
                }
            }
        }
    }
    var showAge = false {
        willSet {
            if(hasFinishedLoading){
                if(newValue){
                    saveUserPreference(hide: 0, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_AGE)
                }else{
                    saveUserPreference(hide: 1, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_AGE)
                }
            }
        }
    }
    var showEmail = false {
        willSet {
            if(hasFinishedLoading){
                if(newValue){
                    saveUserPreference(hide: 0, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_EMAIL)
                }else{
                    saveUserPreference(hide: 1, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_EMAIL)
                }
            }
        }
    }
    var showPlaceofOrigin = false {
        willSet {
            if(hasFinishedLoading){
                if(newValue){
                    saveUserPreference(hide: 0, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN)
                }else{
                    saveUserPreference(hide: 1, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN)
                }
            }
        }
    }
    var showMobileNumber = false {
        willSet {
            if(hasFinishedLoading){
                if(newValue){
                    saveUserPreference(hide: 0, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_MOBILE_NUMBER)
                }else{
                    saveUserPreference(hide: 1, userPreferenceType: UserPreferenceType.USER_PREFERENCE_SHOW_MOBILE_NUMBER)
                }
            }
        }
    }
    
    mutating func saveUserPreference(hide:Int, userPreferenceType:Int) -> Void {
        saveUserPreferenceService.saveUserPreference(userPreferenceType: userPreferenceType, isHidden: hide) {
            (response) in
            
        }
    }
}

struct MyProfileView: View {
    @ObservedObject var viewRouter: ViewRouter
    @State var isInfoLoaded = true
    @State var userPreferenceUIModel:UserPreferenceUIModel = UserPreferenceUIModel()
    @State var imagePickerViewModel = ImagePickerViewModel()
    @State var isAnimating:Bool = false;
    
    
    var saveProfilePicService: SaveProfilePicService
    @ObservedObject var userInfoService:UserInfoService;
      
    @State var profilePic:Image = Image(systemName: "person.crop.circle")
    
    @State var showAction: Bool = false
    @State var showImagePicker: Bool = false
    @State var isCamera: Bool = false
    
    
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter
        UISwitch.appearance().onTintColor = UIColor(named: "aquaMarine")
        saveProfilePicService = SaveProfilePicService(viewRouter: viewRouter)
        userInfoService = UserInfoService(viewRouter: viewRouter)
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
                                   if(self.userInfoService.userInfo.fullName == nil){
                                        Text("") .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                    }else{
                                        Text(self.userInfoService.userInfo.fullName!) .font(Fonts.RobotFieldText)
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
                                    Toggle(isOn:self.$userPreferenceUIModel.showGender)
                                    {
                                        Text("Male")
                                            .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
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
                                    Toggle(isOn:self.$userPreferenceUIModel.showAge)
                                    {
                                        Text("33 Years")
                                            .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
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
                                    Toggle(isOn:self.$userPreferenceUIModel.showMobileNumber)
                                    {
                                        Text("mobile number")
                                            .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
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
                                    Toggle(isOn:self.$userPreferenceUIModel.showMobileNumber)
                                    {
                                        Text("email")
                                            .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
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
                                    Toggle(isOn:self.$userPreferenceUIModel.showMobileNumber)
                                    {
                                        Text("email")
                                            .font(Fonts.RobotFieldText)
                                            .foregroundColor(Color("greyishBrownThree"))
                                    }
                                    
                                }.padding(.horizontal)
                                Divider()
                            }
                            
                    }
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                    HStack{
                        Spacer()
                        
                        if(self.userInfoService.userInfo.id == nil){
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
