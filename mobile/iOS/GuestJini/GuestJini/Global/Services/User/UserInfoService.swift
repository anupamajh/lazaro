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
    var loginService = LoginService()
    @Published var userInfo:UserInfo = UserInfo()
    
    init() {
        getUserInfo { (response) in
            self.userInfo = response
        }
    }
    
    func getUserInfo(completionHandler: @escaping(UserInfo)->Void) -> Void {
        loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
            if(authData.access_token.trimmingCharacters(in: .whitespacesAndNewlines)==""){
                UserDefaults.standard.set(false, forKey: "isLoggedIn")
            }else{
                UserDefaults.standard.set(true, forKey: "isLoggedIn")
                UserDefaults.standard.set(authData.access_token, forKey: "access_token")
                UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
                UserDefaults.standard.set(authData.token_type, forKey: "token_type")
                UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
                
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(authData.access_token)",
                    "Accept": "application/json"
                ]
                AF.request(EndPoints.MY_PROFILE_URL, method: .get, encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData =  try jsonDecoder.decode(UserInfo.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = UserInfo()
                            completionHandler(parsedData)
                        }
                        
                }
                
                
            }
        }
    }
    
}
