//
//  UserInfoService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 06/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class UserInfoService: ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    @Published var userInfo:UserInfo = UserInfo()
    @Published var userPreferenceUIModel:UserPreferenceUIModel = UserPreferenceUIModel()
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        getUserInfo { (response) in
            self.userInfo = response
            let userPref = response.userPreferences
            for pref in userPref{
                switch pref.preferenceType {
                case UserPreferenceType.USER_PREFERENCE_SHOW_AGE:
                    self.userPreferenceUIModel.showAge = pref.isHidden == 0 ? true : false
                case UserPreferenceType.USER_PREFERENCE_SHOW_EMAIL:
                    self.userPreferenceUIModel.showEmail = pref.isHidden == 0 ? true : false
                case UserPreferenceType.USER_PREFERENCE_SHOW_MOBILE_NUMBER:
                    self.userPreferenceUIModel.showMobileNumber = pref.isHidden == 0 ? true : false
                case UserPreferenceType.USER_PREFERENCE_SHOW_PLACE_OF_ORIGIN:
                    self.userPreferenceUIModel.showPlaceofOrigin = pref.isHidden == 0 ? true : false
                case UserPreferenceType.USER_PREFERENCE_SHOW_PROFILE_PIC:
                    self.userPreferenceUIModel.showProfilePic = pref.isHidden == 0 ? true : false
                case UserPreferenceType.USER_PREFERENCE_SHOW_GENDER:
                    self.userPreferenceUIModel.showGender = pref.isHidden == 0 ? true : false
                default:
                    print("none")
                }
            }
            self.userPreferenceUIModel.hasFinishedLoading = true
        }
        
    }
    
    func getUserInfo(completionHandler: @escaping(UserInfo)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                AF.request(EndPoints.MY_PROFILE_URL,
                           method: .get,
                           encoding: JSONEncoding.default,
                           headers: headers
                )
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(UserInfo.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = UserInfo()
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = UserInfo()
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
    
}
