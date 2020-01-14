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
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
        getUserInfo { (response) in
            self.userInfo = response
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
