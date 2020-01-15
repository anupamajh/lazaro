//
//  UserPreferenceUIModel.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
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
