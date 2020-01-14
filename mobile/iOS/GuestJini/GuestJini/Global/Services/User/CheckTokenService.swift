//
//  CheckTokenService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 14/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class CheckTokenService: ObservableObject {
    @ObservedObject var viewRouter: ViewRouter
    var loginService = LoginService()
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
    }
    
    func CheckToken(completionHandler: @escaping(Bool)->Void) -> Void {
        let client_id = EndPoints.CLIENT_ID
        let client_secrete = EndPoints.CLIENT_SECRETE
        let accessToekn = UserDefaults.standard.string(forKey: "access_token")!
        
        let headers: HTTPHeaders = [
            .authorization(username: client_id, password: client_secrete)
        ]
        let parameters = [
            "token" : accessToekn
        ]
        
        AF.request(EndPoints.AUTHORISATION_URL, method: .post, parameters: parameters,headers: headers)
            .responseData { (response) in
                let jsonDecoder = JSONDecoder()
                var isSuccess = false
                do{
                    let parsedData =  try jsonDecoder.decode(CheckTokenResponse.self, from: response.data!)
                    if(parsedData.client_id != nil ){
                        if(parsedData.client_id?.trimmingCharacters(in: .whitespacesAndNewlines) != ""){
                            isSuccess = true
                            completionHandler(isSuccess)
                        }
                    }
                    if(!isSuccess){
                        self.loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
                            if(authData.access_token.trimmingCharacters(in: .whitespacesAndNewlines)==""){
                                UserDefaults.standard.set(false, forKey: "isLoggedIn")
                                self.viewRouter.currentPage = ViewRoutes.LOGIN_PAGE
                            }else{
                                UserDefaults.standard.set(true, forKey: "isLoggedIn")
                                UserDefaults.standard.set(authData.access_token, forKey: "access_token")
                                UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
                                UserDefaults.standard.set(authData.token_type, forKey: "token_type")
                                UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
                                completionHandler(true)
                            }
                        }
                    }
                    
                }catch{
                    let parsedData = TicketResponse()
                    parsedData.error = "Unknow error has occrred"
                    parsedData.success = false;
                    self.loginService.refreshToken(RefreshToken: UserDefaults.standard.string(forKey: "refresh_token")!) { (authData) in
                        if(authData.access_token.trimmingCharacters(in: .whitespacesAndNewlines)==""){
                            UserDefaults.standard.set(false, forKey: "isLoggedIn")
                            self.viewRouter.currentPage = ViewRoutes.LOGIN_PAGE
                        }else{
                            UserDefaults.standard.set(true, forKey: "isLoggedIn")
                            UserDefaults.standard.set(authData.access_token, forKey: "access_token")
                            UserDefaults.standard.set(authData.refresh_token, forKey: "refresh_token")
                            UserDefaults.standard.set(authData.token_type, forKey: "token_type")
                            UserDefaults.standard.set(authData.expires_in, forKey: "expires_in")
                            completionHandler(true)
                        }
                    }
                    //completionHandler(false)
                }
        }
    }
}

/*
 {
 "aud": [
 "inventory",
 "payment"
 ],
 "user_name": "prasanna.pete@gmail.com",
 "scope": [
 "READ",
 "WRITE"
 ],
 "active": true,
 "exp": 1578306823,
 "authorities": [
 "Admin",
 "USER",
 "ROLE_ROLE",
 "ROLE_USER"
 ],
 "client_id": "21e43c55-28ef-478a-ae65-dc896e5eaa34"
 }
 */
